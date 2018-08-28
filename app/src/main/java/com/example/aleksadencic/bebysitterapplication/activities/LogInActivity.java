package com.example.aleksadencic.bebysitterapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.domain.User;
import com.example.aleksadencic.bebysitterapplication.logic.Controller;

import java.io.Serializable;
import java.util.List;

public class LogInActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private Button btnLogIn;
    private CheckBox cbViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        cbViewPassword = (CheckBox) findViewById(R.id.cbViewPassword);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = Controller.getInstance().getAllUsers();
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                User userToSend = new User();
                boolean found = false;
                for (User user: users){
                    if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        found = true;
                        userToSend = user;
                    }
                }
                if(found){
                    toastMessage("You are logged in!");
                    Intent searchActivityIntent = new Intent(LogInActivity.this, AccountActivity.class);
                    searchActivityIntent.putExtra("user", userToSend);
                    startActivity(searchActivityIntent);
                }
                else
                    toastMessage("Not valid parameters!");
            }
        });

        cbViewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbViewPassword.isChecked()){
                    txtPassword.setTransformationMethod(null);
                } else {
                    txtPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

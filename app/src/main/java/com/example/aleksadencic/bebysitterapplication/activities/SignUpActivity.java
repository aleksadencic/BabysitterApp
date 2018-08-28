package com.example.aleksadencic.bebysitterapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.domain.User;
import com.example.aleksadencic.bebysitterapplication.logic.Controller;

public class SignUpActivity extends AppCompatActivity {

    private EditText txtFirstName, txtLastName, txtEmail, txtUsername, txtPassword, txtPassword2;
    private RadioGroup radioGroupGender, radioGroupRole;
    private Button btnSignUp;
    private boolean empty = false;
    private String gender, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword2 = (EditText) findViewById(R.id.txtPassword2);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        radioGroupRole = (RadioGroup) findViewById(R.id.radioGroupRole);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = txtFirstName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String mail = txtEmail.getText().toString();
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String password2 = txtPassword2.getText().toString();
                gender = checkButton(view, radioGroupGender);
                gender = gender.equals("Male") ? "M" : gender.equals("Female") ? "F" : "";
                role = checkButton(view, radioGroupRole);
                role = role.equals("Parent") ? "parent" : role.equals("Babysitter") ? "babysitter" : "";

                validateFields(firstName, lastName, mail, username, password, password2, gender, role);

                if(!empty){
                    if(password.equals(password2)){
                        User user = new User(firstName, lastName, mail, role, gender, username, password);
                        Controller.getInstance().insertUser(user);
                        toastMessage("You are signed up successfully!");
                        finish();
                    } else {
                        toastMessage("Passwords must be the same!");
                    }
                }
                empty = false;
            }
        });

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioFemale = (RadioButton) findViewById(R.id.radioFemale);
                if(gender == "" || gender == "empty"){
                    radioFemale.setError("You need to select one field");
                } else {
                    radioFemale.setError(null);
                }
            }
        });

        radioGroupRole.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioBabysitter = (RadioButton) findViewById(R.id.radioBabysitter);
                if(role == "" || role == "empty"){
                    radioBabysitter.setError("Select Item");
                } else {
                    radioBabysitter.setError(null);
                }
            }
        });
    }

    private String checkButton(View v, RadioGroup radioGroup){
        int radioId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioId);
        try{
            String textButton = radioButton.getText().toString();
            return textButton;
        } catch (Exception e){
            return "empty";
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    private void validateFields(String firstName, String lastName, String mail, String username,
                                String password, String password2, String gender, String role){
        if (firstName.trim().equalsIgnoreCase("")) {
            txtFirstName.setError("This field can not be blank");
            empty = true;
        }
        if (lastName.trim().equalsIgnoreCase("")) {
            txtLastName.setError("This field can not be blank");
            empty = true;
        }
        if(mail.trim().equalsIgnoreCase("")){
            txtEmail.setError("This field can not be blank");
            empty = true;
        } else {
            if (!txtEmail.getText().toString().trim().contains("@") ||
                    !txtEmail.getText().toString().trim().contains(".com")){
                txtEmail.setError("Not valid mail");
                empty = true;
            }
        }
        if (username.trim().equalsIgnoreCase("")) {
            txtUsername.setError("This field can not be blank");
            empty = true;
        }
        if (password.trim().equalsIgnoreCase("")) {
            txtPassword.setError("This field can not be blank");
            empty = true;
        } else {
            boolean isDigit = false;
            boolean isCapital = false;
            boolean isLower = false;
            for (char c : password.toCharArray()) {
                if (Character.isDigit(c)) {
                    isDigit = true;
                } else if (Character.isUpperCase(c)) {
                    isCapital = true;
                } else if (Character.isLowerCase(c)) {
                    isLower = true;
                }
            }
            if(password.length() < 6 || !isDigit || !isCapital || !isLower){
                txtPassword.setError("Password must contain 6 characters or more and at least one digit and one big letter");
                empty = true;
            }
        }
        if (password2.trim().equalsIgnoreCase("")) {
            txtPassword2.setError("This field can not be blank");
            empty = true;
        } else {
            boolean isDigit = false;
            boolean isCapital = false;
            boolean isLower = false;
            for (char c : password2.toCharArray()) {
                if (Character.isDigit(c)) {
                    isDigit = true;
                } else if (Character.isUpperCase(c)) {
                    isCapital = true;
                } else if (Character.isLowerCase(c)) {
                    isLower = true;
                }
            }
            if(password2.length() < 6 || !isDigit || !isCapital || !isLower){
                txtPassword2.setError("Password must contain 6 characters or more and at least one digit and one big letter");
                empty = true;
            }
        }
        RadioButton radioFemale = (RadioButton) findViewById(R.id.radioFemale);
        if(gender == "" || gender == "empty"){
            radioFemale.setError("You need to select one field");
            empty = true;
        } else {
            radioFemale.setError(null);
        }
        RadioButton radioBabysitter = (RadioButton) findViewById(R.id.radioBabysitter);
        if(role == "" || role == "empty"){
            radioBabysitter.setError("You need to select one field");
            empty = true;
        } else {
            radioBabysitter.setError(null);
        }
    }

}

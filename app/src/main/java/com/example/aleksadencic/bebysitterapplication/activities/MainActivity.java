package com.example.aleksadencic.bebysitterapplication.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.activities.notes.MainNotesActivity;
import com.example.aleksadencic.bebysitterapplication.domain.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    private Button btnSignUp, btnLogIn, btnNotes;
    LoginButton btnLoginFb;
    CallbackManager callbackManager;
    User facebookUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        btnLoginFb = (LoginButton) findViewById(R.id.login_button);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnNotes = (Button) findViewById(R.id.btnNotes);

        btnLoginFb.setReadPermissions("email", "public_profile");

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                toastMessage("You are signed up successfully!");

                Intent accountIntent = new Intent(MainActivity.this, AccountActivity.class);
                setFacebookData(loginResult);
                accountIntent.putExtra("user", facebookUser);
                startActivity(accountIntent);
            }

            @Override
            public void onCancel() {
                toastMessage("Login cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                toastMessage("Error occured during sing up process.");
                toastMessage(error.getMessage());
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logInIntent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(logInIntent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notesIntent = new Intent(MainActivity.this, MainNotesActivity.class);
                startActivity(notesIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setFacebookData(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        try {
                            Log.i("Response",response.toString());

                            String email = response.getJSONObject().getString("email");
                            String firstName = response.getJSONObject().getString("first_name");
                            String lastName = response.getJSONObject().getString("last_name");
                            String gender = response.getJSONObject().getString("gender");

                            facebookUser.setFirstName(firstName);
                            facebookUser.setLastName(lastName);
                            facebookUser.setMail(email);
                            //facebookUser.setGender(gender);
                            facebookUser.setRole("parent");
                            facebookUser.setUsername(email);
                            facebookUser.setPassword("aaa");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

package com.martin.cal.chartyourlikes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Keep track of FB login
        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "user_likes"));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Facebook Login Successful!");
                loginCompleted();
            }

            @Override
            public void onCancel() {
                System.out.println("Facebook Login Cancelled!");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("Facebook Login Error!");
                System.out.println(exception.toString());
                SweetAlertDialog dialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Login Error!");
                dialog.setContentText("There was a problem logging in! Please try again! :)");
                dialog.show();
            }
        });

        // Auto login
        if (AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired())
        {
            Profile.fetchProfileForCurrentAccessToken();
            loginCompleted();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    protected void loginCompleted()
    {
        Intent intent = new Intent(LoginActivity.this, PagerActivity.class);
        startActivity(intent);
        finish();
    }
}

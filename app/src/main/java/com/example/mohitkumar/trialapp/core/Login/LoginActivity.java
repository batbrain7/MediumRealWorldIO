package com.example.mohitkumar.trialapp.core.Login;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import static com.example.mohitkumar.trialapp.MainApplication.TAG;
import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.Util.Constants;
import com.example.mohitkumar.trialapp.Util.PrefManager;
import com.example.mohitkumar.trialapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements ILoginView{

    ActivityLoginBinding binding;
    ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        presenter = new LoginPresenter();
        presenter.onAttach(this);
    }

    public void login(View view) {
        if (!TextUtils.isEmpty(binding.emailField.getText().toString()) &&
                                !TextUtils.isEmpty(binding.passwordField.getText()))
        presenter.login(binding.emailField.getText().toString(), binding.passwordField.getText().toString());
    }


    @Override
    public void onLoginSuccess() {
        PrefManager.putBoolean(Constants.LOG_IN, true);
        Log.d(TAG, "Login Successful");
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onLoginError(String title, String message) {
        Log.d(TAG, "Login Error " + title + message);
        Toast.makeText(this, "Unable to log you in, please try again", Toast.LENGTH_SHORT).show();
        finish();
    }
}

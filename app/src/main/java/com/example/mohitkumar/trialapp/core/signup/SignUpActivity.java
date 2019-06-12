package com.example.mohitkumar.trialapp.core.signup;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.util.Constants;
import com.example.mohitkumar.trialapp.util.PrefManager;
import com.example.mohitkumar.trialapp.core.MainActivity;
import com.example.mohitkumar.trialapp.databinding.ActivitySignUpBinding;

import static com.example.mohitkumar.trialapp.MainApplication.TAG;

public class SignUpActivity extends AppCompatActivity implements ISignUpView {

    ActivitySignUpBinding binding;
    ISignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        presenter = new SignUpPresenter();
        presenter.onAttach(this);
    }

    @Override
    public void onSignUpSuccess() {
        Log.d(TAG, "Sign Up Successful");
        PrefManager.putBoolean(Constants.LOG_IN, true);
        Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSignUpError(String title, String message) {
        Log.d(TAG, "Sign Up Error " + title + message);
        Toast.makeText(this, "Unable to sign you up, the user already exists !", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void signUp(View view) {
        if (!TextUtils.isEmpty(binding.emailField.getText().toString()) &&
                !TextUtils.isEmpty(binding.passwordField.getText()) && !TextUtils.isEmpty(binding.usernameField.getText()))
            presenter.signUp(binding.emailField.getText().toString(), binding.passwordField.getText().toString(),
                    binding.usernameField.getText().toString());
    }
}

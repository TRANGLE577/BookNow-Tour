package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.itf.ForgotPasswordInterface;
import com.example.mobileapp.api.ForgotPasswordAPI;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordInterface {

    private Button btnLogin;
    private Button btnSubmit;
    private EditText edtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnLogin = findViewById(R.id.btnLogin);
        btnSubmit = findViewById(R.id.btnSubmit);
        edtUsername = findViewById(R.id.edtUsername);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPasswordAPI forgotPasswordAPI = new ForgotPasswordAPI(ForgotPasswordActivity.this);
                forgotPasswordAPI.forgotPassword(edtUsername.getText().toString());
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(ForgotPasswordActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(ForgotPasswordActivity.this, error, Toast.LENGTH_LONG).show();
    }

}
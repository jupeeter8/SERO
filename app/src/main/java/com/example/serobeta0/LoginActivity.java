package com.example.serobeta0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private Button loginRegBtn;
    private FirebaseAuth mAuth;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        loginEmailText = (EditText) findViewById(R.id.edtTxtMail);
        loginPassText = (EditText) findViewById(R.id.editTextTextPassword);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        loginRegBtn = (Button) findViewById(R.id.btnRegister);
        bar = (ProgressBar) findViewById(R.id.regProgressBar);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();

                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)) {
                    bar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail,loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                sendToMain();
                            } else {

                                String e = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error: " + e, Toast.LENGTH_LONG).show();
                                bar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            sendToMain();
        }
    }

    private void sendToMain() {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    public void onLoginRegPress(View v) {
        Intent login_reg = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(login_reg);
    }
}
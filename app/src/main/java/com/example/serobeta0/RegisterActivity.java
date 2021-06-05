package com.example.serobeta0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private EditText confirm_pass;
    private Button regBtn;
    private Button loginBtn;
    private ProgressBar bar;
    private FirebaseAuth mAuth;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email  = (EditText) findViewById(R.id.regEmail);
        pass = (EditText) findViewById(R.id.regPass);
        confirm_pass = (EditText) findViewById(R.id.regConfirmPass);
        regBtn = (Button) findViewById(R.id.btnRegRegister);
        loginBtn = (Button) findViewById(R.id.btnRegLogin);
        bar = (ProgressBar) findViewById(R.id.regProgressBar);
        context = (Context) this;

        mAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regEmail = email.getText().toString();
                String regPass = pass.getText().toString();
                String regConfirmPassword = confirm_pass.getText().toString();

                if (!TextUtils.isEmpty(regEmail) && !TextUtils.isEmpty(regPass) && !TextUtils.isEmpty(regConfirmPassword)) {
                    if (regPass.equals(regConfirmPassword)) {
                        bar.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(regEmail, regPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = mAuth.getCurrentUser();
                                    currentUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task verify) {
                                            if (verify.isSuccessful()) {
                                                Toast.makeText(context, "verification sent", Toast.LENGTH_LONG).show();
                                                bar.setVisibility(View.INVISIBLE);
                                                finish();
                                            } else {
                                                String error = task.getException().getMessage();
                                                Toast.makeText(context, error, Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });

                                } else {

                                    String error = task.getException().getMessage();
                                    Toast.makeText(context, error, Toast.LENGTH_LONG).show();
                                    bar.setVisibility(View.INVISIBLE);
                                }
                            }
                        });

                    } else {
                        Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        Intent mainIntent = new Intent(context, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }

}
package com.example.serobeta0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Verification extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        mAuth = FirebaseAuth.getInstance();
        ok = findViewById(R.id.verOk);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setupIntent = new Intent(Verification.this, Setup.class);
                startActivity(setupIntent);
                finish();
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "Please verify before signing in again!", Toast.LENGTH_LONG).show();

    }
}
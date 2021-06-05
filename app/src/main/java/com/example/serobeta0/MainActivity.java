package com.example.serobeta0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mainToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.myTool);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("SERO");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){

            sendToLogin();

        }

        if (currentUser != null && currentUser.isEmailVerified() == false) {

           Intent verifyIntent = new Intent(MainActivity.this, Verification.class);
            startActivity(verifyIntent);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.logoutBtn:
                
                Logout();
                
                return true;

                default:
                    return false;
        }
    }

    private void Logout() {
        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        Intent loginintent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginintent);
        finish();
    }
}
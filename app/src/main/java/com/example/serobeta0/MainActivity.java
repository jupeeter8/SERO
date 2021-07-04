package com.example.serobeta0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;

    private FirebaseAuth mAuth;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variable initialization
        mAuth = FirebaseAuth.getInstance();
        addButton = findViewById(R.id.mainNew);
        mainToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.myTool);


        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("SERO");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent postIntent = new Intent(MainActivity.this, NewPost.class);
             startActivity(postIntent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        //Checking if user is logged in
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){

            sendToLogin();

        }
    }


    //Inflating menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;


    }

    //Menu functions
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.logoutBtn:
                
                Logout();
                
                return true;
            case R.id.settingsBtn:

                Intent setup  = new Intent(MainActivity.this, Setup.class);
                startActivity(setup);

                return true;


            default:
                return false;
        }
    }

    //Logging out the user
    private void Logout() {
        mAuth.signOut();
        sendToLogin();
    }

    //Login intent
    private void sendToLogin() {
        Intent loginintent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginintent);
        finish();
    }
}
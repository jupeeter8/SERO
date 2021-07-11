package com.example.serobeta0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;

    private FirebaseAuth mAuth;
    private FloatingActionButton addButton;
    private BottomNavigationView bottomNav;

    private HomeFragment hf;
    private MoodFragment mf;
    private AccFragment af;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variable initialization
        mAuth = FirebaseAuth.getInstance();
        addButton = findViewById(R.id.mainNew);
        mainToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.myTool);
        bottomNav = findViewById(R.id.mainBtmNav);

        //Fragment Initialization


        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("SERO");

        if(mAuth.getCurrentUser() != null ) {
            hf = new HomeFragment();
            mf = new MoodFragment();
            af = new AccFragment();
            replaceFrag(hf);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postIntent = new Intent(MainActivity.this, NewPost.class);
                    startActivity(postIntent);
                }
            });

            bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {

                        case R.id.btmNavHome:
                            replaceFrag(hf);
                            return true;
                        case R.id.btmNavMood:
                            replaceFrag(mf);
                            return true;
                        case R.id.btmNavAcc:
                            replaceFrag(af);
                            return true;

                    }

                    return false;
                }
            });

        }

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

            case R.id.action_page_btn:
                Intent test = new Intent(MainActivity.this, DepressionTestActivity.class);
                startActivity(test);
                return true;

            case R.id.mainMoodBtn:
                Intent moodIntent = new Intent(MainActivity.this, MoodTracker.class);
                startActivity(moodIntent);
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

    private void replaceFrag(Fragment fragment){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container, fragment);
        ft.commit();


    }

}
package com.example.serobeta0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Setup extends AppCompatActivity {

    //private CircleImageView pfp;

    private EditText userName;
    private Button setupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Toolbar setupToolbar = findViewById(R.id.SetUpTool);
        setSupportActionBar(setupToolbar);
        getSupportActionBar().setTitle("User Setup");

        userName = findViewById(R.id.setupName);
        setupButton = findViewById(R.id.Submit);

        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();

                if (!TextUtils.isEmpty(name)) {

                    Toast.makeText(Setup.this, "Enter a name!", Toast.LENGTH_SHORT).show();

                } else {

                    

                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ContextCompat.checkSelfPermission(Setup.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Setup.this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }
}
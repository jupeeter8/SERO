package com.example.serobeta0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Setup extends AppCompatActivity {


    private EditText userName;
    private Button setupButton;
    private String user_id;
    private FirebaseAuth mAuth;
    private StorageReference storageRef;
    private FirebaseFirestore firestoreRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Toolbar setupToolbar = findViewById(R.id.SetUpTool);
        setSupportActionBar(setupToolbar);
        getSupportActionBar().setTitle("User Setup");

        userName = findViewById(R.id.setupName);
        setupButton = findViewById(R.id.Submit);




        //Firebase storage
        storageRef = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();
        firestoreRef = FirebaseFirestore.getInstance();






        //Getting name of user from firebase Firestore
        firestoreRef.collection("User").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<DocumentSnapshot> task) {

                if (task.getResult().exists()) {

                    userName.setText(task.getResult().getString("name"));

                }

            }
        });










        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();

                if (!TextUtils.isEmpty(name)) {

                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("name", name);



                    firestoreRef.collection("User").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(Setup.this, "Name updated", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Setup.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });



                } else {

                    Toast.makeText(Setup.this, "Enter a name!", Toast.LENGTH_SHORT).show();

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
package com.example.serobeta0;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MoodTracker extends AppCompatActivity {

    private ImageView rad, happy, meh, sad, awful;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private String currentUser;
    private List<String> moodList = new ArrayList<String>();
    private SimpleDateFormat sdf;
    String currentDateandTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_tracker);

        rad = findViewById(R.id.moodRad);
        happy = findViewById(R.id.moodHappy);
        meh = findViewById(R.id.moodMeh);
        sad = findViewById(R.id.moodSad);
        awful = findViewById(R.id.moodAwful);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser().getUid();
        sdf = new SimpleDateFormat("ddMMyy", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());


        rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Map<String, Object>MoodMap = new HashMap<>();
                MoodMap.put("val", 0);
                MoodMap.put("mood", "Rad");

                firestore.collection("Mood").document(currentDateandTime).collection(currentUser).document(currentDateandTime).set(MoodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MoodTracker.this, "Mood Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            String e = task.getException().getMessage();
                            Toast.makeText(MoodTracker.this, e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object>MoodMap = new HashMap<>();
                MoodMap.put("val", 1);
                MoodMap.put("mood", "Happy");

                firestore.collection("Mood").document(currentDateandTime).collection(currentUser).document(currentDateandTime).set(MoodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MoodTracker.this, "Mood Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            String e = task.getException().getMessage();
                            Toast.makeText(MoodTracker.this, e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        meh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object>MoodMap = new HashMap<>();
                MoodMap.put("val", 2);
                MoodMap.put("mood", "Meh");

                firestore.collection("Mood").document(currentDateandTime).collection(currentUser).document(currentDateandTime).set(MoodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MoodTracker.this, "Mood Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            String e = task.getException().getMessage();
                            Toast.makeText(MoodTracker.this, e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Map<String, Object>MoodMap = new HashMap<>();
                MoodMap.put("val", 3);
                MoodMap.put("mood", "Sad");

                firestore.collection("Mood").document(currentDateandTime).collection(currentUser).document(currentDateandTime).set(MoodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MoodTracker.this, "Mood Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            String e = task.getException().getMessage();
                            Toast.makeText(MoodTracker.this, e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        awful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object>MoodMap = new HashMap<>();
                MoodMap.put("val", 4);
                MoodMap.put("mood", "Awful");

                firestore.collection("Mood").document(currentDateandTime).collection(currentUser).document(currentDateandTime).set(MoodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MoodTracker.this, "Mood Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            String e = task.getException().getMessage();
                            Toast.makeText(MoodTracker.this, e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}
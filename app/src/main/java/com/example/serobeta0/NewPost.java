package com.example.serobeta0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewPost extends AppCompatActivity {

    private EditText ques;
    private EditText desc;
    private MaterialButton reset;
    private MaterialButton submit;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        ques = findViewById(R.id.ques);
        desc = findViewById(R.id.detail);
        reset = findViewById(R.id.reset);
        submit = findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();
        firestore = FirebaseFirestore.getInstance();


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        //Sending Post data to firestore data base
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ques.getText().toString();
                String description = desc.getText().toString();
                if(!TextUtils.isEmpty(question) && !TextUtils.isEmpty(description))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    Map<String, Object> postMap = new HashMap<>();
                    postMap.put("name", user_id);
                    postMap.put("ques", question);
                    postMap.put("desc", description);
                    //postMap.put("Time", currentDateandTime);

                    firestore.collection("Post").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(Task<DocumentReference> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(NewPost.this, "Shared!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {

                                String e = task.getException().getMessage();
                                Toast.makeText(NewPost.this, e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {

                    Toast.makeText(NewPost.this, "You can't share an empty post!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}
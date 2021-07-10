package com.example.serobeta0;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostComments extends AppCompatActivity {

    private EditText cmntBox;
    private Button cmntSubmitBtn;

    private String docId;

    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private String currUser;

    //REcyclerview
    private RecyclerView cmntList;
    private List<cmntModal> cmntModalList;
    private CommentAdap commentAdap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comments);

        docId = getIntent().getStringExtra("docId");

        cmntBox = findViewById(R.id.cmntText);
        cmntSubmitBtn = findViewById(R.id.cmntSubmit);


        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        currUser = mAuth.getCurrentUser().getUid();

        //Recycler view
        cmntModalList = new ArrayList<>();
        cmntList = findViewById(R.id.cmntList);
        commentAdap = new CommentAdap(cmntModalList);
        cmntList.setLayoutManager(new LinearLayoutManager(this));
        cmntList.setAdapter(commentAdap);


        //RecyclerView

        firestore.collection("Post/" + docId + "/Comments").addSnapshotListener(PostComments.this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable  FirebaseFirestoreException error) {

                if (!value.isEmpty()){


                    for (DocumentChange doc: value.getDocumentChanges()){

                        if (doc.getType() ==  DocumentChange.Type.ADDED){

                            cmntModal pc = doc.getDocument().toObject(cmntModal.class);
                            cmntModalList.add(pc);

                            commentAdap.notifyDataSetChanged();



                        }
                    }

                }

            }
        });

        cmntSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = cmntBox.getText().toString();

                if (!comment.isEmpty()){

                    Map<String, Object> cmntMap = new HashMap<>();
                    cmntMap.put("Message", comment);
                    cmntMap.put("User", currUser);
                    cmntMap.put("timestamp", FieldValue.serverTimestamp());

                    firestore.collection("Post/" + docId + "/Comments").add(cmntMap);
                    finish();

                }

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
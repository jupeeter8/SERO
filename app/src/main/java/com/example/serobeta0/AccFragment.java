package com.example.serobeta0;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccFragment extends Fragment {

    private EditText userName;
    private Button submit, logout;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestoreRef;
    private String user_id;


    public AccFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acc, container, false);

        userName = view.findViewById(R.id.updateEditText);
        submit = view.findViewById(R.id.updateSubmit);
        mAuth = FirebaseAuth.getInstance();
        firestoreRef = FirebaseFirestore.getInstance();
        user_id = mAuth.getCurrentUser().getUid();
        logout = view.findViewById(R.id.updateLogout);

        //Getting name of user from firebase Firestore
        firestoreRef.collection("User").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.getResult().exists()) {

                    userName.setText(task.getResult().getString("name"));

                }

            }
        });










        submit.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(getContext(), "Name updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });



                } else {

                    Toast.makeText(getContext(), "Enter a name!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });

        return view;
    }

    private void Logout() {
        mAuth.signOut();
    }

}
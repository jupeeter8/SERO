package com.example.serobeta0;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView post;
    private List<Post> postList;
    private FirebaseFirestore firestore;
    private PostAdaptar postAdaptar;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        postList = new ArrayList<>();
        post = view.findViewById(R.id.postView);
        firestore = FirebaseFirestore.getInstance();
        postAdaptar = new PostAdaptar(postList);
        post.setLayoutManager(new LinearLayoutManager(getActivity()));
        post.setAdapter(postAdaptar);

        firestore.collection("Post").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for(DocumentChange doc: value.getDocumentChanges()){
                    if (doc.getType() == DocumentChange.Type.ADDED){

                        Post post = doc.getDocument().toObject(Post.class);
                        postList.add(post);

                        postAdaptar.notifyDataSetChanged();

                    }
                }

            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
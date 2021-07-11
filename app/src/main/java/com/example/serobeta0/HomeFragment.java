package com.example.serobeta0;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
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
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();



        if(mAuth.getCurrentUser() != null) {


            Query fq = firestore.collection("Post").orderBy("timestamp", Query.Direction.DESCENDING);

            fq.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                    for (DocumentChange doc : value.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            String PostID = doc.getDocument().getId();
//                            Post POST_ID = doc.getDocument().toObject(PostID.class).withId(PostID);
                            Post post = doc.getDocument().toObject(Post.class);
                            post.setId(PostID);
                            postList.add(post);

                            postAdaptar.notifyDataSetChanged();

                        }
                    }

                }
            });
        }

        // Inflate the layout for this fragment
        return view;
    }

}
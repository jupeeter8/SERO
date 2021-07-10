package com.example.serobeta0;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.List;

public class PostAdaptar extends RecyclerView.Adapter<PostAdaptar.ViewHolder> {

    public List<Post> postList;

    public PostAdaptar(List<Post> postList) {
        this.postList = postList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posti_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdaptar.ViewHolder holder, int position) {

        String postID = postList.get(position).PostID;

        String desc_data = postList.get(position).getDesc();
        String ques_data = postList.get(position).getQues();
        String uname = postList.get(position).getName();
        String timestamp = postList.get(position).getTime();
        holder.setDescView(desc_data);
        holder.QuesView(ques_data);
        holder.setNameView(uname);
        holder.setTimeView(timestamp);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView desc;
        private TextView ques;
        private View mView;
        private TextView name;
        private TextView timetext;
        private FirebaseFirestore firestore;
        private ImageView blogCmntBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            blogCmntBtn = mView.findViewById(R.id.postComment);
        }

        public void setDescView(String text){
            desc = mView.findViewById(R.id.postDesc);
            desc.setText(text);
        }

        public void QuesView(String quesText){
            ques = mView.findViewById(R.id.postHead);
            ques.setText(quesText);
        }

        public void setNameView(String uid){
            name = mView.findViewById(R.id.postUsername);

            firestore = FirebaseFirestore.getInstance();

            DocumentReference docRef = firestore.collection("User").document(uid);
            docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    name.setText(value.getString("name"));
                }
            });
            


        }

        public void setTimeView(String timestamp) {

            timetext = mView.findViewById(R.id.postDate);
            timetext.setText(timestamp);

        }
    }
}

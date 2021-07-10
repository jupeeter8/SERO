package com.example.serobeta0;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

public class CommentAdap extends RecyclerView.Adapter<CommentAdap.ViewHolder> {

    public List<cmntModal> cmntModalList;

    public CommentAdap(List<cmntModal> cmntModalList){

        this.cmntModalList = cmntModalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmnt_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CommentAdap.ViewHolder holder, int position) {

        String cmntTextMessage = cmntModalList.get(position).getMessage();
        String userNameText = cmntModalList.get(position).getUser();
        String cmntTime_Date = cmntModalList.get(position).getTimestamp();
        holder.setNAME(userNameText);
        holder.setTIME(cmntTime_Date);
        holder.setCMNT(cmntTextMessage);

    }

    @Override
    public int getItemCount() {
        return cmntModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView message;
        private TextView cmntName;
        private TextView cmntTimeDate;

        private FirebaseFirestore firestore;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setCMNT(String cmntTextMessage) {

            message = mView.findViewById(R.id.cmntTextid);
            message.setText(cmntTextMessage);
        }

        public void setNAME(String userNameText) {

            cmntName = mView.findViewById(R.id.cmntUserName);
            firestore = FirebaseFirestore.getInstance();

            DocumentReference docRef = firestore.collection("User").document(userNameText);
            docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    cmntName.setText(value.getString("name"));
                }
            });

        }

        public void setTIME(String cmntTime_date) {

            cmntTimeDate = mView.findViewById(R.id.cmntDate);
            cmntTimeDate.setText(cmntTime_date);

        }
    }


}

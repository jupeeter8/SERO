package com.example.serobeta0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DepressionTestAdapter extends RecyclerView.Adapter<DepressionTestAdapter.ViewHolder> {

    public List<DepressionTestModal> depressionTestModals;
    public Context context;
    public int SCORE;

    public DepressionTestAdapter(List<DepressionTestModal> depressionTestModals){

        this.depressionTestModals = depressionTestModals;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.depression_test_layout, parent, false);
        context = parent.getContext();
        SCORE = 0;

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepressionTestAdapter.ViewHolder holder, int position) {
        String head = depressionTestModals.get(position).getHead();
        String ques = depressionTestModals.get(position).getQues();
        String op1 = depressionTestModals.get(position).getOp1();
        String op2 = depressionTestModals.get(position).getOp2();
        String op3 = depressionTestModals.get(position).getOp3();
        String op4 = depressionTestModals.get(position).getOp4();

        holder.setDATA(head, ques, op1, op2, op3, op4);
        holder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int point = holder.group.getCheckedRadioButtonId();
                switch (point){
                    case R.id.option1:
                        SCORE -= 1;
                        break;
                    case R.id.option2:
                        SCORE -= 2;
                        break;
                    case R.id.option3:
                        SCORE -= 3;
                        break;
                    case R.id.option4:
                        SCORE -= 4;
                        break;

                }
                Toast.makeText(context, "" + SCORE, Toast.LENGTH_SHORT).show();
                holder.group.clearCheck();
                holder.submit.setEnabled(true);
                holder.clear.setEnabled(false);
            }
        });

        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int point = holder.group.getCheckedRadioButtonId();

                switch (point){
                    case R.id.option1:
                        SCORE += 1;
                        break;
                    case R.id.option2:
                        SCORE += 2;
                        break;
                    case R.id.option3:
                        SCORE += 3;
                        break;
                    case R.id.option4:
                        SCORE += 4;
                        break;
                }

                Toast.makeText(context, "" + SCORE, Toast.LENGTH_SHORT).show();
                holder.submit.setEnabled(false);
                holder.clear.setEnabled(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return depressionTestModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Head;
        private TextView Ques;
        private RadioButton Op1, Op2, Op3, Op4;
        private Button clear, submit;
        private RadioGroup group;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clear = itemView.findViewById(R.id.depTestLayoutClear);
            submit = itemView.findViewById(R.id.depTestLayoutSubmit);
            group = itemView.findViewById(R.id.depressionTestRadioGroup);
            Head = itemView.findViewById(R.id.depTestLayoutHead);
            Ques = itemView.findViewById(R.id.depTestLayoutQuestion);
            Op1 = itemView.findViewById(R.id.option1);
            Op2 = itemView.findViewById(R.id.option2);
            Op3 = itemView.findViewById(R.id.option3);
            Op4 = itemView.findViewById(R.id.option4);
        }

        public void setDATA(String head, String ques, String op1, String op2, String op3, String op4) {

            Head.setText(head);
            Ques.setText(ques);
            Op1.setText(op1);
            Op2.setText(op2);
            Op3.setText(op3);
            Op4.setText(op4);


        }
    }
}

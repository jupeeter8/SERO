package com.example.serobeta0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class DepressionTestActivity extends AppCompatActivity {

    private RecyclerView deptest;
    private List<DepressionTestModal> depressionTestModals;
    private DepressionTestAdapter depressionTestAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_test);

        deptest = findViewById(R.id.depressionTestRecycler);
        depressionTestModals = new ArrayList<>();
        depressionTestAdapter = new DepressionTestAdapter(depressionTestModals);
        deptest.setLayoutManager(new LinearLayoutManager(this));
        deptest.setAdapter(depressionTestAdapter);

        initDATA();

    }

    private void initDATA() {

        depressionTestModals.add(new DepressionTestModal("Question 1", "Lorem ipsum", "op1", "op2", "op3", "op4"));
        depressionTestModals.add(new DepressionTestModal("Question 2", "impsum lorem", "chad", "chad2", "chad3", "chad4"));
        depressionTestAdapter.notifyDataSetChanged();

    }
}
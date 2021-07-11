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

        initDATA1();
        initRecycler();

    }

    private void initRecycler() {
        depressionTestAdapter = new DepressionTestAdapter(depressionTestModals);
        deptest.setLayoutManager(new LinearLayoutManager(this));
        deptest.setAdapter(depressionTestAdapter);
        depressionTestAdapter.notifyDataSetChanged();
    }

    private void initDATA1() {
        depressionTestModals = new ArrayList<>();
        depressionTestModals.add(new DepressionTestModal("Question 1", "I..",
                "do not feel sad",
                "feel sad",
                "am sad all the time and I can't snap out of it. ",
                "am so sad and unhappy that I can't stand it."));

        depressionTestModals.add(new DepressionTestModal("Question 2", "I..",
                "am not particularly discouraged about the future.",
                "feel discouraged about the future.",
                "feel I have nothing to look forward to.",
                "feel the future is hopeless and that things cannot improve."));

        depressionTestModals.add(new DepressionTestModal("Question 3", "I..",
                "do not feel like a failure",
                "I feel I have failed more than the average person.",
                "As I look back on my life, all I can see is a lot of failures.",
                "feel I am a complete failure as a person"));

        depressionTestModals.add(new DepressionTestModal("Question 4", "I..",
                "get as much satisfaction out of things as I used to",
                "don't enjoy things the way I used to.",
                "don't get real satisfaction out of anything anymore.",
                "am dissatisfied or bored with everything."));

        depressionTestModals.add(new DepressionTestModal("Question 5", "I..",
                "don't feel particularly guilty",
                "feel guilty a good part of the time.",
                "feel quite guilty most of the time.",
                "feel guilty all of the time"));

        depressionTestModals.add(new DepressionTestModal("Question 6", "I..",
                "don't feel I am being punished.",
                "feel I may be punished.",
                "expect to be punished.",
                "feel I am being punished."));

        depressionTestModals.add(new DepressionTestModal("Question 7", "I..",
                "don't feel disappointed in myself",
                "am disappointed in myself.",
                "am disgusted with myself",
                "hate myself"));

        depressionTestModals.add(new DepressionTestModal("Question 8", "I..",
                "don't feel I am any worse than anybody else.",
                "am critical of myself for my weaknesses or mistakes.",
                "blame myself all the time for my faults",
                "blame myself for everything bad that happens"));

        depressionTestModals.add(new DepressionTestModal("Question 9", "I..",
                "don't have any thoughts of killing myself",
                "have thoughts of killing myself, but I would not carry them out",
                "would like to kill myself",
                "would kill myself if I had the chance."));

        depressionTestModals.add(new DepressionTestModal("Question 10", "I..",
                "don't cry any more than usual.",
                "cry more now than I used to.",
                "cry all the time now.",
                "used to be able to cry, but now I can't cry even though I want to."));

        depressionTestModals.add(new DepressionTestModal("Question 11", "I..",
                "am no more irritated by things than I ever was",
                "am slightly more irritated now than usual.",
                "am quite annoyed or irritated a good deal of the time.",
                "feel irritated all the time."));

        depressionTestModals.add(new DepressionTestModal("Question 12", "I..",
                "have not lost interest in other people.",
                "am less interested in other people than I used to be.",
                "have lost most of my interest in other people.",
                "have lost all of my interest in other people."));

        depressionTestModals.add(new DepressionTestModal("Question 13", "I..",
                "make decisions about as well as I ever could.",
                "put off making decisions more than I used to.",
                "have greater difficulty in making decisions more than I used to.",
                "can't make decisions at all anymore."));

        depressionTestModals.add(new DepressionTestModal("Question 14", "I..",
                "don't feel that I look any worse than I used to",
                "am worried that I am looking old or unattractive.",
                "feel there are permanent changes in my appearance that make me look unattractive",
                "believe that I look ugly."));

        depressionTestModals.add(new DepressionTestModal("Question 15", "I..",
                "can work about as well as before.",
                "It takes an extra effort to get started at doing something.",
                "have to push myself very hard to do anything.",
                "can't do any work at all."));

        depressionTestModals.add(new DepressionTestModal("Question 16", "I..",
                "can sleep as well as usual.",
                "don't sleep as well as I used to.",
                "wake up 1-2 hours earlier than usual and find it hard to get back to sleep.",
                "wake up several hours earlier than I used to and cannot get back to sleep."));

        depressionTestModals.add(new DepressionTestModal("Question 17", "I..",
                "don't get more tired than usual.",
                "get tired more easily than I used to.",
                "I get tired from doing almost anything.",
                "am too tired to do anything."));

        depressionTestModals.add(new DepressionTestModal("Question 18", "My..",
                "appetite is no worse than usual.",
                "appetite is not as good as it used to be.",
                "appetite is much worse now.",
                "have no appetite at all anymore."));

        depressionTestModals.add(new DepressionTestModal("Question 19", "I..",
                "haven't lost much weight, if any, lately",
                "have lost more than five pounds.",
                "have lost more than ten pounds.",
                "have lost more than fifteen pounds."));

        depressionTestModals.add(new DepressionTestModal("Question 20", "I..",
                "am no more worried about my health than usual",
                "am worried about physical problems like aches, pains, upset stomach, or constipation.",
                "am very worried about physical problems and it's hard to think of much else.",
                "am so worried about my physical problems that I cannot think of anything else."));

        depressionTestModals.add(new DepressionTestModal("Question 21", "I..",
                "have not noticed any recent change in my interest in sex.",
                "am less interested in sex than I used to be.",
                "have almost no interest in sex.",
                "have lost interest in sex completely."));

    }
}
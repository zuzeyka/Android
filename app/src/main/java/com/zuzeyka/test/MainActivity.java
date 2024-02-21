package com.zuzeyka.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView funnyWordTextView;
    private Button generateButton;

    private String[] funnyWords = {
            "пенопласточка", "шоколадерка", "бананушка", "колбаскин", "картопелька",
            "пельмешкин", "чебуречка", "пердушка", "кашалотик", "котикоптычек"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        funnyWordTextView = findViewById(R.id.funnyWordTextView);
        generateButton = findViewById(R.id.generateButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateFunnyWord();
            }
        });
    }

    private void generateFunnyWord() {
        Random random = new Random();
        int index = random.nextInt(funnyWords.length);
        String funnyWord = funnyWords[index];
        funnyWordTextView.setText(funnyWord);
    }

    public void updateTotalPrice() {
    }
}

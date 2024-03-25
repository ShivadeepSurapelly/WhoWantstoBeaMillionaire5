package com.example.whowantstobeamillionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity1 extends AppCompatActivity {

    private int moneyEarned = 0; // Initial money earned
    private String selectedOptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1); // Set your activity layout here

        TextView moneyEarnedView = findViewById(R.id.moneyEarnedText);
        String earnedText = "You Earned $";
        moneyEarnedView.setText(earnedText + moneyEarned);

        // Find views by their IDs
        TextView questionTextView = findViewById(R.id.question_textview);
        RadioButton option1RadioButton = findViewById(R.id.option1_radio_button);
        RadioButton option2RadioButton = findViewById(R.id.option2_radio_button);
        RadioButton option3RadioButton = findViewById(R.id.option3_radio_button);
        RadioButton option4RadioButton = findViewById(R.id.option4_radio_button);

        // Set question and options
        String question = "Which planet is known as the Red Planet?";
        String[] options = {"Mars", "Venus", "Mercury", "Jupiter"};
        double questionReward = 100000; // Set the reward for this question

        // Set values to views
        questionTextView.setText(question);
        option1RadioButton.setText(options[0]);
        option2RadioButton.setText(options[1]);
        option3RadioButton.setText(options[2]);
        option4RadioButton.setText(options[3]);
        RadioGroup optionsRadioGroup = findViewById(R.id.options_radio_group);
        // Set OnCheckedChangeListener for RadioGroup to capture the selected option
        optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the text of the selected RadioButton
                RadioButton selectedRadioButton = findViewById(checkedId);
                selectedOptionText = selectedRadioButton.getText().toString();
            }
        });

        // Set onClickListener for Confirm Button
        findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if any option is selected
                if (selectedOptionText == null) {
                    Toast.makeText(QuestionActivity1.this, "Please, select an option", Toast.LENGTH_LONG).show();
                } else {
                    // Check if the selected option is correct
                    if (selectedOptionText.equals("Mars")) {
                        moneyEarned += 10000; // Increment money earned for correct answer
                        Intent intent = new Intent(QuestionActivity1.this, QuestionActivity2.class);
                        intent.putExtra("moneyEarned", moneyEarned);
                        startActivity(intent);
                        Toast.makeText(QuestionActivity1.this, "Correct! You earned $" + moneyEarned, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(QuestionActivity1.this, "Incorrect!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(QuestionActivity1.this, LostActivity.class);
                        startActivity(intent);
                    }
                    // Finish this activity
                    finish();
                }
            }
        });
    }
}
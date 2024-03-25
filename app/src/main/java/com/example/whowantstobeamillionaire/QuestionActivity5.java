package com.example.whowantstobeamillionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity5 extends AppCompatActivity {

    private int moneyEarned = 0; // Initial money earned
    private String selectedOptionText;
    private final int award = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5); // Set your activity layout here

        Intent intent = getIntent();
        moneyEarned = intent.getIntExtra("moneyEarned", 0);

        // Find views by their IDs
        TextView moneyEarnedTextView = findViewById(R.id.moneyEarnedText);
        TextView questionTextView = findViewById(R.id.question_textview);
        RadioButton option1RadioButton = findViewById(R.id.option1_radio_button);
        RadioButton option2RadioButton = findViewById(R.id.option2_radio_button);
        RadioButton option3RadioButton = findViewById(R.id.option3_radio_button);
        RadioButton option4RadioButton = findViewById(R.id.option4_radio_button);
        RadioGroup optionsRadioGroup = findViewById(R.id.options_radio_group);

        // Set the question and options
        String question = "What is the capital of France?";
        String[] options = {"Berlin", "London", "Paris", "Madrid"};
        String correctAnswer = options[2]; // "Paris" is the correct option

        String earnedText = "You Earned $" + moneyEarned;
        moneyEarnedTextView.setText(earnedText);

        // Set values to views
        questionTextView.setText(question);
        option1RadioButton.setText(options[0]);
        option2RadioButton.setText(options[1]);
        option3RadioButton.setText(options[2]);
        option4RadioButton.setText(options[3]);

        // Set OnCheckedChangeListener for RadioGroup
        optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the selected RadioButton's text
                RadioButton selectedRadioButton = findViewById(checkedId);
                selectedOptionText = selectedRadioButton.getText().toString();
            }
        });

        // Set onClickListener for Confirm Button
        findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder logic for checking correct answer and updating money earned
                if (selectedOptionText == null) {
                    Toast.makeText(QuestionActivity5.this, "Please, select an option", Toast.LENGTH_LONG).show();
                } else if (selectedOptionText.equals(correctAnswer)) {
                    moneyEarned += award; // Update money earned for correct answer
                    Intent intent = new Intent(QuestionActivity5.this, QuestionActivity6.class);
                    intent.putExtra("moneyEarned", moneyEarned);
                    startActivity(intent);
                    // Show a toast message for correct answer
                    Toast.makeText(QuestionActivity5.this, "Correct! You earned $" + moneyEarned, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(QuestionActivity5.this, "Incorrect!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(QuestionActivity5.this, LostActivity.class);
                    startActivity(intent);
                }

                // Finish the current activity
                finish();
            }
        });
    }
}

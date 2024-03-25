package com.example.whowantstobeamillionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity6 extends AppCompatActivity {

    private int moneyEarned = 0; // Initial money earned
    private String selectedOptionText;
    private final int award = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6); // Set your activity layout here

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
        String question = "Which of the following is not a prime number?";
        String[] options = {"2", "3", "6", "7"};
        String correctAnswer = options[2]; // "6" is not a prime number

        // Set the text for money earned
        String earnedText = "You Earned $" + moneyEarned;
        moneyEarnedTextView.setText(earnedText);

        // Set values to views
        questionTextView.setText(question);
        option1RadioButton.setText(options[0]);
        option2RadioButton.setText(options[1]);
        option3RadioButton.setText(options[2]);
        option4RadioButton.setText(options[3]);

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
                    Toast.makeText(QuestionActivity6.this, "Please, select an option", Toast.LENGTH_LONG).show();
                } else {
                    // Check if the selected option is correct
                    if (selectedOptionText.equals(correctAnswer)) {
                        moneyEarned += award; // Increment money earned for correct answer
                        Intent intent = new Intent(QuestionActivity6.this, QuestionActivity7.class);
                        intent.putExtra("moneyEarned", moneyEarned);
                        startActivity(intent);
                        // Show a toast message for correct answer
                        Toast.makeText(QuestionActivity6.this, "Correct! You earned $" + moneyEarned, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(QuestionActivity6.this, "Incorrect!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(QuestionActivity6.this, LostActivity.class);
                        startActivity(intent);
                    }
                    // Finish this activity
                    finish();
                }
            }
        });
    }
}

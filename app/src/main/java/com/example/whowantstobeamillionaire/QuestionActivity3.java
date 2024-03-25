package com.example.whowantstobeamillionaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity3 extends AppCompatActivity {

    private int moneyEarned = 0; // Initial money earned
    private String selectedOptionText; // Holds the text of the selected option
    private final int award = 100000; // Fixed reward for correct answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3); // Set the layout for this activity

        // Retrieve the money earned from the previous activity
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
        String question = "Which of the following is a mammal?";
        String[] options = {"Snake", "Frog", "Bat", "Turtle"};
        String correctOption = options[2]; // "Bat" is the correct option

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
                    Toast.makeText(QuestionActivity3.this, "Please, select an option", Toast.LENGTH_LONG).show();
                } else {
                    // Check if the selected option is correct
                    if (selectedOptionText.equals(correctOption)) {
                        moneyEarned += award; // Increment money earned for correct answer
                        Intent intent = new Intent(QuestionActivity3.this, QuestionActivity4.class);
                        intent.putExtra("moneyEarned", moneyEarned);
                        startActivity(intent);
                        Toast.makeText(QuestionActivity3.this, "Correct! You earned $" + moneyEarned, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(QuestionActivity3.this, "Incorrect!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(QuestionActivity3.this, LostActivity.class);
                        startActivity(intent);
                    }
                    // Finish this activity
                    finish();
                }
            }
        });

        // Display the money earned
        String earnedText = "You Earned $" + moneyEarned;
        moneyEarnedTextView.setText(earnedText);
    }
}

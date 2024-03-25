package com.example.whowantstobeamillionaire;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LostActivity extends AppCompatActivity {

    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        messageTextView = findViewById(R.id.message_textview);

        // Get the earned money amount passed from the previous activity
        int earnedMoney = getIntent().getIntExtra("earnedMoney", 0);

        // Set the message
        messageTextView.setText("You Lost the Game !");
    }
}

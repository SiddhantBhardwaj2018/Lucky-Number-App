package com.siddhantbhardwaj.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Second_Activity extends AppCompatActivity {


    TextView luckyNumberTitle;
    TextView  luckyNumberText;

    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        luckyNumberTitle = findViewById(R.id.luckyNumberTitle);
        luckyNumberText = findViewById(R.id.luckyNumberTxt);
        shareBtn = findViewById(R.id.shareBtn);

        Intent intent = getIntent();
        String username = intent.getStringExtra("name");

        int randomNumber = generateRandomNumber();
        luckyNumberText.setText(""+randomNumber);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(username,randomNumber);
            }
        });
    }

    private void shareData(String username, int randomNumber) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,username + " got lucky today !");
        intent.putExtra(Intent.EXTRA_TEXT,"His lucky number is:" + randomNumber);
        startActivity(Intent.createChooser(intent, "Choose a platform"));
    }

    public int generateRandomNumber(){
        Random random = new Random();
        int upperLimit = 1000;
        int randomNumberGenerated = random.nextInt(upperLimit);
        return randomNumberGenerated;
    }


}
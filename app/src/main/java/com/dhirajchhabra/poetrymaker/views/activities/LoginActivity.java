package com.dhirajchhabra.poetrymaker.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dhirajchhabra.poetrymaker.R;

public class LoginActivity extends AppCompatActivity {

    private CardView headingCardView;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        headingCardView = findViewById(R.id.headingCardView);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FirebaseUIActivity.class);
                startActivity(intent);
            }
        });
    }

}

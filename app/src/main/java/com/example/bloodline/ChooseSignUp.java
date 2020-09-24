package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sign_up);
        Button submitButton,submitButton2;
    submitButton=findViewById(R.id.submit_button);
        submitButton2=findViewById(R.id.submit_button2);
    submitButton.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            startActivity(new Intent(ChooseSignUp.this,DonorSignup.class));
        }
    });
        submitButton2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseSignUp.this,doctorSignupActivity.class));
            }
        });
    }
}
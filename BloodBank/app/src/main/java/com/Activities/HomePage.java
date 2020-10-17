package com.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bloodbank.R;

public class HomePage extends AppCompatActivity {
    private ImageButton request;
    private ImageButton donate;
    private ImageButton search;
    private ImageButton log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        request=findViewById(R.id.Request);
        donate=findViewById(R.id.donate);
        search=findViewById(R.id.Search);
        log=findViewById(R.id.logout);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,LoginActivity.class));
                Toast.makeText(HomePage.this, "Log out successful", Toast.LENGTH_SHORT).show();
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,MakeRequestActivity.class));
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,MainActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,SearchActivity.class));
            }
        });

    }
}
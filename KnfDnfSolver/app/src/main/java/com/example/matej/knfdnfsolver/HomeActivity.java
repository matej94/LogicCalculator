package com.example.matej.knfdnfsolver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button AboutBtn,CalculateBtn,SymbolsBtn;
    Intent explicitIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpUI();
    }
    private void setUpUI(){
        CalculateBtn = findViewById(R.id.calculatebtn);
        AboutBtn = findViewById(R.id.aboutbtn);
        SymbolsBtn = findViewById(R.id.symbolsbtn);

        CalculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explicitIntent.setClass(getApplicationContext(),CalculateActivity.class);
                startActivity(explicitIntent);
            }
        });

        SymbolsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explicitIntent.setClass(getApplicationContext(),SymbolsActivity.class);
                startActivity(explicitIntent);
            }
        });

        AboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explicitIntent.setClass(getApplicationContext(),AboutActivity.class);
                startActivity(explicitIntent);
            }
        });
    }
}

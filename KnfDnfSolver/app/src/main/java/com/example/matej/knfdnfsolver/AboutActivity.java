package com.example.matej.knfdnfsolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView AboutTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setUpUI();
    }
    private void setUpUI(){
        AboutTv = findViewById(R.id.abouttv);

    }

}

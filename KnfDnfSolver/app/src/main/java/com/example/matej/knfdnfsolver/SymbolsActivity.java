package com.example.matej.knfdnfsolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SymbolsActivity extends AppCompatActivity {
    TextView SymbolsTv,ConjuctionBtn,DisjunctionBtn,NegationBtn,ImplicationBtn,EquivalenceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbols);
        setUpUI();
    }
    private void setUpUI(){

        SymbolsTv = findViewById(R.id.symbolstv);
        ConjuctionBtn = findViewById(R.id.contv);
        DisjunctionBtn = findViewById(R.id.distv);
        NegationBtn = findViewById(R.id.negtv);
        ImplicationBtn = findViewById(R.id.impltv);
        EquivalenceBtn = findViewById(R.id.equtv);

    }
}

package com.example.matej.knfdnfsolver;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CalculateActivity extends AppCompatActivity {
    TextView CalculateTv,ResultTv;
    EditText FormulaEt,VariableEt;
    Button ResultBtn,ResetBtn;
    int Row;
    int Col,Col2;
    int i, j;
    TableLayout TabLayout_Create;
    TableLayout TabLayout1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        setUpUI();
    }
    private void setUpUI(){

        CalculateTv = findViewById(R.id.calculatetv);
        ResultTv = findViewById(R.id.resulttv);
        ResultTv.setMovementMethod(new ScrollingMovementMethod());
        FormulaEt = findViewById(R.id.formulaet);
        VariableEt = findViewById(R.id.variableset);
        TabLayout_Create = findViewById(R.id.TableLayout);
        TabLayout1 = findViewById(R.id.TableLayout1);
        ResetBtn = findViewById(R.id.resetbtn);
        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultTv.setText("");
                TabLayout_Create.removeAllViews();
                TabLayout1.removeAllViews();

            }
        });
        ResultBtn = findViewById(R.id.resultbtn);
        ResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String formula = "ft("+ VariableEt.getText().toString() +")=" + FormulaEt.getText().toString();
                //brojanje varijabli
               /* String input = VariableEt.getText().toString().toLowerCase();
                for ( int k = 0; k < input.length(); k++ ) {
                    char chr=  input.charAt(k);
                    int value = (int) chr;
                    if (value >= 97 && value <= 122){
                        counter++;
                    }
                }*/

                double d = Math.pow(2,3);
                int n = (int) d;
                List<String> stringData = new ArrayList<String>();
                Function ft = new Function(formula);

                //dekadski u binarni konverter
                for (int i = 0 ; i <n ; i++) {
                    String result="";
                    String s = Integer.toBinaryString(i);
                    while (s.length() != 3) {
                        s = '0'+s;
                    }
                //dodavanje zareza poslije svake znamenke
                    for (int j = s.length()-1; j >= 0 ; j--) {
                        char ch = s.charAt(j);
                        result = ch + "," + result;
                    }
                //brisanje zadnjeg zareza
                    if (result.charAt(result.length()-1)==','){
                        result = result.substring(0,result.length()-1);
                    }
                    stringData.add(result);
                }
                //ispis u textView
               /* for(int i = 0; i <stringData.size(); i++) {
                    Expression e1 = new Expression("ft(" + stringData.get(i) + ")", ft);
                    ResultTv.setText(ResultTv.getText() + "Formula je: " + FormulaEt.getText().toString() + "\nRezultat je: "
                            + e1.calculate() + "\nVrijednost je: " + stringData.get(i) + "\n\n");
                }*/

                Row = 8;
                Col = 3;
                Col2 = 1;
                // ispis svih kombinacija za 3 varijable;
                int [] numbers = {0,0,0,0,0,1,0,1,0,0,1,1,1,0,0,1,0,1,1,1,0,1,1,1};
                int brojac = 0;
                for (i = 1; i <= Row; i++) {
                    final TableRow row = new TableRow(CalculateActivity.this);
                    if (i % 2 == 0) {
                        row.setBackgroundColor(Color.WHITE);
                    } else {
                        row.setBackgroundColor(Color.LTGRAY);
                    }

                    for (j = 1; j <= Col; j++) {
                        if(brojac==numbers.length) break;
                        final TextView txt = new TextView(CalculateActivity.this);
                        txt.setTextColor(Color.BLACK);
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
                        txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
                        txt.setGravity(Gravity.LEFT);
                        txt.setText(" "+ numbers[brojac]);
                        row.addView(txt);
                        brojac++;

                    }
                    TabLayout_Create.addView(row);

                }
                //ispis rezultata za svaku kombinaciju s 3 varijable
                int br =0;
                for (i = 1; i <= Row; i++) {
                    final TableRow row = new TableRow(CalculateActivity.this);
                    if (i % 2 == 0) {
                        row.setBackgroundColor(Color.WHITE);
                    } else {
                        row.setBackgroundColor(Color.LTGRAY);
                    }

                    for (j = 1; j <= Col2; j++) {
                        Expression e1 = new Expression("ft(" + stringData.get(br) + ")", ft);
                        double r = e1.calculate();
                        int r1 = (int) r;
                        final TextView txt = new TextView(CalculateActivity.this);
                        txt.setTextColor(Color.BLACK);
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
                        txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
                        txt.setGravity(Gravity.LEFT);
                        txt.setText(" "+ r1);
                        row.addView(txt);
                        br++;
                    }
                    TabLayout1.addView(row);

                }

            }

        });

    }
}

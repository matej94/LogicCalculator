package com.example.matej.knfdnfsolver;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;
/*
Copyright 2010-2017 MARIUSZ GROMADA. All rights reserved. You may use this software under the condition of Simplified BSD License.
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions
are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the distribution.
THIS SOFTWARE IS PROVIDED BY MARIUSZ GROMADA ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL MARIUSZ
GROMADA OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those of the authors and should not be
interpreted as representing official policies, either expressed or implied, of MARIUSZ GROMADA.
*/
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CalculateActivity extends AppCompatActivity{
    TextView ResultTv,FormulaTv;
    Button ResultBtn, ResetBtn;
    EditText VariableEt;
    Button ConjunctionBtn,DisjunctionBtn,ImplicationBtn,EquivalenceBtn,NegationBtn,LeftBracketBtn,RightBracketBtn;
    Spinner VariableSpinner;
    TableLayout ValuesTableLayout;
    TableLayout ResultTableLayout, VariableTableLayout;
    int Row;
    int Col, Col2;
    int i, j;
    String znamenke = "";
    int brojac_varijabli;
    CalculationClass calculator = new CalculationClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        setUpUI();
    }

    private void setUpUI() {

        ResultTv = findViewById(R.id.resulttv);
        ResultTv.setMovementMethod(new ScrollingMovementMethod());
        FormulaTv = findViewById(R.id.formulatv);
        VariableEt = findViewById(R.id.variableset);
        VariableSpinner = findViewById(R.id.variableSpinner);
        ValuesTableLayout = findViewById(R.id.valuesTableLayout);
        ResultTableLayout = findViewById(R.id.resultTableLayout);
        VariableTableLayout = findViewById(R.id.variableTableLayout);
        ConjunctionBtn = findViewById(R.id.conbtn);
        DisjunctionBtn = findViewById(R.id.disbtn);
        ImplicationBtn = findViewById(R.id.impbtn);
        EquivalenceBtn = findViewById(R.id.equbtn);
        NegationBtn = findViewById(R.id.negbtn);
        LeftBracketBtn = findViewById(R.id.lbtn);
        RightBracketBtn = findViewById(R.id.rbtn);
        ResultBtn = findViewById(R.id.resultbtn);
        ResetBtn = findViewById(R.id.resetbtn);

        ConjunctionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormulaTv.append("\u2227");
            }
        });
        DisjunctionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormulaTv.append("\u2228");
            }
        });
        ImplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormulaTv.append("\u27F6");
            }
        });
        EquivalenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormulaTv.append("\u27F7");
            }
        });
        NegationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormulaTv.append("¬");
            }
        });
        LeftBracketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormulaTv.append("(");
            }
        });
        RightBracketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormulaTv.append(")");
            }
        });

        VariableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    String s = VariableSpinner.getSelectedItem().toString();
                    FormulaTv.append(s);
                    VariableSpinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.variableArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        VariableSpinner.setAdapter(adapter);

        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultTv.setText("");
                FormulaTv.setText("");
                ValuesTableLayout.removeAllViews();
                ResultTableLayout.removeAllViews();
                VariableTableLayout.removeAllViews();
            }
        });

        ResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //spremanje varijabli u polje i string
                char[] var = VariableEt.getText().toString().toCharArray();
                String varijable = VariableEt.getText().toString();

                String unesenaFormula = FormulaTv.getText().toString();
                String prilagodenaFormula = unesenaFormula.replace('\u2227','\u0026').replace('\u2228','\u007C').
                        replace('\u00AC','\u007E').replace("\u27F6","\u002D\u002D\u003E").replace("\u27F7","\u003C\u002D\u003E");
                //dodavanje zareza poslije svake znamenke
                String rez_var = "";
                for (int j = varijable.length() - 1; j >= 0; j--) {
                    char ch = varijable.charAt(j);
                    rez_var = ch + "," + rez_var;
                }
                //brisanje zadnjeg zareza
                if (rez_var.charAt(rez_var.length() - 1) == ',') {
                    rez_var = rez_var.substring(0, rez_var.length() - 1);
                }
                //formula
                String formula = "ft(" + rez_var + ")=" + prilagodenaFormula;
                //brojanje varijabli
                String input = VariableEt.getText().toString().toLowerCase();
                for (int i = 0; i < input.length(); i++) {
                    char chr = input.charAt(i);
                    int value = (int) chr;
                    if (value >= 97 && value <= 122) {
                        brojac_varijabli++;
                    }
                }
                double doubleKombinacija = Math.pow(2, brojac_varijabli);
                int brojKombinacija = (int) doubleKombinacija;
                List<String> stringData = new ArrayList<String>();
                Function ft = new Function(formula);

                //spremanje znamenki u string pa polje
                for (int i = 0; i < brojKombinacija; i++) {
                    String znam = Integer.toBinaryString(i);
                    while (znam.length() < brojac_varijabli) {
                        znam = '0' + znam;
                    }
                    znamenke = znamenke + znam;

                }
                char[] numbers = znamenke.toCharArray();

                //dekadski u binarni konverter
                for (int i = 0; i < brojKombinacija; i++) {
                    String result = "";
                    String s = Integer.toBinaryString(i);
                    while (s.length() < brojac_varijabli) {
                        s = '0' + s;
                    }
                    //dodavanje zareza poslije svake znamenke
                    for (int j = s.length() - 1; j >= 0; j--) {
                        char ch = s.charAt(j);
                        result = ch + "," + result;
                    }
                    //brisanje zadnjeg zareza
                    if (result.charAt(result.length() - 1) == ',') {
                        result = result.substring(0, result.length() - 1);
                    }
                    stringData.add(result);
                }

                Row = brojKombinacija;
                Col = brojac_varijabli;
                Col2 = 1;

                // ispis svih kombinacija za n varijable;
                int brojac = 0;
                for (i = 1; i <= Row; i++) {
                    final TableRow row = new TableRow(CalculateActivity.this);
                    if (i % 2 == 0) {
                        row.setBackgroundColor(Color.WHITE);
                    } else {
                        row.setBackgroundColor(Color.LTGRAY);
                    }

                    for (j = 1; j <= Col; j++) {
                        if (brojac == numbers.length) break;

                        final TextView txt = new TextView(CalculateActivity.this);
                        txt.setTextColor(Color.BLACK);
                        txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 8);
                        txt.setText(" " + numbers[brojac]);
                        row.addView(txt);
                        brojac++;

                    }
                    ValuesTableLayout.addView(row);
                }

                //ispis rezultata za svaku kombinaciju s n varijable
                int br = 0;
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
                        txt.setText(" " + r1);
                        row.addView(txt);
                        br++;
                    }
                    ResultTableLayout.addView(row);
                }

                //ispis imena varijabli za svaku kombinaciju s n varijable
                int brojac3 = 0;
                for (i = 1; i <= 1; i++) {
                    final TableRow row = new TableRow(CalculateActivity.this);
                    for (j = 1; j <= brojac_varijabli; j++) {
                        final TextView txt = new TextView(CalculateActivity.this);
                        txt.setTextColor(Color.BLACK);
                        txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
                        txt.setText(var[brojac3] + " ");
                        row.addView(txt);
                        brojac3++;
                    }
                    VariableTableLayout.addView(row);
                }

                if (brojac_varijabli == 1) {

                    String OneVariable = calculator.calculateForOne(ft, var, stringData);
                    ResultTv.append(OneVariable);

                } else if (brojac_varijabli == 2) {

                    String TwoVariables = calculator.calculateForTwo(ft, var, stringData);
                    ResultTv.append(TwoVariables);
                } else {

                    String ThreeVariables = calculator.calculateForThree(ft, var, stringData);
                    ResultTv.append(ThreeVariables);
                }
            }

        });
    }
}
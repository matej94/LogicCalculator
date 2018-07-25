package com.example.matej.knfdnfsolver;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.util.List;

/**
 * Created by Matej on 19.7.2018..
 */

public class CalculationClass {
    String jedan, dva, tri, cetiri, pet, sest, sedam, osam;
    String KNF = "";
    String DNF = "";

    public String calculateForOne(Function ft , char[] var, List<String> podaci) {
        for (int i = 0; i < podaci.size(); i++) {
            Expression e1 = new Expression("ft(" + podaci.get(i) + ")", ft);
            double rez = e1.calculate();
            int rez1 = (int) rez;
            if (rez1 == 0 && podaci.get(i).equals("0")) {
                jedan = ("(" + var[0] + ")");
                KNF = KNF + jedan;
            }
            if (rez1 == 0 && podaci.get(i).equals("1")) {
                dva = ("(" + '¬' + var[0] + ")");
                KNF = KNF + dva;
            }
        }
        if (KNF.charAt(KNF.length() - 1) == '∧') {
            KNF = KNF.substring(0, KNF.length() - 1);
        }
        for (int i = 0; i < podaci.size(); i++) {
            Expression e1 = new Expression("ft(" + podaci.get(i) + ")", ft);
            double rez = e1.calculate();
            int rez1 = (int) rez;
            if (rez1 == 1 && podaci.get(i).equals("0")) {
                jedan = ("(" + "¬" + var[0] + ")");
                DNF = DNF + jedan;
            }
            if (rez1 == 1 && podaci.get(i).equals("1")) {
                dva = ("(" + var[0] + ")");
                DNF = DNF + dva;
            }
        }
        if (DNF.charAt(DNF.length() - 1) == '∨') {
            DNF = DNF.substring(0, DNF.length() - 1);
        }
        String konacni = "KNF:" + "\n" + KNF + "\n" + "DNF:" + "\n" + DNF;
        return konacni;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    public String calculateForTwo(Function ft ,char[] var, List<String> podaci) {
        ///////////////ispis KNF 2 varijable u textView//////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < podaci.size(); i++) {
            Expression e1 = new Expression("ft(" + podaci.get(i) + ")", ft);
            double rez = e1.calculate();
            int rez1 = (int) rez;
            if (rez1 == 0 && podaci.get(i).equals("0,0")) {
                jedan = ("(" + var[0] + "∨" + var[1] + ")" + "∧");
                KNF = KNF + jedan;
            }
            if (rez1 == 0 && podaci.get(i).equals("0,1")) {
                dva = ("(" + var[0] + "∨" + "¬" + var[1] + ")" + "∧");
                KNF = KNF + dva;

            }
            if (rez1 == 0 && podaci.get(i).equals("1,0")) {
                tri = ("(" + "¬" + var[0] + "∨" + var[1] + ")" + "∧");
                KNF = KNF + tri;

            }
            if (rez1 == 0 && podaci.get(i).equals("1,1")) {
                cetiri = ("(" + "¬" + var[0] + "∨" + "¬" + var[1] + ")" + "∧");
                KNF = KNF + cetiri;

            }

        }
        if (KNF.charAt(KNF.length() - 1) == '∧') {
            KNF = KNF.substring(0, KNF.length() - 1);
        }


        //////////////////ispis DNF 2 varijable u textView///////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < podaci.size(); i++) {
            Expression e1 = new Expression("ft(" + podaci.get(i) + ")", ft);
            double rez = e1.calculate();
            int rez1 = (int) rez;
            if (rez1 == 1 && podaci.get(i).equals("0,0")) {
                jedan = ("(" + "¬" + var[0] + "∧" + "¬" + var[1] + ")" + "∨");
                DNF = DNF + jedan;
            }
            if (rez1 == 1 && podaci.get(i).equals("0,1")) {
                dva = ("(" + "¬" + var[0] + "∧" + var[1] + ")" + "∨");
                DNF = DNF + dva;

            }
            if (rez1 == 1 && podaci.get(i).equals("1,0")) {
                tri = ("(" + var[0] + "∧" + "¬" + var[1] + ")" + "∨");
                DNF = DNF + tri;

            }
            if (rez1 == 1 && podaci.get(i).equals("1,1")) {
                cetiri = ("(" + var[0] + "∧" + var[1] + ")" + "∨");
                DNF = DNF + cetiri;

            }
        }
        if (DNF.charAt(DNF.length() - 1) == '∨') {
            DNF = DNF.substring(0, DNF.length() - 1);
        }
        String konacni = "KNF:" + "\n" + KNF + "\n" + "DNF:" + "\n" + DNF;
        return konacni;
    }
    ////////////////////////////////////////////////////////////////////////////
    public String calculateForThree(Function ft ,char[] var, List<String> podaci) {
        ///////////////ispis KNF 3 varijable u textView/////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < podaci.size(); i++) {
            Expression e1 = new Expression("ft(" + podaci.get(i) + ")", ft);
            double rez = e1.calculate();
            int rez1 = (int) rez;
            if (rez1 == 0 && podaci.get(i).equals("0,0,0")) {
                jedan = ("(" + var[0] + "∨" + var[1] + "∨" + var[2] + ")" + "∧");
                KNF = KNF + jedan;
            }
            if (rez1 == 0 && podaci.get(i).equals("0,0,1")) {
                dva = ("(" + var[0] + "∨" + var[1] + "∨" + "¬" + var[2] + ")" + "∧");
                KNF = KNF + dva;

            }
            if (rez1 == 0 && podaci.get(i).equals("0,1,0")) {
                tri = ("(" + var[0] + "∨" + "¬" + var[1] + "∨" + var[2] + ")" + "∧");
                KNF = KNF + tri;

            }
            if (rez1 == 0 && podaci.get(i).equals("0,1,1")) {
                cetiri = ("(" + var[0] + "∨" + "¬" + var[1] + "∨" + "¬" + var[2] + ")" + "∧");
                KNF = KNF + cetiri;

            }
            if (rez1 == 0 && podaci.get(i).equals("1,0,0")) {
                pet = ("(" + "¬" + var[0] + "∨" + var[1] + "∨" + var[2] + ")" + "∧");
                KNF = KNF + pet;

            }
            if (rez1 == 0 && podaci.get(i).equals("1,0,1")) {
                sest = ("(" + "¬" + var[0] + "∨" + var[1] + "∨" + "¬" + var[2] + ")" + "∧");
                KNF = KNF + sest;

            }
            if (rez1 == 0 && podaci.get(i).equals("1,1,0")) {
                sedam = ("(" + "¬" + var[0] + "∨" + "¬" + var[1] + "∨" + var[2] + ")" + "∧");
                KNF = KNF + sedam;
            }
            if (rez1 == 0 && podaci.get(i).equals("1,1,1")) {
                osam = ("(" + "¬" + var[0] + "∨" + "¬" + var[1] + "∨" + "¬" + var[2] + ")" + "∧");
                KNF = KNF + osam;

            }

        }
        if (KNF.charAt(KNF.length() - 1) == '∧') {
            KNF = KNF.substring(0, KNF.length() - 1);
        }

        /////////////////ispis DNF 3 varijable u textView///////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < podaci.size(); i++) {
            Expression e1 = new Expression("ft(" + podaci.get(i) + ")", ft);
            double rez = e1.calculate();
            int rez1 = (int) rez;
            if (rez1 == 1 && podaci.get(i).equals("0,0,0")) {
                jedan = ("(" + "¬" + var[0] + "∧" + "¬" + var[1] + "∧" + "¬" + var[2] + ")" + "∨");
                DNF = DNF + jedan;
            }
            if (rez1 == 1 && podaci.get(i).equals("0,0,1")) {
                dva = ("(" + "¬" + var[0] + "∧" + "¬" + var[1] + "∧" + var[2] + ")" + "∨");
                DNF = DNF + dva;

            }
            if (rez1 == 1 && podaci.get(i).equals("0,1,0")) {
                tri = ("(" + "¬" + var[0] + "∧" + var[1] + "∧" + "¬" + var[2] + ")" + "∨");
                DNF = DNF + tri;

            }
            if (rez1 == 1 && podaci.get(i).equals("0,1,1")) {
                cetiri = ("(" + "¬" + var[0] + "∧" + var[1] + "∧" + var[2] + ")" + "∨");
                DNF = DNF + cetiri;

            }
            if (rez1 == 1 && podaci.get(i).equals("1,0,0")) {
                pet = ("(" + var[0] + "∧" + "¬" + var[1] + "∧" + "¬" + var[2] + ")" + "∨");
                DNF = DNF + pet;

            }
            if (rez1 == 1 && podaci.get(i).equals("1,0,1")) {
                sest = ("(" + var[0] + "∧" + "¬" + var[1] + "∧" + var[2] + ")" + "∨");
                DNF = DNF + sest;

            }
            if (rez1 == 1 && podaci.get(i).equals("1,1,0")) {
                sedam = ("(" + var[0] + "∧" + var[1] + "∧" + "¬" + var[2] + ")" + "∨");
                DNF = DNF + sedam;
            }
            if (rez1 == 1 && podaci.get(i).equals("1,1,1")) {
                osam = ("(" + var[0] + "∧" + var[1] + "∧" + var[2] + ")" + "∨");
                DNF = DNF + osam;

            }

        }
        if (DNF.charAt(DNF.length() - 1) == '∨') {
            DNF = DNF.substring(0, DNF.length() - 1);
        }
        String konacni = "KNF:" + "\n" + KNF + "\n" + "DNF:" + "\n" + DNF;
        return konacni;
    }
}


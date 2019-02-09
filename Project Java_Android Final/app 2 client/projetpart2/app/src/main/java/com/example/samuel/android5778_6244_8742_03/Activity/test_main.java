package com.example.samuel.android5778_6244_8742_03.Activity;

import java.util.Random;

/**
 * Created by nathanmsika on 04/01/2018.
 */

public class test_main {

    public static void main(String[] args) {

        char[] s = new char[100];  // char de 10 valeur
        Random r = new Random();
        for (int i=0 ; i<s.length ; i++) {
            if (r.nextInt(2) == 1) // 1 ou 0
                s[i] = (char) (65 + r.nextInt(90 - 65));   //Table ASCII  A = 65 until Z= 90
            else  // sinon
                s[i] = (char) (97 + r.nextInt(122 - 97));   //Table ASCII  a = 97 until z = 122
            System.out.println(s[i]);  // print  index i in array char s
        }





    }
}


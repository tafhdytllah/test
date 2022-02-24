package com.example.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBataraGuru {


    /**
     *
     *
     * Buatlah sebuah fungsi untuk memasukan dash “-” di antara angka
     * genap (20 menit)
     *
     * Contoh : input = 553847
     * Output = 5538-47
     *
     * Contoh : input 2468
     * Output = 2-4-6-8
     *
     */

    public String dashGenap(String value) {
        String stringTemp = "";
        int next = 0;
        for (int i = 0; i < value.length(); i++) {
            var number1 = Integer.parseInt(String.valueOf(value
                    .charAt(i)));
            if (number1 % 2 == 1) {
                stringTemp = stringTemp + number1;
            } else {
                if (i != value.length()-1) {
                    var number2 = Integer.parseInt(String
                            .valueOf(value.charAt(i+1)));
                    if (number2 % 2 == 0) {
                        stringTemp = stringTemp + (number1+"-");
                    } else {
                        stringTemp = stringTemp + number1;
                    }
                } else {
                    stringTemp = stringTemp + number1;
                }
            }
        }
        return stringTemp;
    }
    @Test
    void dashGenapTest() {
        Assertions.assertEquals("5538-47", dashGenap("553847"));
        Assertions.assertEquals("2-4-6-8", dashGenap("2468"));
    }







    public int factorial(int value) {

        if (value == 0) {
            return 1;
        } else {
            return value * factorial(value - 1);
        }
    }

    @Test
    void factorialTest() {
        Assertions.assertEquals(24, factorial(4));
        Assertions.assertEquals(40320, factorial(8));

    }

}

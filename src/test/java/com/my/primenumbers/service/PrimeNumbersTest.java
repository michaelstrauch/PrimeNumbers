package com.my.primenumbers.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class PrimeNumbersTest {

    @Test
    void isPrimeNumberTest() {
        PrimeNumbers primeTest = new PrimeNumbers();
        boolean expected = true;
        boolean actual = primeTest.isPrimeNumber(7);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void getPrimeNumbersTest() {
        PrimeNumbers primeListTest = new PrimeNumbers();
        List<Long> numbersTest = new ArrayList<>(List.of((long)122,(long)6067,(long)3329,(long)1,(long)7643,(long)9547,(long)1187));
        List<Long> expected = new ArrayList<>(List.of((long)6067,(long)3329,(long)7643,(long)9547,(long)1187));
        List<Long> actual = new ArrayList<>(primeListTest.getPrimeNumbers(numbersTest));
        Assertions.assertEquals(expected, actual);
    }
}

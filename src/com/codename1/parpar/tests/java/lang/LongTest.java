/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.lang;

import com.codename1.testing.AbstractTest;

/**
 *
 * @author shannah
 */
public class LongTest extends AbstractTest {

    @Override
    public String toString() {
        return "LongTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        compares();
        sortParparVMArraysSort();
        
        return true;
    }
    
    
    public void compares() {
        assertTrue(Long.compare(10, 5) > 0);
        assertTrue(Long.compare(5, 10) < 0);
        assertTrue(Long.compare(5, 5) == 0);
        assertTrue(Long.compare(Long.MAX_VALUE, Long.MIN_VALUE) > 0);
        assertTrue(Long.compare(Long.MIN_VALUE, Long.MAX_VALUE) < 0);
    }
    
    
    public void sortParparVMArraysSort() {
        long[] arr = new long[]{1L, Long.MIN_VALUE, Long.MAX_VALUE};
        sort(arr);
        assertEqual(1L, arr[1]);
        assertEqual(Long.MIN_VALUE, arr[0]);
        assertEqual(Long.MAX_VALUE, arr[2]);
    }
    
    public static void sort(long[] array) {
        sort(0, array.length, array);
    }
    
    /**
     * Implementation of sort that matches the one in ParparVM's Arrays.sort(long[]) to allow for 
     * testing algorithm in simulator.
     * @param array
     * @param start
     * @param end 
     */
    private static void sort(long[] array, int start, int end) {
        if (array == null) {
            throw new NullPointerException();
        }
        checkBounds(array.length, start, end);
        sort(start, end, array);
    }

    private static void sort(int start, int end, long[] array) {
        long temp;
        int length = end - start;
        if (length < 7) {
            for (int i = start + 1; i < end; i++) {
                for (int j = i; j > start && array[j - 1] > array[j]; j--) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
            return;
        }
        int middle = (start + end) / 2;
        if (length > 7) {
            int bottom = start;
            int top = end - 1;
            if (length > 40) {
                length /= 8;
                bottom = med3(array, bottom, bottom + length, bottom
                        + (2 * length));
                middle = med3(array, middle - length, middle, middle + length);
                top = med3(array, top - (2 * length), top - length, top);
            }
            middle = med3(array, bottom, middle, top);
        }
        long partionValue = array[middle];
        int a, b, c, d;
        a = b = start;
        c = d = end - 1;
        while (true) {
            while (b <= c && array[b] <= partionValue) {
                if (array[b] == partionValue) {
                    temp = array[a];
                    array[a++] = array[b];
                    array[b] = temp;
                }
                b++;
            }
            while (c >= b && array[c] >= partionValue) {
                if (array[c] == partionValue) {
                    temp = array[c];
                    array[c] = array[d];
                    array[d--] = temp;
                }
                c--;
            }
            if (b > c) {
                break;
            }
            temp = array[b];
            array[b++] = array[c];
            array[c--] = temp;
        }
        length = a - start < b - a ? a - start : b - a;
        int l = start;
        int h = b - length;
        while (length-- > 0) {
            temp = array[l];
            array[l++] = array[h];
            array[h++] = temp;
        }
        length = d - c < end - 1 - d ? d - c : end - 1 - d;
        l = b;
        h = end - length;
        while (length-- > 0) {
            temp = array[l];
            array[l++] = array[h];
            array[h++] = temp;
        }
        if ((length = b - a) > 0) {
            sort(start, start + length, array);
        }
        if ((length = d - c) > 0) {
            sort(end - length, end, array);
        }
    }

    
    private static void checkBounds(int arrLength, int start, int end) {
        if (start > end) {
            // luni.35=Start index ({0}) is greater than end index ({1})
            throw new IndexOutOfBoundsException("" + start + " out of: " + end);
        }
        if (start < 0) {
            // luni.36=Array index out of range\: {0}
            throw new IndexOutOfBoundsException("" + start);
        }
        if (end > arrLength) {
            // luni.36=Array index out of range\: {0}
            throw new IndexOutOfBoundsException("" + end);
        }
    }
    
    private static int med3(long[] array, int a, int b, int c) {
        long x = array[a], y = array[b], z = array[c];
        return x < y ? (y < z ? b : (x < z ? c : a)) : (y > z ? b : (x > z ? c
                : a));
    }

}

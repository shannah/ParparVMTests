/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.util;

import java.util.*;
import com.codename1.testing.AbstractTest;

/**
 *
 * @author shannah
 */
public class ArraysTest extends AbstractTest {

    @Override
    public String toString() {
        return "AbstractTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        arrayExposedAsList();
        arrayExposedAsString();
        arraySorted();
        binarySearchWorks();
        return true;
    }
    
    public void arraySorted() {
        Integer[] array = { 2, 5, 7, 3, 5, 6 };
        Arrays.sort(array);
        assertEqual(Integer.valueOf(2), array[0]);
        assertEqual(Integer.valueOf(3), array[1]);
        assertEqual(Integer.valueOf(5), array[2]);
        assertEqual(Integer.valueOf(5), array[3]);
        assertEqual(Integer.valueOf(6), array[4]);
        assertEqual(Integer.valueOf(7), array[5]);
    }

    
    public void binarySearchWorks() {
        Integer[] array = { 2, 4, 6, 8, 10, 12, 14, 16 };
        assertEqual(3, Arrays.binarySearch(array, 8));
        assertEqual(7, Arrays.binarySearch(array, 16));
        assertEqual(0, Arrays.binarySearch(array, 2));
        assertEqual(-1, Arrays.binarySearch(array, 1));
        assertEqual(-2, Arrays.binarySearch(array, 3));
        assertEqual(-3, Arrays.binarySearch(array, 5));
        assertEqual(-8, Arrays.binarySearch(array, 15));
        assertEqual(-9, Arrays.binarySearch(array, 17));
    }

    
    public void arrayExposedAsList() {
        Integer[] array = { 2, 3, 4 };
        List<Integer> list = Arrays.asList(array);
        assertEqual(3, list.size());
        assertEqual(Integer.valueOf(4), list.get(2));
    }

    
    public void arrayExposedAsString() {
        Object[] array = { 1, 2, null, null, "foo" };
        array[3] = array;
        assertEqual("[1, 2, null, [...], foo]", Arrays.deepToString(array));
    }

  

    
    
    
    
    
    
}

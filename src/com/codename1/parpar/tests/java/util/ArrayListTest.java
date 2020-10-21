/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.util;

import com.codename1.testing.AbstractTest;
import java.util.*;
/**
 *
 * @author shannah
 */
public class ArrayListTest extends AbstractTest {

    @Override
    public String toString() {
        return "ArrayListTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        capacityIncreased();
        concurrentModificationsRestricted();
        elementIndexFound();
        elementsAdded();
        elementsInserted();
        elementsRemoved();
        manyElementsAdded();
        manyElementsRemoved();
        //removeIf();
        subListRange();
        return true;
    }
    
    
    public void elementsAdded() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        assertEqual(3, list.size());
        assertEqual(Integer.valueOf(4), list.get(2));
    }

    
    public void capacityIncreased() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 150; ++i) {
            list.add(i);
        }
        assertEqual(150, list.size());
        assertEqual(Integer.valueOf(101), list.get(101));
    }

    
    public void elementsInserted() {
        List<Integer> list = fillFromZeroToNine();
        list.add(5, -1);
        assertEqual(11, list.size());
        assertEqual(Integer.valueOf(-1), list.get(5));
        assertEqual(Integer.valueOf(5), list.get(6));
        assertEqual(Integer.valueOf(9), list.get(10));
    }

    
    public void elementsRemoved() {
        List<Integer> list = fillFromZeroToNine();
        list.remove(5);
        assertEqual(9, list.size());
        assertEqual(Integer.valueOf(6), list.get(5));
        assertEqual(Integer.valueOf(9), list.get(8));
    }


    public void concurrentModificationsRestricted() {
        Throwable error = null;
        try {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                list.add(i);
            }
            for (Integer item : list) {
                if (item.equals(5)) {
                    list.remove(5);
                }
            }
        } catch (Throwable ex) {
            error = ex;
        }
        assertTrue(error instanceof ConcurrentModificationException, "ConcurrentModificationExcpetion should be thrown");
        
    }

    
    public void manyElementsAdded() {
        List<Integer> list = fillFromZeroToNine();
        list.addAll(3, fillFromZeroToNine());
        assertEqual(20, list.size());
        assertEqual(Integer.valueOf(2), list.get(2));
        assertEqual(Integer.valueOf(0), list.get(3));
        assertEqual(Integer.valueOf(9), list.get(12));
        assertEqual(Integer.valueOf(3), list.get(13));
        assertEqual(Integer.valueOf(9), list.get(19));
    }

    
    public void manyElementsRemoved() {
        List<Integer> list = fillFromZeroToNine();
        list.subList(2, 4).clear();
        assertEqual(8, list.size());
        assertEqual(Integer.valueOf(1), list.get(1));
        assertEqual(Integer.valueOf(4), list.get(2));
        assertEqual(Integer.valueOf(9), list.get(7));
    }

    
    public void elementIndexFound() {
        List<Integer> list = fillFromZeroToNine();
        assertEqual(3, list.indexOf(3));
        assertEqual(-1, list.indexOf(100));
    }

    private List<Integer> fillFromZeroToNine() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(i);
        }
        return list;
    }

    
    public void subListRange() {
        List<Integer> list = fillFromZeroToNine();

        List<Integer> sublist = list.subList(0, 10);
        assertEqual(10, sublist.size());

        sublist = list.subList(0, 0);
        assertEqual(0, sublist.size());

        sublist = list.subList(10, 10);
        assertEqual(0, sublist.size());

        sublist = list.subList(5, 5);
        assertEqual(0, sublist.size());

        try {
            list.subList(-1, -1);
            fail("Expected IOOBE for negative indexes");
        } catch (IndexOutOfBoundsException e) {
            // OK
        }

        try {
            list.subList(11, 11);
            fail("Expected IOOBE for indexes beyond size");
        } catch (IndexOutOfBoundsException e) {
            // OK
        }

        try {
            list.subList(-1, 11);
            fail("Expected IOOBE for indexes beyond limits");
        } catch (IndexOutOfBoundsException e) {
            // OK
        }

        try {
            list.subList(5, 4);
            fail("Expected IAE for lowerIndex > upperIndex");
        } catch (IllegalArgumentException e) {
            // OK
        }
    }

    /*
    public void removeIf() {
        List<String> list = new ArrayList<>();
        list.add("A1");
        list.add("A2");
        list.add("B1");
        list.add("B2");

        list.removeIf(e -> e.endsWith("2"));

        assertEqual(2, list.size());
        assertEqual("A1", list.get(0));
        assertEqual("B1", list.get(1));
    }
*/
    
}

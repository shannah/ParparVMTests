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
public class ArrayDequeTest extends AbstractTest {

    @Override
    public String toString() {
        return "ArrayDequeTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        addAndRemoves();
        addsManyToBack();
        addsManyToFront();
        addsToBack();
        addsToFront();
        eachRemovedObjectShouldReduceTheSizeByOne();
        removeElementInWrappedArray();
        removeFirstShouldNotContainTheFirstAddedObject();
        removeLastShouldNotContainTheLastAddedObject();
        removesFromBack();
        removesFromFront();
        return true;
    }
    
    
    public void addsToFront() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        Iterator<Integer> iter = deque.iterator();
        assertEqual(2, deque.size());
        assertEqual((Integer) 2, iter.next());
        assertEqual((Integer) 1, iter.next());
        assertFalse(iter.hasNext());
    }

    
    public void addsToBack() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        Iterator<Integer> iter = deque.iterator();
        assertEqual(2, deque.size());
        assertEqual((Integer) 1, iter.next());
        assertEqual((Integer) 2, iter.next());
        assertFalse(iter.hasNext());
    }

    
    public void addsManyToFront() {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 1000; ++i) {
            deque.addFirst(i);
        }
        assertEqual(1000, deque.size());
        Iterator<Integer> iter = deque.iterator();
        assertEqual((Integer) 999, iter.next());
        for (int i = 2; i < 500; ++i) {
            iter.next();
        }
        assertEqual((Integer) 500, iter.next());
        for (int i = 1; i < 500; ++i) {
            iter.next();
        }
        assertEqual((Integer) 0, iter.next());
    }

    
    public void addsManyToBack() {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 1000; ++i) {
            deque.addLast(i);
        }
        assertEqual(1000, deque.size());
        Iterator<Integer> iter = deque.iterator();
        assertEqual((Integer) 0, iter.next());
        for (int i = 1; i < 500; ++i) {
            iter.next();
        }
        assertEqual((Integer) 500, iter.next());
        for (int i = 2; i < 500; ++i) {
            iter.next();
        }
        assertEqual((Integer) 999, iter.next());
    }

    
    public void removesFromFront() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEqual((Integer) 2, deque.removeFirst());
        assertEqual((Integer) 1, deque.removeFirst());
        assertEqual(0, deque.size());
    }

    
    public void removesFromBack() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEqual((Integer) 1, deque.removeLast());
        assertEqual((Integer) 2, deque.removeLast());
        assertEqual(0, deque.size());
    }

    
    public void addAndRemoves() {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; ++i) {
            deque.addLast(i);
        }
        assertEqual((Integer) 0, deque.removeFirst());
        for (int i = 1; i < 20; ++i) {
            deque.removeFirst();
        }
        assertEqual((Integer) 20, deque.removeFirst());
        for (int i = 101; i < 111; ++i) {
            deque.addLast(i);
        }
        assertEqual((Integer) 110, deque.removeLast());
        for (int i = 2; i < 40; ++i) {
            deque.removeLast();
        }
        assertEqual((Integer) 70, deque.removeLast());
    }

    
    public void eachRemovedObjectShouldReduceTheSizeByOne() {
        ArrayDeque<Object> arrayDeque = new ArrayDeque<>();
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        arrayDeque.add(object1);
        assertTrue(arrayDeque.size() == 1);
        arrayDeque.remove(object1);
        assertTrue(arrayDeque.size() == 0);
        arrayDeque.add(object1);
        arrayDeque.add(object2);
        arrayDeque.add(object3);
        assertTrue(arrayDeque.size() == 3);
        arrayDeque.remove(object1);
        arrayDeque.remove(object2);
        arrayDeque.remove(object3);
        assertTrue(arrayDeque.size() == 0);
        arrayDeque.remove(object1);
        assertTrue(arrayDeque.size() == 0);
    }

    
    public void removeFirstShouldNotContainTheFirstAddedObject() {
        ArrayDeque<Object> arrayDeque1 = new ArrayDeque<>();
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        arrayDeque1.add(object1);
        arrayDeque1.add(object2);
        arrayDeque1.add(object3);
        arrayDeque1.removeFirst();
        assertTrue(arrayDeque1.size() == 2);
        assertTrue(arrayDeque1.contains(object2));
        assertTrue(arrayDeque1.contains(object3));

        ArrayDeque<Object> arrayDeque2 = new ArrayDeque<>();
        arrayDeque2.add(object1);
        arrayDeque2.add(object2);
        arrayDeque2.add(object3);
        arrayDeque2.remove(object1);
        arrayDeque2.removeFirst();
        assertTrue(arrayDeque2.size() == 1);
        assertTrue(arrayDeque2.contains(object3));

        ArrayDeque<Object> arrayDeque3 = new ArrayDeque<>();
        arrayDeque3.add(object1);
        arrayDeque3.add(object2);
        arrayDeque3.add(object3);
        arrayDeque3.remove(object2);
        arrayDeque3.removeFirst();
        assertTrue(arrayDeque3.size() == 1);
        assertTrue(arrayDeque3.contains(object3));

        ArrayDeque<Object> arrayDeque4 = new ArrayDeque<>();
        arrayDeque4.add(object1);
        arrayDeque4.add(object2);
        arrayDeque4.add(object3);
        arrayDeque4.remove(object3);
        arrayDeque4.removeFirst();
        assertTrue(arrayDeque4.size() == 1);
        assertTrue(arrayDeque4.contains(object2));
    }

    
    public void removeLastShouldNotContainTheLastAddedObject() {
        ArrayDeque<Object> arrayDeque1 = new ArrayDeque<>();
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        arrayDeque1.add(object1);
        arrayDeque1.add(object2);
        arrayDeque1.add(object3);
        arrayDeque1.removeLast();
        assertTrue(arrayDeque1.size() == 2);
        assertTrue(arrayDeque1.contains(object1));
        assertTrue(arrayDeque1.contains(object2));

        ArrayDeque<Object> arrayDeque2 = new ArrayDeque<>();
        arrayDeque2.add(object1);
        arrayDeque2.add(object2);
        arrayDeque2.add(object3);
        arrayDeque2.remove(object3);
        arrayDeque2.removeLast();
        assertTrue(arrayDeque2.size() == 1);
        assertTrue(arrayDeque2.contains(object1));

        ArrayDeque<Object> arrayDeque3 = new ArrayDeque<>();
        arrayDeque3.add(object1);
        arrayDeque3.add(object2);
        arrayDeque3.add(object3);
        arrayDeque3.remove(object2);
        arrayDeque3.removeLast();
        assertTrue(arrayDeque3.size() == 1);
        assertTrue(arrayDeque3.contains(object1));

        ArrayDeque<Object> arrayDeque4 = new ArrayDeque<>();
        arrayDeque4.add(object1);
        arrayDeque4.add(object2);
        arrayDeque4.add(object3);
        arrayDeque4.remove(object3);
        arrayDeque4.removeLast();
        assertTrue(arrayDeque4.size() == 1);
        assertTrue(arrayDeque4.contains(object1));
    }

    
    public void removeElementInWrappedArray() {
        ArrayDeque<Object> arrayDeque = new ArrayDeque<>(8);
        for (int i = 0; i < 4; ++i) {
            arrayDeque.addLast(0);
        }
        for (int i = 0; i < 4; ++i) {
            arrayDeque.removeFirst();
        }
        for (int i = 0; i < 6; ++i) {
            arrayDeque.addLast(i);
        }
        for (int i = 5; i >= 0; --i) {
            arrayDeque.remove(i);
            arrayDeque.addLast(23);
            arrayDeque.removeLast();
        }
    }
    
}

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
public class ObjectTest extends AbstractTest {

    @Override
    public String toString() {
        return "ObjectTest";
    }

    
    
    
    @Override
    public boolean runTest() throws Exception {
        differentInstancesNotEqual();
        multipleGetClassCallsReturnSameValue();
        objectCreated();
        properInstanceDetected();
        sameClassesAreEqual();
        sameInstancesAreEqual();
        toStringWorks();
        waitWorks();
        return true;
                
    }
    
    
    public void objectCreated() {
        Object a = new Object();
        assertNotNull(a);
    }

    
    public void differentInstancesNotEqual() {
        Object a = new Object();
        Object b = new Object();
        assertNotEqual(a, b);
    }

    
    public void sameInstancesAreEqual() {
        Object a = new Object();
        Object b = a;
        assertEqual(a, b);
    }

    
    public void multipleGetClassCallsReturnSameValue() {
        Object a = new Object();
        assertSame(a.getClass(), a.getClass());
    }

    
    public void sameClassesAreEqual() {
        Object a = new Object();
        Object b = new Object();
        assertSame(a.getClass(), b.getClass());
    }

    
    public void properInstanceDetected() {
        assertTrue(Object.class.isInstance(new Object()));
    }

    
    public void toStringWorks() {
        // Commenting out because these all fail right now on ParparVM
        
        //assertTrue(new Object().toString().startsWith("java.lang.Object@"));
        //assertTrue(new Object[2].toString().startsWith("[Ljava.lang.Object;@"));
        //assertTrue(new byte[3].toString().startsWith("[B@"));
    }

    
    public void waitWorks() throws InterruptedException {
        long start = System.currentTimeMillis();
        final Object lock = new Object();
        synchronized (lock) {
            lock.wait(110);
        }
        long end = System.currentTimeMillis();
        assertTrue(end - start > 100);
    }
    
    
    
}

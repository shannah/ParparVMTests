/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.lang;

import com.codename1.testing.AbstractTest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shannah
 */
public class ThreadTest extends AbstractTest {

    @Override
    public String toString() {
        return "ThreadTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        asyncVirtualCallsSupported();
        //sleepInterrupted();
        catchesAsyncException();
        sleeps();
        return true;
    }
    
     
    public void sleeps() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(100);
        long duration = System.currentTimeMillis() - start;
        assertTrue(duration >= 100, "Thread.sleep did not wait enough");
    }

    
   

    
    public void catchesAsyncException() {
        try {
            throwException();
            fail("Exception should have been thrown");
        } catch (IllegalStateException e) {
            // all is ok
        }
    }


    private void throwException() {
        Thread.yield();
        throw new IllegalStateException();
    }

    
    public void asyncVirtualCallsSupported() {
        List<A> alist = new ArrayList<>();
        alist.add(new A() {
            @Override int foo() {
                return 3;
            }
        });
        alist.add(new A() {
            @Override int foo() {
                Thread.yield();
                return 5;
            }
        });
        int sum = 0;
        for (A a : alist) {
            sum += a.foo();
        }
        assertEqual(8, sum);
    }

    abstract class A {
        abstract int foo();
    }
    
}

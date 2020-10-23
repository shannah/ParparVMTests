/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.core;

import com.codename1.io.Log;
import com.codename1.testing.AbstractTest;
import com.codename1.util.EasyThread;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shannah
 */
public class ThrowCatchTest extends AbstractTest {
    
    
    private List<String> testsStarted = new ArrayList<>();
    private List<String> testsCompleted = new ArrayList<>();
    List<Throwable> exceptions = new ArrayList<>();

    @Override
    public String toString() {
        return "ThrowCatchTest";
    }
    
    
    
    
    
    @Override
    public boolean runTest() throws Exception {
        testsStarted.clear();
        testsCompleted.clear();
        exceptions.clear();
        
        EasyThread thread = EasyThread.start("Thread1");
        try {
            testsStarted.add("test1");
            thread.run(() -> runTest1());
            testsStarted.add("test1");
            runTest1();

            //Log.p("TEST 1 COMPLETE");

            testsStarted.add("test2");
            thread.run(() -> runTest2());
            testsStarted.add("test2");
            runTest2();
            //Log.p("TEST 2 COMPLETE");
            testsStarted.add("test3");
            thread.run(() -> runTest3());
            testsStarted.add("test3");
            runTest3();
            testsStarted.add("test4");
            thread.run(() -> runTest4());
            testsStarted.add("test4");
            runTest4();
            testsStarted.add("test5");
            thread.run(() -> runTest5());
            testsStarted.add("test5");
            runTest5();
            testsStarted.add("test6");
            thread.run(() -> runTest6());
            testsStarted.add("test6");
            runTest6();
            
            int waitCounter = 0;
            while (testsStarted.size() != testsCompleted.size()) {
                synchronized(this) {
                    wait(100);
                    waitCounter++;
                }
                if (waitCounter > 10) {
                    throw new RuntimeException("Tests still not completed after 1 second.  Too long.  Started:"+testsStarted+", completed="+testsCompleted);
                }
            }
            
            if (!exceptions.isEmpty()) {
                Log.p("Failed ThrowCatchTest.  There were exceptions:");
                for (Throwable t : exceptions) {
                    Log.e(t);
                }
                fail("Exceptions were thrown out of the throwcatch test");
            }
            
        } finally {
            thread.kill();
        }
        TestNested nested = new TestNested();
        
        nested.nestedSynchronized();
        Throwable error = null;
        try {
            nested.nestedSynchronizedThrows();
        } catch (RuntimeException ex) {
            error = ex;
        }
        assertTrue(error instanceof RuntimeException);
        error  = null;
        try {
            nested.nestedSynchronizedThrows2();
        } catch (RuntimeException ex) {
            error = ex;
        }
        assertTrue(error instanceof RuntimeException);
        
        return true;
    }
    
    
    private void runTest1()
    {
        try
        {
            test1();
        }
        catch (Exception e)
        {
            Log.p("EXCEPTION CAUGHT");
        }
        testsCompleted.add("test1");
    }
    
    private void runTest2()
    {
        try
        {
            test2();
        }
        catch (Exception e)
        {
            Log.p("EXCEPTION CAUGHT");
        }
        testsCompleted.add("test2");
    }
    
    private void runTest3()
    {
        try
        {
            test3();
        }
        catch (Exception e)
        {
            Log.p("EXCEPTION CAUGHT");
        }
        testsCompleted.add("test3");
    }
    
    private void runTest4() {
        test4();
        testsCompleted.add("test4");
    }
    
    private void runTest5() {
        test5();
        testsCompleted.add("test5");
    }
    
    private void test1() throws Exception
    {
        synchronized(this)
        {
            throw new Exception();
        }
    }
    
    private void runTest6() {
        try {
            test6();
        } catch (Exception ex) {
            
        }
        testsCompleted.add("test6");
    }
    
    private void test6() {
        synchronized(this) {
            throwRuntimeException();
        }
    }
    
    private synchronized void test2() throws Exception
    {
        throw new Exception();
    }
    
    private void test3() throws Exception {
        throw new Exception();
    }
    
    private void test4() {
        try {
            throw new Exception();
        } catch (Exception ex) {
            Log.p("EXCEPTION CAUGHT");
        }
    }
    
    private void test5() {
        int finallyCount = 0;
        try {
            try {
                try {
                    throwRuntimeException();
                } catch (Exception ex) {
                    Log.p("RUNTIME EXCEPTION CAUGHT IN EXCEPTION BLOCK");
                } finally {
                    finallyCount++;
                }
            }  catch (RuntimeException ex) {
                Log.p("RUNTIME EXCEPTION CAUGHT TEST 5");
                throw new Exception();
            } finally {
                finallyCount++;
            }
        } catch (Exception ex) {
            Log.p("EXCEPTION CAUGHT TEST 5");
        } finally {
            finallyCount++;
        }
        if (finallyCount != 3) {
            throw new RuntimeException("Wrong finally count in test 5.  Should be 3 but was "+finallyCount);
        }
    }
    
    private void throwRuntimeException() {
        throw new RuntimeException();
    }
    
    private void throwException() throws Exception {
        throw new Exception();
    }
    
    
    private class TestNested {
        final ArrayList lock = new ArrayList();
        {
            lock.add("Foo");
            lock.add("Bar");
        }
        
        synchronized void nestedSynchronized() {
            ArrayList tmp;
            synchronized(lock) {
                tmp = new ArrayList(lock);
            }
            
            for (Object item : tmp) {
                System.out.println("item");
            }
        }
        
        synchronized void nestedSynchronizedThrows() {
            ArrayList tmp;
            synchronized(lock) {
                tmp = new ArrayList(lock);
                if (true) {
                    throw new RuntimeException();
                }
            }
            
            for (Object item : tmp) {
                System.out.println("item");
            }
        }
        
        synchronized void nestedSynchronizedThrows2() {
            ArrayList tmp;
            synchronized(lock) {
                tmp = new ArrayList(lock);
                
            }
            if (true) {
                throw new RuntimeException();
            }
            for (Object item : tmp) {
                System.out.println("item");
            }
        }
    }
    
}

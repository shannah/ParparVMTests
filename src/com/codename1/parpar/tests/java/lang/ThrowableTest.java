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
public class ThrowableTest extends AbstractTest {

    @Override
    public String toString() {
        return "ThrowableTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        causeWorks();
        toStringWorks();
        return true;
    }
    
    
    public void causeWorks() {
        RuntimeException e = new RuntimeException("fail", new RuntimeException("OK"));
        assertTrue(e.getCause() instanceof RuntimeException);
        assertEqual("OK", e.getCause().getMessage());
    }

   
    public void toStringWorks() {
        assertEqual("java.lang.RuntimeException: fail", new RuntimeException("fail").toString());
        assertEqual("java.lang.RuntimeException", new RuntimeException().toString());
    }
    
}

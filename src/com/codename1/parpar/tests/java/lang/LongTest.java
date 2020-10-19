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
        
        return true;
    }
    
    
    public void compares() {
        assertTrue(Long.compare(10, 5) > 0);
        assertTrue(Long.compare(5, 10) < 0);
        assertTrue(Long.compare(5, 5) == 0);
        assertTrue(Long.compare(Long.MAX_VALUE, Long.MIN_VALUE) > 0);
        assertTrue(Long.compare(Long.MIN_VALUE, Long.MAX_VALUE) < 0);
    }


}

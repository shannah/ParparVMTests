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
public class BooleanTest extends AbstractTest {

    @Override
    public String toString() {
        return "BooleanTest";
    }

    
    
    
    @Override
    public boolean runTest() throws Exception {
        parsesBoolean();
        return true;
    }
    
    
    public void parsesBoolean() {
        assertTrue(Boolean.valueOf("TruE"));
        assertFalse(Boolean.valueOf("False"));
        assertFalse(Boolean.valueOf("True15"));
    }


    
    
}

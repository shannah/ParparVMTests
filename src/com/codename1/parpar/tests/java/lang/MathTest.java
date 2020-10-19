/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.lang;

import com.codename1.testing.AbstractTest;
import com.codename1.util.MathUtil;

/**
 *
 * @author shannah
 */
public class MathTest extends AbstractTest {

    @Override
    public String toString() {
        return "MathTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        expComputed();
        roundWorks();
        sinComputed();
        
        return true;
    }
    

    public void sinComputed() {
        assertEqual(0.90929742682568, Math.sin(2), 1E-12);
    }

    
    public void expComputed() {
        assertEqual(3.4212295362896734, MathUtil.exp(1.23), 1E-14);
    }

   

   
    
    
    public void roundWorks() {
        assertEqual(1, Math.round(1.3));
        assertEqual(2, Math.round(1.8));
        assertEqual(-1, Math.round(-1.3));
        assertEqual(-2, Math.round(-1.8));
    }
    
}

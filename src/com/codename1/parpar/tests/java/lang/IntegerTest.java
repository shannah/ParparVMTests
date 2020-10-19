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
public class IntegerTest extends AbstractTest {

    @Override
    public String toString() {
        return "IntegerTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        
        
        compares();
        

        
        parsesInteger();
        parsesMinInteger();
        rejectsIntegerWithDigitsOutOfRadix();
        rejectsTooBigInteger();
        toHex();
        writesInteger();
        writesSingleDigitInteger();
        return true;
    }
    
    
    public void parsesInteger() {
        assertEqual(0, Integer.parseInt("0", 10));
        assertEqual(473, Integer.parseInt("473", 10));
        assertEqual(42, Integer.parseInt("+42", 10));
        assertEqual(0, Integer.parseInt("-0", 10));
        assertEqual(-255, Integer.parseInt("-FF", 16));
        assertEqual(102, Integer.parseInt("1100110", 2));
        assertEqual(2147483647, Integer.parseInt("2147483647", 10));
        assertEqual(411787, Integer.parseInt("Kona", 27));
    }

    
    public void parsesMinInteger() {
        assertEqual(-2147483648, Integer.parseInt("-2147483648", 10));
        assertEqual(-2147483648, Integer.parseInt("-80000000", 16));
    }

    public void rejectsTooBigInteger() {
        Throwable error = null;
        try {
            Integer.parseInt("2147483648", 10);
        } catch (NumberFormatException ex) {
            error = ex;
        }
        
        assertNotNull(error);
        assertTrue(error instanceof NumberFormatException);
        
    }

    public void rejectsIntegerWithDigitsOutOfRadix() {
        Throwable error = null;
        try {
            Integer.parseInt("99", 8);
        } catch (NumberFormatException ex) {
            error = ex;
        }
        assertNotNull(error);
        assertTrue(error instanceof NumberFormatException);
    }

    
    public void writesInteger() {
        assertEqual("473", Integer.toString(473, 10));
        assertEqual("-ff", Integer.toString(-255, 16));
        assertEqual("kona", Integer.toString(411787, 27));
    }

    
    public void writesSingleDigitInteger() {
        assertEqual("a", Integer.toString(10, 16));
    }

    

    
    
    
   
    
   

    
    public void compares() {
        assertTrue(Integer.compare(10, 5) > 0);
        assertTrue(Integer.compare(5, 10) < 0);
        assertTrue(Integer.compare(5, 5) == 0);
        assertTrue(Integer.compare(Integer.MAX_VALUE, Integer.MIN_VALUE) > 0);
        assertTrue(Integer.compare(Integer.MIN_VALUE, Integer.MAX_VALUE) < 0);
    }


    
    
    public void toHex() {
        assertEqual("0", Integer.toHexString(0));
        assertEqual("1", Integer.toHexString(1));
        assertEqual("a", Integer.toHexString(10));
        assertEqual("11", Integer.toHexString(17));
        assertEqual("ff", Integer.toHexString(255));
        assertEqual("ffffffff", Integer.toHexString(-1));
    }
    
}

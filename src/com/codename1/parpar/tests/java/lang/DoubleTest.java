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
public class DoubleTest extends AbstractTest {

    @Override
    public String toString() {
        return "DoubleTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        parsed();
        compares();
        //hexStringBuilt();
        longBitsExtracted();
        longBitsPacked();
        parsedWithError();
        return true;
    }
    
    
    public void parsed() {
        assertEqual(23, Double.parseDouble("23"), 1E-12);
        assertEqual(23, Double.parseDouble("23.0"), 1E-12);
        assertEqual(23, Double.parseDouble("23E0"), 1E-12);
        assertEqual(23, Double.parseDouble("2.30000E1"), 1E-12);
        assertEqual(23, Double.parseDouble("0.23E2"), 1E-12);
        assertEqual(23, Double.parseDouble("0.000023E6"), 1E-12);
        assertEqual(23, Double.parseDouble("00230000e-4"), 1E-12);
        assertEqual(23, Double.parseDouble("2300000000000000000000e-20"), 1E-12);
        assertEqual(23, Double.parseDouble("2300000000000000000000e-20"), 1E-12);
        assertEqual(23, Double.parseDouble("23."), 1E-12);
        assertEqual(0.1, Double.parseDouble("0.1"), 0.001);
        assertEqual(0.1, Double.parseDouble(".1"), 0.001);
        assertEqual(0.1, Double.parseDouble(" .1"), 0.001);
        assertEqual(0.1, Double.parseDouble(".1 "), 0.001);
        assertEqual(-23, Double.parseDouble("-23"), 1E-12);
        assertEqual(0, Double.parseDouble("0.0"), 1E-12);
        assertEqual(0, Double.parseDouble("0"), 1E-12);
        assertEqual(0, Double.parseDouble("00"), 1E-12);
        assertEqual(0, Double.parseDouble("0."), 1E-12);
        assertEqual(0, Double.parseDouble(".0"), 1E-12);
        assertEqual(0, Double.parseDouble("23E-8000"), 1E-12);
        assertEqual(0, Double.parseDouble("00000"), 1E-12);
        assertEqual(0, Double.parseDouble("00000.0000"), 1E-12);

        assertEqual(4499999999999888888888888.0, Double.parseDouble("4499999999999888888888888"), 1E9);
        //assertEqual(0.4499999999999888888888888, Double.parseDouble("0.4499999999999888888888888"), 1E-9);
    }

    
    public void parsedWithError() {
        checkIllegalFormat("");
        checkIllegalFormat("  ");
        checkIllegalFormat("a");
        checkIllegalFormat(" a ");
        checkIllegalFormat("-");
        checkIllegalFormat("-.");
        checkIllegalFormat(".");
        checkIllegalFormat("1e-");
        checkIllegalFormat("1e");
    }

    private void checkIllegalFormat(String string) {
        try {
            Double.parseDouble(string);
            fail("Exception expected parsing string: " + string);
        } catch (NumberFormatException e) {
            // It's expected
        }
    }

    
    public void longBitsExtracted() {
        assertEqual(0x41E23456789ABCDEL, Double.doubleToLongBits(0x1.23456789ABCDEP+31));
        assertEqual(0x3FE1C28F5C28F5C3L >>> 3, Double.doubleToLongBits(0.555) >>> 3);
        assertEqual(0x00000056789ABCDEL, Double.doubleToLongBits(0x0.00056789ABCDEP-1022));
    }

    
    public void longBitsPacked() {
        assertEqual(0x1.23456789ABCDEP+31, Double.longBitsToDouble(0x41E23456789ABCDEL), 0x1.0P-19);
        assertEqual(0x0.00056789ABCDEP-1022, Double.longBitsToDouble(0x00000056789ABCDEL), 0x1.0P-19);
    }

    
    
    
    public void compares() {
        assertTrue(Double.compare(10, 5) > 0);
        assertTrue(Double.compare(5, 10) < 0);
        assertTrue(Double.compare(5, 5) == 0);
    }
    
}

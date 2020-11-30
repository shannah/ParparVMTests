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
        parseDouble3250Test();
        parsed();
        compares();
        //hexStringBuilt();
        longBitsExtracted();
        longBitsPacked();
        parsedWithError();
        equals();
        lessThan();
        isSame();
        return true;
    }
    
    
    public void parseDouble3250Test() {
        String test1 = "1.000000108001807564";
        String test2 = "1.0000001080018075648";
        assertTrue(Math.abs(Double.parseDouble(test1) - 1.000000108001807564) < 0.0001);
        assertTrue(Math.abs(Double.parseDouble(test2) - 1.0000001080018075648) < 0.0001);
        
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
    
    public void equals() {
        assertEqual(Double.valueOf(Double.NaN), Double.valueOf(Double.NaN));
        assertEqual(Double.valueOf(Double.POSITIVE_INFINITY), Double.valueOf(Double.POSITIVE_INFINITY));
        assertNotEqual(Double.valueOf(Double.POSITIVE_INFINITY), Double.valueOf(Double.NEGATIVE_INFINITY));
        assertNotEqual(Double.valueOf(Double.NaN), Double.valueOf(Double.POSITIVE_INFINITY));
        assertEqual(Double.valueOf(10.0), Double.valueOf(10.0));
        assertNotEqual(Double.valueOf(10.0), Double.valueOf(10.1));
        assertTrue(0.0==-0.0, "0.0 should be equal to -0.0");
        assertFalse(Double.doubleToLongBits(0.0) == Double.doubleToLongBits(-0.0), "Double.doubleToLongBits(0.0) should be different than Double.double.toLongBits(-0.0)");
        assertFalse(Double.valueOf(0.0).equals(Double.valueOf(-0.0)), "Double(0.0) should NOT be equal to Double(-0.0)");
    }
    
    public void lessThan() {
        assertTrue(lessThan(-0.0, 0.0));
        assertTrue(!lessThan(0.0, -0.0));
        assertTrue(lessThan(Double.POSITIVE_INFINITY, Double.NaN));
        assertTrue(!lessThan(Double.NaN, Double.POSITIVE_INFINITY));
        assertTrue(!lessThan(Double.NaN, Double.NaN));
        assertTrue(!lessThan(0.0, 0.0));
        assertTrue(!lessThan(-0.0, -0.0));
        
    }
    
    /**
     * Extracted this private method from Arrays.java in ParparVM JavaAPI to test algorithm without having
     * to build on iOS.  Used for sorting double arrays, and needs to behave like Double.compare(double, double)
     * @param double1
     * @param double2
     * @return 
     */
    private static boolean lessThan(double double1, double double2) {
        // A slightly specialized version of
        // Double.compare(double1, double2) < 0.

        // Non-zero and non-NaN checking.

        // NaNs are equal to other NaNs and larger than any other double.
        if (Double.isNaN(double1)) {
            return false;
        } else if (Double.isNaN(double2)) {
            return true;
        }
        
        if (double1 == 0d && double1 == double2) {
            long bits1 = Double.doubleToLongBits(double1);
            long bits2 = Double.doubleToLongBits(double2);
            long neg0 = Double.doubleToLongBits(-0.0);
            return bits1 != bits2 && bits1 == neg0;
        }

        // Deal with +0.0 and -0.0.
        //long d1 = Double.doubleToRawLongBits(double1);
        //long d2 = Double.doubleToRawLongBits(double2);
        return double1 < double2;
    }
    
    /**
     * Extracted this private method from Arrays.java in ParparVM JavaAPI to test algorithm without having
     * to build on iOS.  Used for sorting double arrays, and needs to behave like Double.equals(double, double)
     */
    private void isSame() {
        assertFalse(isSame(-0.0, 0.0));
        assertFalse(isSame(0.0, -0.0));
        assertFalse(isSame(Double.POSITIVE_INFINITY, Double.NaN));
        assertFalse(isSame(Double.NaN, Double.POSITIVE_INFINITY));
        assertTrue(isSame(Double.NaN, Double.NaN));
        assertTrue(isSame(0.0, 0.0));
        assertTrue(isSame(-0.0, -0.0));
    }
    
    private static boolean isSame(double double1, double double2) {
        
        if (Double.isNaN(double1)) {
            return Double.isNaN(double2);
        }
        if (Double.isNaN(double2)) {
            return false;
        }
        long d1 = Double.doubleToLongBits(double1);
        long d2 = Double.doubleToLongBits(double2);
        return d1 == d2;

    }
    
}

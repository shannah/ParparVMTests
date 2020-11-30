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
public class FloatTest extends AbstractTest {

    @Override
    public String toString() {
        return "FloatTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        compares();
        floatBitsExtracted();
        floatBitsPacked();
        
        parsed();
        parsedWithError();
        
        lessThan();
        isSame();
        return true;
    }
    
    public void parsed() {
        assertEqual(23, Float.parseFloat("23"), 1E-12F);
        assertEqual(23, Float.parseFloat("23.0"), 1E-12F);
        assertEqual(23, Float.parseFloat("23E0"), 1E-12F);
        assertEqual(23, Float.parseFloat("2.30000E1"), 1E-12F);
        assertEqual(23, Float.parseFloat("0.23E2"), 1E-12F);
        assertEqual(23, Float.parseFloat("0.000023E6"), 1E-12F);
        assertEqual(23, Float.parseFloat("00230000e-4"), 1E-12F);
        assertEqual(23, Float.parseFloat("2300000000000000000000e-20"), 1E-12F);
        assertEqual(23, Float.parseFloat("2300000000000000000000e-20"), 1E-12F);
        assertEqual(23, Float.parseFloat("2300000000000000000000e-20"), 1E-12F);
        assertEqual(23, Float.parseFloat("23."), 1E-12F);
        assertEqual(0.1F, Float.parseFloat("0.1"), 0.001F);
        assertEqual(0.1F, Float.parseFloat(".1"), 0.001F);
        assertEqual(0.1F, Float.parseFloat(" .1"), 0.001F);
        assertEqual(0.1F, Float.parseFloat(".1 "), 0.001F);
        assertEqual(-23, Float.parseFloat("-23"), 1E-12F);
        assertEqual(0, Float.parseFloat("0.0"), 1E-12F);
        assertEqual(0, Float.parseFloat("0"), 1E-12F);
        assertEqual(0, Float.parseFloat("00"), 1E-12F);
        assertEqual(0, Float.parseFloat(".0"), 1E-12F);
        assertEqual(0, Float.parseFloat("0."), 1E-12F);
        assertEqual(0, Float.parseFloat("23E-8000"), 1E-12F);
        assertEqual(0, Float.parseFloat("00000"), 1E-12F);
        assertEqual(0, Float.parseFloat("00000.0000"), 1E-12F);
        assertEqual(4499999285F, Float.parseFloat("4499999285"), 100F);
        assertEqual(0.4499999285F, Float.parseFloat("0.4499999285"), 1E-9F);
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
            Float.parseFloat(string);
            fail("Exception expected parsing string: " + string);
        } catch (NumberFormatException e) {
            // It's expected
        }
    }

    
    public void floatBitsExtracted() {
        assertEqual(0x4591A2B4, Float.floatToIntBits(0x1.234567p+12f));
        assertEqual(0x800000, Float.floatToIntBits((float) MathUtil.pow(2, -126)));
        assertEqual(0x000092, Float.floatToIntBits(0x0.000123p-126f));
    }

    
    public void floatBitsPacked() {
        assertEqual(0x1.234567p+12f, Float.intBitsToFloat(0x4591A2B4), 1e7);
        assertEqual(0x0.000123p-126f, Float.intBitsToFloat(0x000092), 0x000008p-126);
    }

    
    

    
    public void compares() {
        assertTrue(Float.compare(10, 5) > 0);
        assertTrue(Float.compare(5, 10) < 0);
        assertTrue(Float.compare(5, 5) == 0);
    }
    /**
     * Extracted this private method from Arrays.java in ParparVM JavaAPI to test algorithm without having
     * to build on iOS.  Used for sorting double arrays, and needs to behave like Float.compare(float, float)
     */
    public void lessThan() {
        assertTrue(lessThan(-0.0f, 0.0f));
        assertTrue(!lessThan(0.0f, -0.0f));
        assertTrue(lessThan(Float.POSITIVE_INFINITY, Float.NaN));
        assertTrue(!lessThan(Float.NaN, Float.POSITIVE_INFINITY));
        assertTrue(!lessThan(Float.NaN, Float.NaN));
        assertTrue(!lessThan(0.0f, 0.0f));
        assertTrue(!lessThan(-0.0f, -0.0f));
        
    }
    
    /**
     * Extracted this private method from Arrays.java in ParparVM JavaAPI to test algorithm without having
     * to build on iOS.  Used for sorting double arrays, and needs to behave like Float.equals(float, float)
     */
    private void isSame() {
        assertFalse(isSame(-0.0f, 0.0f));
        assertFalse(isSame(0.0f, -0.0f));
        assertFalse(isSame(Float.POSITIVE_INFINITY, Float.NaN));
        assertFalse(isSame(Float.NaN, Float.POSITIVE_INFINITY));
        assertTrue(isSame(Float.NaN, Float.NaN));
        assertTrue(isSame(0.0f, 0.0f));
        assertTrue(isSame(-0.0f, -0.0f));
    }
    
    private static boolean isSame(float double1, float double2) {
        
        if (Float.isNaN(double1)) {
            return Float.isNaN(double2);
        }
        if (Float.isNaN(double2)) {
            return false;
        }
        long d1 = Float.floatToIntBits(double1);
        long d2 = Float.floatToIntBits(double2);
        return d1 == d2;

    }
    
    /**
     * Extracted this private method from Arrays.java in ParparVM JavaAPI to test algorithm without having
     * to build on iOS.  Used for sorting double arrays, and needs to behave like Float.compare(float, float)
     * @param double1
     * @param double2
     * @return 
     */
    private static boolean lessThan(float double1, float double2) {
        // A slightly specialized version of
        // Double.compare(double1, double2) < 0.

        // Non-zero and non-NaN checking.

        // NaNs are equal to other NaNs and larger than any other double.
        if (Float.isNaN(double1)) {
            return false;
        } else if (Float.isNaN(double2)) {
            return true;
        }
        
        if (double1 == 0d && double1 == double2) {
            long bits1 = Float.floatToIntBits(double1);
            long bits2 = Float.floatToIntBits(double2);
            long neg0 = Float.floatToIntBits(-0.0f);
            return bits1 != bits2 && bits1 == neg0;
        }

        return double1 < double2;
    }
    
}

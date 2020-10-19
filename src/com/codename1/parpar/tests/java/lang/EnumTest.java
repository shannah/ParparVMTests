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
public class EnumTest extends AbstractTest {

    @Override
    public String toString() {
        return "EnumTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        comparisonGivesNegativeForEarlierConstant();
        comparisonGivesPositiveForLaterConstant();
        comparisonGivesZeroForSameConstants();
        constantsOfDifferentEnumsAreNotEqual();
        //declaringClassComputed();
        differentConstansAreNotEqual();
        sameConstantsAreEqual();
        valueOfReturnsConstant();
        return true;
        
        
    }
    
    private enum Foo {
        A, B, C
    }

    private enum Bar {
        D, E
    }

    
    public void sameConstantsAreEqual() {
        assertEqual(Foo.A, Foo.A);
    }

    
    public void differentConstansAreNotEqual() {
        assertNotEqual(Foo.A, Foo.B);
    }

    
    public void constantsOfDifferentEnumsAreNotEqual() {
        assertNotEqual(Foo.A, Bar.D);
    }

    
    //public void declaringClassComputed() {
    //    assertEqual(Foo.class, Foo.A.getDeclaringClass());
    //}

    
    public void comparisonGivesZeroForSameConstants() {
        assertEqual(0, Foo.A.compareTo(Foo.A));
    }

    
    public void comparisonGivesPositiveForLaterConstant() {
        assertTrue(Foo.B.compareTo(Foo.A) > 0);
    }

    
    public void comparisonGivesNegativeForEarlierConstant() {
        assertTrue(Foo.A.compareTo(Foo.B) < 0);
    }

    
    public void valueOfReturnsConstant() {
        assertEqual("A", Enum.valueOf(Foo.class, "A").name());
    }
    
}

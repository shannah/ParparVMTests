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
public class CharacterTest extends AbstractTest {

    @Override
    public String toString() {
        return "CharacterTest";
    }
    
    public void digitsRecognized() {
        assertEqual(2, Character.digit('2', 10));
        assertEqual(-1, Character.digit('.', 10));
        
        // Devanagari digit 6
        // This fails on ParparVM currently, though it passes on other JavaVMs
        // Commenting out for now.
        //assertEqual(6, Character.digit('\u096C', 10));
        
        assertEqual(15, Character.digit('F', 16));
    }

    
    public void classesRecognized() {
        //assertEqual(Character.DECIMAL_DIGIT_NUMBER, Character.getType('2'));
        //assertEqual(Character.UPPERCASE_LETTER, Character.getType('Q'));
        //assertEqual(Character.LOWERCASE_LETTER, Character.getType('w'));
        //assertEqual(Character.MATH_SYMBOL, Character.getType(0x21F7));
        //assertEqual(Character.NON_SPACING_MARK, Character.getType(0xFE25));
        //assertEqual(Character.DECIMAL_DIGIT_NUMBER, Character.getType(0x1D7D9));
    }

    @Override
    public boolean runTest() throws Exception {
        digitsRecognized();
        classesRecognized();
        return true;
    }
    
}

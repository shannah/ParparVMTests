/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests;

import com.codename1.testing.TestUtils;

/**
 *
 * @author shannah
 */
public class TestAdapter {
    public static void assertEquals(String message, int actual, int expected) {
        TestUtils.assertEqual(actual, expected, message);
    }
    
    public static void assertEqual(String message, int actual, int expected) {
        TestUtils.assertEqual(actual, expected, message);
    }
    
    public static void assertSame(String message, Object expected, Object actual) {
        TestUtils.assertSame(expected, actual, message);
    }
    
    
}

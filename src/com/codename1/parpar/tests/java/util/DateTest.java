/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.util;

import com.codename1.testing.AbstractTest;
import java.util.*;
/**
 *
 * @author shannah
 */
public class DateTest extends AbstractTest {

    @Override
    public String toString() {
        return "DateTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        setsDateAndMonth();
        return true;
    }
    
    public void setsDateAndMonth() {
        Date date = new Date();
        /*
        date.setMonth(0);
        date.setDate(4);
        date.setYear(115);
        assertEqual(0, date.getMonth());
        assertEqual(4, date.getDate());
        assertEqual(115, date.getYear());
*/
    }
    
}

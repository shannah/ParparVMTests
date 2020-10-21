/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.lang;

import com.codename1.io.Log;
import com.codename1.testing.AbstractTest;

/**
 *
 * @author shannah
 */
public class StringBuilderTest extends AbstractTest {

    @Override
    public String toString() {
        return "StringBuilderTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        
        deletesNothing();
        deletesRange();
        doubleAppended();
        doubleInfinityAppended();
        doubleNaNAppended();
        floatAppended();
        floatAppended2();
        integerAppended();
        integerInserted();
        largeIntegerAppended();
        largeLongAppended();
        longAppended();longAppended2();
        maxDoubleAppended();
        maxFloatAppended();
        maxIntegerAppended();
        maxLongAppended();
        minDoubleAppended();
        minFloatAppended();
        nanFloatAppended();
        negativeDoubleAppended();
        negativeFloatAppended();
        negativeFloatAppended2();
        negativeInfinityAppended();
        negativeIntegerAppended();
        negativeLongAppended();
        negativeSmallFloatAppended();
        negativeSmallFloatAppended2();
        normalDoubleAppended();
        normalFloatAppended();
        normalSmallDoubleAppended();
        normalSmallFloatAppended();
        oneFloatAppended();
        positiveInfinityAppended();
        powTenDoubleAppended();
        //replacesRangeWithLongerSequence();
        //replacesRangeWithSequenceOfSameLength();
        //replacesRangeWithShorterSequence();
        //searchedBackward();smallDoubleAppended();
        smallFloatAppended();
        smallFloatAppended2();
        substringWithUpperBoundAtEndWorks();
        zeroDoubleAppended();
        zeroFloatAppended();
        return true;
    }
    
    
    
    public void integerAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(23);
        assertEqual("23", sb.toString());
    }

    
    public void integerInserted() {
        StringBuilder sb = new StringBuilder("[]");
        sb.insert(1, 23);
        assertEqual("[23]", sb.toString());
        sb = new StringBuilder("[]");
        sb.insert(1, 10);
        assertEqual("[10]", sb.toString());
        sb = new StringBuilder("[]");
        sb.insert(1, 100);
        assertEqual("[100]", sb.toString());
    }

    
    public void largeIntegerAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(123456);
        assertEqual("123456", sb.toString());
    }

    
    public void negativeIntegerAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(-23);
        assertEqual("-23", sb.toString());
    }

    
    public void maxIntegerAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(2147483647);
        assertEqual("2147483647", sb.toString());
    }

    
    public void longAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(23L);
        assertEqual("23", sb.toString());
    }

    
    public void longAppended2() {
        StringBuilder sb = new StringBuilder();
        sb.append(2971215073L);
        assertEqual("2971215073", sb.toString());
    }

    
    public void negativeLongAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(-23L);
        assertEqual("-23", sb.toString());
    }

    
    public void largeLongAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(12345678901234L);
        assertEqual("12345678901234", sb.toString());
    }

    
    public void maxLongAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(9223372036854775807L);
        assertEqual("9223372036854775807", sb.toString());
    }

    
    public void floatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1.234E25F);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E25", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("1.234"));
        //assertEqual("1.234E25", sb.toString());
    }

    
    public void floatAppended2() {
        StringBuilder sb = new StringBuilder();
        sb.append(9.8765E30F);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E30", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("9.8765"));
        //assertEqual("9.8765E30", sb.toString());
    }

    
    public void negativeFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(-1.234E25F);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E25", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("-1.234"));
        //assertEqual("-1.234E25", sb.toString());
    }

    
    public void negativeFloatAppended2() {
        StringBuilder sb = new StringBuilder();
        sb.append(9.8765E30F);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E30", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("9.8765"));
        //assertEqual("9.8765E30", sb.toString());
    }

    
    public void maxFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(3.402823E38f);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E38", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("3.402823"));
        //assertEqual("3.402823E38", sb.toString());
    }

    
    public void smallFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1.234E-25F);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E-25", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("1.234") || sb.toString().startsWith("1.2339"));
        //assertEqual("1.234E-25", sb.toString());
    }

    
    public void smallFloatAppended2() {
        StringBuilder sb = new StringBuilder();
        sb.append(9.8764E-30F);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E-30", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("9.8764") || sb.toString().startsWith("9.876399"));
        //assertEqual("9.8764E-30", sb.toString());
    }

    
    public void negativeSmallFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(-1.234E-25F);
        //Log.p("[negativeSmallFloatAppended] expected -1.234E-25 ;  found "+sb);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E-25", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("-1.234") || sb.toString().startsWith("-1.2339"));
        
        //assertEqual("-1.234E-25", sb.toString());
    }

    
    public void negativeSmallFloatAppended2() {
        StringBuilder sb = new StringBuilder();
        sb.append(-9.8764E-30F);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E-30", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("-9.8764") || sb.toString().startsWith("-9.87639"));
        //assertEqual("-9.8764E-30", sb.toString());
    }

    
    public void minFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1.17549E-38f);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E-38", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("1.17549"));
        //assertEqual("1.17549E-38", sb.toString());
    }

    
    public void normalFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1200f);
        assertEqual("1200.0", sb.toString());
    }

    
    public void normalSmallFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(0.023f);
        assertEqual("0.023", sb.toString());
    }

    
    public void zeroFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(0f);
        assertEqual("0.0", sb.toString());
    }

    
    public void oneFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1f);
        assertEqual("1.0", sb.toString());
    }

    
    public void nanFloatAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(Float.NaN);
        assertEqual("NaN", sb.toString());
    }

    
    public void positiveInfinityAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(Float.POSITIVE_INFINITY);
        assertEqual("Infinity", sb.toString());
    }

    
    public void negativeInfinityAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(Float.NEGATIVE_INFINITY);
        assertEqual("-Infinity", sb.toString());
    }

    
    public void doubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1.23456789E150);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E150", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("1.23456789"));
        //assertEqual("1.23456789E150", sb.toString());
    }

    
    public void powTenDoubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(10.0);
        assertEqual("10.0", sb.toString());
        sb.setLength(0);
        sb.append(20.0);
        assertEqual("20.0", sb.toString());
        sb.setLength(0);
        sb.append(100.0);
        assertEqual("100.0", sb.toString());
        sb.setLength(0);
        sb.append(1000.0);
        assertEqual("1000.0", sb.toString());
        sb.setLength(0);
        sb.append(0.1);
        assertEqual("0.1", sb.toString());
        sb.setLength(0);
        sb.append(0.01);
        assertEqual("0.01", sb.toString());
        sb.setLength(0);
        sb.append(1e20);
        assertEqual("1.0E20", sb.toString());
        sb.setLength(0);
        sb.append(2e20);
        assertEqual("2.0E20", sb.toString());
        sb.setLength(0);
        sb.append(1e-12);
        //int eIndex = sb.toString().indexOf("E");
        //assertTrue(eIndex>0);
        //assertEqual("E-12", sb.toString().substring(eIndex));
        //assertTrue(sb.toString().startsWith("1.0"));
        //assertEqual("1.0E-12", sb.toString());
    }

    
    public void negativeDoubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(-1.23456789E150);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E150", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("-1.23456789"));
        //assertEqual("-1.23456789E150", sb.toString());
    }

    
    public void smallDoubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1.23456789E-150);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E-150", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("-1.23456789"));
        //assertEqual("1.23456789E-150", sb.toString());
    }

    
    public void maxDoubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1.79769313486231E308);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E308", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("1.797693134"));
        //assertEqual("1.79769313486231E308", sb.toString());
    }

    
    public void minDoubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(3E-308);
        int eIndex = sb.toString().indexOf("E");
        assertTrue(eIndex>0);
        assertEqual("E-308", sb.toString().substring(eIndex));
        assertTrue(sb.toString().startsWith("3.0"));
        //assertEqual("3.0E-308", sb.toString());
    }

    
    public void zeroDoubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(0);
        assertEqual("0", sb.toString());
    }

    
    public void doubleInfinityAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(Double.POSITIVE_INFINITY);
        assertEqual("Infinity", sb.toString());
    }

    
    public void doubleNaNAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(Double.NaN);
        assertEqual("NaN", sb.toString());
    }

    
    public void normalDoubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(1200.0);
        assertEqual("1200.0", sb.toString());
    }

    
    public void normalSmallDoubleAppended() {
        StringBuilder sb = new StringBuilder();
        sb.append(0.023);
        assertEqual("0.023", sb.toString());
    }

    
   
    
    public void deletesRange() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; ++i) {
            sb.append((char) ('0' + i));
        }
        sb.delete(4, 6);
        assertEqual(8, sb.length());
        assertEqual('0', sb.charAt(0));
        assertEqual('3', sb.charAt(3));
        assertEqual('6', sb.charAt(4));
        assertEqual('9', sb.charAt(7));
    }

    
    public void deletesNothing() {
        StringBuilder sb = new StringBuilder();
        sb.delete(0, 0);
        assertEqual(0, sb.length());
    }

    /*
    public void replacesRangeWithSequenceOfSameLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; ++i) {
            sb.append((char) ('0' + i));
        }
        sb.replace(4, 6, "ab");
        assertEqual(10, sb.length());
        assertEqual('0', sb.charAt(0));
        assertEqual('3', sb.charAt(3));
        assertEqual('a', sb.charAt(4));
        assertEqual('6', sb.charAt(6));
        assertEqual('9', sb.charAt(9));
    }

    
    public void replacesRangeWithShorterSequence() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; ++i) {
            sb.append((char) ('0' + i));
        }
        sb.replace(4, 6, "a");
        assertEqual(9, sb.length());
        assertEqual('0', sb.charAt(0));
        assertEqual('3', sb.charAt(3));
        assertEqual('a', sb.charAt(4));
        assertEqual('6', sb.charAt(5));
        assertEqual('9', sb.charAt(8));
    }

    
    public void replacesRangeWithLongerSequence() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; ++i) {
            sb.append((char) ('0' + i));
        }
        sb.replace(4, 6, "abc");
        assertEqual(11, sb.length());
        assertEqual('0', sb.charAt(0));
        assertEqual('3', sb.charAt(3));
        assertEqual('a', sb.charAt(4));
        assertEqual('c', sb.charAt(6));
        assertEqual('6', sb.charAt(7));
        assertEqual('9', sb.charAt(10));
    }
    */
    /*
    public void searchedBackward() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; ++i) {
            sb.append((char) ('0' + i));
        }
        assertEqual(3, sb.lastIndexOf("345"));
        assertEqual(-1, sb.lastIndexOf("35"));
    }
    */
    
    public void substringWithUpperBoundAtEndWorks() {
        assertEqual("23", "123".substring(1, 3));
    }
}

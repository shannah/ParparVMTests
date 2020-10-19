/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.lang;

import com.codename1.testing.AbstractTest;
import com.codename1.util.StringUtil;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author shannah
 */
public class StringTest extends AbstractTest {

    @Override
    public String toString() {
        return "StringTest";
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        charsExtracted();
        comparesSameStrings();
        comparesToPrecedingStrings();
        comparesToSuccessorStrings();
        concatenatesStrings();
        containsWorks();
        convertedToCharArray();
        
        createFromLongUTF8ByteArray();
        createdFromByteArray();
        createdFromUTF8ByteArray();
        
        endsWithWorkds();
       
        findsString();
        findsStringBackward();
        getByteArray();
        getUTF8ByteArray();
        getUTF8ByteArrayOfLongString();
        
        interns();
        internsConstants();
        //joinNoStrings();
        //joinSingleString();
        //joinStrings();
        lengthComputed();
        makesLowerCase();
        
        replacesCharacter();
        sequenceReplaced();
        startsWithWorks();
        stringCharactersRead();
        stringCreatedFromChars();
        stringIsEqualToBuilder();
        stringsAreEqual();
        stringsAreNotEqual();
        
        trimWorks();
        return true;
    }
    
    
    
    public void charsExtracted() {
        String str = "123";
        assertEqual('1', str.charAt(0));
        assertEqual('2', str.charAt(1));
        assertEqual('3', str.charAt(2));
    }

    
    public void lengthComputed() {
        String str = "123";
        assertEqual(3, str.length());
    }

    
    public void stringCreatedFromChars() {
        String str = new String(new char[] { '1', '2', '3' });
        assertEqual("123", str);
    }

    
    public void stringsAreEqual() {
        String a = new String(new char[] { '1', '2', '3' });
        String b = new String(new char[] { '1', '2', '3' });
        assertEqual(a, b);
    }

    
    public void stringsAreNotEqual() {
        String a = new String(new char[] { '1', '2', '3' });
        String b = new String(new char[] { '1', '4', '3' });
        assertNotEqual(a, b);
    }

    
    public void stringCharactersRead() {
        char[] buffer = new char[4];
        "123".getChars(0, 3, buffer, 0);
        assertArrayEqual(new char[] { '1', '2', '3', '\0' }, buffer);
    }

    
    public void stringIsEqualToBuilder() {
        assertTrue("123".contentEquals(new StringBuilder().append('1').append('2').append('3')));
    }

    
    public void comparesSameStrings() {
        String a = "123";
        String b = new String(a);
        assertTrue(a.compareTo(b) == 0);
    }

    
    public void comparesToPrecedingStrings() {
        assertTrue("abc".compareTo("abbc") > 0);
    }

    
    public void comparesToSuccessorStrings() {
        assertTrue("abc".compareTo("abdc") < 0);
    }

    
    public void startsWithWorks() {
        assertTrue("123".startsWith("12"));
    }

   
    
    public void endsWithWorkds() {
        assertTrue("12345".endsWith("45"));
    }

   
  
   
  
    
    public void findsString() {
        assertEqual(1, "abcdbcd".indexOf("bc"));
        assertEqual(3, "abcdbcd".indexOf("dbcd"));
        assertEqual(-1, "abcdbcd".indexOf("bb"));
    }

    
    public void findsStringBackward() {
        assertEqual(4, "abcdbcd".lastIndexOf("bc"));
        assertEqual(-1, "abcdbcd".lastIndexOf("bb"));
    }

    
    public void concatenatesStrings() {
        assertEqual("abcd", "ab".concat("cd"));
    }

    
    public void replacesCharacter() {
        assertEqual("abbdbbd", "abcdbcd".replace('c', 'b'));
    }

    
    public void containsWorks() {
        assertTrue("abcd".contains("bc"));
    }

    
    public void sequenceReplaced() {
        assertEqual("ba", StringUtil.replaceAll("aaa", "aa", "b"));
    }

    
    public void trimWorks() {
        assertEqual("ab", "  ab   ".trim());
        assertEqual("ab", "ab".trim());
        assertEqual("", "  ".trim());
    }

    
    public void convertedToCharArray() {
        char[] array = "123".toCharArray();
        assertEqual(3, array.length);
        assertArrayEqual(new char[] { '1', '2', '3' }, array);
    }
    
    private boolean assertArrayEqual(char[] a1, char[] a2) {
        assertEqual(a1.length, a2.length);
        int len = a1.length;
        for (int i=0; i<len; i++) {
            assertEqual(a1[i], a2[i]);
        }
        return true;
    }

    
    public void createdFromByteArray() throws UnsupportedEncodingException {
        byte[] bytes = { 49, 50, 51 };
        assertEqual("123", new String(bytes, "UTF-8"));
    }

    
    public void createdFromUTF8ByteArray() throws UnsupportedEncodingException {
        byte[] bytes = { 65, -62, -69, -32, -82, -69, -16, -66, -78, -69 };
        assertEqual("A\u00BB\u0BBB\uD8BB\uDCBB", new String(bytes, "UTF-8"));
    }

    
    public void createFromLongUTF8ByteArray() throws UnsupportedEncodingException {
        byte[] bytes = new byte[16384];
        for (int i = 0; i < bytes.length;) {
            bytes[i++] = -16;
            bytes[i++] = -66;
            bytes[i++] = -78;
            bytes[i++] = -69;
        }
        String str = new String(bytes, "UTF-8");
        assertEqual('\uD8BB', str.charAt(8190));
        assertEqual('\uDCBB', str.charAt(8191));
    }

    
    public void getByteArray() throws UnsupportedEncodingException {
        byte[] bytes = "123".getBytes("UTF-8");
        assertArrayEqual(new byte[] { 49, 50, 51 }, bytes);
        assertEqual(3, bytes.length);
    }

    
    public void getUTF8ByteArray() throws UnsupportedEncodingException {
        byte[] bytes = "A\u00BB\u0BBB\uD8BB\uDCBB".getBytes("UTF-8");
        assertArrayEqual(new byte[] { 65, -62, -69, -32, -82, -69, -16, -66, -78, -69 }, bytes);
    }

    
    public void getUTF8ByteArrayOfLongString() throws UnsupportedEncodingException {
        char[] chars = new char[8192];
        for (int i = 0; i < chars.length;) {
            chars[i++] = '\uD8BB';
            chars[i++] = '\uDCBB';
        }
        byte[] bytes = new String(chars).getBytes("UTF-8");
        assertEqual(-16, bytes[16380]);
        assertEqual(-66, bytes[16381]);
        assertEqual(-78, bytes[16382]);
        assertEqual(-69, bytes[16383]);
    }

    
  
    
    public void makesLowerCase() {
        assertEqual("foo bar", "FoO bAr".toLowerCase());
    }

    
    public void interns() {
        //assertSame("xabc".substring(1).intern(), "abcx".substring(0, 3).intern());
        //assertSame("xabc".substring(1).intern(), "abc");
    }

    
    public void internsConstants() {
        //assertSame("abc", ("a" + "bc").intern());
    }

    /*
    public void joinStrings() {
        String str = String.join("/", "a", "b");
        assertEqual(3, str.length());
        assertEqual("a/b", str);
    }
    */
    /*
    public void joinSingleString() {
        String str = String.join("/", "a");
        assertEqual(1, str.length());
        assertEqual("a", str);
    }
    */
    /*
    public void joinNoStrings() {
        String str = String.join("/");
        assertEqual(0, str.length());
        assertEqual("", str);
    }
    */
    
    
    
}

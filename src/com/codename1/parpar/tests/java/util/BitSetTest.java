/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.parpar.tests.java.util;

import java.util.*;
import com.codename1.testing.AbstractTest;

/**
 *
 * @author shannah
 */
public class BitSetTest extends AbstractTest {

    @Override
    public String toString() {
        return "BitSetTest";
    }

    public BitSetTest() {
        eightbs = new BitSet();
        for (int i = 0; i < 8; i++) {
            eightbs.set(i);
        }
    }
    
    
    private void reset() {
        eightbs = new BitSet();
        for (int i = 0; i < 8; i++) {
            eightbs.set(i);
        }
    }

    
    
    @Override
    public boolean runTest() throws Exception {
        and();
        reset();
        andNot();
        reset();
        cardinality();
        reset();
        clear();
        reset();
        clearI();
        reset();
        clearII();
        reset();
        //clonePerformed();
        //constructFromBytes();
        constructor();
        reset();
        constructorI();
        reset();
        equalityComputed();
        reset();
        flipI();
        reset();
        flipII();
        reset();
        getI();
        reset();
        getII();
        reset();
        hashCodeComputed();
        reset();
        intersects();
        reset();
        isEmpty();
        reset();
        length();
        reset();
        nextClearBitI();
        reset();
        nextSetBitI();
        reset();
        or();
        reset();
        //previousClearBitFound();
        //previousSetBitFound();
        setI();
        reset();
        setII();
        reset();
        setIIZ();
        reset();
        setIZ();
        reset();
        toStringComputed();
        reset();
        xor();
        reset();
        return true;
    }
    
    BitSet eightbs;

    

    
    public void constructor() {
        BitSet bs = new BitSet();
        assertEqual( "{}", bs.toString(), "New BitSet had invalid string representation");
    }

    
    public void constructorI() {
        BitSet bs = new BitSet(128);
        assertEqual( "{}", bs.toString(), "New BitSet had invalid string representation: " + bs);
    }

    /*
    public void constructFromBytes() {
        for (int i = 4; i < 8; ++i) {
            byte[] bytes = new byte[i];
            Arrays.fill(bytes, (byte) 0x80);
            BitSet bs = BitSet.valueOf(bytes);
            assertEqual("Wrong length of BitSet", i * 8, bs.length());
            for (int j = 0; j < bs.length(); ++j) {
                if (j % 8 == 7) {
                    assertTrue("Expected that " + j + "th bit is to be set", bs.get(j));
                } else {
                    assertFalse("Expected that " + j + "th bit is not to be set", bs.get(j));
                }
            }
        }
    }
    */
    private void assertEqual(String message, int expected, int actual) {
        assertEqual(expected, actual, message);
    }
    
    private void assertTrue(String message, boolean val) {
        assertTrue(val, message);
    }
    
    private void assertFalse(String message, boolean val) {
        assertFalse(val, message);
    }

    /*
    public void clonePerformed() {
        BitSet bs;
        bs = (BitSet) eightbs.clone();
        assertEqual("clone failed to return equal BitSet", bs, eightbs);
    }
    */
    
    private BitSet cloneBitSet(BitSet bs) {
        BitSet out = new BitSet();
        out.clear();
        out.or(bs);
        return out;
        
    }
    
    public void equalityComputed() {
        BitSet bs;
        bs = (BitSet) cloneBitSet(eightbs);
        assertEqual("Same BitSet returned false", eightbs, eightbs);
        assertEqual("Identical BitSet returned false", bs, eightbs);
        bs.clear(6);
        assertFalse("Different BitSets returned true", eightbs.equals(bs));

        bs = (BitSet) cloneBitSet(eightbs);
        bs.set(128);
        assertFalse("Different sized BitSet with higher bit set returned true", eightbs.equals(bs));
        bs.clear(128);
        assertTrue("Different sized BitSet with higher bits not set returned false", eightbs.equals(bs));
    }

    
    public void hashCodeComputed() {
        // Test for method int java.util.BitSet.hashCode()
        BitSet bs = (BitSet) cloneBitSet(eightbs);
        bs.clear(2);
        bs.clear(6);
        assertEqual("BitSet returns wrong hash value", 1129, bs.hashCode());
        bs.set(10);
        bs.clear(3);
        assertEqual("BitSet returns wrong hash value", 97, bs.hashCode());
    }

    
    public void clear() {
        eightbs.clear();
        for (int i = 0; i < 8; i++) {
            assertFalse("Clear didn't clear bit " + i, eightbs.get(i));
        }
        assertEqual("Test1: Wrong length", 0, eightbs.length());

        BitSet bs = new BitSet(3400);
        bs.set(0, bs.size() - 1); // ensure all bits are 1's
        bs.set(bs.size() - 1);
        bs.clear();
        assertEqual("Test2: Wrong length", 0, bs.length());
        assertTrue("Test2: isEmpty() returned incorrect value", bs.isEmpty());
        assertEqual("Test2: cardinality() returned incorrect value", 0, bs.cardinality());
    }

    
    public void clearI() {
        // Test for method void java.util.BitSet.clear(int)

        eightbs.clear(7);
        assertFalse("Failed to clear bit", eightbs.get(7));

        // Check to see all other bits are still set
        for (int i = 0; i < 7; i++) {
            assertTrue("Clear cleared incorrect bits", eightbs.get(i));
        }

        eightbs.clear(165);
        assertFalse("Failed to clear bit", eightbs.get(165));

        BitSet bs = new BitSet(0);
        assertEqual("Test1: Wrong length,", 0, bs.length());

        bs.clear(0);
        assertEqual("Test2: Wrong length,", 0, bs.length());

        bs.clear(60);
        assertEqual("Test3: Wrong length,", 0, bs.length());

        bs.clear(120);
        assertEqual("Test4: Wrong length,", 0, bs.length());

        bs.set(25);
        assertEqual("Test5: Wrong length,", 26, bs.length());

        bs.clear(80);
        assertEqual("Test6: Wrong length,", 26, bs.length());

        bs.clear(25);
        assertEqual("Test7: Wrong length,", 0, bs.length());
    }

    
    public void clearII() throws IndexOutOfBoundsException {
        // Regression for HARMONY-98
        BitSet bitset = new BitSet();
        for (int i = 0; i < 20; i++) {
            bitset.set(i);
        }
        bitset.clear(10, 10);

        // Test for method void java.util.BitSet.clear(int, int)
        // pos1 and pos2 are in the same bitset element
        BitSet bs = new BitSet(16);
        int initialSize = bs.size();
        bs.set(0, initialSize);
        bs.clear(5);
        bs.clear(15);
        bs.clear(7, 11);
        for (int i = 0; i < 7; i++) {
            if (i == 5) {
                assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertTrue("Shouldn't have cleared bit " + i, bs.get(i));
            }
        }
        for (int i = 7; i < 11; i++) {
            assertFalse("Failed to clear bit " + i, bs.get(i));
        }

        for (int i = 11; i < initialSize; i++) {
            if (i == 15) {
                assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertTrue("Shouldn't have cleared bit " + i, bs.get(i));
            }
        }

        for (int i = initialSize; i < bs.size(); i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }

        // pos1 and pos2 is in the same bitset element, boundry testing
        bs = new BitSet(16);
        initialSize = bs.size();
        bs.set(0, initialSize);
        bs.clear(7, 64);
        for (int i = 0; i < 7; i++) {
            assertTrue("Shouldn't have cleared bit " + i, bs.get(i));
        }
        for (int i = 7; i < 64; i++) {
            assertFalse("Failed to clear bit " + i, bs.get(i));
        }
        for (int i = 64; i < bs.size(); i++) {
            assertTrue("Shouldn't have flipped bit " + i, !bs.get(i));
        }
        // more boundary testing
        bs = new BitSet(32);
        initialSize = bs.size();
        bs.set(0, initialSize);
        bs.clear(0, 64);
        for (int i = 0; i < 64; i++) {
            assertFalse("Failed to clear bit " + i, bs.get(i));
        }
        for (int i = 64; i < bs.size(); i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }

        bs = new BitSet(32);
        initialSize = bs.size();
        bs.set(0, initialSize);
        bs.clear(0, 65);
        for (int i = 0; i < 65; i++) {
            assertFalse("Failed to clear bit " + i, bs.get(i));
        }
        for (int i = 65; i < bs.size(); i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }

        // pos1 and pos2 are in two sequential bitset elements
        bs = new BitSet(128);
        initialSize = bs.size();
        bs.set(0, initialSize);
        bs.clear(7);
        bs.clear(110);
        bs.clear(9, 74);
        for (int i = 0; i < 9; i++) {
            if (i == 7) {
                assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertTrue("Shouldn't have cleared bit " + i, bs.get(i));
            }
        }
        for (int i = 9; i < 74; i++) {
            assertFalse("Failed to clear bit " + i, bs.get(i));
        }
        for (int i = 74; i < initialSize; i++) {
            if (i == 110) {
                assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertTrue("Shouldn't have cleared bit " + i, bs.get(i));
            }
        }
        for (int i = initialSize; i < bs.size(); i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }

        // pos1 and pos2 are in two non-sequential bitset elements
        bs = new BitSet(256);
        bs.set(0, 256);
        bs.clear(7);
        bs.clear(255);
        bs.clear(9, 219);
        for (int i = 0; i < 9; i++) {
            if (i == 7) {
                assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertTrue("Shouldn't have cleared bit " + i, bs.get(i));
            }
        }

        for (int i = 9; i < 219; i++) {
            assertFalse("failed to clear bit " + i, bs.get(i));
        }

        for (int i = 219; i < 255; i++) {
            assertTrue("Shouldn't have cleared bit " + i, bs.get(i));
        }

        for (int i = 255; i < bs.size(); i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }

        bs.set(2, 4);
        bs.clear(2, 2);
        assertTrue("Bit got cleared incorrectly ", bs.get(2));

        /*try {
            bs.clear(4, 2);
            fail("Test4: Attempt to flip with illegal args failed to generate exception");
        } catch (IndexOutOfBoundsException e) {
            // excepted
        }*/

        bs = new BitSet(0);
        assertEqual("Test1: Wrong length,", 0, bs.length());

        bs.clear(0, 2);
        assertEqual("Test2: Wrong length,", 0, bs.length());

        bs.clear(60, 64);
        assertEqual("Test3: Wrong length,", 0, bs.length());

        bs.clear(64, 120);
        assertEqual("Test4: Wrong length,", 0, bs.length());

        bs.set(25);
        assertEqual("Test5: Wrong length,", 26, bs.length());

        bs.clear(60, 64);
        assertEqual("Test6: Wrong length,", 26, bs.length());

        bs.clear(64, 120);
        assertEqual("Test7: Wrong length,", 26, bs.length());

        bs.clear(80);
        assertEqual("Test8: Wrong length,", 26, bs.length());

        bs.clear(25);
        assertEqual("Test9: Wrong length,", 0, bs.length());
    }

    
    public void getI() {
        // Test for method boolean java.util.BitSet.get(int)

        BitSet bs = new BitSet();
        bs.set(8);
        assertFalse("Get returned true for index out of range", eightbs.get(99));
        assertTrue("Get returned false for set value", eightbs.get(3));
        assertFalse("Get returned true for a non set value", bs.get(0));

        /*try {
            bs.get(-1);
            fail("Attempt to get at negative index failed to generate exception");
        } catch (IndexOutOfBoundsException e) {
            // Correct behaviour
        }*/

        bs = new BitSet(1);
        assertFalse("Access greater than size", bs.get(64));

        bs = new BitSet();
        bs.set(63);
        assertTrue("Test highest bit", bs.get(63));

        bs = new BitSet(0);
        assertEqual("Test1: Wrong length,", 0, bs.length());

        bs.get(2);
        assertEqual("Test2: Wrong length,", 0, bs.length());

        bs.get(70);
        assertEqual("Test3: Wrong length,", 0, bs.length());
    }

    
    private void assertEqual(String message, BitSet expected, BitSet actual) {
        assertEqual(expected, actual, message);
    }
    
    public void getII() {
        BitSet bitset = new BitSet(30);
        bitset.get(3, 3);

        // Test for method boolean java.util.BitSet.get(int, int)
        BitSet bs;
        BitSet resultbs;
        BitSet correctbs;
        bs = new BitSet(512);
        bs.set(3, 9);
        bs.set(10, 20);
        bs.set(60, 75);
        bs.set(121);
        bs.set(130, 140);

        // pos1 and pos2 are in the same bitset element, at index0
        resultbs = bs.get(3, 6);
        correctbs = new BitSet(3);
        correctbs.set(0, 3);
        assertEqual("Test1: Returned incorrect BitSet", correctbs, resultbs);

        // pos1 and pos2 are in the same bitset element, at index 1
        resultbs = bs.get(100, 125);
        correctbs = new BitSet(25);
        correctbs.set(21);
        assertEqual("Test2: Returned incorrect BitSet", correctbs, resultbs);

        // pos1 in bitset element at index 0, and pos2 in bitset element at
        // index 1
        resultbs = bs.get(15, 125);
        correctbs = new BitSet(25);
        correctbs.set(0, 5);
        correctbs.set(45, 60);
        correctbs.set(121 - 15);
        assertEqual("Test3: Returned incorrect BitSet", correctbs, resultbs);

        // pos1 in bitset element at index 1, and pos2 in bitset element at
        // index 2
        resultbs = bs.get(70, 145);
        correctbs = new BitSet(75);
        correctbs.set(0, 5);
        correctbs.set(51);
        correctbs.set(60, 70);
        assertEqual("Test4: Returned incorrect BitSet", correctbs, resultbs);

        // pos1 in bitset element at index 0, and pos2 in bitset element at
        // index 2
        resultbs = bs.get(5, 145);
        correctbs = new BitSet(140);
        correctbs.set(0, 4);
        correctbs.set(5, 15);
        correctbs.set(55, 70);
        correctbs.set(116);
        correctbs.set(125, 135);
        assertEqual("Test5: Returned incorrect BitSet", correctbs, resultbs);

        // pos1 in bitset element at index 0, and pos2 in bitset element at
        // index 3
        resultbs = bs.get(5, 250);
        correctbs = new BitSet(200);
        correctbs.set(0, 4);
        correctbs.set(5, 15);
        correctbs.set(55, 70);
        correctbs.set(116);
        correctbs.set(125, 135);
        assertEqual("Test6: Returned incorrect BitSet", correctbs, resultbs);

        assertEqual("equality principle 1 ", bs.get(0, bs.size()), bs);

        // more tests
        BitSet bs2 = new BitSet(129);
        bs2.set(0, 20);
        bs2.set(62, 65);
        bs2.set(121, 123);
        resultbs = bs2.get(1, 124);
        correctbs = new BitSet(129);
        correctbs.set(0, 19);
        correctbs.set(61, 64);
        correctbs.set(120, 122);
        assertEqual("Test7: Returned incorrect BitSet", correctbs, resultbs);

        // equality principle with some boundary conditions
        bs2 = new BitSet(128);
        bs2.set(2, 20);
        bs2.set(62);
        bs2.set(121, 123);
        bs2.set(127);
        resultbs = bs2.get(0, bs2.size());
        assertEqual("equality principle 2 ", resultbs, bs2);

        bs2 = new BitSet(128);
        bs2.set(2, 20);
        bs2.set(62);
        bs2.set(121, 123);
        bs2.set(127);
        bs2.flip(0, 128);
        resultbs = bs2.get(0, bs.size());
        assertEqual("equality principle 3 ", resultbs, bs2);

        bs = new BitSet(0);
        assertEqual("Test1: Wrong length,", 0, bs.length());

        bs.get(0, 2);
        assertEqual("Test2: Wrong length,", 0, bs.length());

        bs.get(60, 64);
        assertEqual("Test3: Wrong length,", 0, bs.length());

        bs.get(64, 120);
        assertEqual("Test4: Wrong length,", 0, bs.length());

        bs.set(25);
        assertEqual("Test5: Wrong length,", 26, bs.length());

        bs.get(60, 64);
        assertEqual("Test6: Wrong length,", 26, bs.length());

        bs.get(64, 120);
        assertEqual("Test7: Wrong length,", 26, bs.length());

        bs.get(80);
        assertEqual("Test8: Wrong length,", 26, bs.length());

        bs.get(25);
        assertEqual("Test9: Wrong length,", 26, bs.length());
    }

    
    public void flipI() {
        // Test for method void java.util.BitSet.flip(int)
        BitSet bs = new BitSet();
        bs.clear(8);
        bs.clear(9);
        bs.set(10);
        bs.flip(9);
        assertFalse("Failed to flip bit", bs.get(8));
        assertTrue("Failed to flip bit", bs.get(9));
        assertTrue("Failed to flip bit", bs.get(10));

        bs.set(8);
        bs.set(9);
        bs.clear(10);
        bs.flip(9);
        assertTrue("Failed to flip bit", bs.get(8));
        assertFalse("Failed to flip bit", bs.get(9));
        assertFalse("Failed to flip bit", bs.get(10));

        /*try {
            bs.flip(-1);
            fail("Attempt to flip at negative index failed to generate exception");
        } catch (IndexOutOfBoundsException e) {
            // Correct behaviour
        }*/

        // Try setting a bit on a 64 boundary
        bs.flip(128);
        assertTrue("Failed to flip bit", bs.get(128));

        bs = new BitSet(64);
        for (int i = bs.size(); --i >= 0;) {
            bs.flip(i);
            assertTrue("Test1: Incorrectly flipped bit" + i, bs.get(i));
            assertEqual("Incorrect length", i + 1, bs.length());
            for (int j = bs.size(); --j > i;) {
                assertTrue("Test2: Incorrectly flipped bit" + j, !bs.get(j));
            }
            for (int j = i; --j >= 0;) {
                assertTrue("Test3: Incorrectly flipped bit" + j, !bs.get(j));
            }
            bs.flip(i);
        }

        BitSet bs0 = new BitSet(0);
        assertEqual("Test1: Wrong length", 0, bs0.length());

        bs0.flip(0);
        assertEqual("Test2: Wrong length", 1, bs0.length());

        bs0.flip(63);
        assertEqual("Test3: Wrong length", 64, bs0.length());

        eightbs.flip(7);
        assertTrue("Failed to flip bit 7", !eightbs.get(7));

        // Check to see all other bits are still set
        for (int i = 0; i < 7; i++) {
            assertTrue("Flip flipped incorrect bits", eightbs.get(i));
        }

        eightbs.flip(127);
        assertTrue("Failed to flip bit 127", eightbs.get(127));

        eightbs.flip(127);
        assertTrue("Failed to flip bit 127", !eightbs.get(127));
    }

    
    public void flipII() {
        BitSet bitset = new BitSet();
        for (int i = 0; i < 20; i++) {
            bitset.set(i);
        }
        bitset.flip(10, 10);

        // Test for method void java.util.BitSet.flip(int, int)
        // pos1 and pos2 are in the same bitset element
        BitSet bs = new BitSet(16);
        bs.set(7);
        bs.set(10);
        bs.flip(7, 11);
        for (int i = 0; i < 7; i++) {
            assertTrue("Shouldn't have flipped bit " + i, !bs.get(i));
        }
        assertFalse("Failed to flip bit 7", bs.get(7));
        assertTrue("Failed to flip bit 8", bs.get(8));
        assertTrue("Failed to flip bit 9", bs.get(9));
        assertFalse("Failed to flip bit 10", bs.get(10));
        for (int i = 11; i < bs.size(); i++) {
            assertTrue("Shouldn't have flipped bit " + i, !bs.get(i));
        }

        // pos1 and pos2 is in the same bitset element, boundry testing
        bs = new BitSet(16);
        bs.set(7);
        bs.set(10);
        bs.flip(7, 64);
        for (int i = 0; i < 7; i++) {
            assertTrue("Shouldn't have flipped bit " + i, !bs.get(i));
        }
        assertFalse("Failed to flip bit 7", bs.get(7));
        assertTrue("Failed to flip bit 8", bs.get(8));
        assertTrue("Failed to flip bit 9", bs.get(9));
        assertFalse("Failed to flip bit 10", bs.get(10));
        for (int i = 11; i < 64; i++) {
            assertTrue("failed to flip bit " + i, bs.get(i));
        }
        assertFalse("Shouldn't have flipped bit 64", bs.get(64));

        // more boundary testing
        bs = new BitSet(32);
        bs.flip(0, 64);
        for (int i = 0; i < 64; i++) {
            assertTrue("Failed to flip bit " + i, bs.get(i));
        }
        assertFalse("Shouldn't have flipped bit 64", bs.get(64));

        bs = new BitSet(32);
        bs.flip(0, 65);
        for (int i = 0; i < 65; i++) {
            assertTrue("Failed to flip bit " + i, bs.get(i));
        }
        assertFalse("Shouldn't have flipped bit 65", bs.get(65));

        // pos1 and pos2 are in two sequential bitset elements
        bs = new BitSet(128);
        bs.set(7);
        bs.set(10);
        bs.set(72);
        bs.set(110);
        bs.flip(9, 74);
        for (int i = 0; i < 7; i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }
        assertTrue("Shouldn't have flipped bit 7", bs.get(7));
        assertFalse("Shouldn't have flipped bit 8", bs.get(8));
        assertTrue("Failed to flip bit 9", bs.get(9));
        assertFalse("Failed to flip bit 10", bs.get(10));
        for (int i = 11; i < 72; i++) {
            assertTrue("failed to flip bit " + i, bs.get(i));
        }
        assertFalse("Failed to flip bit 72", bs.get(72));
        assertTrue("Failed to flip bit 73", bs.get(73));
        for (int i = 74; i < 110; i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }
        assertTrue("Shouldn't have flipped bit 110", bs.get(110));
        for (int i = 111; i < bs.size(); i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }

        // pos1 and pos2 are in two non-sequential bitset elements
        bs = new BitSet(256);
        bs.set(7);
        bs.set(10);
        bs.set(72);
        bs.set(110);
        bs.set(181);
        bs.set(220);
        bs.flip(9, 219);
        for (int i = 0; i < 7; i++) {
            assertFalse("Shouldn't have flipped bit " + i, bs.get(i));
        }
        assertTrue("Shouldn't have flipped bit 7", bs.get(7));
        assertFalse("Shouldn't have flipped bit 8", bs.get(8));
        assertTrue("Failed to flip bit 9", bs.get(9));
        assertFalse("Failed to flip bit 10", bs.get(10));
        for (int i = 11; i < 72; i++) {
            assertTrue("failed to flip bit " + i, bs.get(i));
        }
        assertFalse("Failed to flip bit 72", bs.get(72));
        for (int i = 73; i < 110; i++) {
            assertTrue("failed to flip bit " + i, bs.get(i));
        }
        assertFalse("Failed to flip bit 110", bs.get(110));
        for (int i = 111; i < 181; i++) {
            assertTrue("failed to flip bit " + i, bs.get(i));
        }
        assertFalse("Failed to flip bit 181", bs.get(181));
        for (int i = 182; i < 219; i++) {
            assertTrue("failed to flip bit " + i, bs.get(i));
        }
        assertFalse("Shouldn't have flipped bit 219", bs.get(219));
        assertTrue("Shouldn't have flipped bit 220", bs.get(220));
        for (int i = 221; i < bs.size(); i++) {
            assertTrue("Shouldn't have flipped bit " + i, !bs.get(i));
        }
    }

    
    public void setI() {
        // Test for method void java.util.BitSet.set(int)

        BitSet bs = new BitSet();
        bs.set(8);
        assertTrue("Failed to set bit", bs.get(8));

        // Try setting a bit on a 64 boundary
        bs.set(128);
        assertTrue("Failed to set bit", bs.get(128));

        bs = new BitSet(64);
        for (int i = bs.size(); --i >= 0;) {
            bs.set(i);
            assertTrue("Incorrectly set", bs.get(i));
            assertEqual("Incorrect length", i + 1, bs.length());
            for (int j = bs.size(); --j > i;) {
                assertFalse("Incorrectly set bit " + j, bs.get(j));
            }
            int j = i;
            while (--j >= 0) {
                assertFalse("Incorrectly set bit " + j, bs.get(j));
            }
            bs.clear(i);
        }

        bs = new BitSet(0);
        assertEqual("Test1: Wrong length", 0, bs.length());
        bs.set(0);
        assertEqual("Test2: Wrong length", 1, bs.length());
    }

    
    public void setIZ() {
        // Test for method void java.util.BitSet.set(int, boolean)
        eightbs.set(5, false);
        assertFalse("Should have set bit 5 to true", eightbs.get(5));

        eightbs.set(5, true);
        assertTrue("Should have set bit 5 to false", eightbs.get(5));
    }

    
    public void setII() throws IndexOutOfBoundsException {
        BitSet bitset = new BitSet(30);
        bitset.set(29, 29);

        // Test for method void java.util.BitSet.set(int, int)
        // pos1 and pos2 are in the same bitset element
        BitSet bs = new BitSet(16);
        bs.set(5);
        bs.set(15);
        bs.set(7, 11);
        for (int i = 0; i < 7; i++) {
            if (i == 5) {
                assertTrue("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertFalse("Shouldn't have set bit " + i, bs.get(i));
            }
        }
        for (int i = 7; i < 11; i++) {
            assertTrue("Failed to set bit " + i, bs.get(i));
        }
        for (int i = 11; i < bs.size(); i++) {
            if (i == 15) {
                assertTrue("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertFalse("Shouldn't have set bit " + i, bs.get(i));
            }
        }

        // pos1 and pos2 is in the same bitset element, boundry testing
        bs = new BitSet(16);
        bs.set(7, 64);
        for (int i = 0; i < 7; i++) {
            assertFalse("Shouldn't have set bit " + i, bs.get(i));
        }
        for (int i = 7; i < 64; i++) {
            assertTrue("Failed to set bit " + i, bs.get(i));
        }
        assertFalse("Shouldn't have set bit 64", bs.get(64));

        // more boundary testing
        bs = new BitSet(32);
        bs.set(0, 64);
        for (int i = 0; i < 64; i++) {
            assertTrue("Failed to set bit " + i, bs.get(i));
        }
        assertFalse("Shouldn't have set bit 64", bs.get(64));

        bs = new BitSet(32);
        bs.set(0, 65);
        for (int i = 0; i < 65; i++) {
            assertTrue("Failed to set bit " + i, bs.get(i));
        }
        assertFalse("Shouldn't have set bit 65", bs.get(65));

        // pos1 and pos2 are in two sequential bitset elements
        bs = new BitSet(128);
        bs.set(7);
        bs.set(110);
        bs.set(9, 74);
        for (int i = 0; i < 9; i++) {
            if (i == 7) {
                assertTrue("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertFalse("Shouldn't have set bit " + i, bs.get(i));
            }
        }
        for (int i = 9; i < 74; i++) {
            assertTrue("Failed to set bit " + i, bs.get(i));
        }
        for (int i = 74; i < bs.size(); i++) {
            if (i == 110) {
                assertTrue("Shouldn't have flipped bit " + i, bs.get(i));
            } else {
                assertFalse("Shouldn't have set bit " + i, bs.get(i));
            }
        }

        // pos1 and pos2 are in two non-sequential bitset elements
        bs = new BitSet(256);
        bs.set(7);
        bs.set(255);
        bs.set(9, 219);
        for (int i = 0; i < 9; i++) {
            if (i == 7) {
                assertTrue("Shouldn't have set flipped " + i, bs.get(i));
            } else {
                assertFalse("Shouldn't have set bit " + i, bs.get(i));
            }
        }

        for (int i = 9; i < 219; i++) {
            assertTrue("failed to set bit " + i, bs.get(i));
        }

        for (int i = 219; i < 255; i++) {
            assertFalse("Shouldn't have set bit " + i, bs.get(i));
        }

        assertTrue("Shouldn't have flipped bit 255", bs.get(255));

        // test illegal args
        bs = new BitSet(10);

        bs.set(2, 2);
        assertFalse("Bit got set incorrectly ", bs.get(2));
    }

    
    public void setIIZ() {
        // Test for method void java.util.BitSet.set(int, int, boolean)
        eightbs.set(3, 6, false);
        assertTrue("Should have set bits 3, 4, and 5 to false", !eightbs.get(3) && !eightbs.get(4) && !eightbs.get(5));

        eightbs.set(3, 6, true);
        assertTrue("Should have set bits 3, 4, and 5 to true", eightbs.get(3) && eightbs.get(4) && eightbs.get(5));
    }

    
    public void intersects() {
        // Test for method boolean java.util.BitSet.intersects(java.util.BitSet)
        BitSet bs = new BitSet(500);
        bs.set(5);
        bs.set(63);
        bs.set(64);
        bs.set(71, 110);
        bs.set(127, 130);
        bs.set(192);
        bs.set(450);

        BitSet bs2 = new BitSet(8);
        assertFalse("Test1: intersects() returned incorrect value", bs.intersects(bs2));
        assertFalse("Test1: intersects() returned incorrect value", bs2.intersects(bs));

        bs2.set(4);
        assertFalse("Test2: intersects() returned incorrect value", bs.intersects(bs2));
        assertFalse("Test2: intersects() returned incorrect value", bs2.intersects(bs));

        bs2.clear();
        bs2.set(5);
        assertTrue("Test3: intersects() returned incorrect value", bs.intersects(bs2));
        assertTrue("Test3: intersects() returned incorrect value", bs2.intersects(bs));

        bs2.clear();
        bs2.set(63);
        assertTrue("Test4: intersects() returned incorrect value", bs.intersects(bs2));
        assertTrue("Test4: intersects() returned incorrect value", bs2.intersects(bs));

        bs2.clear();
        bs2.set(80);
        assertTrue("Test5: intersects() returned incorrect value", bs.intersects(bs2));
        assertTrue("Test5: intersects() returned incorrect value", bs2.intersects(bs));

        bs2.clear();
        bs2.set(127);
        assertTrue("Test6: intersects() returned incorrect value", bs.intersects(bs2));
        assertTrue("Test6: intersects() returned incorrect value", bs2.intersects(bs));

        bs2.clear();
        bs2.set(192);
        assertTrue("Test7: intersects() returned incorrect value", bs.intersects(bs2));
        assertTrue("Test7: intersects() returned incorrect value", bs2.intersects(bs));

        bs2.clear();
        bs2.set(450);
        assertTrue("Test8: intersects() returned incorrect value", bs.intersects(bs2));
        assertTrue("Test8: intersects() returned incorrect value", bs2.intersects(bs));

        bs2.clear();
        bs2.set(500);
        assertFalse("Test9: intersects() returned incorrect value", bs.intersects(bs2));
        assertFalse("Test9: intersects() returned incorrect value", bs2.intersects(bs));
    }

    
    public void and() {
        // Test for method void java.util.BitSet.and(java.util.BitSet)
        BitSet bs = new BitSet(128);
        // Initialize the bottom half of the BitSet

        for (int i = 64; i < 128; i++) {
            bs.set(i);
        }
        eightbs.and(bs);
        assertFalse("AND failed to clear bits", eightbs.equals(bs));
        eightbs.set(3);
        bs.set(3);
        eightbs.and(bs);
        assertTrue("AND failed to maintain set bits", bs.get(3));
        bs.and(eightbs);
        for (int i = 64; i < 128; i++) {
            assertFalse("Failed to clear extra bits in the receiver BitSet", bs.get(i));
        }
    }

    
    public void andNot() {
        BitSet bs = (BitSet) cloneBitSet(eightbs);
        bs.clear(5);
        BitSet bs2 = new BitSet();
        bs2.set(2);
        bs2.set(3);
        bs.andNot(bs2);
        assertEqual("{0, 1, 4, 6, 7}", bs.toString(), "Incorrect bitset after andNot");

        bs = new BitSet(0);
        bs.andNot(bs2);
        assertEqual("Incorrect size", 0, bs.size());
    }

    
    public void or() {
        // Test for method void java.util.BitSet.or(java.util.BitSet)
        BitSet bs = new BitSet(128);
        bs.or(eightbs);
        for (int i = 0; i < 8; i++) {
            assertTrue("OR failed to set bits", bs.get(i));
        }

        bs = new BitSet(0);
        bs.or(eightbs);
        for (int i = 0; i < 8; i++) {
            assertTrue("OR(0) failed to set bits", bs.get(i));
        }

        eightbs.clear(5);
        bs = new BitSet(128);
        bs.or(eightbs);
        assertFalse("OR set a bit which should be off", bs.get(5));
    }

    
    public void xor() {
        // Test for method void java.util.BitSet.xor(java.util.BitSet)

        BitSet bs = (BitSet) cloneBitSet(eightbs);
        bs.xor(eightbs);
        for (int i = 0; i < 8; i++) {
            assertFalse("XOR failed to clear bits", bs.get(i));
        }

        bs.xor(eightbs);
        for (int i = 0; i < 8; i++) {
            assertTrue("XOR failed to set bits", bs.get(i));
        }

        bs = new BitSet(0);
        bs.xor(eightbs);
        for (int i = 0; i < 8; i++) {
            assertTrue("XOR(0) failed to set bits", bs.get(i));
        }

        bs = new BitSet();
        bs.set(63);
        assertEqual("{63}", bs.toString(), "Test highest bit");
    }

    
    public void toStringComputed() {
        // Test for method java.lang.String java.util.BitSet.toString()
        assertEqual(
                "{0, 1, 2, 3, 4, 5, 6, 7}", eightbs.toString(), "Returned incorrect string representation");
        eightbs.clear(2);
        assertEqual(
                "{0, 1, 3, 4, 5, 6, 7}", eightbs.toString(), "Returned incorrect string representation");
    }

    
    public void length() {
        BitSet bs = new BitSet();
        assertEqual("BitSet returned wrong length", 0, bs.length());
        bs.set(5);
        assertEqual("BitSet returned wrong length", 6, bs.length());
        bs.set(10);
        assertEqual("BitSet returned wrong length", 11, bs.length());
        bs.set(432);
        assertEqual("BitSet returned wrong length", 433, bs.length());
        bs.set(300);
        assertEqual("BitSet returned wrong length", 433, bs.length());
    }

    
    public void nextSetBitI() {
        // Test for method int java.util.BitSet.nextSetBit()
        BitSet bs = new BitSet(500);
        bs.set(5);
        bs.set(32);
        bs.set(63);
        bs.set(64);
        bs.set(71, 110);
        bs.set(127, 130);
        bs.set(193);
        bs.set(450);
        /*try {
            bs.nextSetBit(-1);
            fail("Expected IndexOutOfBoundsException for negative index");
        } catch (IndexOutOfBoundsException e) {
            // correct behavior
        }*/
        assertEqual("nextSetBit() returned the wrong value", 5, bs.nextSetBit(0));
        assertEqual("nextSetBit() returned the wrong value", 5, bs.nextSetBit(5));
        assertEqual("nextSetBit() returned the wrong value", 32, bs.nextSetBit(6));
        assertEqual("nextSetBit() returned the wrong value", 32, bs.nextSetBit(32));
        assertEqual("nextSetBit() returned the wrong value", 63, bs.nextSetBit(33));

        // boundary tests
        assertEqual("nextSetBit() returned the wrong value", 63, bs.nextSetBit(63));
        assertEqual("nextSetBit() returned the wrong value", 64, bs.nextSetBit(64));

        // at bitset element 1
        assertEqual("nextSetBit() returned the wrong value", 71, bs.nextSetBit(65));
        assertEqual("nextSetBit() returned the wrong value", 71, bs.nextSetBit(71));
        assertEqual("nextSetBit() returned the wrong value", 72, bs.nextSetBit(72));
        assertEqual("nextSetBit() returned the wrong value", 127, bs.nextSetBit(110));

        // boundary tests
        assertEqual("nextSetBit() returned the wrong value", 127, bs.nextSetBit(127));
        assertEqual("nextSetBit() returned the wrong value", 128, bs.nextSetBit(128));

        // at bitset element 2
        assertEqual("nextSetBit() returned the wrong value", 193, bs.nextSetBit(130));

        assertEqual("nextSetBit() returned the wrong value", 193, bs.nextSetBit(191));
        assertEqual("nextSetBit() returned the wrong value", 193, bs.nextSetBit(192));
        assertEqual("nextSetBit() returned the wrong value", 193, bs.nextSetBit(193));
        assertEqual("nextSetBit() returned the wrong value", 450, bs.nextSetBit(194));
        assertEqual("nextSetBit() returned the wrong value", 450, bs.nextSetBit(255));
        assertEqual("nextSetBit() returned the wrong value", 450, bs.nextSetBit(256));
        assertEqual("nextSetBit() returned the wrong value", 450, bs.nextSetBit(450));

        assertEqual("nextSetBit() returned the wrong value", -1, bs.nextSetBit(451));
        assertEqual("nextSetBit() returned the wrong value", -1, bs.nextSetBit(511));
        assertEqual("nextSetBit() returned the wrong value", -1, bs.nextSetBit(512));
        assertEqual("nextSetBit() returned the wrong value", -1, bs.nextSetBit(800));
    }

    
    public void nextClearBitI() {
        // Test for method int java.util.BitSet.nextSetBit()
        BitSet bs = new BitSet(500);
        bs.set(0, bs.size() - 1); // ensure all the bits from 0 to bs.size()
        // -1
        bs.set(bs.size() - 1); // are set to true
        bs.clear(5);
        bs.clear(32);
        bs.clear(63);
        bs.clear(64);
        bs.clear(71, 110);
        bs.clear(127, 130);
        bs.clear(193);
        bs.clear(450);
        /*try {
            bs.nextClearBit(-1);
            fail("Expected IndexOutOfBoundsException for negative index");
        } catch (IndexOutOfBoundsException e) {
            // correct behavior
        }*/
        assertEqual("nextClearBit() returned the wrong value", 5, bs.nextClearBit(0));
        assertEqual("nextClearBit() returned the wrong value", 5, bs.nextClearBit(5));
        assertEqual("nextClearBit() returned the wrong value", 32, bs.nextClearBit(6));
        assertEqual("nextClearBit() returned the wrong value", 32, bs.nextClearBit(32));
        assertEqual("nextClearBit() returned the wrong value", 63, bs.nextClearBit(33));

        // boundary tests
        assertEqual("nextClearBit() returned the wrong value", 63, bs.nextClearBit(63));
        assertEqual("nextClearBit() returned the wrong value", 64, bs.nextClearBit(64));

        // at bitset element 1
        assertEqual("nextClearBit() returned the wrong value", 71, bs.nextClearBit(65));
        assertEqual("nextClearBit() returned the wrong value", 71, bs.nextClearBit(71));
        assertEqual("nextClearBit() returned the wrong value", 72, bs.nextClearBit(72));
        assertEqual("nextClearBit() returned the wrong value", 127, bs.nextClearBit(110));

        // boundary tests
        assertEqual("nextClearBit() returned the wrong value", 127, bs.nextClearBit(127));
        assertEqual("nextClearBit() returned the wrong value", 128, bs.nextClearBit(128));

        // at bitset element 2
        assertEqual("nextClearBit() returned the wrong value", 193, bs.nextClearBit(130));
        assertEqual("nextClearBit() returned the wrong value", 193, bs.nextClearBit(191));

        assertEqual("nextClearBit() returned the wrong value", 193, bs.nextClearBit(192));
        assertEqual("nextClearBit() returned the wrong value", 193, bs.nextClearBit(193));
        assertEqual("nextClearBit() returned the wrong value", 450, bs.nextClearBit(194));
        assertEqual("nextClearBit() returned the wrong value", 450, bs.nextClearBit(255));
        assertEqual("nextClearBit() returned the wrong value", 450, bs.nextClearBit(256));
        assertEqual("nextClearBit() returned the wrong value", 450, bs.nextClearBit(450));

        // bitset has 1 still the end of bs.size() -1, but calling nextClearBit
        // with any index value
        // after the last true bit should return bs.size(),
        assertEqual("nextClearBit() returned the wrong value", 512, bs.nextClearBit(451));
        assertEqual("nextClearBit() returned the wrong value", 512, bs.nextClearBit(511));
        assertEqual("nextClearBit() returned the wrong value", 512, bs.nextClearBit(512));

        // if the index is larger than bs.size(), nextClearBit should return
        // index;
        assertEqual("nextClearBit() returned the wrong value", 513, bs.nextClearBit(513));
        assertEqual("nextClearBit() returned the wrong value", 800, bs.nextClearBit(800));
    }

    
    public void isEmpty() {
        BitSet bs = new BitSet(500);
        assertTrue("Test: isEmpty() returned wrong value", bs.isEmpty());

        // at bitset element 0
        bs.set(3);
        assertFalse("Test0: isEmpty() returned wrong value", bs.isEmpty());

        // at bitset element 1
        bs.clear();
        bs.set(12);
        assertFalse("Test1: isEmpty() returned wrong value", bs.isEmpty());

        // at bitset element 2
        bs.clear();
        bs.set(128);
        assertFalse("Test2: isEmpty() returned wrong value", bs.isEmpty());

        // boundary testing
        bs.clear();
        bs.set(459);
        assertFalse("Test3: isEmpty() returned wrong value", bs.isEmpty());

        bs.clear();
        bs.set(511);
        assertFalse("Test4: isEmpty() returned wrong value", bs.isEmpty());
    }

    
    public void cardinality() {
        // test for method int java.util.BitSet.cardinality()
        BitSet bs = new BitSet(500);
        bs.set(5);
        bs.set(32);
        bs.set(63);
        bs.set(64);
        bs.set(71, 110);
        bs.set(127, 130);
        bs.set(193);
        bs.set(450);
        assertEqual("cardinality() returned wrong value", 48, bs.cardinality());

        bs.flip(0, 500);
        assertEqual("cardinality() returned wrong value", 452, bs.cardinality());

        bs.clear();
        assertEqual("cardinality() returned wrong value", 0, bs.cardinality());

        bs.set(0, 500);
        assertEqual("cardinality() returned wrong value", 500, bs.cardinality());
    }

    /*
    public void previousSetBitFound() {
        BitSet bs = new BitSet();
        bs.set(2, 10);
        bs.set(16, 19);
        bs.set(31, 64);
        bs.set(96, 98);
        assertEqual(97, bs.previousSetBit(100));
        assertEqual(97, bs.previousSetBit(97));
        assertEqual(96, bs.previousSetBit(96));
        assertEqual(63, bs.previousSetBit(95));
        assertEqual(63, bs.previousSetBit(63));
        assertEqual(62, bs.previousSetBit(62));
        assertEqual(32, bs.previousSetBit(32));
        assertEqual(31, bs.previousSetBit(31));
        assertEqual(18, bs.previousSetBit(30));
        assertEqual(18, bs.previousSetBit(18));
        assertEqual(17, bs.previousSetBit(17));
        assertEqual(16, bs.previousSetBit(16));
        assertEqual(9, bs.previousSetBit(15));
        assertEqual(9, bs.previousSetBit(9));
        assertEqual(2, bs.previousSetBit(2));
        assertEqual(-1, bs.previousSetBit(1));
        assertEqual(-1, bs.previousSetBit(0));
    }
    */
    /*
    public void previousClearBitFound() {
        BitSet bs = new BitSet();
        bs.set(0, 10);
        bs.set(16, 19);
        bs.set(31, 64);
        bs.set(96, 98);
        assertEqual(100, bs.previousClearBit(100));
        assertEqual(98, bs.previousClearBit(98));
        assertEqual(95, bs.previousClearBit(97));
        assertEqual(95, bs.previousClearBit(95));
        assertEqual(64, bs.previousClearBit(64));
        assertEqual(30, bs.previousClearBit(63));
        assertEqual(30, bs.previousClearBit(32));
        assertEqual(30, bs.previousClearBit(31));
        assertEqual(30, bs.previousClearBit(30));
        assertEqual(29, bs.previousClearBit(29));
        assertEqual(20, bs.previousClearBit(20));
        assertEqual(19, bs.previousClearBit(19));
        assertEqual(15, bs.previousClearBit(17));
        assertEqual(15, bs.previousClearBit(15));
        assertEqual(-1, bs.previousClearBit(9));
        assertEqual(-1, bs.previousClearBit(1));
        assertEqual(-1, bs.previousClearBit(0));
    }
    */
}

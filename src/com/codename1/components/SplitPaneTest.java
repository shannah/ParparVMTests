/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.components;

import com.codename1.testing.AbstractTest;
import com.codename1.ui.Button;

/**
 *
 * @author shannah
 */
public class SplitPaneTest extends AbstractTest {

    @Override
    public String toString() {
        return "SplitPaneTest";
    }

    private void testSetAndGetComponents() throws Exception {
        Button b1 = new Button();
        Button b2 = new Button();
        SplitPane splitPane = new SplitPane(SplitPane.HORIZONTAL_SPLIT, b1, b2, "0", "25%", "100%"); 
        assertEqual(b1, splitPane.getTopOrLeftComponent());
        assertEqual(b1, splitPane.getTop());
        assertEqual(b1, splitPane.getLeft());
        assertEqual(b2, splitPane.getBottomOrRightComponent());
        assertEqual(b2, splitPane.getBottom());
        assertEqual(b2, splitPane.getRight());
      
        Button b3 = new Button();
        splitPane.setTop(b3);
        assertEqual(b3, splitPane.getTop());
        assertEqual(b3, splitPane.getLeft());
        assertEqual(b3, splitPane.getTopOrLeftComponent());
        assertEqual(b2, splitPane.getBottom());
        
        Button b4 = new Button();
        splitPane.setBottom(b4);
        assertEqual(b4, splitPane.getBottom());
        assertEqual(b4, splitPane.getBottomOrRightComponent());
        assertEqual(b4, splitPane.getRight());
        
    }
    
    
    @Override
    public boolean runTest() throws Exception {
        testSetAndGetComponents();
        return true;
    }
    
}

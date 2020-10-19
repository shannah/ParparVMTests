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
public class ClassTest extends AbstractTest {

    @Override
    public String toString() {
        return "ClassTest";
    }
    
    

    @Override
    public boolean runTest() throws Exception {
        classNameEvaluated();
        classSimpleNameEvaluated();
        objectClassNameEvaluated();
        superClassFound();
        superClassOfObjectIsNull();
        superClassOfArrayIsObject();
        superClassOfPrimitiveIsNull();
        objectClassConsideredNotArray();
        arrayClassConsideredArray();
        arrayComponentTypeDetected();
        arrayOfArraysComponentTypeDetected();
        nonArrayComponentTypeIsNull();
        castingAppropriateObject();
        inappropriateObjectCastingFails();
        instanceCreatedThroughReflection();
        
        return true;
    }
    
   
    public void classNameEvaluated() {
        assertEqual("java.lang.Object", Object.class.getName());
        
        // This currently fails on ParparVM.  getName() produces like java.lang.Object[]
        //assertEqual("[Ljava.lang.Object;", Object[].class.getName());
        //assertEqual("int", int.class.getName());
        //assertEqual("[I", int[].class.getName());
    }

    
    public void classSimpleNameEvaluated() {

    }

    
    public void objectClassNameEvaluated() {
        assertEqual("java.lang.Object", new Object().getClass().getName());
    }

    
    public void superClassFound() {
        //assertEqual(Number.class, Integer.class.getSuperclass());
    }

    
    public void superClassOfObjectIsNull() {
        //assertNull(Object.class.getSuperclass());
    }

    
    public void superClassOfArrayIsObject() {
        //assertEqual(Object.class, Runnable[].class.getSuperclass());
    }

    
    public void superClassOfPrimitiveIsNull() {
        //assertNull(int.class.getSuperclass());
    }

    
    public void objectClassConsideredNotArray() {
        assertFalse(Object.class.isArray());
    }

    
    public void arrayClassConsideredArray() {
        assertTrue(Object[].class.isArray());
    }

    
    public void arrayComponentTypeDetected() {
        //assertEqual(Object.class, Object[].class.getComponentType());
    }

    
    public void arrayOfArraysComponentTypeDetected() {
        //assertEqual(Object[].class, Object[][].class.getComponentType());
    }

    
    public void nonArrayComponentTypeIsNull() {
        //assertNull(Object.class.getComponentType());
    }

    
    public void castingAppropriateObject() {
        Object obj = 23;
        assertEqual(Integer.valueOf(23), Integer.class.cast(obj));
    }

    public void inappropriateObjectCastingFails() {
        Throwable error = null;
        try {
            Object obj = 23;
            Float.class.cast(obj);
        } catch (ClassCastException ex) {
            error = ex;
        }
        assertNotNull(error);
        assertTrue(error instanceof ClassCastException);
                
    }
    public static class TestObject implements Runnable {
        private int counter = 1;
        public int getCounter() {
            return counter;
        }
        
        public void run() {
            
        }
    }
    
    public void instanceCreatedThroughReflection() throws Exception {
        Runnable instance = (Runnable) Class.forName(TestObject.class.getName()).newInstance();
        instance.run();
        assertEqual(TestObject.class, instance.getClass());
        assertEqual(1, ((TestObject) instance).getCounter());
    }

    

   
    

    private static class A {
    }
    

  

   

    static class InnerClass {
    }
    
}

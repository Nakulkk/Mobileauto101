package com.ccst.automation.framework;

import org.testng.annotations.Test;

public class SimpleTest {

    @Test(priority = -1)
    public void testMethod1() {
        System.out.println("This is a simple test method1.");
    }

    @Test(priority = 1, dependsOnMethods = "testMethod1")
    public void testMethodd2() {
        System.out.println("This is a simple test method2.");
    }

    @Test(priority = 3, enabled = false)
    public void tesMethod3() {
        System.out.println("This is a simple test method3.");
    }

    @Test(priority = 2, expectedExceptions = NegativeArraySizeException.class)
    public void testMthodd4() {
        System.out.println("This is a simple test method4.");
        int[] arr = new int[-1];
    }
}
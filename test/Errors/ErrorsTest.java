/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Errors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guillaume
 */
public class ErrorsTest {
    
    ErrorsTest errorTest;
    
    public ErrorsTest() {
    }
    
    @Before
    public void setUp() {
        errorTest = new ErrorsTest();
    }
    
    @After
    public void tearDown() {
        errorTest = null;
    }

    /**
     * Test of errorsMessages method, of class Errors.
     */
    public void testErrorsMessageslackBox() {
        System.out.println("ErrorsMessageslackBox");
        
        Object valueTested = null;
        boolean expResult = false;
        boolean result = Errors.errorsMessages(valueTested);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of errorDate method, of class Errors.
     */
    public void testErrorDate() {
        System.out.println("errorDate");
        String dateTested = "";
        boolean expResult = false;
        boolean result = Errors.errorDate(dateTested);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of errorsOs method, of class Errors.
     */
    public void testErrorsOs() {
        System.out.println("errorsOs");
        Errors.errorsOs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void test(){
        testErrorsMessageslackBox();
    }
    
}

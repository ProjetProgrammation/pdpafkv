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
    public void testErrorsMessagesBlackBox() {
        System.out.println("ErrorsMessagesBlackBox");
        boolean expResult;
        Object valueTested;
        boolean result;

        valueTested = null;
        expResult = false;
        result = Errors.errorsMessages(valueTested);
        assertEquals("Error in ErrorsMessagesBlackBox", expResult, result);

        valueTested = "Guillaume";
        expResult = true;
        result = Errors.errorsMessages(valueTested);
        assertEquals("Error in ErrorsMessagesBlackBox", expResult, result);

        valueTested = "150";
        expResult = false;
        result = Errors.errorsMessages(valueTested);
        assertEquals("Error in ErrorsMessagesBlackBox", expResult, result);

        valueTested = "guillaume2-ver";
        expResult = false;
        result = Errors.errorsMessages(valueTested);
        assertEquals("Error in ErrorsMessagesBlackBox", expResult, result);

        valueTested = 22;
        expResult = true;
        result = Errors.errorsMessages(valueTested);
        assertEquals("Error in ErrorsMessagesBlackBox", expResult, result);

        valueTested = -1;
        expResult = false;
        result = Errors.errorsMessages(valueTested);
        assertEquals("Error in ErrorsMessagesBlackBox", expResult, result);

        valueTested = 150;
        expResult = false;
        result = Errors.errorsMessages(valueTested);
        assertEquals("Error in ErrorsMessagesBlackBox", expResult, result);

    }

    /**
     * Test of errorDate method, of class Errors.
     */
    public void testErrorDateBlackBox() {
        System.out.println("ErrorDateBlackBox");
        String dateTested = "";
        boolean expResult;
        boolean result;

        dateTested = "12-12-1992";
        expResult = false;
        result = Errors.errorDate(dateTested);
        assertEquals(expResult, result);

        dateTested = "1992/12/12";
        expResult = false;
        result = Errors.errorDate(dateTested);
        assertEquals(expResult, result);

        dateTested = "12/23/1992";
        expResult = false;
        result = Errors.errorDate(dateTested);
        assertEquals(expResult, result);

        dateTested = "12/12";
        expResult = false;
        result = Errors.errorDate(dateTested);
        assertEquals(expResult, result);

        dateTested = "12";
        expResult = false;
        result = Errors.errorDate(dateTested);
        assertEquals(expResult, result);

        dateTested = "date";
        expResult = false;
        result = Errors.errorDate(dateTested);
        assertEquals(expResult, result);

        dateTested = "12/12/1992";
        expResult = true;
        result = Errors.errorDate(dateTested);
        assertEquals(expResult, result);
    }

    @Test
    public void test() {
        testErrorsMessagesBlackBox();
        testErrorDateBlackBox();
    }

}

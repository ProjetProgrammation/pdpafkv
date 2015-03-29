/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDD;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guillaume21
 */
public class DataBaseTest {
    
    private DataBase dataBaseTest;
    
    public DataBaseTest() {
        
    }
    
    @Before
    public void setUp() {
        dataBaseTest = new DataBase();
    }
    
    @After
    public void tearDown() {
        dataBaseTest = null;
    }
    
   @Test
    public void testConnection(){
      assertTrue( "La connection à échoué", dataBaseTest.connexion() != null);
    }
    
}

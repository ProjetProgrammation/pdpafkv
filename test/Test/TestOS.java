/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author alexandre
 */
public class TestOS {
    
    public static void main(String[] args){
        String name = System.getProperty ( "os.name" );
        String version = System.getProperty ( "os.version" );
        System.out.println("Name : " + name);
        System.out.println("Version : " + version);
    }
    
}

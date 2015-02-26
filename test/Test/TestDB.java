/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import BDD.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Thibaut
 */
public class TestDB {
    
    DataBase db;
    
    public TestDB(){
            Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM Audio;" );
                while ( rs.next() ) {
                   int id = rs.getInt("id");
                   String  name = rs.getString("name");
                   String  path = rs.getString("file_path");
                   String  language = rs.getString("language");
                   String  format = rs.getString("format");
                   System.out.println( "ID = " + id );
                   System.out.println( "NAME = " + name );
                   System.out.println( "path = " + path );
                   System.out.println( "language = " + language );
                   System.out.println( "format = " + format );
                   System.out.println();
                }
              rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }        
    }
          
    
    
}

/*
 * Cette classe sert à tester la classe DataBase
 */

//package BDD;
import java.sql.*;

/**
 *
 * @author akervadec
 */
public class Main {

	public static void main(String[] args) {
		DataBase db = new DataBase();
		db.createTables();
	}
}

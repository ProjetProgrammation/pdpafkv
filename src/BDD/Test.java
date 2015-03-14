/*
 * Cette classe sert à tester la classe DataBase
 * Attention à bien ajouter les langues en premier
 */

package BDD;
import java.sql.*;

/**
 *
 * @author akervadec
 */
public class Test {

	public static void main(String[] args) {
		DataBase db = new DataBase();
		db.createTables();

	}
}

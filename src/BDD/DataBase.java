/*
 * Cette classe sert à créer la base de données et intéragir avec cette dernière.
 */

package BDD;
import java.sql.*;


/**
 *
 * @author akervadec
 */
public class DataBase {

	public DataBase(){
		this.connexion();
	}


	/**
	* Etabli une connection avec la base de données dataBase.db
	*/
	public void connexion(){
	    Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}

	/**
	* Exécute les requêtes SQL permettant de créer les tables dans la base de données dataBase.db
	*/
	public void createTables(){
            Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE Question ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
					"content VARCHAR(255) NOT NULL,"+
					"id_video INTEGER NOT NULL,"+
					"id_audio INTEGER NOT NULL);"+
				"CREATE TABLE Video ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
					"name VARCHAR(50) NOT NULL,"+
					"file_path VARCHAR(255) NOT NULL,"+
					"language VARCHAR(25) NOT NULL,"+
					"format VARCHAR(25));"+
				"CREATE TABLE Audio ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
					"name VARCHAR(50) NOT NULL,"+
					"file_path VARCHAR(255) NOT NULL,"+
					"language VARCHAR(25) NOT NULL,"+
					"format VARCHAR(25));"+
				"CREATE TABLE Language ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
					"name VARCHAR(25));";
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Tables created successfully");
	}

	/**
	* Ajoute une vidéo dans la base de données dataBase.db puis créé un objet correspondant
	* @param name Le nom de la vidéo à ajouter
	* @param file_path Le chemin sur le disque de la vidéo à ajouter
	* @param language La langue de la vidéo
	* @param format Le format de la vidéo
	*/
	public void addVideo(String name, String filePath, String format, Language language){
		Connection c = null;
	    PreparedStatement stmtLang = null;
	    PreparedStatement stmtAdd = null;
	    int idLang;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
	    	c.setAutoCommit(false);	//Mise en place de la transaction manuelle
	    	System.out.println("Opened database successfully");

	    	//Recherche de l'id de la langue de la vidéo

	    	stmtLang = c.prepareStatement("SELECT name FROM Language WHERE Language.name=?;");
	    	stmtLang.setString(1,language.getName());
	    	ResultSet rs = stmtLang.executeQuery();
			while ( rs.next() ) {
				idLang = rs.getInt("id");
			}
			idLang = rs.getInt("id");
			rs.close();
			stmtLang.close();

			//Ajout de la vidéo

	    	stmtAdd = c.prepareStatement("INSERT INTO Video(name,filePath,language,format) VALUES (?,?,?,?);");
	    	stmtAdd.setString(1,name);
	    	stmtAdd.setString(2,filePath);
	    	stmtAdd.setInt(3,idLang);
	    	stmtAdd.setString(4,format);
	    	stmtAdd.executeQuery();

	    	stmtAdd.close();
	    	c.commit();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records created successfully");
	}


	/**
	*	Ajout d'un fichier audio dans la base de données dataBase.db
	*	@param name Le nom du fichier
	*	@param filePath Le chemin du fichier
	*	@param format Le format du fichier
	*	@param laguage La langue du fichier
	*/
	public void addAudio(String name, String filePath, String format, Language language){
		Connection c = null;
	    PreparedStatement stmtLang = null;
	    PreparedStatement stmtAdd = null;
	    int idLang;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
	    	c.setAutoCommit(false);	//Mise en place de la transaction manuelle
	    	System.out.println("Opened database successfully");

	    	//Recherche de l'id de la langue de l'audio

	    	stmtLang = c.prepareStatement("SELECT name FROM Language WHERE Language.name=?;");
	    	stmtLang.setString(1,language.getName());
	    	ResultSet rs = stmtLang.executeQuery();
			while ( rs.next() ) {
				idLang = rs.getInt("id");
			}
			idLang = rs.getInt("id");
			rs.close();
			stmtLang.close();

			//Ajout de l'audio

	    	stmtAdd = c.prepareStatement("INSERT INTO Audio(name,filePath,language,format) VALUES (?,?,?,?);");
	    	stmtAdd.setString(1,name);
	    	stmtAdd.setString(2,filePath);
	    	stmtAdd.setInt(3,idLang);
	    	stmtAdd.setString(4,format);
	    	stmtAdd.executeQuery();

	    	stmtAdd.close();
	    	c.commit();
	    	c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records created successfully");
	}

	/**
	*	Ajout d'une question dans la base de données dataBase.db, avec les objets Video et Audio connus et créés
	*	@param content La question (son contenu)
	*	@param video La vidéo correspondante à la réponse attendue
	*	@param audio L'audio correspondant à la réponse attendue
	*/
	public void addQuestion(String content, Video video, Audio audio){
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);	//Mise en place de la transaction manuelle
			System.out.println("Opened database successfully");

			stmt = c.prepareStatement("INSERT INTO Question (content,id_video,id_audio) VALUES (?,?,?);");
			stmt.setString(1, content);
			stmt.setInt(2, video.getId());
			stmt.setInt(3, audio.getId());
			stmt.executeQuery();

			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	/**
	*	Ajout d'un langage dans la base de données dataBase.db
	*	@param name La langue en format String
	*/
	public void addLanguage(String name){}

	public Video manageVideo(){
		Connection c = null;
		Statement stmt = null;
		Video result;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Video ORDER BY random() LIMIT 1;" );
			result = new Video(rs.getInt("id"),rs.getString("name"),rs.getString("filePath"),rs.getString("format"),rs.getInt("idLanguage"));
			rs.close();
			stmt.close();
			c.close();
			return(result);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return(null);
	}

	public Audio manageAudio(){
		Connection c = null;
		Statement stmt = null;
		Audio result;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Audio ORDER BY random() LIMIT 1;" );
			result = new Audio(rs.getInt("id"),rs.getString("name"),rs.getString("filePath"),rs.getString("format"),rs.getInt("idLanguage"));
			rs.close();
			stmt.close();
			c.close();
			return(result);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return(null);
	}
/*
	public Question manageQuestion(){
		Connection c = null;
		Statement stmt = null;
		Question result;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Question ORDER BY random() LIMIT 1;" );
			result = new Question(rs.getInt("id"),rs.getString("name"),rs.getString("filePath"),rs.getString("format"),rs.getInt("idLanguage"));
			rs.close();
			stmt.close();
			c.close();
			return(result);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return(null);
	}
	*/
}

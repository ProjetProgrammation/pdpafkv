/*
 * Cette classe sert à créer la base de données et intéragir avec cette dernière.
 */

/***********************************************


ATTENTION : LA NOMENCLATURE N'EST PAS LA MÊME EN BDD ET EN Java !
BDD : file_path
Java : filePath


***********************************************/



package BDD;
import java.sql.*;
import java.util.ArrayList;


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
			System.out.println("[CreateTables]Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Question ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
					"content VARCHAR(255) UNIQUE NOT NULL,"+
					"id_video INTEGER NOT NULL,"+
					"id_audio INTEGER NOT NULL,"+
					"id_language INTEGER NOT NULL);"+
				"CREATE TABLE IF NOT EXISTS Video ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
					"name VARCHAR(50) UNIQUE NOT NULL,"+
					"file_path VARCHAR(255) NOT NULL,"+
					"id_language INTEGER NOT NULL,"+
					"format VARCHAR(25));"+
				"CREATE TABLE IF NOT EXISTS Audio ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
					"name VARCHAR(50) UNIQUE NOT NULL,"+
					"file_path VARCHAR(255) NOT NULL,"+
					"id_language INTEGER NOT NULL,"+
					"format VARCHAR(25));"+
				"CREATE TABLE IF NOT EXISTS Language ("+
					"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
					"name VARCHAR(25) UNIQUE);";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("[CreateTables]Tables created successfully");
	}

	/**
	* Exécute les requêtes SQL permettant de créer les triggers pour la gestion de l'unicité des tuples dans la base de données dataBase.db
	*/
/*	public void createTriggers(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			System.out.println("[CreateTriggers]Opened database successfully");

			stmt = c.createStatement();
			String sql = "
				CREATE TRIGGER unicityLanguageTrigger IF NOT EXISTS
				BEFORE INSERT OR UPDATE ON Language
				FOR EACH ROW
				BEGIN
					;";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("[CreateTables]Tables created successfully");
	}*/

	/**
	* Ajoute une vidéo dans la base de données dataBase.db puis créé un objet correspondant
	* @param name Le nom de la vidéo à ajouter
	* @param file_path Le chemin sur le disque de la vidéo à ajouter
	* @param language La langue de la vidéo
	* @param format Le format de la vidéo
	*/
	public void addVideo(String name, String filePath, String format, String nameLanguage){
		Connection c = null;
		PreparedStatement stmtLang = null;
		PreparedStatement stmtAdd = null;
		int idLang=0;
		if(this.searchVideoByNameFormat(name, format).getId() != 0){
			System.out.println("[addVideo]This video already exists.");
		}
		else{
			String query = new String("SELECT id FROM Language WHERE Language.name=?;");
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
		    	c.setAutoCommit(false);	//Mise en place de la transaction manuelle
		    	System.out.println("[addVideo]Opened database successfully");

		    	//Recherche de l'id de la langue de la vidéo

		    	stmtLang = c.prepareStatement(query);
		    	stmtLang.setString(1,nameLanguage);	//Ajout des paramètres (variables) "?" de la ligne d'avant.
		    	ResultSet rs = stmtLang.executeQuery();
		    	while(rs.next()){
		    		idLang = rs.getInt("id");
		    	}

				//Ajout de la vidéo

		    	query = "INSERT INTO Video(name,file_path,id_language,format) VALUES (?,?,?,?);";
		    	stmtAdd = c.prepareStatement(query);
		    	//Paramétrage des variables de requête
		    	stmtAdd.setString(1,name);
		    	stmtAdd.setString(2,filePath);
		    	stmtAdd.setInt(3,idLang);
		    	stmtAdd.setString(4,format);
		    	stmtAdd.executeUpdate();

		    	rs.close();
		    	stmtLang.close();
		    	stmtAdd.close();
		    	c.commit();
		    	c.close();
		    } catch ( Exception e ) {
		    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    	System.exit(0);
		    }
		    System.out.println("[addVideo]The video "+ name +"."+ format +" successfuly added to the DB");
		}
	}


	/**
	*	Ajout d'un fichier audio dans la base de données dataBase.db
	*	@param name Le nom du fichier
	*	@param filePath Le chemin du fichier
	*	@param format Le format du fichier
	*	@param laguage La langue du fichier
	*/
	public void addAudio(String name, String filePath, String format, String nameLanguage){
		Connection c = null;
		PreparedStatement stmtLang = null;
		PreparedStatement stmtAdd = null;
		int idLang=0;
		if(this.searchAudioByNameFormat(name, format).getId() != 0){
			System.out.println("[addAudio]This audio already exists.");
		}
		else{
			String query = new String("SELECT id FROM Language WHERE Language.name=?;");
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
		    	c.setAutoCommit(false);	//Mise en place de la transaction manuelle
		    	System.out.println("[addAudio]Opened database successfully");

		    	//Recherche de l'id de la langue de la vidéo

		    	stmtLang = c.prepareStatement(query);
		    	stmtLang.setString(1,nameLanguage);	//Ajout des paramètres (variables) "?" de la ligne d'avant.
		    	ResultSet rs = stmtLang.executeQuery();
		    	while(rs.next()){
		    		idLang = rs.getInt("id");
		    	}

				//Ajout de la vidéo

		    	query = "INSERT INTO Audio(name,file_path,id_language,format) VALUES (?,?,?,?);";
		    	stmtAdd = c.prepareStatement(query);
		    	//Paramétrage des variables de requête
		    	stmtAdd.setString(1,name);
		    	stmtAdd.setString(2,filePath);
		    	stmtAdd.setInt(3,idLang);
		    	stmtAdd.setString(4,format);
		    	stmtAdd.executeUpdate();

		    	rs.close();
		    	stmtLang.close();
		    	stmtAdd.close();
		    	c.commit();
		    	c.close();
		    } catch ( Exception e ) {
		    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    	System.exit(0);
		    }
		    System.out.println("[addAudio]The audio "+ name +"."+ format +" successfuly added to the DB");
		}
	}

	/**
	*	Ajout d'une question dans la base de données dataBase.db, avec les objets Video et Audio connus et créés
	*	@param content La question (son contenu)
	*	@param video La vidéo correspondante à la réponse attendue
	*	@param audio L'audio correspondant à la réponse attendue
	*/
	public void addQuestion(String content, Video video, Audio audio, String nameLanguage){
		Connection c = null;
		PreparedStatement stmtLang = null;
		PreparedStatement stmtAdd = null;
		int idLang=0;
		String query = new String("SELECT id FROM Language WHERE Language.name=?;");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);	//Mise en place de la transaction manuelle
			System.out.println("[addQuestion]Opened database successfully");

			stmtLang = c.prepareStatement(query);
	    	stmtLang.setString(1,nameLanguage);	//Ajout des paramètres (variables) "?" de la ligne d'avant.
	    	ResultSet rs = stmtLang.executeQuery();
	    	while(rs.next()){
	    		idLang = rs.getInt("id");
	    	}

	    	query = "INSERT INTO Question (content,id_video,id_audio,id_language) VALUES (?,?,?,?);";
	    	stmtAdd = c.prepareStatement(query);
	    	stmtAdd.setString(1, content);
	    	stmtAdd.setInt(2, video.getId());
	    	stmtAdd.setInt(3, audio.getId());
	    	stmtAdd.setInt(4, idLang);
	    	stmtAdd.executeUpdate();

	    	stmtLang.close();
	    	stmtAdd.close();
	    	c.commit();
	    	c.close();
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    System.out.println("[addQuestion]The question \"" + content + "\" successfuly added.");
	}

	/**
	*	Ajout d'un langage dans la base de données dataBase.db
	*	@param name La langue en format String
	*/
	public void addLanguage(String name){
		Connection c = null;
		PreparedStatement stmt = null;
		if(this.searchLanguageByName(name).getId() != 0){
			System.out.println("[addLanguage]This language already exists.");
		}
		else{
			String query = new String("INSERT INTO Language (name) VALUES (?);");
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
				c.setAutoCommit(false);	//Mise en place de la transaction manuelle
				System.out.println("[addLanguage]Opened database successfully");

				stmt = c.prepareStatement(query);
				stmt.setString(1, name);
				stmt.executeUpdate();

				stmt.close();
				c.commit();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("[addLanguage]The language " + name + " successfuly added.");
		}
	}

	/**
	*	Sélection au hasard d'une vidéo dans la base de données en fonction d'une langue voulue
	*	@param Language La langue de la vidéo souhaitée
	*	@return Une instance de la vidéo choisie au hasard dans la base de données
	*/
	public Video manageVideo(Language language){
		Connection c = null;
		PreparedStatement stmt = null;
		Video result = new Video();
		int idLanguage = language.getId();
		String query = new String("SELECT * FROM Video WHERE id_language=? ORDER BY random() LIMIT 1;");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);
			System.out.println("[manageVideo]Opened database successfully");

			stmt = c.prepareStatement(query);
			stmt.setInt(1,idLanguage);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				result = new Video(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("file_path"),
					rs.getString("format"),
					rs.getInt("id_language"));
			}
			rs.close();
			stmt.close();
			c.close();
			return(result);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("[manageVideo]Error");
		return(null);
	}

	/**
	*	Sélection au hasard d'un audio dans la base de données en fonction d'une langue voulue
	*	@param Language La langue de l'audio souhaité
	*	@return Une instance de l'audio choisi au hasard dans la base de données
	*/
	public Audio manageAudio(Language language){
		Connection c = null;
		PreparedStatement stmt = null;
		Audio result = new Audio();
		//int idLanguage = language.getId();
		String query = new String("SELECT * FROM Audio WHERE id_language=1 ORDER BY random() LIMIT 1;");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);
			System.out.println("[manageAudio]Opened database successfully");

			stmt = c.prepareStatement(query);
			//stmt.setInt(1,idLanguage);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				result = new Audio(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("file_path"),
					rs.getString("format"),
					rs.getInt("id_language"));
			}
			rs.close();
			stmt.close();
			c.close();
			return(result);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("[manageAudio]Error");
		return(null);
	}

	/**
	*	Sélection au hasard d'une question dans la base de données en fonction d'une langue voulue
	*	@param Language La langue de la question souhaitée
	*	@return Une instance de la question choisie au hasard dans la base de données
	*/
	public Question manageQuestion(Language language){
		Connection c = null;
		PreparedStatement stmt = null;
		Question result = new Question();
		int idLanguage = language.getId();
		String query = new String("SELECT * FROM Question WHERE id_language=? ORDER BY random() LIMIT 1;");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);
			System.out.println("[manageQuestion]Opened database successfully");

			stmt = c.prepareStatement(query);
			stmt.setInt(1,idLanguage);
			ResultSet rs = stmt.executeQuery();
			result = new Question(
				rs.getInt("id"),
				rs.getString("content"),
				rs.getInt("id_video"),
				rs.getInt("id_audio"),
				rs.getInt("id_language"));
			rs.close();
			stmt.close();
			c.close();
			return(result);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("[manageQuestion]Error");
		return(null);
	}

	/**
	*	Recherche d'un langage dans la base de données avec son nom.
	*	La langue DOIT exister.
	*
	*	@param name
	*				Le nom de la langue recherchée
	*	@return Une instance de la langue recherchée dans la base de données
	*/
	public Language searchLanguageByName(String name){
		Connection c = null;
		PreparedStatement stmt = null;
		Language language = new Language();
		String query = new String("SELECT * FROM Language WHERE Language.name=?;");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);	//Mise en place de la transaction manuelle
			System.out.println("[searchLanguageByName]Opened database successfully");

			stmt = c.prepareStatement(query);
	    	stmt.setString(1,name);	//Ajout des paramètres (variables) "?" de la ligne d'avant.
	    	ResultSet rs = stmt.executeQuery();
	    	while(rs.next()){
	    		language.setId(rs.getInt("id"));
	    		language.setName(rs.getString("name"));
	    	}
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    	return(language);
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    System.out.println("[searchLanguageByName]Error");
	    return(null);
	}

	/**
	*	Recherche d'une vidéo dans la base de données avec son nom et son extension.
	*	La vidéo DOIT exister.
	*
	*	@param name
	*				Le nom de la vidéo recherchée
	*	@param format
	*				Le format de la vidéo recherchée
	*	@return Une instance de la vidéo recherchée dans la base de données
	*/
	public Video searchVideoByNameFormat(String name, String format){
		Connection c = null;
		PreparedStatement stmt = null;
		Video video = new Video();
		String query = new String("SELECT * FROM Video WHERE Video.name=? AND Video.format=?;");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);	//Mise en place de la transaction manuelle
			System.out.println("[searchVideoByNameFormat]Opened database successfully");

			stmt = c.prepareStatement(query);
	    	stmt.setString(1,name);	//Ajout des paramètres (variables) "?" de la ligne d'avant.
	    	stmt.setString(2,format);
	    	ResultSet rs = stmt.executeQuery();
	    	while(rs.next()){
	    		video.setId(rs.getInt("id"));
	    		video.setName(rs.getString("name"));
	    		video.setFormat(rs.getString("format"));
	    		video.setFilePath(rs.getString("file_path"));
	    		video.setIdLanguage(rs.getInt("id_language"));
	    	}
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    	return(video);
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    System.out.println("[searchVideoByNameFormat]Error");
	    return(null);
	}

	/**
	*	Recherche d'un audio dans la base de données avec son nom et son extension.
	*	L'audio DOIT exister.
	*
	*	@param name
	*				Le nom de l'audio recherché
	*	@param format
	*				Le format de l'audio recherché
	*	@return Une instance de l'audio recherché dans la base de données
	*/
	public Audio searchAudioByNameFormat(String name, String format){
		Connection c = null;
		PreparedStatement stmt = null;
		Audio audio = new Audio();
		String query = new String("SELECT * FROM Audio WHERE Audio.name=? AND Audio.format=?;");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);	//Mise en place de la transaction manuelle
                System.out.println("[searchAudioByNameFormat]Opened database successfully");

                stmt = c.prepareStatement(query);
	    	stmt.setString(1,name);	//Ajout des paramètres (variables) "?" de la ligne d'avant.
	    	stmt.setString(2,format);
	    	ResultSet rs = stmt.executeQuery();
	    	while(rs.next()){
	    		audio.setId(rs.getInt("id"));
	    		audio.setName(rs.getString("name"));
	    		audio.setFormat(rs.getString("format"));
	    		audio.setFilePath(rs.getString("file_path"));
	    		audio.setIdLanguage(rs.getInt("id_language"));
	    	}
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    	return(audio);
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    System.out.println("[searchAudioByNameFormat]Error");
	    return(null);
	}

	/**
	*	Recherche d'une question dans la base de données avec son content, l'id de sa vidéo, l'id de son audio et l'id de sa langue.
	*	La question DOIT exister.
	*
	*	@param name
	*				Le nom de la vidéo recherchée
	*	@param format
	*				Le format de la vidéo recherchée
	*	@return Une instance de la vidéo recherchée dans la base de données
	*/
	/*public Video searchVideoByNameFormat(String name, String format){
		Connection c = null;
		PreparedStatement stmt = null;
		Video video = new Video();
		String query = new String("SELECT * FROM Video WHERE Video.name=? AND Video.format=?;");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
			c.setAutoCommit(false);	//Mise en place de la transaction manuelle
			System.out.println("[searchVideoByNameFormat]Opened database successfully");

			stmt = c.prepareStatement(query);
	    	stmt.setString(1,name);	//Ajout des paramètres (variables) "?" de la ligne d'avant.
	    	stmt.setString(2,format);
	    	ResultSet rs = stmt.executeQuery();
	    	while(rs.next()){
	    		video.setId(rs.getInt("id"));
	    		video.setName(rs.getString("name"));
	    		video.setFormat(rs.getString("format"));
	    		video.setFilePath(rs.getString("file_path"));
	    		video.setIdLanguage(rs.getInt("id_language"));
	    	}
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    	return(video);
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    System.out.println("[searchVideoByNameFormat]Error");
	    return(null);
	}*/

        /**
         * Cette méthode retourne l'ensemble des questions contenues dans la base de données.
         * @return La liste de toutes les question dans un ArrayList\<Question\>
         */
        public ArrayList<Question> getAllQuestions(){
        	Connection c = null;
        	PreparedStatement stmt = null;
        	ArrayList<Question> result;
        	result = new ArrayList<>();
        	Question tempQuestion = new Question();
        	String query = new String("SELECT * FROM Question;");
        	try {
        		Class.forName("org.sqlite.JDBC");
        		c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);	//Mise en place de la transaction manuelle
                System.out.println("[getAllQuestions]Opened database successfully");

                stmt = c.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                	tempQuestion = new Question();
                	tempQuestion.setId(rs.getInt("id"));
                	tempQuestion.setContent(rs.getString("content"));
                	tempQuestion.setIdVideo(rs.getInt("id_video"));
                	tempQuestion.setIdAudio(rs.getInt("id_audio"));
                	tempQuestion.setIdLanguage(rs.getInt("id_language"));
                    //Transfert de la tempQuestion dans la ArrayList
                	result.add(tempQuestion);
                }
                rs.close();
                stmt.close();
                c.close();
                return(result);
            } catch ( Exception e ) {
            	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
            System.out.println("[getAllQuestions]Error");
            return(null);
        }
        
        /**
         * Cette méthode retourne l'ensemble des vidéos contenues dans la base de données.
         * @return La liste de toutes les vidéos dans un ArrayList\<Video\>
         */
        public ArrayList<Video> getAllVideos(){
        	Connection c = null;
        	PreparedStatement stmt = null;
        	ArrayList<Video> result;
        	result = new ArrayList<>();
        	Video tempVideo = new Video();
        	String query = new String("SELECT * FROM Video;");
        	try {
        		Class.forName("org.sqlite.JDBC");
        		c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);	//Mise en place de la transaction manuelle
                System.out.println("[getAllVideo]Opened database successfully");

                stmt = c.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                	tempVideo = new Video();
                	tempVideo.setId(rs.getInt("id"));
                	tempVideo.setName(rs.getString("name"));
                	tempVideo.setFilePath(rs.getString("file_path"));
                	tempVideo.setIdLanguage(rs.getInt("id_language"));
                	tempVideo.setFormat(rs.getString("format"));
                    //Transfert de la tempVideo dans la ArrayList
                	result.add(tempVideo);
                }
                rs.close();
                stmt.close();
                c.close();
                return(result);
            } catch ( Exception e ) {
            	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
            System.out.println("[getAllVideos]Error");
            return(null);
        }
        
        /**
         * Cette méthode retourne l'ensemble des audios contenus dans la base de données.
         * @return La liste de toutes les audios dans un ArrayList\<Audio\>
         */
        public ArrayList<Audio> getAllAudios(){
        	Connection c = null;
        	PreparedStatement stmt = null;
        	ArrayList<Audio> result;
        	result = new ArrayList<>();
        	Audio tempAudio = new Audio();
        	String query = new String("SELECT * FROM Audio;");
        	try {
        		Class.forName("org.sqlite.JDBC");
        		c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);	//Mise en place de la transaction manuelle
                System.out.println("[getAllAudio]Opened database successfully");

                stmt = c.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                	tempAudio = new Audio();
                	tempAudio.setId(rs.getInt("id"));
                	tempAudio.setName(rs.getString("name"));
                	tempAudio.setFilePath(rs.getString("file_path"));
                	tempAudio.setIdLanguage(rs.getInt("id_language"));
                	tempAudio.setFormat(rs.getString("format"));
                    //Transfert de la tempAudio dans la ArrayList
                	result.add(tempAudio);
                }
                rs.close();
                stmt.close();
                c.close();
                return(result);
            } catch ( Exception e ) {
            	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
            System.out.println("[getAllAudios]Error");
            return(null);
        }
        
        /**
         * Cette méthode retourne l'ensemble des langages contenus dans la base de données.
         * @return La liste de toutes les langages dans un ArrayList\<Language\>
         */
        public ArrayList<Language> getAllLanguages(){
        	Connection c = null;
        	PreparedStatement stmt = null;
        	ArrayList<Language> result;
        	result = new ArrayList<>();
        	Language tempLanguage = new Language();
        	String query = new String("SELECT * FROM Language;");
        	try {
        		Class.forName("org.sqlite.JDBC");
        		c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);	//Mise en place de la transaction manuelle
                System.out.println("[getAllLanguages]Opened database successfully");

                stmt = c.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                	tempLanguage = new Language();
                	tempLanguage.setId(rs.getInt("id"));
                	tempLanguage.setName(rs.getString("name"));
                    //Transfert de la tempLanguage dans la ArrayList
                	result.add(tempLanguage);
                }
                rs.close();
                stmt.close();
                c.close();
                return(result);
            } catch ( Exception e ) {
            	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
            System.out.println("[getAllLanguages]Error");
            return(null);
        }
    }
/**
 * *********************************************
 *
 *
 * <b>Warning: the nomenclature is not the same in the database and Java.</b>
 * <p>
 * The right way to name variables :
 * <ul>
 * <li>Database : file_path</li>
 * <li>Java : filePath</li>
 * </ul>
 * </p>
 *
 *
 **********************************************
 */
package BDD;

import java.sql.*;
import java.util.ArrayList;

/**
 * Use this class to create a database and interact with it.
 *
 * @author akervadec
 */
public class DataBase {

    public DataBase() {
        this.connexion();
        this.createTables();
    }

    /**
     * Establishes a connection with the database dataBase.db, or create it if
     * it doesn't exist yet.
     *
     * @return Connection.
     */
    public Connection connexion() {
        boolean test = false;
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    /**
     * Creates the tables in the database.
     */
    private void createTables() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            System.out.println("[CreateTables]Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Question ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + "content VARCHAR(255) UNIQUE NOT NULL,"
                    + "id_video INTEGER NOT NULL,"
                    + "id_audio INTEGER NOT NULL,"
                    + "id_language INTEGER NOT NULL);"
                    + "CREATE TABLE IF NOT EXISTS Video ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + "name VARCHAR(50) UNIQUE NOT NULL,"
                    + "file_path VARCHAR(255) NOT NULL,"
                    + "id_language INTEGER NOT NULL,"
                    + "format VARCHAR(25));"
                    + "CREATE TABLE IF NOT EXISTS Audio ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + "name VARCHAR(50) UNIQUE NOT NULL,"
                    + "file_path VARCHAR(255) NOT NULL,"
                    + "id_language INTEGER NOT NULL,"
                    + "format VARCHAR(25));"
                    + "CREATE TABLE IF NOT EXISTS Language ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + "name VARCHAR(25) UNIQUE);";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("[CreateTables]Tables created successfully");
    }

    /**
     * Adds a Video object (actually its metadata) in the database.
     *
     * @param name Video's name to add.
     * @param filePath Video's file path to add.
     * @param nameLanguage Video's language to add.
     * @param format Video's format to add.
     */
    public void addVideo(String name, String filePath, String format, String nameLanguage) {
        Connection c = null;
        PreparedStatement stmtLang = null;
        PreparedStatement stmtAdd = null;
        int idLang = 0;
        if (this.searchVideoByNameFormat(name, format).getId() != 0) {
            System.out.println("[addVideo]This video already exists.");
        } else {
            String query = new String("SELECT id FROM Language WHERE Language.name=?;");
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

                c.setAutoCommit(false);
                System.out.println("[addVideo]Opened database successfully");

                //Searching the language's id of the Video
                stmtLang = c.prepareStatement(query);
                stmtLang.setString(1, nameLanguage);
                ResultSet rs = stmtLang.executeQuery();
                while (rs.next()) {
                    idLang = rs.getInt("id");
                }

                //Adding the Video
                query = "INSERT INTO Video(name,file_path,id_language,format) VALUES (?,?,?,?);";
                stmtAdd = c.prepareStatement(query);

                stmtAdd.setString(1, name);
                stmtAdd.setString(2, filePath);
                stmtAdd.setInt(3, idLang);
                stmtAdd.setString(4, format);
                stmtAdd.executeUpdate();

                rs.close();
                stmtLang.close();
                stmtAdd.close();
                c.commit();
                c.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("[addVideo]The video " + name + "." + format + " successfuly added to the DB");
        }
    }

    /**
     * Adds a Audio object (actually its metadata) in the database.
     *
     * @param name Audio's name to add.
     * @param filePath Audio's file path to add.
     * @param nameLanguage Audio's language to add.
     * @param format Audio's format to add.
     */
    public void addAudio(String name, String filePath, String format, String nameLanguage) {
        Connection c = null;
        PreparedStatement stmtLang = null;
        PreparedStatement stmtAdd = null;
        int idLang = 0;
        if (this.searchAudioByNameFormat(name, format).getId() != 0) {
            System.out.println("[addAudio]This audio already exists.");
        } else {
            String query = new String("SELECT id FROM Language WHERE Language.name=?;");
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

                c.setAutoCommit(false);
                System.out.println("[addAudio]Opened database successfully");

                //Searching language's id of the audio
                stmtLang = c.prepareStatement(query);
                stmtLang.setString(1, nameLanguage);
                ResultSet rs = stmtLang.executeQuery();
                while (rs.next()) {
                    idLang = rs.getInt("id");
                }

                //Adding the Audio
                query = "INSERT INTO Audio(name,file_path,id_language,format) VALUES (?,?,?,?);";
                stmtAdd = c.prepareStatement(query);

                stmtAdd.setString(1, name);
                stmtAdd.setString(2, filePath);
                stmtAdd.setInt(3, idLang);
                stmtAdd.setString(4, format);
                stmtAdd.executeUpdate();

                rs.close();
                stmtLang.close();
                stmtAdd.close();
                c.commit();
                c.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("[addAudio]The audio " + name + "." + format + " successfuly added to the DB");
        }
    }

    /**
     * dds a Question object to the database.
     *
     * @param content Question's content.
     * @param video Question's Video expected.
     * @param audio Question's Audio expected.
     * @param nameLanguage Question's Language.
     */
    public void addQuestion(String content, Video video, Audio audio, String nameLanguage) {
        Connection c = null;
        PreparedStatement stmtLang = null;
        PreparedStatement stmtAdd = null;
        int idLang = 0;
        if (this.searchQuestionByContent(content).getId() != 0) {
            System.out.println("[addQuestion]This Question already exists.");
        } else {
            String query = new String("SELECT id FROM Language WHERE Language.name=?;");
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);
                System.out.println("[addQuestion]Opened database successfully");

                stmtLang = c.prepareStatement(query);

                stmtLang.setString(1, nameLanguage);
                ResultSet rs = stmtLang.executeQuery();
                while (rs.next()) {
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
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("[addQuestion]The question \"" + content + "\" successfuly added.");
        }
    }

    /**
     * Adds a language in tha database.
     *
     * @param name The language in String type.
     */
    public void addLanguage(String name) {
        Connection c = null;
        PreparedStatement stmt = null;
        if (this.searchLanguageByName(name).getId() != 0) {
            System.out.println("[addLanguage]This language already exists.");
        } else {
            String query = new String("INSERT INTO Language (name) VALUES (?);");
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);
                System.out.println("[addLanguage]Opened database successfully");

                stmt = c.prepareStatement(query);
                stmt.setString(1, name);
                stmt.executeUpdate();

                stmt.close();
                c.commit();
                c.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("[addLanguage]The language " + name + " successfuly added.");
        }
    }

    /**
     * Selects a random Video in the database, with the Language matching with
     * the paramater.
     *
     * @param language Video's Language wanted.
     * @return Video
     */
    public Video manageVideo(Language language) {
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
            stmt.setInt(1, idLanguage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
            return (result);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("[manageVideo]Error");
        return (null);
    }

    /**
     * Selects a random Audio in the database, with the Language matching with
     * the paramater.
     *
     * @param language Audio's Language wanted.
     * @return Audio
     */
    public Audio manageAudio(Language language) {
        Connection c = null;
        PreparedStatement stmt = null;
        Audio result = new Audio();
        int idLanguage = language.getId();
        String query = new String("SELECT * FROM Audio WHERE id_language=? ORDER BY random() LIMIT 1;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("[manageAudio]Opened database successfully");

            stmt = c.prepareStatement(query);
            stmt.setInt(1, idLanguage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
            return (result);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("[manageAudio]Error");
        return (null);
    }

    /**
     * Selects a random Question in the database, with the Language matching
     * with the paramater.
     *
     * @param language Question's Language wanted.
     * @return Question
     */
    public Question manageQuestion(Language language) {
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
            stmt.setInt(1, idLanguage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = new Question(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getInt("id_video"),
                        rs.getInt("id_audio"),
                        rs.getInt("id_language"));
            }
            rs.close();
            stmt.close();
            c.close();
            return (result);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("[manageQuestion]Error");
        return (null);
    }

    /**
     * Searches a Langugae in the database by its name.
     *
     * @param name Language's name to search.
     * @return Language
     */
    public Language searchLanguageByName(String name) {
        Connection c = null;
        PreparedStatement stmt = null;
        Language language = new Language();
        String query = new String("SELECT * FROM Language WHERE Language.name=?;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("[searchLanguageByName]Opened database successfully");

            stmt = c.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                language.setId(rs.getInt("id"));
                language.setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            c.close();
            return (language);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("[searchLanguageByName]Error");
        return (null);
    }

    /**
     * Searches a Video in the database by its name and its format, if not
     * found, returns an object with a id = 0.
     *
     * @param name Video's name searched.
     * @param format Video's format searched.
     * @return Video
     */
    public Video searchVideoByNameFormat(String name, String format) {
        Connection c = null;
        PreparedStatement stmt = null;
        Video video = new Video();
        String query = new String("SELECT * FROM Video WHERE Video.name=? AND Video.format=?;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("[searchVideoByNameFormat]Opened database successfully");

            stmt = c.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, format);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                video.setId(rs.getInt("id"));
                video.setName(rs.getString("name"));
                video.setFormat(rs.getString("format"));
                video.setFilePath(rs.getString("file_path"));
                video.setIdLanguage(rs.getInt("id_language"));
            }
            rs.close();
            stmt.close();
            c.close();
            return (video);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("[searchVideoByNameFormat]Error");
        return (null);
    }

    /**
     * Searches a Audio in the database by its name and its format, if not
     * found, returns an object with a id = 0.
     *
     * @param name Audio's name searched.
     * @param format Audio's format searched.
     * @return Audio
     */
    public Audio searchAudioByNameFormat(String name, String format) {
        Connection c = null;
        PreparedStatement stmt = null;
        Audio audio = new Audio();
        String query = new String("SELECT * FROM Audio WHERE Audio.name=? AND Audio.format=?;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);
            System.out.println("[searchAudioByNameFormat]Opened database successfully");

            stmt = c.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, format);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                audio.setId(rs.getInt("id"));
                audio.setName(rs.getString("name"));
                audio.setFormat(rs.getString("format"));
                audio.setFilePath(rs.getString("file_path"));
                audio.setIdLanguage(rs.getInt("id_language"));
            }
            rs.close();
            stmt.close();
            c.close();
            return (audio);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("[searchAudioByNameFormat]Error");
        return (null);
    }

    /**
     * Searches a Question in the database by its content, if not found, returns
     * an object with a id = 0.
     *
     * @param content Question's content searched.
     * @return Question
     */
    public Question searchQuestionByContent(String content) {
        Connection c = null;
        PreparedStatement stmt = null;
        Question question = new Question();
        String query = new String("SELECT * FROM Question WHERE Question.content=?;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            c.setAutoCommit(false);
            System.out.println("[searchQuestionByContent]Opened database successfully");

            stmt = c.prepareStatement(query);
            stmt.setString(1, content);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                question.setId(rs.getInt("id"));
                question.setContent(rs.getString("content"));
                question.setIdVideo(rs.getInt("id_video"));
                question.setIdAudio(rs.getInt("id_audio"));
                question.setIdLanguage(rs.getInt("id_language"));
            }
            rs.close();
            stmt.close();
            c.close();
            return (question);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("[searchQuestionByContent]Error");
        return (null);
    }

    /**
     * Returns a ArrayList of Question of all the questions in the database.
     *
     * @return ArrayList\<Question\>
     */
    public ArrayList<Question> getAllQuestions() {
        Connection c = null;
        PreparedStatement stmt = null;
        ArrayList<Question> result;
        result = new ArrayList<>();
        Question tempQuestion = new Question();
        String query = new String("SELECT * FROM Question;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            c.setAutoCommit(false);
            System.out.println("[getAllQuestions]Opened database successfully");

            stmt = c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tempQuestion = new Question();
                tempQuestion.setId(rs.getInt("id"));
                tempQuestion.setContent(rs.getString("content"));
                tempQuestion.setIdVideo(rs.getInt("id_video"));
                tempQuestion.setIdAudio(rs.getInt("id_audio"));
                tempQuestion.setIdLanguage(rs.getInt("id_language"));
                //Transfering tempQuestion into the ArrayList
                result.add(tempQuestion);
            }
            rs.close();
            stmt.close();
            c.close();
            return (result);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("[getAllQuestions]Error");
        return (null);
    }

    /**
     * Returns a ArrayList of Video of all the videos in the database.
     *
     * @return ArrayList\<Video\>
     */
    public ArrayList<Video> getAllVideos() {
        Connection c = null;
        PreparedStatement stmt = null;
        ArrayList<Video> result;
        result = new ArrayList<>();
        Video tempVideo = new Video();
        String query = new String("SELECT * FROM Video;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            c.setAutoCommit(false);
            System.out.println("[getAllVideo]Opened database successfully");

            stmt = c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tempVideo = new Video();
                tempVideo.setId(rs.getInt("id"));
                tempVideo.setName(rs.getString("name"));
                tempVideo.setFilePath(rs.getString("file_path"));
                tempVideo.setIdLanguage(rs.getInt("id_language"));
                tempVideo.setFormat(rs.getString("format"));
                //Transfering tempVideo into the ArrayList
                result.add(tempVideo);
            }
            rs.close();
            stmt.close();
            c.close();
            return (result);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("[getAllVideos]Error");
        return (null);
    }

    /**
     * Returns a ArrayList of Audio of all the audios in the database.
     *
     * @return ArrayList\<Audio\>
     */
    public ArrayList<Audio> getAllAudios() {
        Connection c = null;
        PreparedStatement stmt = null;
        ArrayList<Audio> result;
        result = new ArrayList<>();
        Audio tempAudio = new Audio();
        String query = new String("SELECT * FROM Audio;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            c.setAutoCommit(false);
            System.out.println("[getAllAudio]Opened database successfully");

            stmt = c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tempAudio = new Audio();
                tempAudio.setId(rs.getInt("id"));
                tempAudio.setName(rs.getString("name"));
                tempAudio.setFilePath(rs.getString("file_path"));
                tempAudio.setIdLanguage(rs.getInt("id_language"));
                tempAudio.setFormat(rs.getString("format"));
                //Transfering tempAudio into the ArrayList
                result.add(tempAudio);
            }
            rs.close();
            stmt.close();
            c.close();
            return (result);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("[getAllAudios]Error");
        return (null);
    }

    /**
     * Returns a ArrayList of Language of all the languages in the database.
     *
     * @return ArrayList\<Language\>
     */
    public ArrayList<Language> getAllLanguages() {
        Connection c = null;
        PreparedStatement stmt = null;
        ArrayList<Language> result;
        result = new ArrayList<>();
        Language tempLanguage = new Language();
        String query = new String("SELECT * FROM Language;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            c.setAutoCommit(false);
            System.out.println("[getAllLanguages]Opened database successfully");

            stmt = c.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tempLanguage = new Language();
                tempLanguage.setId(rs.getInt("id"));
                tempLanguage.setName(rs.getString("name"));
                //Transfering tempLanguage into the ArrayList
                result.add(tempLanguage);
            }
            rs.close();
            stmt.close();
            c.close();
            return (result);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("[getAllLanguages]Error");
        return (null);
    }

    /**
     * Counts the amount of languages in the database Audio table with the
     * idLanguage matching with the parameter.
     *
     * @param idLanguage Audios idLanguage to search.
     * @return int
     */
    public int countAudio(int idLanguage) {
        int result = 0;

        Connection c = null;
        PreparedStatement stmt = null;
        String query = new String("SELECT COUNT(*) AS rowcount FROM Audio where id_language = ? ;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            c.setAutoCommit(false);
            System.out.println("[countAudio]Opened database successfully");

            stmt = c.prepareStatement(query);
            stmt.setInt(1, idLanguage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("rowcount");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return result;
    }

    /**
     * Counts the amount of languages in the database Question table with the
     * idLanguage matching with the parameter.
     *
     * @param idLanguage Question idLanguage to search.
     * @return int
     */
    public int countQuestion(int idLanguage) {
        int result = 0;

        Connection c = null;
        PreparedStatement stmt = null;
        String query = new String("SELECT COUNT(*) AS rowcount FROM Question where id_language=?;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            c.setAutoCommit(false);
            System.out.println("[CountQuestion]Opened database successfully");

            stmt = c.prepareStatement(query);
            stmt.setInt(1, idLanguage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("rowcount");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("erreurs");
        }

        return result;
    }

    /**
     * Counts the amount of languages in the database Video table with the
     * idLanguage matching with the parameter.
     *
     * @param idLanguage Video idLanguage to search.
     * @return int
     */
    public int countVideo(int idLanguage) {
        int result = 0;

        Connection c = null;
        PreparedStatement stmt = null;
        String query = new String("SELECT COUNT(*) AS rowcount FROM Video where id_language = ? ;");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");

            c.setAutoCommit(false);
            stmt = c.prepareStatement(query);
            stmt.setInt(1, idLanguage);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("rowcount");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return result;
    }

    /**
     * Removes a Video from the database knowing its name and format.
     *
     * @param videoName Video's name to remove.
     * @param format Video's format to remove.
     */
    public void rmVideo(String videoName, String format) {
        Connection c = null;
        PreparedStatement stmtCheck = null;
        PreparedStatement stmtRm = null;
        int idLang = 0;
        Question tmpQuestion = new Question();
        Video tmp = new Video(this.searchVideoByNameFormat(videoName, format));
        if (tmp.getId() == 0) {
            System.out.println("[rmVideo]This video doesn't exist.");
        } else {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);
                System.out.println("[rmVideo]Opened database successfully");

                //Looking for a Question using the video we want to remove
                String query = "SELECT id, content, id_audio, id_video, id_language FROM Question WHERE id_video=?";
                stmtCheck = c.prepareStatement(query);

                stmtCheck.setInt(1, tmp.getId());
                ResultSet rs = stmtCheck.executeQuery();
                while (rs.next()) {
                    tmpQuestion.setId(rs.getInt("id"));
                    tmpQuestion.setContent(rs.getString("content"));
                    tmpQuestion.setIdAudio(rs.getInt("id_audio"));
                    tmpQuestion.setIdVideo(rs.getInt("id_video"));
                    tmpQuestion.setIdLanguage(rs.getInt("id_language"));
                }
                rs.close();
                stmtCheck.close();

                if (tmpQuestion.getId() == 0) {
                    //Removing the Audio
                    query = "DELETE FROM Video WHERE id=?;";
                    stmtRm = c.prepareStatement(query);

                    stmtRm.setInt(1, tmp.getId());
                    stmtRm.executeQuery();

                    stmtRm.close();
                } else {
                    System.out.println("Video cannot be removed, it is linked to the following question :\n" + tmpQuestion.toString());
                }
                c.commit();
                c.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("[rmVideo]The video " + tmp.getName() + "." + tmp.getFormat() + " successfuly removed from the DB");
        }
    }

    /**
     * Removes a Audio from the database knowing its name and format.
     *
     * @param audioName Audio's name to remove.
     * @param format Audio's format to remove.
     */
    public void rmAudio(String audioName, String format) {
        Connection c = null;
        PreparedStatement stmtCheck = null;
        PreparedStatement stmtRm = null;
        int idLang = 0;
        Question tmpQuestion = new Question();
        Audio tmp = new Audio(this.searchAudioByNameFormat(audioName, format));
        if (tmp.getId() == 0) {
            System.out.println("[rmAudio]This video doesn't exist.");
        } else {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
                c.setAutoCommit(false);
                System.out.println("[rmAudio]Opened database successfully");

                //Looking for a Question using the audio we want to remove
                String query = "SELECT id, content, id_audio, id_video, id_language FROM Question WHERE id_audio=?";
                stmtCheck = c.prepareStatement(query);

                stmtCheck.setInt(1, tmp.getId());
                ResultSet rs = stmtCheck.executeQuery();
                while (rs.next()) {
                    tmpQuestion.setId(rs.getInt("id"));
                    tmpQuestion.setContent(rs.getString("content"));
                    tmpQuestion.setIdAudio(rs.getInt("id_audio"));
                    tmpQuestion.setIdVideo(rs.getInt("id_video"));
                    tmpQuestion.setIdLanguage(rs.getInt("id_language"));
                }
                rs.close();
                stmtCheck.close();

                if (tmpQuestion.getId() == 0) {
                    //Removing the Audio
                    query = "DELETE FROM Audio WHERE id=?;";
                    stmtRm = c.prepareStatement(query);

                    stmtRm.setInt(1, tmp.getId());
                    stmtRm.executeQuery();

                    stmtRm.close();
                } else {
                    System.out.println("Audio cannot be removed, it is linked to the following question :\n" + tmpQuestion.toString());
                }
                c.commit();
                c.close();
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("[rmAudio]The video " + tmp.getName() + "." + tmp.getFormat() + " successfuly removed from the DB");
        }
    }

}

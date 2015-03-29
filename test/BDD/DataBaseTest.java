/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test all DataBase fonctions.
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

    /**
     * Test of Connexion method, of class DataBase.
     */
    @Test
    public void testConnection() {
        assertTrue("The connection failed", dataBaseTest.connexion() != null);
    }

    /**
     * Test of createTables method, of class DataBase.
     */
    @Test
    public void testDataBaseTables() {

        Connection c;
        PreparedStatement stmt;
        String tablesName[] = {"Question", "sqlite_sequence", "Video", "Audio", "Language"};
        try {
            c = dataBaseTest.connexion();
            String sql = " SELECT name FROM sqlite_master WHERE type='table';";
            stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                if (rs.getString("name").contains(tablesName[0]) && !tablesName[0].isEmpty()) {
                    tablesName[0] = "";
                }
                if (rs.getString("name").contains(tablesName[1]) && !tablesName[1].isEmpty()) {
                    tablesName[1] = "";
                }
                if (rs.getString("name").contains(tablesName[2]) && !tablesName[2].isEmpty()) {
                    tablesName[2] = "";
                }
                if (rs.getString("name").contains(tablesName[3]) && !tablesName[3].isEmpty()) {
                    tablesName[3] = "";
                }

                if (rs.getString("name").contains(tablesName[4]) && !tablesName[4].isEmpty()) {
                    tablesName[4] = "";
                }
            }
            stmt.close();
            c.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("error in Question table", tablesName[0].isEmpty());
        assertTrue("error in Master table", tablesName[1].isEmpty());
        assertTrue("error in Video table", tablesName[2].isEmpty());
        assertTrue("error in Audio table", tablesName[3].isEmpty());
        assertTrue("error in Language table", tablesName[4].isEmpty());
    }

    /**
     * Test of addVideo method, of class DataBase.
     */
    @Test
    public void testAddVideo() {
        System.out.println("addVideo test");
        String name = "sethgecks";
        String filePath = "Audio\\\\sethsbree.mp3";
        String format = "mp3";
        String nameLanguage = "French";
        boolean checkVideo = false;
        dataBaseTest.addVideo(name, filePath, format, nameLanguage);
        try {
            PreparedStatement prepaS;
            Connection c = dataBaseTest.connexion();
            String query = "select * from Video where name='" + name + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                String nameDataBase = rs.getString("name");
                String filepathDataBase = rs.getString("file_path");
                String formatDataBase = rs.getString("format");
                if (name.equals(nameDataBase) && filePath.equals(filepathDataBase) && format.equals(formatDataBase) && nameLanguage.equals("French")) {
                    checkVideo = true;
                    System.out.println("the vidéo was found");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in the add of audio file", checkVideo == true);
    }

    /**
     * Test of addAudio method, of class DataBase.
     */
    @Test
    public void testAddAudio() {
        System.out.println("addAudio test");
        String name = "sethgeceeks";
        String filePath = "Audio\\\\sethsbreedd.mp3";
        String format = "mp3";
        String nameLanguage = "French";
        boolean checkAudio = false;
        dataBaseTest.addAudio(name, filePath, format, nameLanguage);

        try {
            PreparedStatement prepaS;
            Connection c = dataBaseTest.connexion();
            String query = "select * from Audio where name='" + name + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                String nameDataBase = rs.getString("name");
                String filepathDataBase = rs.getString("file_path");
                String formatDataBase = rs.getString("format");
                if (name.equals(nameDataBase) && filePath.equals(filepathDataBase) && format.equals(formatDataBase) && nameLanguage.equals("French")) {
                    checkAudio = true;
                    System.out.println("the audio was found");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in the add of audio file", checkAudio == true);
    }

    /**
     * Test of addQuestion method, of class DataBase.
     */
    @Test
    public void testAddQuestion() {
        System.out.println("addQuestion test");
        String content = "hello everybody how are you?";
        Audio audioTest = new Audio(2000, "sethgeceeks", "Audio\\sethsbreedd.mp3", "mp3", 1);
        Video videoTest = new Video(2000, "sethgecks", "Audio\\sethsbree.mp3", "mp3", 1);
        String nameLanguage = "French";
        boolean checkQuestion = false;

        dataBaseTest.addQuestion(content, videoTest, audioTest, nameLanguage);

        try {
            PreparedStatement prepaS;
            Connection c = dataBaseTest.connexion();
            String query = "select * from Question where content='" + content + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                String contentDataBase = rs.getString("content");
                String languageBDD = "French";
                if (content.equals(contentDataBase) && languageBDD.equals(nameLanguage)) {
                    checkQuestion = true;
                    System.out.println("the question was found");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in the add of question file", checkQuestion == true);

    }

    /**
     * Test of addLanguage method, of class DataBase.
     */
    @Test
    public void testAddLanguage() {
        System.out.println("addLanguage");
        String name = "Japanese";
        dataBaseTest.addLanguage(name);
        boolean checkLanguage = false;

        dataBaseTest.addLanguage(name);

        try {
            PreparedStatement prepaS;
            Connection c = dataBaseTest.connexion();
            String query = "select * from Language where name='" + name + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                checkLanguage = true;
                System.out.println("the question was found");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        assertTrue("Error in the add of question file", checkLanguage == true);

    }

}

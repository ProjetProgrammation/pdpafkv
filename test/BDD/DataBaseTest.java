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
    public void testConnection() {
        assertTrue("The connection failed", dataBaseTest.connexion() != null);
    }

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

    @Test
    public void testAddVideo() {
        System.out.println("addVideo");
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
                String nameBDD = rs.getString("name");
                String filepathBDD = rs.getString("file_path");
                String formatBDD = rs.getString("format");
                if (name.equals(nameBDD) && filePath.equals(filepathBDD) && format.equals(formatBDD) && nameLanguage.equals("French")) {
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

    @Test
    public void testAddAudio() {
        System.out.println("addAudio");
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
                String nameBDD = rs.getString("name");
                String filepathBDD = rs.getString("file_path");
                String formatBDD = rs.getString("format");
                if (name.equals(nameBDD) && filePath.equals(filepathBDD) && format.equals(formatBDD) && nameLanguage.equals("French")) {
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

}

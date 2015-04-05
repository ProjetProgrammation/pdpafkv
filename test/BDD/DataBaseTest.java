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
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests all DataBase fonctions.
 *
 * @author guillaume21
 */
public class DataBaseTest {


    public DataBaseTest() {

    }

    @Before
    public void setUp() {
        DataBase.initiateDataBase();
    }

    @After
    public void tearDown() {
      //  DataBase = null;
    }

    /**
     *Connexion method test for DataBase class.
     */
    public void testConnexion() {
        assertTrue("Connexion failed", DataBase.connexion() != null);
    }

    /**
     *CreateTables method Test for DataBase Class.
     */
    public void testCreateTables() {

        Connection c;
        PreparedStatement stmt;
        String tablesName[] = {"Question", "sqlite_sequence", "Video", "Audio", "Language"};
        try {
            c = DataBase.connexion();
            String sql = " SELECT name FROM sqlite_master WHERE type='table';";
            stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                if ((rs.getString("name").contains(tablesName[0])) && (!tablesName[0].isEmpty())) {
                    tablesName[0] = "";
                }
                if ((rs.getString("name").contains(tablesName[1])) && (!tablesName[1].isEmpty())) {
                    tablesName[1] = "";
                }
                if ((rs.getString("name").contains(tablesName[2])) && (!tablesName[2].isEmpty())) {
                    tablesName[2] = "";
                }
                if ((rs.getString("name").contains(tablesName[3])) && (!tablesName[3].isEmpty())) {
                    tablesName[3] = "";
                }

                if ((rs.getString("name").contains(tablesName[4])) && (!tablesName[4].isEmpty())) {
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
     * Video methods Test for DataBase Class. White-box tests and
     * black-box tests.
     */
    public void testBlackBoxAddVideo() {
        System.out.println("addVideo test");

        String name = "2013_3_20_S33_fr_L1_TEST_B_ok";
        String filePath = "Audio\\\\2013_3_20_S33_fr_L1_TEST_B_ok.mp4";
        String format = "mp4";
        String nameLanguage = "French";
        String thumbnail = "2013_3_20_S33_fr_L1_TEST_B_ok.jpg";
        String thumbnailgif = "2013_3_20_S33_fr_L1_TEST_B_ok.gif";
        boolean checkVideo = false;

        DataBase.addVideo(name, filePath, format, nameLanguage, thumbnail, thumbnailgif);
        
        ArrayList<Video> videoListTest;
        videoListTest = DataBase.getAllVideos();
        for (Video v : videoListTest) {
            System.out.println("Video name :" + v.getName());
            if ((name.equals(v.getName())) && (filePath.equals(v.getFilePath())) && (format.equals(v.getFormat())) && (nameLanguage.equals("French"))) {
                    checkVideo = true;
                    System.out.println("Video found");
                }
        }
        
        assertTrue("Error in BlackBoxAddVideo", checkVideo == true);
    }

    public void testBlackBoxSearchVideoByNameFormat() {
        System.out.println("searchVideoByNameFormat");

        String name = "2013_3_19_S29_fr_L1_ADMK_B_ok";
        String filePath = "Video\\2013_3_19_S29_fr_L1_ADMK_B_ok.mp4";
        String format = "mp4";
        String nameLanguage = "French";
        String thumbnail = "sethgecks.jpg";
        String thumbnailgif = "sethgecks.gif";
        int idLanguage = DataBase.searchLanguageByName(nameLanguage).getId();

        Video expResult = new Video(DataBase.getAllVideos().size() + 1, name, filePath, format, idLanguage);
        DataBase.addVideo(name, filePath, format, nameLanguage, thumbnailgif, thumbnailgif);
        Video result = DataBase.searchVideoByNameFormat(name, format);
        assertTrue("Problem BlackBoxSearchVideoByNameFormat", (expResult.getFilePath().equals(result.getFilePath())) && (expResult.getName().equals(result.getName())) && (expResult.getFormat().equals(result.getFormat())));
        
        
        DataBase.searchVideoByNameFormat("salut", "mp4");
        DataBase.searchVideoByNameFormat(name, "mp3");
        DataBase.rmVideo(name, format);
    }

    public void testBlackBoxRmVideo() {
        System.out.println("BlackBoxrmVideo");

        ArrayList<Video> videoListTest;
        videoListTest = DataBase.getAllVideos();
        
        for (Video v : videoListTest) {
            System.out.println("Video name :" + v.getName());
        }
       
        DataBase.rmVideo("2013_3_20_S33_fr_L1_TEST_B_ok", "mp4");
        videoListTest = DataBase.getAllVideos();
        for (Video v : videoListTest) {
            System.out.println("Video name :" + v.getName());
            if ((v.getName().equals("2013_3_20_S33_fr_L1_TEST_B_ok")) && (v.getFormat().equals("mp4"))) {
                throw new AssertionError("Problem with BlackBoxRmVideo");
            }
        }
    }

    public void testBlackBoxCountVideo() {
        System.out.println("BlackBoxcountVideo");

        int idLanguage = 1;
        int expResult = 0;
        int result = DataBase.countVideo(idLanguage);

        ArrayList<Video> videoListTest;
        videoListTest = DataBase.getAllVideos();
        for (Video v : videoListTest) {
            if (v.getIdLanguage() == idLanguage) {
                expResult += 1;
            }
        }
        assertEquals("Problem with BlackBoxCountVideo", expResult, result);
    }

    public void testAddVideoWhiteBox() {
        System.out.println("VideoWhiteBox");

        String name = "2013_3_19_S29_fr_L1_ADMK_B_ok";
        String filePath = "Video\\2013_3_19_S29_fr_L1_ADMK_B_ok.mp4";
        String format = "mp4";
        String nameLanguage = "French";
        String thumbnail = "sethgecks.jpg";
        String thumbnailgif = "sethgecks.gif";

        DataBase.addVideo(name, filePath, format, nameLanguage, thumbnail, thumbnailgif);
        DataBase.addVideo(name, filePath, format, nameLanguage, thumbnail, thumbnailgif);
        
     }

    public void testRmVideoWhiteBox() {
        System.out.println("RmVideoWhiteBox");

        String name = "2013_3_19_S29_fr_L1_ADMK_B_ok";
        String format = "mp4";

        DataBase.rmVideo(name, format);
        DataBase.rmVideo(name, format);

        ArrayList<Question> questionListTest;
        questionListTest = DataBase.getAllQuestions();
        int idVideo = -1;

        for (Question v : questionListTest) {
                idVideo = v.getIdVideo();
                break;
        }

        Video videoTest = DataBase.searchVideoById(idVideo);
        DataBase.rmVideo(videoTest.getName(), videoTest.format);
 }

    public void testBlackBoxManageVideo() {
        System.out.println("BlackBoxManageVideo");

        Language languageTest = new Language(1, "French");
        Video video = DataBase.manageVideo(languageTest);

        assertTrue("error on manageVideo", languageTest.getId() == video.getIdLanguage());
    }

    public void testBlackBoxSearchVideoById() {
        System.out.println("BlackBoxSearchVideoById");

        Video video = new Video(DataBase.searchVideoById(DataBase.getAllVideos().size()));
        ArrayList<Video> videoListTest;
        videoListTest = DataBase.getAllVideos();

        for (Video v : videoListTest) {
            System.out.println("Video name:" + v.getName());
            if (!(v.getName().equals(video.getName())) && (v.getId() == (video.getId()))) {
                throw new AssertionError("BlackBoxSearchVideoById problem");
            }
        }
    }

    /**
     * Audio methods Test for DataBase class. White-box tests and
     * black-box tests.
     */
    public void testBlackBoxAddAudio() {
        System.out.println("BlackBoxAddAudio");

        String name = "2013_3_20_S33_fr_L1_TEST";
        String filePath = "Audio\\\\2013_3_20_S33_fr_L1_TEST";
        String format = "mp3";
        String nameLanguage = "French";
        boolean checkAudio = false;

        DataBase.addAudio(name, filePath, format, nameLanguage);
        
        ArrayList<Audio> audioListTest;
        audioListTest = DataBase.getAllAudios();
        for (Audio v : audioListTest) {
            if ((name.equals(v.getName())) && (filePath.equals(v.getFilePath())) && (format.equals(v.getFormat())) && (nameLanguage.equals("French"))) {
                    checkAudio = true;
                    System.out.println("Audio found");
             }
        }
      
        assertTrue("Error in BlackBoxAddAudio", checkAudio == true);
    }

    public void testBlackBoxRmAudio() {
        System.out.println("BlackBoxRmAudio");

        ArrayList<Audio> audioListTest;
        audioListTest = DataBase.getAllAudios();

        for (Audio v : audioListTest) {
            System.out.println("The audio name is:" + v.getName());
        }

        String name = "2013_3_20_S33_fr_L1_TEST";
        String format = "mp3";
        DataBase.rmAudio(name, format);

        audioListTest = DataBase.getAllAudios();

        for (Audio v : audioListTest) {
            System.out.println("The audio name is:" + v.getName());
            if ((v.getName().equals(name)) && (v.getFormat().equals(format))) {
                throw new AssertionError("Problem with BlackBoxRmAudio");
            }
        }
    }

    public void testBlackBoxSearchAudioByNameFormat() {
        System.out.println("BlackBoxSearchAudioByNameFormat");

        String name = "2013_3_20_S33_fr_L1_TEST2";
        String filePath = "Audio\\\\2013_3_20_S33_fr_L1_TEST2.mp3";
        String format = "mp3";
        String nameLanguage = "French";

        int idLanguage = DataBase.searchLanguageByName(nameLanguage).getId();

        Audio expResult = new Audio(DataBase.getAllAudios().size() + 1,name, filePath, format, idLanguage);
        DataBase.addAudio(name, filePath, format, nameLanguage);
        Audio result = DataBase.searchAudioByNameFormat(name, format);

        assertTrue("Problem with BlackBoxSearchAudioByNameFormat", (expResult.getFilePath().equals(result.getFilePath())) && (expResult.getName().equals(result.getName())) && (expResult.getFormat().equals(result.getFormat())));
        DataBase.searchAudioByNameFormat(name, "mp3");
        DataBase.searchAudioByNameFormat("test", format);
        DataBase.rmAudio(name, format);
    }

    public void testBlackBoxCountAudio() {
        System.out.println("BlackBoxCountAudio");

        int idLanguage = 1;
        int expResult = 0;
        int result = DataBase.countAudio(idLanguage);

        ArrayList<Audio> audioListTest;
        audioListTest = DataBase.getAllAudios();
        for (Audio v : audioListTest) {
            if (v.getIdLanguage() == idLanguage) {
                expResult += 1;
            }
        }
        assertEquals("problem with BlackBoxCountAudio", expResult, result);
    }

    public void testAddAudioWhiteBox() {
        System.out.println("AddAudioWhiteBox");

        String name = "2013_3_20_S33_fr_L1_TEST2";
        String filePath = "Audio\\\\2013_3_20_S33_fr_L1_TEST2.mp3";
        String format = "mp3";
        String nameLanguage = "French";

        DataBase.addAudio(name, filePath, format, nameLanguage);
        DataBase.addAudio(name, filePath, format, nameLanguage);
    }

    public void testRmAudioWhiteBox() {
        System.out.println("RmAudioWhiteBox");

       String name = "2013_3_20_S33_fr_L1_TEST2";
       String format = "mp3";

        DataBase.rmAudio(name, format);
        DataBase.rmAudio(name, format);

        ArrayList<Question> questionListTest;
        questionListTest = DataBase.getAllQuestions();
        int idAudio = -1;

        for (Question v : questionListTest) {
            
                idAudio = v.getIdAudio();
                break;
        }

        Audio audioTest = DataBase.searchAudioById(idAudio);
        DataBase.rmAudio(audioTest.getName(), audioTest.format);

    }

    public void testBlackBoxManageAudio() {
        Language languageTest = new Language(1, "French");
        Audio audio = DataBase.manageAudio(languageTest);

        assertTrue("error in BlackBoxManageAudio", languageTest.getId() == audio.getIdLanguage());
    }

    /**
     * Questions methods for DataBase class. White-box tests
     * and black-box tests.
     */
    public void testBlackBoxAddQuestion() {
        System.out.println("BlackBoxAddQuestion");

        String content = "hello everybody, how are you?";
        Audio audioTest = new Audio(DataBase.getAllAudios().size() + 1, "2013_3_20_S33_fr_L1_TEST", "Audio\\2013_3_20_S33_fr_L1_TEST.mp3", "mp3", 1);
        Video videoTest = new Video(DataBase.getAllVideos().size() + 1, "2013_3_20_S33_fr_L1_TEST_B_ok", "Video\\2013_3_20_S33_fr_L1_TEST_B_ok.mp4", "mp4", 1);
        String nameLanguage = "French";
        boolean checkQuestion = false;

        DataBase.addQuestion(content, videoTest, audioTest, nameLanguage);
        
        ArrayList<Question> questionsListTest;
        questionsListTest = DataBase.getAllQuestions();

        for (Question v : questionsListTest) {
           if((content.equals(v.getContent())) && (v.getIdLanguage()==(DataBase.getLanguageByName(nameLanguage)))){
               checkQuestion = true;
           }
        }
        assertTrue("Error in BlackBoxAddQuestion", checkQuestion == true);
    }

    public void testBlackBoxSearchAudioById() {
        System.out.println("BlackBoxSearchAudioById");

        Audio audio = new Audio(DataBase.searchAudioById(DataBase.getAllAudios().size()));

        ArrayList<Audio> audioListTest;
        audioListTest = DataBase.getAllAudios();
        for (Audio v : audioListTest) {
            System.out.println("The audio name is:" + v.getName());
            if (!(v.getName().equals(audio.getName())) && (v.getId() == (audio.getId()))) {
                throw new AssertionError("BlackBoxSearchAudioById problem");
            }
        }
        DataBase.searchAudioById(DataBase.getAllAudios().size() + 1);
    }

    public void testBlackBoxRmQuestion() {
        System.out.println("BlackBoxRmQuestion");

        ArrayList<Question> questionsListTest;
        questionsListTest = DataBase.getAllQuestions();

        for (Question v : questionsListTest) {
            System.out.println("The question content is:" + v.getContent());
        }

        String content = "hello everybody, how are you?";
        DataBase.rmQuestion(content);

        questionsListTest = DataBase.getAllQuestions();
        for (Question v : questionsListTest) {
            System.out.println("Question name :" + v.getContent());
            if (v.getContent().equals(content)) {
                throw new AssertionError("Error in BlackBoxRmQuestion");
            }
        }
    }

    public void testBlackBoxSearchQuestionByContent() {
        System.out.println("BlackBoxSearchQuestionByContent");

        String content = "hello everybody, how are you?";
        String nameLanguage = "French";
        int idLanguage = DataBase.searchLanguageByName(nameLanguage).getId();
        Question result = DataBase.searchQuestionByContent(content);

        assertTrue("Problem with BlackBoxSearchQuestionByContent", result.getContent().equals(content));
    }

    public void testBlackBoxCountQuestion() {
        System.out.println("BlackBoxCountQuestion");

        int idLanguage = 1;
        int expResult = 0;
        int result = DataBase.countQuestion(idLanguage);

        ArrayList<Question> questionListTest;
        questionListTest = DataBase.getAllQuestions();
        for (Question v : questionListTest) {
            if (v.getIdLanguage() == idLanguage) {
                expResult += 1;
            }
        }
        assertEquals("Problem with BlackBoxCountQuestion", expResult, result);

        DataBase.countQuestion(-1);
    }

    public void testAddQuestionWhiteBox() {
        System.out.println("AddQuestionWhiteBox");

        Audio audioTest = new Audio(DataBase.getAllAudios().size() + 1, "2013_3_20_S33_fr_L1_TEST2", "Audio\\2013_3_20_S33_fr_L1_TEST2.mp3", "mp3", 1);
        Video videoTest = new Video(DataBase.getAllVideos().size() + 1, "2013_3_19_S29_fr_L1_ADMK_B_ok", "Video\\2013_3_19_S29_fr_L1_ADMK_B_ok.mp4", "mp4", 1);
        String nameLanguage = "French";
        String content = "we try this!";

        DataBase.addQuestion(content, videoTest, audioTest, nameLanguage);
        DataBase.addQuestion(content, videoTest, audioTest, nameLanguage);
        DataBase.addQuestion(content, videoTest, audioTest, "Coreen");
        
    }

    public void testRmQuestionWhiteBox() {
        System.out.println("RmQuestionWhiteBox");

        String content = "we try this!";
       
        DataBase.rmQuestion(content);
        DataBase.rmQuestion(content);
              
    }

    public void testBlackBoxManageQuestion() {
        System.out.println("BlackBoxManageQuestion");

        Language languageTest = DataBase.searchLanguageByName("French");
        Question question = DataBase.manageQuestion(languageTest);

        assertTrue("error with BlackBoxManageQuestion", languageTest.getId() == question.getIdLanguage());
    }

    /**
     * Language methods test for DataBase class. White-box tests
     * and black-box tests.
     */
    public void testBlackBoxAddLanguage() {
        System.out.println("BlackBoxAddLanguage");

        String name = "Japanese";
        DataBase.addLanguage(name);
        boolean checkLanguage = false;

        try {
            PreparedStatement prepaS;
            Connection c = DataBase.connexion();
            String query = "select * from Language where name='" + name + "';)";
            prepaS = c.prepareStatement(query);

            ResultSet rs = prepaS.executeQuery();
            while (rs.next()) {
                checkLanguage = true;
                System.out.println("the language was found");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        assertTrue("Error with BlackBoxAddLanguage", checkLanguage == true);
    }

    public void testBlackBoxSearchLanguageByName() {
        System.out.println("BlackBoxSearchLanguageByName");

        ArrayList<Language> languageListTest;
        languageListTest = DataBase.getAllLanguages();
        for (Language v : languageListTest) {
            if (v.getId() != DataBase.searchLanguageByName(v.getName()).getId()) {
                Assert.fail("error in BlackBoxSearchLanguageByName");
            }
        }
    }

    public void testBlackBoxRmLanguage() {
        System.out.println("BlackBoxRmLanguage");
        ArrayList<Language> languageListTest;
        languageListTest = DataBase.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("The language name is:" + v.getName() + v.getId());
        }
        String name = "Japanese";

        DataBase.rmLanguage(name);

        languageListTest = DataBase.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("The language name is:" + v.getName());
            System.out.println("The language name is:" + v.getId());
            if (v.getName().equals(name)) {
                throw new AssertionError("Problem with BlackBoxRmLanguage");
            }
        }
    }

    public void testRmLanguageWhiteBox() {
        System.out.println("RmLanguageWhiteBox");

        String name = "Coreen";
        int languageName = 0;
        DataBase.addLanguage(name);
        DataBase.rmLanguage(name);
        DataBase.rmLanguage(name);

        ArrayList<Language> languageListTest;
        languageListTest = DataBase.getAllLanguages();
        for (Language v : languageListTest) {
                DataBase.rmLanguage(v.getName());
                break;
        }
    }

    public void testAddLanguageWhiteBox() {
        System.out.println("AddLanguageWhiteBox");

        String languageTest = "American";
        DataBase.addLanguage(languageTest);
        DataBase.addLanguage(languageTest);
    }

    public void testBlackBoxGetLanguageById() {
        System.out.println("BlackBoxGetLanguageById");

        Language langueTest = new Language(1, DataBase.getLanguageById(1));

        ArrayList<Language> languageListTest;
        languageListTest = DataBase.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("Language name:" + v.getName());
            if (!(v.getName().equals(langueTest.getName())) && (v.getId() == langueTest.getId())) {
                throw new AssertionError("BlackBoxGetLanguageById have a problem");
            }
        }
    }

    public void testBlackBoxGetLanguageByName() {
        System.out.println("BlackBoxGetLanguageByName");

        Language langueTest = new Language(DataBase.getLanguageByName("French"), "French");

        ArrayList<Language> languageListTest;
        languageListTest = DataBase.getAllLanguages();
        for (Language v : languageListTest) {
            System.out.println("The language name is:" + v.getName());
            if ((v.getName().equals(langueTest.getName())) && !(v.getId() == langueTest.getId())) {
                throw new AssertionError("BlackBoxGetLanguageByName have a problem");
            }
        }

    }

    @Test
    public void testSuite() {

        testConnexion();
        testCreateTables();

        testBlackBoxAddVideo();
        testBlackBoxAddAudio();
        testBlackBoxAddQuestion();
        testBlackBoxAddLanguage();

        testBlackBoxSearchLanguageByName();
        testBlackBoxSearchVideoByNameFormat();
        testBlackBoxSearchAudioByNameFormat();
        testBlackBoxSearchQuestionByContent();

        testBlackBoxRmVideo();
        testBlackBoxRmAudio();
        testBlackBoxRmLanguage();
        testBlackBoxRmQuestion();

        testBlackBoxCountAudio();
        testBlackBoxCountQuestion();
        testBlackBoxCountVideo();

        testAddVideoWhiteBox();
        testAddQuestionWhiteBox();
        testAddAudioWhiteBox();
        testAddLanguageWhiteBox();

        testBlackBoxManageVideo();
        testBlackBoxManageQuestion();
        testBlackBoxManageAudio();

        testRmVideoWhiteBox();
        testRmAudioWhiteBox();
        testRmQuestionWhiteBox();
        testRmLanguageWhiteBox();

        testBlackBoxGetLanguageById();
        testBlackBoxGetLanguageByName();
        testBlackBoxSearchVideoById();
        testBlackBoxSearchAudioById();
    }

}

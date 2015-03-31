/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BDD.Language;
import Controller.ControllerDatabase;
import Controller.MediaSelected;
import Controller.SelectMedia;
import Result.Answer;
import Result.Extract;
import Result.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author guillaume
 */
public class endTest {
    private User userSel;
    private int currentQuestionNumber = 1;
    private int nbQuestion;
    private Stage stage;
    private MediaSelected mediaSel;
    private SelectMedia selMedia;
    private Language language;
    private ControllerDatabase db;

   
    /**
     * 
     * Constructs a new Scene with the test template.
     * 
     * @param stage The stage of the interface.
     * @param userSel The user who run the application.
     */
    public endTest(Stage stage,User userSel) {
        this.stage = stage;
        this.userSel = userSel;
        this.launchEndGUI();
    }
    
    private void launchEndGUI() {


        Label title = new Label("Prosodic Adventure".toUpperCase());
        Label titleThanks = new Label("Merci pour avoir effectué notre test".toUpperCase());
        Button validate = new Button("Next".toUpperCase());

        //Organize the interface
        BorderPane global = new BorderPane();
        BorderPane root = new BorderPane();
        root.setCenter(titleThanks);
        

        //Add style classe
        title.getStyleClass().add("label-header");

        
        GridPane.setHalignment(validate, HPos.RIGHT);

        global.setTop(title);
        global.setCenter(root);

        //Add container to the scene
        this.stage.getScene().setRoot(global);

    }
}
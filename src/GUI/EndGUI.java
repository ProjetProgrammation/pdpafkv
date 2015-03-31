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
public class EndGUI {
    
    private Stage stage;   
    
    /**
     * 
     * Constructs a new Scene with the test template.
     * 
     * @param stage The stage of the interface.
     */
    public EndGUI(Stage stage) {
        this.stage = stage;
        this.launchEndGUI();
    }
    
    private void launchEndGUI() {


        Label title = new Label("Prosodic Adventure".toUpperCase());
        Label titleThanks = new Label("Thanks you for your participation".toUpperCase());
        Button validate = new Button("Return".toUpperCase());
        validate.setPrefSize(100, 40);

        //Organize the interface
        BorderPane global = new BorderPane();
        BorderPane root = new BorderPane();
        root.setCenter(titleThanks);
        root.setBottom(validate);
        
        validate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.gc();
                new UserGUI(stage);
            }
        });

        //Add style classe
        title.getStyleClass().add("label-header");

        GridPane.setHalignment(validate, HPos.RIGHT);

        global.setTop(title);
        global.setCenter(root);

        //Add container to the scene
        this.stage.getScene().setRoot(global);

    }
}
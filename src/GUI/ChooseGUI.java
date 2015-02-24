/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Thibaut
 */
public class ChooseGUI {

    private Stage stage;
    
    public ChooseGUI(Stage primaryStage) {
        this.stage=primaryStage;
        this.launchChooseGUI();
    }

    private void launchChooseGUI() {
        
        //Création layout avec boutons
        GridPane root = new GridPane();
        Button learnOption = new Button("Learn");
        Button testOption = new Button("The Real One");
        
        //Action button learnOption
        learnOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                TestGUI tGUI = new TestGUI(stage,5);
            }
        });
        
        //Action button learnOption
        learnOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                TestGUI tGUI = new TestGUI(stage,20);
            }
        });
        
        //Add buttons to layout
        root.add(learnOption, 0, 1);
        root.add(testOption, 1, 1);
        root.setAlignment(Pos.CENTER);
        
        //Create scene and add to stage
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setResizable(true);
        
        this.stage.hide();
        this.stage.show();
    }
   
    
    
    
    
    
    
}

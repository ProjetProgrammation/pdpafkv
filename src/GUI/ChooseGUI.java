/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import BDD.DataBase;
import BDD.Language;
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
    private UserGUI user;
    
    public ChooseGUI(Stage primaryStage, Language langSel, DataBase db, UserGUI user) {
        this.stage=primaryStage;
        this.launchChooseGUI(langSel,db);
        this.user = user;
    }

    private void launchChooseGUI(final Language langSel,final DataBase db) {
        
        //Création layout avec boutons
        GridPane root = new GridPane();
        Button learnOption = new Button("Learn");
        Button testOption = new Button("The Real One");
        
        //Action button learnOption
        learnOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                TestGUI tGUI = new TestGUI(stage,5,langSel,db, user);
                System.out.println(" le nom du mec est " + user.nom());
            }
        });
        
        //Action button learnOption
        testOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                TestGUI tGUI = new TestGUI(stage,20,langSel,db, user);
            }
        });
        
        //Add buttons to layout
        root.add(learnOption, 0, 1);
        root.add(testOption, 1, 1);
        root.setAlignment(Pos.CENTER);
        
        
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%,lightgrey, white) ; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2");
        learnOption.setStyle("-fx-background-color:lightgrey;-fx-font: 20 arial;-fx-border-radius: 5;-fx-border-color: grey;");
        testOption.setStyle("-fx-background-color:lightgrey;-fx-font: 20 arial;-fx-border-radius: 5;-fx-border-color: grey;");
        
//Create scene and add to stage
        Scene scene = new Scene(root,600, 400);
        this.stage.setScene(scene);
        this.stage.setResizable(true);
        this.stage.setFullScreen(true);
                
        this.stage.hide();
        this.stage.show();
    }
   
    
    
    
    
    
    
}

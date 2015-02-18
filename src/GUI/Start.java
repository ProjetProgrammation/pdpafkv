/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Result.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Thibaut
 */
public class Start extends Application {
    
    //Création de l'interface graphique
    @Override
    public void start(Stage primaryStage) {
        
        //Création des labels
        Label lLN = new Label("Last Name");
        Label lFN = new Label("First Name");
        Label lB = new Label("Birthday");
        Label lMT = new Label("Mother Tongue");
        Label lYS = new Label("Years Studying");
        
        //Création des différent TextField à remplir par l'utilisateur
        final TextField lastName = new TextField();
        final TextField firstName = new TextField();
        final TextField birthday = new TextField();        
        final TextField motherTongue = new TextField();
        final TextField yearStudying = new TextField();
        
        //Date format
        birthday.setPromptText("Par exemple : 12/07/1998");
        final SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        
        //Bouton d'accès à la suite du programme
        Button btn = new Button();
        btn.setText("Dis moi qui est ce ?");
        
        //Action du bouton
        btn.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                
                //Récupération données dans les champs
                String ln = lastName.getText().toString();
                String fn = firstName.getText().toString();
                String mt = motherTongue.getText().toString();
                try {
                    Date date = format.parse(birthday.getText());
                } catch (ParseException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                }
                int ys = Integer.parseInt(yearStudying.getText().toString());
                // Création de l'utilisateur
                User us = new User(ln,fn,"26/02/1991",mt,ys);
                // Affichage pour voir si ajout OK               
                System.out.println(us.toString());
            }
        });
        
        
        //Création + ajout au layout
        GridPane root = new GridPane();
        root.add(lLN,1,1);
        root.add(lFN,1,2);
        root.add(lB,1,3);
        root.add(lMT,1,4);
        root.add(lYS,1,5);
        root.add(lastName,2,1);
        root.add(firstName,2,2);
        root.add(birthday,2,3);
        root.add(motherTongue,2,4);
        root.add(yearStudying,2,5);
        root.add(btn,2,6);
        
        //Création de la scène avec ajour du layout
        Scene scene = new Scene(root, 300, 250);
        
        //Configuration du Stage
        primaryStage.setTitle("Hello San Francisco");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);  
    }
    
}

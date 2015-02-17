/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import Result.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
        
        //Création des différent champs à remplir par l'utilisateur
        final TextField lastName = new TextField();
        final TextField firstName = new TextField();
            //DatePicker birthday = new DatePicker();
        final TextField motherTongue = new TextField();
        final TextField yearStudying = new TextField();
        
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
                int ys = Integer.parseInt(yearStudying.getText().toString());
                // Création de l'utilisateur
                User us = new User(ln,fn,"26/02/1991",mt,ys);
                // Affichage pour voir si ajout OK               
                System.out.println(us.toString());
            }
        });
        
        
        //Création + ajout au layout
        VBox root = new VBox();
        root.getChildren().add(lastName);
        root.getChildren().add(firstName);
        root.getChildren().add(motherTongue);
        root.getChildren().add(yearStudying);
        root.getChildren().add(btn);
        
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

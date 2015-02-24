/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Result.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Thibaut
 */
public class UserGUI {

    private Stage stage;
    
    public UserGUI(Stage primaryStage) {
        this.stage=primaryStage;
        this.launchUserGUI();
    }
    
    private void launchUserGUI() {
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
        
        
        //Groupe de Radio Buttons
        final ToggleGroup choose = new ToggleGroup();

        //Création + personnalisation des radio buttons
        final RadioButton french = new RadioButton();
        french.setText("French");
        french.setId("French");
        french.setToggleGroup(choose);
        final RadioButton japanese = new RadioButton();
        japanese.setText("Japanese");
        japanese.setId("Japanese");
        japanese.setToggleGroup(choose);
        final RadioButton english = new RadioButton();
        english.setText("English");
        english.setId("English");
        english.setToggleGroup(choose);
        final RadioButton portuguese = new RadioButton();
        portuguese.setText("Portuguese");
        portuguese.setId("Portuguese");
        portuguese.setToggleGroup(choose);
        
        
        //Bouton d'accès à la suite du programme
        Button access = new Button();
        access.setText("Accéder");
        
        //Action du bouton
        access.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                
                /*if (lastName.equals("") && firstName.equals("") && motherTongue.equals("") && birthday.equals("") && yearStudying.equals("") && choose.getSelectedToggle().isSelected()==false){
                    //We'll se, mettre une croix à coté de celui pas ou mal rempli
                }
                else{
                    
                    //Récupération données dans les champs
                    String ln = lastName.getText();
                    String fn = firstName.getText();
                    String mt = motherTongue.getText();
                    String bd = birthday.getText();
                    int ys = Integer.parseInt(yearStudying.getText());*/

                    // Création de l'utilisateur
                    //User us = new User(ln,fn,bd,mt,ys);
                    
                    // Affichage du bouton sélectionné.
                    //System.out.println(choose.selectedToggleProperty().toString());

                    //Affichage pour voir si ajout OK
                    //System.out.println(us.toString());
                    
                    ChooseGUI cGUI = new ChooseGUI(stage);
                    /*stage.setScene(scene);
                    stage.show();*/
                //}
            }
        });

        //Création layout
        GridPane root = new GridPane();
        GridPane user = new GridPane();
        VBox language = new VBox();
        
        //Affectation Layout root
        root.add(user,1,2);
        root.add(language, 2, 1);
        root.add(access,2,2);
        
        //Affectation Layout language
        language.getChildren().add(english);
        language.getChildren().add(french);
        language.getChildren().add(japanese);
        language.getChildren().add(portuguese);
        
        
        //Affection Layout user
        user.add(lLN,1,1);
        user.add(lFN,1,2);
        user.add(lB,1,3);
        user.add(lMT,1,4);
        user.add(lYS,1,5);
        user.add(lastName,2,1);
        user.add(firstName,2,2);
        user.add(birthday,2,3);
        user.add(motherTongue,2,4);
        user.add(yearStudying,2,5);
        
        
        //Création de la scène avec ajour du layout
        //Scene scene = new Scene(root, 300, 250);
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setResizable(true);
        
        this.stage.hide();
        this.stage.show();
    }
    
}

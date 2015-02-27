/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BDD.DataBase;
import BDD.Language;
import Errors.Errors;
import Result.Extraction;
import Result.User;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Thibaut
 */
public class UserGUI {

    private final Stage stage;
    
    public UserGUI(Stage primaryStage) {
        this.stage=primaryStage;
        this.launchUserGUI(this.createDataBase());
    }
    
    private void launchUserGUI(final DataBase db) {
        
        final Errors erreurs = new Errors();
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
        french.setUserData(new Language("french"));
        french.setToggleGroup(choose);
        final RadioButton japanese = new RadioButton();
        japanese.setText("Japanese");
        japanese.setUserData(new Language("japanese"));
        japanese.setToggleGroup(choose);
        final RadioButton english = new RadioButton();
        english.setText("English");
        english.setUserData(new Language("english"));
        english.setToggleGroup(choose);
        final RadioButton portuguese = new RadioButton();
        portuguese.setText("Portuguese");
        portuguese.setUserData(new Language("portugese"));
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
                else{ */           
                    //Récupération données dans les champs
                   String ln = lastName.getText();
                    if (erreurs.ErrorsMessages(ln) != null){
                       javax.swing.JOptionPane.showMessageDialog(null,"Le nom est incorrect");
                    };
                    String fn = firstName.getText();
                    if (erreurs.ErrorsMessages(fn) != null){
                       javax.swing.JOptionPane.showMessageDialog(null,"Le prénom est incorrect");
                    };
                    
                    String mt = motherTongue.getText();
                    if (erreurs.ErrorsMessages(mt) != null){
                       javax.swing.JOptionPane.showMessageDialog(null,"La langue est incorrect");
                    };
                    
                    String bd = birthday.getText();
                    try{
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy" );
                        dateFormat.parse(bd);
                    } catch (ParseException ex) {
                        javax.swing.JOptionPane.showMessageDialog(null,"Le birthday est incorrect");
                    }
                    
                    try{
                        Integer ys = Integer.parseInt(yearStudying.getText());
                         if (ys == null  && erreurs.ErrorsMessages(ys) != null){
                           javax.swing.JOptionPane.showMessageDialog(null,"L'age est incorrect");
                        };
                    }
                    catch (Exception e){
                       javax.swing.JOptionPane.showMessageDialog(null,"L'age est incorrect");
                    }
                    // Création de l'utilisateur
                    //User us = new User(ln,fn,bd,mt,ys);

                    // Affichage du bouton sélectionné.
                    Language languageSelect = (Language)choose.selectedToggleProperty().get().getUserData();


                    //Affichage pour voir si ajout OK
                    //Extraction medias = new Extraction("..\\pdpafkv\\src\\Result\\Résultats.txt");
                    //medias.extraire();

                    ChooseGUI cGUI = new ChooseGUI(stage, languageSelect, db);
                //}
            }
        });

        //Création layout
        GridPane root = new GridPane();
        GridPane user = new GridPane();
        VBox language = new VBox();
        
         language.setStyle("-fx-background-color: palegreen; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
        //Affectation Layout root
        root.add(user,0,1);
        root.add(language,1,0);
        root.add(access,1,1);
        
        
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
        Scene scene = new Scene(root,650,350);
        
        this.stage.setScene(scene);
        this.stage.setResizable(true);
        this.stage.centerOnScreen();
        this.stage.hide();
        this.stage.show();
    }
    
    private DataBase createDataBase(){
        return new DataBase();
    }
    
}

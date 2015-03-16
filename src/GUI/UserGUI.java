/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BDD.*;

import Errors.Errors;
import Result.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Thibaut
 */
public class UserGUI {

    private final Stage stage;
    
    private int firstMistake = 0;
    private int secondMistake = 0;
    private int thirdMistake = 0;
    private int fourthMistake = 0;
    private int fifthMistake = 0;
    private TextField lastName = new TextField();
    private TextField firstName = new TextField();
    private TextField birthday = new TextField();        
    private TextField motherTongue = new TextField();
    private TextField yearStudying = new TextField();
    private Language languageSelect;
            
    public UserGUI(Stage primaryStage) {
        
        this.stage=primaryStage;
        
        this.launchUserGUI(this.createDataBase());
        
        //TextField Design
        lastName.setPrefWidth(200);
        lastName.setPrefHeight(30);
        firstName.setPrefHeight(30);
        birthday.setPrefHeight(30);
        motherTongue.setPrefHeight(30);
        yearStudying.setPrefHeight(30);    
    }
    
    private void launchUserGUI(final DataBase db) {
        
        final Errors erreurs = new Errors();
        //label's design
        Label lLN = new Label("Last Name");
        Label lFN = new Label("First Name");
        Label lB = new Label("Birthday");
        Label lMT = new Label("Mother Tongue");
        Label lYS = new Label("Years Studying");
        
        //character font
        Font font = new Font("Arial",14);
        
        //character font design
        //lLN.setStyle("-fx-font-weight: bold;");
        //lLN.setFont(new Font("Serif", 14));
        //lFN.setStyle("-fx-font-weight: bold;");
        //lFN.setFont(new Font("Serif", 14));
        //lB.setStyle("-fx-font-weight: bold;");
        //lB.setFont(new Font("Serif", 14));
        //lMT.setStyle("-fx-font-weight: bold;");
        //lMT.setFont(new Font("Serif", 14));
        //lYS.setStyle("-fx-font-weight: bold;");
        //lYS.setFont(new Font("Serif", 14));
        
        //Date format
        birthday.setPromptText("Example : 12/07/1998");
        
        //Radio Buttons group
        final ToggleGroup choose = new ToggleGroup();
       
        //Language layout
        VBox language = new VBox();
        
        ArrayList<Language> listL = db.getAllLanguages();        
        for (Language l : listL){
            //the text of the radiobutton
            RadioButton tmpRB = new RadioButton(l.getName());
            //add audio object in tmpRB
            tmpRB.setUserData(l);
            //add tmpRB in toggle group
            tmpRB.setToggleGroup(choose);
            //customization of tmpRB
            tmpRB.setFocusTraversable(false);
            //add tmpRB in VBox
            language.getChildren().add(tmpRB);
        }
        
        
        
        //Access button to go on next interface
        Button access = new Button("Acces");
        access.setPrefSize(100, 40);
        //access.setStyle("-fx-background-color:lightgrey;-fx-font: 20 arial;-fx-border-radius: 5;-fx-border-color: grey;");
              
        
        //Action button
        access.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                /*if (lastName.equals("") && firstName.equals("") && motherTongue.equals("") && birthday.equals("") && yearStudying.equals("") && choose.getSelectedToggle().isSelected()==false){
                    //We'll se, mettre une croix à coté de celui pas ou mal rempli
                }
                else{ */           
                    //Récupération données dans les champs
                /*   String ln = lastName.getText();
                    if (erreurs.ErrorsMessages(ln) != null){
                        System.out.println("Le nom est incorrect");
                       fauteA ++;
                    }
                    else{
                       if(fauteA != 0){
                           fauteA --; 
                       }
                    }
                    
                    String fn = firstName.getText();
                    if (erreurs.ErrorsMessages(fn) != null){
                        System.out.println("Le prénom est incorrect");
                       fauteB ++;
                    }
                    else{
                       if(fauteB != 0){
                           fauteB --; 
                       } 
                    }
                    
                    String mt = motherTongue.getText();
                    if (erreurs.ErrorsMessages(mt) != null){
                        System.out.println("La langue est incorrect");
                       fauteC ++;
                    }
                    else{
                      if(fauteC != 0){
                           fauteC --; 
                       }
                    }
                    
                    String bd = birthday.getText();
                    
                    if (erreurs.errorDate(bd) == null){
                        if(fauteD != 0){
                           fauteD --; 
                        }
                    }
                    else {
                         System.out.println("Le birthday est incorrect");
                        fauteD ++;
                    }
                    
                    try{
                        Integer ys = Integer.parseInt(yearStudying.getText());
                         if (erreurs.ErrorsMessages(ys) != null){
                            System.out.println("Le study est incorrect");
                           fauteE ++;
                         }
                         else{
                            if(fauteE != 0){
                           fauteE --; 
                       }
                         }
                    }
                    catch (Exception e){
                        System.out.println("Le study est incorrect");
                       fauteE ++;
                    }
                    // Création de l'utilisateur
                    //User us = new User(ln,fn,bd,mt,ys);
                    // Affichage du bouton sélectionné.
                   
                    if (choose.getSelectedToggle() == null){
                        System.out.println("selectionner une langue");
                    }
                    else{
                         languageSelect = (Language)choose.selectedToggleProperty().get().getUserData();
                    }

                    //Affichage pour voir si ajout OK
                    //Extraction medias = new Extraction("..\\pdpafkv\\src\\Result\\Résultats.txt");
                    //medias.extraire();
                    if (fauteA == 0 && fauteB == 0 && fauteC == 0 && fauteD == 0 && fauteE == 0 && languageSelect != null){
                       */ 
                        User user = new User("Thibaut","Fabre","26/02/1991","French",1);
                       languageSelect = (Language)choose.getSelectedToggle().getUserData();
                    try {
                        
                        File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem("Result\\"+user.getNameToFile()+"_"+languageSelect.getName()+".json"));
                        System.out.println(f.getAbsolutePath());
                    } catch (Exception e) {
                        System.out.println("Problème création." + e.getMessage());
                    }
                       new ChooseGUI(stage,languageSelect,db,user);
                       
                        /*
                        erreurs.ErrorsOs();
                    }
                    
                //}*/
            }
        });

        //Layout design
        GridPane root = new GridPane();
        GridPane user = new GridPane();
        BorderPane global= new BorderPane();
        
        
        //Layout user assignement
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
        
        //GUI background
        //global.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%,lightgrey, white) ; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2");
        root.setAlignment(Pos.CENTER);
        //root.setHgap(20);
        //root.setVgap(20);
        
        //Text on top in primaryStage
        Text title = new Text("The Prosodic Adventure");
        //title.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        //title.setStyle("-fx-alignment: center;");
        //title.setFill(Paint.valueOf("#22427C"));
        
        //Text left part gridPane
        Text titleInformations = new Text("User's Information");
        //titleInformations.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        //titleInformations.setStyle("-fx-alignment: center;");
        //titleInformations.setFill(Paint.valueOf("#22427C"));
        
        //Text right part gridPane
        Text titleLangue = new Text("User's language");
        //titleLangue.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        //titleLangue.setStyle("-fx-alignment: center;");
        //titleLangue.setFill(Paint.valueOf("#22427C"));
        
        //Constraints
        //root.setHalignment(titleInformations,HPos.CENTER);
        //root.setMargin(titleInformations,new Insets(0,0,20,0));
        //root.setHalignment(titleLangue,HPos.CENTER);
        //root.setMargin(titleLangue,new Insets(0,0,20,50));
        //root.setMargin(language,new Insets(0,0,0,100));
        
        //Layout root assignement
        root.add(user,0,2);
        root.add(language,1,2);
        root.add(access,1,3);
        root.add(titleInformations,0,1);
        root.add(titleLangue,1,1);
        
        //definition elements in the principal layout
        global.setTop(title);
        //global.setAlignment(title,CENTER);
        //global.setMargin(title, new Insets(20,0,20,0));
        global.setCenter(root);
        //global.setAlignment(root,CENTER);
        //global.setMargin(root, new Insets(0,0,0,20));
        
        //Scene layout and addition of global
        Scene scene = new Scene(global);
        
        /*File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem("src/GUI/caspian.css"));
        scene.getStylesheets().clear();
        scene.getStylesheets().add(f.toURI().toString());*/
         File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem("src/GUI/stylesheet.css"));
        scene.getStylesheets().clear();
        scene.getStylesheets().add(f.toURI().toString());
        
        access.setId("access");
        user.setId("user");
        language.setId("language");
        
        
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        this.stage.setTitle("projet prosodie");
        
        //set Stage boundaries to visible bounds of the main screen
        /*Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        this.stage.setX(primaryScreenBounds.getMinX());
        this.stage.setY(primaryScreenBounds.getMinY());
        this.stage.setWidth(primaryScreenBounds.getWidth());
        this.stage.setHeight(primaryScreenBounds.getHeight());*/
        
        this.stage.show();  
        this.stage.setFullScreenExitHint("");
        //this.stage.setFullScreen(true);
    }
    
    //DataBase design
    private DataBase createDataBase(){
        return new DataBase();
    }
    
    
    
}

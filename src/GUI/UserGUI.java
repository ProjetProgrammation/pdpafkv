/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BDD.*;

import Errors.Errors;
import Result.User;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonWriter;
import java.io.File;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.apache.commons.io.FilenameUtils;

/**
 * Cette classe perme de gérer l'interface graphique récupérant les informations sur l'utilisateur
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
    }
    
    /**
     * Cette méthode permet de créer l'interface graphique pour la page d'identification de l'utilisateur
     * @param db La base de données contenant les médias
     */
    private void launchUserGUI(final DataBase db) {
        
        final Errors errors = new Errors();
        
        //Pré-remplissage du champ Birthday
        birthday.setPromptText("Example : 12/07/1998");

        //Titre de la page
        Label title = new Label("Prosodic Adventure".toUpperCase());
        
        //Titres des box
        Label titleInformations = new Label("ABOUT YOU  ");
        Label titleLangue = new Label("TEST LANGUAGE  ");
       
        //Création de la boites pour l'organisation de l'interface
        VBox vBoxLanguage = new VBox();
        GridPane root = new GridPane();
        GridPane user = new GridPane();
        BorderPane global= new BorderPane();
        
        //Création des Labels pour la case user
        Label labelLastName = new Label("Last Name".toUpperCase());
        Label labelFirstName = new Label("First Name".toUpperCase());
        Label labelBirthday = new Label("Birthday".toUpperCase());
        Label labelMotherTongue = new Label("Mother Tongue".toUpperCase());
        Label labelYearStudying = new Label("Years Studying".toUpperCase());
        
        //Ajout des classes de style
        title.getStyleClass().add("label-header");
        titleInformations.getStyleClass().add("label-header");
        titleLangue.getStyleClass().add("label-header");
        labelLastName.getStyleClass().add("label-bright");
        labelFirstName.getStyleClass().add("label-bright");
        labelBirthday.getStyleClass().add("label-bright");
        labelMotherTongue.getStyleClass().add("label-bright");
        labelYearStudying.getStyleClass().add("label-bright");
        
        user.getStyleClass().add("box");
        vBoxLanguage.getStyleClass().add("box");
                
        //Création du bouton NEXT
        Button access = new Button("NEXT");
        access.setPrefSize(100, 40);
        
        //Création d'un groupe de boutons radio
        final ToggleGroup choose = new ToggleGroup();
        
        //Récuération des langues et création des boutons radio
        ArrayList<Language> listL = db.getAllLanguages();        
        for (Language l : listL){
            RadioButton tmp = new RadioButton(l.getName().toUpperCase());
            tmp.getStyleClass().add("radio-button");
            tmp.setUserData(l);
            tmp.setToggleGroup(choose);
            tmp.setFocusTraversable(false);
            vBoxLanguage.getChildren().add(tmp);
        }
        
        //Gestion d'évenement lors de l'appui sur le bouton NEXT
        access.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                /*if (lastName.equals("") && firstName.equals("") && motherTongue.equals("") && birthday.equals("") && yearStudying.equals("") && choose.getSelectedToggle().isSelected()==false){
                    //We'll se, mettre une croix à coté de celui pas ou mal rempli
                }
                else{ */           
                    //Récupération données dans les champs
                /*   String ln = lastName.getText();
                    if (errors.ErrorsMessages(ln) != null){
                        System.out.println("Le nom est incorrect");
                       fauteA ++;
                    }
                    else{
                       if(fauteA != 0){
                           fauteA --; 
                       }
                    }
                    
                    String fn = firstName.getText();
                    if (errors.ErrorsMessages(fn) != null){
                        System.out.println("Le prénom est incorrect");
                       fauteB ++;
                    }
                    else{
                       if(fauteB != 0){
                           fauteB --; 
                       } 
                    }
                    
                    String mt = motherTongue.getText();
                    if (errors.ErrorsMessages(mt) != null){
                        System.out.println("La langue est incorrect");
                       fauteC ++;
                    }
                    else{
                      if(fauteC != 0){
                           fauteC --; 
                       }
                    }
                    
                    String bd = birthday.getText();
                    
                    if (errors.errorDate(bd) == null){
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
                         if (errors.ErrorsMessages(ys) != null){
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
                
                    if (fauteA == 0 && fauteB == 0 && fauteC == 0 && fauteD == 0 && fauteE == 0 && languageSelect != null){
                       */ 
                        User user = new User("Thibaut","Fabre","26/02/1991","French",1);
                       languageSelect = (Language)choose.getSelectedToggle().getUserData();
                    try {
                        File f = new File("..\\Result\\"+user.getNameToFile()+"_"+languageSelect.getName()+".json");
                        FileOutputStream oFile = new FileOutputStream(f.getAbsoluteFile(), true);
                        FileWriter fw = new FileWriter(f);
                        
                        JsonWriter json = new JsonWriter(fw);
                        json.beginObject();
                        json.name("User").value(user.getFirstName());
                        json.endObject();
                    } catch (Exception e) {
                        System.out.println("Problème création." + e.getMessage());
                    }
                       new ChooseGUI(stage,languageSelect,db,user);
                       
                        /*
                        errors.ErrorsOs();
                    }
                    
                //}*/
            }
        });

        
        //Remplissage des boites pour l'organisation de l'interface
        user.add(labelLastName,1,1);
        user.add(labelFirstName,1,2);
        user.add(labelBirthday,1,3);
        user.add(labelMotherTongue,1,4);
        user.add(labelYearStudying,1,5);
        user.add(lastName,2,1);
        user.add(firstName,2,2);
        user.add(birthday,2,3);
        user.add(motherTongue,2,4);
        user.add(yearStudying,2,5);
        root.add(user,0,2);
        root.add(vBoxLanguage,1,2);
        root.add(access,1,3);
        root.add(titleInformations,0,1);
        root.add(titleLangue,1,1);
        global.setTop(title);
        global.setCenter(root);
        
        //Positionnement de root sur la page
        root.setAlignment(Pos.CENTER);
        
        //Ajout de tout le contenu dans la scene
        Scene scene = new Scene(global);
        
        //Chargement de la police pour le design
        Font.loadFont(UserGUI.class.getResource("HelveticaNeueLTStd-LtCn.ttf").toExternalForm(), 10);

        //Création du lien vers la feuille de style
        File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem("src/GUI/DarkStyle.css"));
        scene.getStylesheets().clear();
        scene.getStylesheets().add(f.toURI().toString());
        
        //Initialisation de la fenêtre
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        this.stage.setTitle("The Prosodic Adventure");
        
        //Définition de la taille de la fenêtre
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        this.stage.setX(primaryScreenBounds.getMinX());
        this.stage.setY(primaryScreenBounds.getMinY());
        this.stage.setWidth(primaryScreenBounds.getWidth());
        this.stage.setHeight(primaryScreenBounds.getHeight());
        
        this.stage.show();
    }
    
    //Ouverture de la base de données
    private DataBase createDataBase(){
        return new DataBase();
    }
    
    
    
}

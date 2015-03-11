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
import com.sun.prism.paint.Color;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import static javafx.scene.control.ContentDisplay.TOP;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Thibaut
 */
public class UserGUI {

    private final Stage stage;

    private int fauteA = 0;
    private int fauteB = 0;
    private int fauteC = 0;
    private int fauteD = 0;
    private int fauteE = 0;
    private TextField lastName = new TextField();
    private TextField firstName = new TextField();
    private TextField birthday = new TextField();        
    private TextField motherTongue = new TextField();
    private TextField yearStudying = new TextField();
    private Language languageSelect;
    private final UserGUI user = this;
            
    public UserGUI(Stage primaryStage) {
        this.stage=primaryStage;
        this.launchUserGUI(this.createDataBase());
        
        lastName.setPrefWidth(200);
        lastName.setPrefHeight(30);
        firstName.setPrefHeight(30);
        birthday.setPrefHeight(30);
        motherTongue.setPrefHeight(30);
        yearStudying.setPrefHeight(30);    
    }
    
    private void launchUserGUI(final DataBase db) {
        
        final Errors erreurs = new Errors();
        
        //Création des labels
        Label lLN = new Label("Last Name");
        Label lFN = new Label("First Name");
        Label lB = new Label("Birthday");
        Label lMT = new Label("Mother Tongue");
        Label lYS = new Label("Years Studying");
        
        Font font = new Font("Arial",14);
        
        lLN.setStyle("-fx-font-weight: bold;");
        lLN.setFont(new Font("Serif", 14));
        lFN.setStyle("-fx-font-weight: bold;");
        lFN.setFont(new Font("Serif", 14));
        lB.setStyle("-fx-font-weight: bold;");
        lB.setFont(new Font("Serif", 14));
        lMT.setStyle("-fx-font-weight: bold;");
        lMT.setFont(new Font("Serif", 14));
        lYS.setStyle("-fx-font-weight: bold;");
        lYS.setFont(new Font("Serif", 14));
        
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
       
        Button access = new Button("Accés");
        access.setPrefSize(100, 40);
        access.setStyle("-fx-background-color:lightgrey;-fx-font: 20 arial;-fx-border-radius: 5;-fx-border-color: grey;");
        
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
                        ChooseGUI cGUI = new ChooseGUI(stage, languageSelect, db, user);
                        erreurs.ErrorsOs();
                    }
                    
                //}
            }
        });

        //Création layout
        GridPane root = new GridPane();
        GridPane user = new GridPane();
        VBox language = new VBox();
        BorderPane global= new BorderPane();
       
        //Affectation Layout root
        
        root.add(user,0,2);
        root.add(language,1,2);
        root.add(access,1,3);
        
        
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
        
        //GUI
        //user.setStyle("-fx-background-color: #FFE082; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2; -fx-border-color: #000000;");
       // language.setStyle("-fx-background-color: #FFE082; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2; -fx-border-color: #000000;");
        global.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%,lightgrey, white) ; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2");
        root.setHgap(20);
        root.setVgap(20);
          
        Text title = new Text("Test prosodique");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        title.setStyle("-fx-alignment: center;");
        title.setFill(Paint.valueOf("#22427C"));
        
        Text titleInformations = new Text("User's Information");
        titleInformations.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleInformations.setStyle("-fx-alignment: center;");
        titleInformations.setFill(Paint.valueOf("#22427C"));
        
        Text titleLangue = new Text("User's language");
        titleLangue.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLangue.setStyle("-fx-alignment: center;");
        titleLangue.setFill(Paint.valueOf("#22427C"));
        
        root.add(titleInformations,0,1);
        root.setHalignment(titleInformations,HPos.CENTER);
        root.setMargin(titleInformations,new Insets(0,0,20,0));
        
        root.add(titleLangue,1,1);
        root.setHalignment(titleLangue,HPos.CENTER);
        root.setMargin(titleLangue,new Insets(0,0,20,50));
        root.setMargin(language,new Insets(0,0,0,100));
        
        global.setTop(title);
        global.setAlignment(title,CENTER);
        global.setMargin(title, new Insets(20,0,20,0));
        
        global.setCenter(root);
        global.setAlignment(root,CENTER);
        global.setMargin(root, new Insets(0,0,0,20));
       //Création de la scène avec ajour du layout
        Scene scene = new Scene(global,600, 400);
        
        root.setAlignment(Pos.CENTER);
        
        this.stage.setScene(scene);
        //this.stage.setResizable(true);
        this.stage.centerOnScreen();
        this.stage.setTitle("projet prosodie");
        this.stage.hide();
        this.stage.show();
        this.stage.setFullScreen(true);
    }
    
    private DataBase createDataBase(){
        return new DataBase();
    }
    
    public String nom(){
        return firstName.getText();
    }
    
    public String prénom(){
        return lastName.getText();
    }
    
}

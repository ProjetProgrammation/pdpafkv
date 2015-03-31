/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BDD.*;
import Controller.ControllerDatabase;

import Errors.Errors;
import Result.User;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class allows to manage the graphic interface which gathers user
 * information
 *
 * @author Jeremy
 */
public class UserGUI {

    private final Stage stage;
    private final ControllerDatabase db;
    private Language languageSelect;
    private int lastNameMistake = 0;
    private int firstNameMistake = 0;
    private int birthdayMistake = 0;
    private int motherTongueMistake = 0;
    private int yearStudyingMistake = 0;
    private int globalMistake = 0;

    /**
     * Contructs a new ChooseGUI.
     *
     * @param primaryStage The stage of the interface.
     */
    public UserGUI(Stage primaryStage) {
        this.stage = primaryStage;
        this.db = new ControllerDatabase();
        this.launchUserGUI(this.db);
    }

    /**
     * This method allows to create graphic user interface for the
     * identification page of the user. This interface contains all essential
     * components for the user and their statements
     *
     * @param db The BDD which contains media
     */
    private void launchUserGUI(final ControllerDatabase db) {

        //Components design
        final ToggleGroup choose = new ToggleGroup();
        Label titleInterface = new Label("Prosodic Adventure".toUpperCase());
        Label titleInformations = new Label("ABOUT YOU  ");
        Label titleLangue = new Label("TEST LANGUAGE  ");
        Button access = new Button("NEXT");
        access.setPrefSize(100, 40);

        //Create differents containers
        VBox vBoxLanguage = new VBox();
        GridPane root = new GridPane();
        GridPane user = new GridPane();
        BorderPane global = new BorderPane();

        //TextField's design for user case  
        final TextField lastName = new TextField();
        final TextField firstName = new TextField();
        final TextField birthday = new TextField();
        birthday.setPromptText("Example : 12/07/1998");
        final TextField motherTongue = new TextField();
        final TextField yearStudying = new TextField();
        
        //Add style classes
        lastName.getStyleClass().add("text-field-valid");
        firstName.getStyleClass().add("text-field-valid");
        birthday.getStyleClass().add("text-field-valid");
        motherTongue.getStyleClass().add("text-field-valid");
        yearStudying.getStyleClass().add("text-field-valid");
        
        //Label's design for user case
        Label labelLastName = new Label("Last Name".toUpperCase());
        Label labelFirstName = new Label("First Name".toUpperCase());
        Label labelBirthday = new Label("Birthday".toUpperCase());
        Label labelMotherTongue = new Label("Mother Tongue".toUpperCase());
        Label labelYearStudying = new Label("Years Studying".toUpperCase());       
        
        //Add style classes
        titleInterface.getStyleClass().add("label-header");
        titleInformations.getStyleClass().add("label-header-2");
        titleLangue.getStyleClass().add("label-header-2");
        labelLastName.getStyleClass().add("label-bright");
        labelFirstName.getStyleClass().add("label-bright");
        labelBirthday.getStyleClass().add("label-bright");
        labelMotherTongue.getStyleClass().add("label-bright");
        labelYearStudying.getStyleClass().add("label-bright");

        user.getStyleClass().add("box");
        vBoxLanguage.getStyleClass().add("box");

        //languages recovery and radiobuttons design
        ArrayList<Language> listL = db.getLanguageList();
        for (Language l : listL) {
            RadioButton tmp = new RadioButton(l.getName().toUpperCase());
            tmp.getStyleClass().add("radio-button");
            tmp.setUserData(l);
            tmp.setToggleGroup(choose);
            tmp.setFocusTraversable(false);
            vBoxLanguage.getChildren().add(tmp);
        }

        //Managing event since the push on NEXT
        access.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Récupération données dans les champs
                String ln = lastName.getText();
              /*  if (!Errors.errorsMessages(ln)) {
                    lastName.getStyleClass().add("text-field-error");
                    System.out.println("[UserGUI]Wrong last name");
                    lastNameMistake++;
                    globalMistake++;
                } else {
                    if (lastNameMistake != 0) {
                        lastNameMistake--;
                        globalMistake--;
                    }
                }*/

                String fn = firstName.getText();
               /* if (!Errors.errorsMessages(fn)) {
                    firstName.getStyleClass().add("text-field-error");
                    for (int i=0;i<firstName.getStyleClass().size();i++){
                        System.out.println(firstName.getStyleClass().get(i).toString());
                    }
                    System.out.println("[UserGUI]Wrong first name");
                    firstNameMistake++;
                    globalMistake++;
                } else {
                    if (firstNameMistake != 0) {
                        firstNameMistake--;
                        globalMistake--;
                    }
                }*/

                String mt = motherTongue.getText();
               /* if (!Errors.errorsMessages(mt)) {
                    motherTongue.getStyleClass().add("text-field-error");
                    System.out.println("[UserGUI]Wrong mother tongue");
                    motherTongueMistake++;
                    globalMistake++;
                } else {
                    if (motherTongueMistake != 0) {
                        motherTongueMistake--;
                        globalMistake--;
                    }
                }*/

                String bd = birthday.getText();
              /*  if (!Errors.errorDate(bd)) {
                    birthday.getStyleClass().add("text-field-error");
                    System.out.println("[UserGUI]Wrong birthday");
                    birthdayMistake++;
                    globalMistake++;
                } else {
                    if (birthdayMistake != 0) {
                        birthdayMistake--;
                        globalMistake--;
                    }
                }*/
                
                Integer ys=0;
              /* try{
                    ys = Integer.parseInt(yearStudying.getText());
                    if (!Errors.errorsMessages(ys)) {
                        yearStudying.getStyleClass().add("text-field-error");
                        System.out.println("[UserGUI]Wrong years studying");
                        yearStudyingMistake++;
                        globalMistake++;
                    } else {
                        if (yearStudyingMistake != 0) {
                            yearStudyingMistake--;
                            globalMistake--;
                        }
                }
                }catch(NumberFormatException ex){
                    System.out.println("[UserGUI]String in TextField yearsStudying");
                }
*/
                //
                if (choose.getSelectedToggle() == null) {
                    System.out.println("[UserGUI]Select one language");
                } else {
                    languageSelect = (Language) choose.selectedToggleProperty().get().getUserData();
                }
                
                //
                if (globalMistake == 0 && languageSelect != null) {

                    //User user = new User("Thibaut", "Fabre", "26/02/1991", "French", 1);
                    //languageSelect = (Language) choose.getSelectedToggle().getUserData();
                    User user = new User(ln,fn,bd,mt,ys);
                    new ChooseGUI(stage, languageSelect, db, user);

                }

            }
        }
        );

        //Organize the interface
        root.setAlignment(Pos.CENTER);

        user.add(labelLastName,1, 1);
        user.add(labelFirstName,1, 2);
        user.add(labelBirthday,1, 3);
        user.add(labelMotherTongue,1, 4);
        user.add(labelYearStudying,1, 5);
        user.add(lastName,2, 1);
        user.add(firstName,2, 2);
        user.add(birthday,2, 3);
        user.add(motherTongue,2, 4);
        user.add(yearStudying, 2, 5);
        
        root.add(user,0, 2);
        root.add(vBoxLanguage, 1, 2);
        root.add(access, 1, 3);
        root.add(titleInformations, 0, 1);
        root.add(titleLangue, 1, 1);

        global.setTop(titleInterface);
        global.setCenter(root);

        //Add container to the scene
        this.stage.getScene().setRoot(global);

    }

}

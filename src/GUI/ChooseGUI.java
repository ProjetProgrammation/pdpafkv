package GUI;
import BDD.DataBase;
import BDD.Language;
import BDD.Question;
import Controller.SelectMedia;
import Result.User;
import java.io.File;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;




/**
 * Cette classe perme de gérer l'interface graphique pour le choix entre le mode test ou apprentissage.
 * @author Thibaut
 */
public class ChooseGUI {

    private Stage stage;
    private User userSel;
    private BorderPane global= new BorderPane();
    
    public ChooseGUI(Stage primaryStage, Language langSel, DataBase db, User user) {
        this.stage=primaryStage;
        this.userSel = user;
        this.launchChooseGUI(langSel,db);
        
    }

    /**
     * Cette méthode permet de créer l'interface graphique pour le choix entre le mode test ou apprentissage.
     * @param langSel La langue qui a été sélectionnée précédemment
     * @param db La base de données qui contient les médias du test
     */
    private void launchChooseGUI(final Language langSel,final DataBase db) {
        
        //Titre de la page
        Label titleTop = new Label("Prosodic Adventure".toUpperCase());
        
        //Question-titre
        Label titleButtons = new Label("What do you want to do?".toUpperCase());
        
        //Création des boutons
        Button learnOption = new Button("Training".toUpperCase());
        Button testOption = new Button("Not training".toUpperCase());
        learnOption.setPrefSize(150, 50);
        testOption.setPrefSize(150, 50);
        
        //Création des boites pour l'organisation de l'interface
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        BorderPane paneButtons = new BorderPane();
        
        //Ajout des classes de style
        titleTop.getStyleClass().add("label-header");
        titleButtons.getStyleClass().add("label-header");
        
        //Définition de l'action pour le bouton learnOption
        learnOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new TestGUI(stage,5,langSel,db,userSel);
            }
        });
        
        //Définition de l'action pour le bouton testOption
        testOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new TestGUI(stage,2,langSel,db,userSel);
            }
        });
        
        //Remplissage des boites pour l'organisation de l'interface
        root.add(titleButtons, 1, 1);
        paneButtons.setLeft(learnOption);
        paneButtons.setRight(testOption);
        root.add(paneButtons, 1, 2);
        global.setTop(titleTop);
        global.setCenter(root);
        
        //Ajout de tout le contenu dans la scene
        Scene scene = new Scene(global);        
        
        //Loading Helvetica font
        Font.loadFont(UserGUI.class.getResource("HelveticaNeueLTStd-LtCn.ttf").toExternalForm(), 10);

        //Création du lien vers la feuille de style
        File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem("src/GUI/DarkStyle.css"));
        scene.getStylesheets().clear();
        scene.getStylesheets().add(f.toURI().toString());
        
        //background elements
        //global.setId("global");

        //Initialisation de la fenêtre
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        this.stage.setTitle("The Prosodic Adventure");
    }  
    
}

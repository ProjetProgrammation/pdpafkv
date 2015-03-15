package GUI;


import BDD.DataBase;
import BDD.Language;
import BDD.Question;
import Controller.SelectMedia;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;





public class ChooseGUI {

    Stage stage;
    private UserGUI user;
    BorderPane global= new BorderPane();
    private SelectMedia select;
    private Scene scene;
    private ArrayList<Question>questions;
    
    public ChooseGUI(Stage primaryStage, Language langSel, DataBase db, UserGUI user) {
        this.stage=primaryStage;
        this.scene=primaryStage.getScene();
        select = new SelectMedia(db,langSel);
        this.user = user;
        questions = select.SelectQuestionList();
        this.launchChooseGUI(langSel,db);
        
    }

    private void launchChooseGUI(final Language langSel,final DataBase db) {
        
        //Création layout avec boutons
        GridPane root = new GridPane();
        Button learnOption = new Button("Acces");
        Button testOption = new Button("Acces");
        
        //Action button learnOption
        learnOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new TestGUI(stage,5,langSel,db, user,questions);
            }
        });
        
        //Action button learnOption
        testOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
               
                new TestGUI(stage,20,langSel,db, user,questions);
            }
        });
        
        //background fenêtre
        
        //Texte partie haute de l'interface
        Text title = new Text("Test prosodique");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        title.setStyle("-fx-alignment: center;");
        title.setFill(Paint.valueOf("#22427C"));
        
        //Texte partie gauche
        Text titleInformations = new Text("Entrainement");
        titleInformations.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleInformations.setStyle("-fx-alignment: center;");
        titleInformations.setFill(Paint.valueOf("#22427C"));
        
        //Texte partie droite
        Text titleTest = new Text("Test");
        titleTest.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleTest.setStyle("-fx-alignment: center;");
        titleTest.setFill(Paint.valueOf("#22427C"));
        
        //définition de la taille des boutons
        testOption.setPrefSize(150, 50);
        learnOption.setPrefSize(150, 50);
        
        //contraintes
        root.setMargin(titleInformations, new Insets(0,0,0,10));
        root.setMargin(titleTest,new Insets(0,0,0,100));
        root.setMargin(testOption,new Insets(0,0,0,50));
     
        //Add buttons to layout
        root.add(titleInformations,0,1);
        root.add(titleTest,1,1);
        root.add(learnOption, 0, 2);
        root.add(testOption, 1, 2);
        root.setAlignment(Pos.CENTER);
        //root.setGridLinesVisible(true);
        
        //ajout des éléments dans le layout principal
        global.setCenter(root);
        global.setTop(title);
        global.setAlignment(title,CENTER);
        global.setMargin(title, new Insets(20,0,20,0));
        
        
        //Create scene and add to stage
        Scene scene = new Scene(global);        
        scene.getStylesheets().add("stylesheet.css");
        
        //background éléments
        global.setId("global");

        //définition de la scène
        this.stage.setScene(scene);

        this.stage.setResizable(true);
        //this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint("");       
    }  
    
}

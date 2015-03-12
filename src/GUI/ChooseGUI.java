package GUI;


import BDD.DataBase;
import BDD.Language;
import GUI.TestGUI;
import GUI.UserGUI;
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

    private Stage stage;
    private UserGUI user;

    BorderPane global= new BorderPane();

    private Scene scene;

    
    public ChooseGUI(Stage primaryStage, Language langSel, DataBase db, UserGUI user) {
        this.stage=primaryStage;
        this.scene=this.stage.getScene();
        this.launchChooseGUI(langSel,db);
        this.user = user;
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
                TestGUI tGUI = new TestGUI(stage,5,langSel,db, user);
                System.out.println(" le nom du mec est " + user.nom());
            }
        });
        
        //Action button learnOption
        testOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                TestGUI tGUI = new TestGUI(stage,20,langSel,db, user);
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
        Scene scene = new Scene(global,600, 400);

        //background éléments
        global.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%,lightgrey, white) ; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2");
        learnOption.setStyle("-fx-background-color:lightgrey;-fx-font: 20 arial;-fx-border-radius: 5;-fx-border-color: grey;");
        testOption.setStyle("-fx-background-color:lightgrey;-fx-font: 20 arial;-fx-border-radius: 5;-fx-border-color: grey;");
        


        //définition de la scène
        this.stage.setScene(scene);

        //Create scene and add to stage
        //Scene scene = new Scene(root,600, 400);
        //this.stage.setScene(scene);

        this.stage.setResizable(true);
        this.stage.setFullScreen(true);
                
        this.stage.hide();
        this.stage.show();
    }
   
    
    
    
    
    
    
}

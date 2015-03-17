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





public class ChooseGUI {

    private Stage stage;
    private User userSel;
    private BorderPane global= new BorderPane();
    
    public ChooseGUI(Stage primaryStage, Language langSel, DataBase db, User user) {
        this.stage=primaryStage;
        this.userSel = user;
        this.launchChooseGUI(langSel,db);
        
    }

    private void launchChooseGUI(final Language langSel,final DataBase db) {
        
        //Loading Helvetica font
        Font.loadFont(UserGUI.class.getResource("HelveticaNeueLTStd-LtCn.ttf").toExternalForm(), 10);
        
        //Layout design and buttons
        GridPane root = new GridPane();
        Button learnOption = new Button("Training".toUpperCase());
        Button testOption = new Button("Not training".toUpperCase());
        
        //Action button learnOption
        learnOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new TestGUI(stage,5,langSel,db,userSel);
            }
        });
        
        //Action button learnOption
        testOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new TestGUI(stage,2,langSel,db,userSel);
            }
        });
        
        //GUI background
        root.setAlignment(Pos.CENTER);
        
        //Text on top in primaryStage
        Label titleTop = new Label("Prosodic Adventure".toUpperCase());
        titleTop.getStyleClass().add("label-header");
        Label titleButtons = new Label("What do you want to do?".toUpperCase());
        titleButtons.getStyleClass().add("label-header");
        
        //Adding LabelButtons to layout
        root.add(titleButtons, 1, 1);
        
        //size of buttons
        testOption.setPrefSize(150, 50);
        learnOption.setPrefSize(150, 50);
        
        //Add buttons to layout
        BorderPane paneButtons = new BorderPane();
        paneButtons.setLeft(learnOption);
        paneButtons.setRight(testOption);
        root.add(paneButtons, 1, 2);
        //root.setGridLinesVisible(true);
        
        //add items in main layout
        global.setTop(titleTop);
        global.setCenter(root);
        
        
        //Create scene and add to stage
        Scene scene = new Scene(global);        

        File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem("src/GUI/DarkStyle.css"));
        scene.getStylesheets().clear();
        scene.getStylesheets().add(f.toURI().toString());
        
        //background elements
        global.setId("global");

        //scene design
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        this.stage.setTitle("The Prosodic Adventure");
        //this.stage.setResizable(true);
        //this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint("");       
    }  
    
}

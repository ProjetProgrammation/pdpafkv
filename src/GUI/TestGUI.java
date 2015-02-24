package GUI;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TestGUI extends Parent{
        
    private int nombre = 1;
    private int nbQuestion;
    private final Stage stage;
    
    public TestGUI(Stage primaryStage, int nbQuest){
        
        this.stage=primaryStage;
        this.nbQuestion=nbQuest;
        this.launchTestGUI();
        
    }
    
    private void launchTestGUI(){
        
        QuestionGUI question = new QuestionGUI();
        SonGUI son = new SonGUI();
        VideoGUI video = new VideoGUI();
        
   
        //Zone pour les boutons
        Button mix = new Button("Mix");
        Button validate = new Button("Validate");
        
        
        
        GridPane root = new GridPane();
        
        root.add(question, 1, 1, 4, 1);
        root.add(video, 1, 2, 2, 4);
        root.add(son, 3, 2, 4, 3);
        root.add(mix, 3 , 4);
        root.add(validate, 4 , 4);
        
        root.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        
        this.stage.hide();
        this.stage.show();
        
    }
    
}
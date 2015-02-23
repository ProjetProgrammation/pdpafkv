package GUI;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TestGUI extends Parent{
        
    private int nombre = 1;
    private Stage stage;
    
    public TestGUI(Stage primaryStage){
        
        this.stage=primaryStage;
        
        //Zone pour les video
        Rectangle fond_video = new Rectangle();
        fond_video.setWidth(75);
        fond_video.setHeight(70);
        fond_video.setArcWidth(50);
        fond_video.setArcHeight(50);
        fond_video.setFill(Color.WHITE);
        fond_video.setStroke(Color.BLACK);
        
        //Zone pour le son
        Rectangle fond_son = new Rectangle();
        fond_son.setWidth(75);
        fond_son.setHeight(40);
        fond_son.setArcWidth(50);
        fond_son.setArcHeight(50);
        fond_son.setFill(Color.WHITE);
        fond_son.setStroke(Color.BLACK);
        
        //Zone pour les boutons
        Button mix = new Button("Mix");
        Button validate = new Button("Validate");
        
        //Zone pour la question
    
        Rectangle fond_question = new Rectangle();
        fond_question.setWidth(16);
        fond_question.setHeight(15);
        fond_question.setArcWidth(50);
        fond_question.setArcHeight(50);
        fond_question.setFill(Color.WHITE);
        fond_question.setStroke(Color.BLACK);
        
                    
        //Position et ajout
        /*fond_video.setTranslateX(100);
        fond_video.setTranslateY(250);
        
        fond_son.setTranslateX(950);
        fond_son.setTranslateY(250);
        
        fond_question.setTranslateX(100);
        fond_question.setTranslateY(50);
                
        mix.setTranslateX(1200);
        mix.setTranslateY(800);
        validate.setTranslateX(1400);
        validate.setTranslateY(800);
          
        this.getChildren().add(mix);
        this.getChildren().add(validate);
        this.getChildren().add(fond_video);
        this.getChildren().add(fond_question);
        this.getChildren().add(fond_son);*/
        
        BorderPane root = new BorderPane();
        VBox right = new VBox();
        right.getChildren().add(fond_son);
        right.getChildren().add(mix);
        right.getChildren().add(validate);
        
        root.setTop(fond_question);
        root.setLeft(fond_video);
        root.setRight(right);
        
        
        
        
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setResizable(true);
        
        this.stage.hide();
        this.stage.show();
        
    }
}
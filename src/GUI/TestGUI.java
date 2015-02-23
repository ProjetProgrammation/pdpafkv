package GUI;

import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class TestGUI extends Parent{
        
    public TestGUI(){
               
        //Zone pour les video
        Rectangle fond_video = new Rectangle();
        fond_video.setWidth(750);
        fond_video.setHeight(700);
        fond_video.setArcWidth(50);
        fond_video.setArcHeight(50);
        fond_video.setFill( //on remplie notre rectangle avec un dégradé
            new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                new Stop[] {
                    new Stop(0, Color.web("#333333")),
                    new Stop(1, Color.web("#000000"))
                }
            )
        );
        
        //Zone pour le son
        
        Rectangle fond_son = new Rectangle();
        fond_son.setWidth(750);
        fond_son.setHeight(400);
        fond_son.setArcWidth(50);
        fond_son.setArcHeight(50);
        fond_son.setFill( //on remplie notre rectangle avec un dégradé
            new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                new Stop[] {
                    new Stop(0, Color.web("#333333")),
                    new Stop(1, Color.web("#000000"))
                }
            )
        );
        
        //Zone pour les boutons
        Button mix = new Button("Mix");
        Button validate = new Button("Validate");
        
                    
        //Position et ajout
        fond_video.setTranslateX(100);
        fond_video.setTranslateY(250);
        
        fond_son.setTranslateX(950);
        fond_son.setTranslateY(250);
                
        mix.setTranslateX(1200);
        mix.setTranslateY(800);
        validate.setTranslateX(1400);
        validate.setTranslateY(800);
        
        this.getChildren().add(mix);
        this.getChildren().add(validate);
        this.getChildren().add(fond_video);
        this.getChildren().add(fond_son);
        
    }
}
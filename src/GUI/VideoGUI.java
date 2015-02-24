package GUI;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Thibaut
 */
public class VideoGUI extends Parent {

    private RadioButton b1;
    private RadioButton b2;
    private RadioButton b3;
    private RadioButton b4;
    private RadioButton b5;
    private RadioButton b6; 
    
    public VideoGUI() {
        this.launchVideoGUI();
    }
    
    private void launchVideoGUI(){
        
    
        GridPane gridpane = new GridPane();
        
        //Zone pour les video
        Rectangle fond_video = new Rectangle();
        fond_video.setWidth(750);
        fond_video.setHeight(700);
        fond_video.setArcWidth(50);
        fond_video.setArcHeight(50);
        fond_video.setFill(Color.WHITE);
        fond_video.setStroke(Color.BLACK);

        //création des boutons
        ToggleGroup groupe = new ToggleGroup();
        b1 = new RadioButton();
        b2 = new RadioButton();
        b3 = new RadioButton();
        b4 = new RadioButton();
        b5 = new RadioButton();
        b6 = new RadioButton();
        b1.setToggleGroup(groupe);
        b2.setToggleGroup(groupe);
        b3.setToggleGroup(groupe);
        b4.setToggleGroup(groupe);
        b5.setToggleGroup(groupe);
        b6.setToggleGroup(groupe);
        b1.setFocusTraversable(false);
        b2.setFocusTraversable(false);
        b3.setFocusTraversable(false);
        b4.setFocusTraversable(false);
        b5.setFocusTraversable(false);
        b6.setFocusTraversable(false);
        b1.setSelected(true);
        
        //on ajoute les boutons au layout
        gridpane.setPrefSize(200, 200);
        gridpane.add(b1, 0, 0);
        gridpane.add(b2, 1, 0);
        gridpane.add(b3, 2, 0);
        gridpane.add(b4, 0, 1);
        gridpane.add(b5, 1, 1);
        gridpane.add(b6, 2, 1);
        gridpane.setHgap(20);
        
        this.getChildren().add(fond_video);
        this.getChildren().add(gridpane);
    }
}

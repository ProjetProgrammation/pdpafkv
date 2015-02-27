package GUI;

import BDD.DataBase;
import BDD.Language;
import Controller.SelectMedia;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Thibaut
 */
public class VideoGUI extends Parent {
    
    private final SelectMedia controlSM;
    
    public VideoGUI(Language langSel, DataBase db) {
        this.launchVideoGUI();
        this.controlSM = new SelectMedia(db, langSel);
    }
    
    private void launchVideoGUI(){
        
        
        FlowPane fond_video = new FlowPane();
        fond_video.setVgap(8);
        fond_video.setHgap(4);
        fond_video.autosize();        
        //fond_video.setPrefWrapLength(300);
        //fond_video.setPadding(new Insets(15, 12, 15, 12));
        fond_video.setStyle("-fx-background-color: #336699; -fx-border-color: #000000;");
        
        //création des boutons
        ToggleGroup groupVideo = new ToggleGroup();
        GridPane zoneVideo = new GridPane();

        
        
        //on ajoute les boutons au layout
        zoneVideo.autosize();
        zoneVideo.setHgap(20);
        zoneVideo.setAlignment(Pos.CENTER);
                
        fond_video.getChildren().add(zoneVideo);
        this.getChildren().add(fond_video);
        //this.getChildren().add(zoneVideo);
    }
}

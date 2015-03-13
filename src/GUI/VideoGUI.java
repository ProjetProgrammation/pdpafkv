package GUI;

import BDD.DataBase;
import BDD.Language;
import Controller.SelectMedia;
//import com.sun.jna.Native;
//import com.sun.jna.NativeLibrary;
import java.awt.Canvas;
import java.io.File;
//import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;



/**
 *
 * @author Thibaut
 */
public class VideoGUI extends Parent {
    
    private final SelectMedia controlSM;
        
    double width = 426;
    double height = 240;

    Media media;
    MediaPlayer mediaplayer;
    MediaView mediaview;
    
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
        fond_video.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2; -fx-border-color: #000000;");
        
        //création des boutons
        Group group = new Group();
        GridPane zoneVideo = new GridPane();
        
        //
        zoneVideo.autosize();
        zoneVideo.setHgap(20);
        zoneVideo.setAlignment(Pos.CENTER);
        //zoneVideo.add(mediaview, 0, 0);
        
        fond_video.getChildren().add(zoneVideo);
        this.getChildren().add(fond_video);        
    }
}

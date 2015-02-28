package GUI;

import BDD.DataBase;
import BDD.Language;
import Controller.SelectMedia;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;




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
        fond_video.setStyle("-fx-background-color: #FFE082; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2; -fx-border-color: #000000;");
        
        //création des boutons
        ToggleGroup groupVideo = new ToggleGroup();
        GridPane zoneVideo = new GridPane();
        
        Canvas canvas = new Canvas();
        
        /*NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "E:\\Programme\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);*/

        
        
        //on ajoute les boutons au layout
        zoneVideo.autosize();
        zoneVideo.setHgap(20);
        zoneVideo.setAlignment(Pos.CENTER);
        
        
        
        fond_video.getChildren().add(zoneVideo);
        this.getChildren().add(fond_video);
        //this.getChildren().add(zoneVideo);
    }
}

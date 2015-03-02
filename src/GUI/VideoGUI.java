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
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapter;
import uk.co.caprica.vlcj.player.embedded.videosurface.ComponentIdVideoSurface;



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
        
        /*final Media media;
        final MediaPlayer mediaplayer;
        MediaView mediaview;
    
        final ImageView image_bouton;
        final Image img_play;
        final Image img_pause;
    
    
     //ajout d'une video
        Group root = new Group();
        //Media
        media = new Media("E:\\Documents\\Mes Documents\\Divers\\YouTube- Compilation des répliques de Kadoc.mp4");
        
        //MediaPlayer
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setOnReady(new Runnable(){//dès que le lecteur est prêt, on set les start et stop times
            @Override
            public void run() {
                mediaplayer.setStartTime(Duration.ZERO);
                mediaplayer.setStopTime(media.getDuration().subtract(Duration.valueOf("50")));
            }
        });
        mediaplayer.setOnEndOfMedia(new Runnable(){//dès qu'on arrive à la fin du média : stop
            @Override
            public void run() {
                mediaplayer.stop();
            }
        });
        
        //MediaView
        mediaview = new MediaView(mediaplayer);
        mediaview.setFitWidth(426);
        mediaview.setFitHeight(240);
        root.getChildren().add(mediaview);

        
        Group fonctions = new Group();
        
        //création du fond de la barre de fonction
        Rectangle fond = new Rectangle(426,30, Paint.valueOf("#333333"));
        fond.setOpacity(0.5);
        fonctions.getChildren().add(fond);
        fonctions.setTranslateY(240-30);
        root.getChildren().add(fonctions);
        
               //création du bouton play/pause
        Group play_pause = new Group();
        Rectangle fond_bouton = new Rectangle(28,24,Paint.valueOf("#111111"));
        fond_bouton.setArcHeight(5);
        fond_bouton.setArcWidth(5);
        fond_bouton.setStroke(Paint.valueOf("#8FD8D8"));
        img_play = new Image("http://www.pianovirtuel.fr/Site_du_zero/sigleplay.png");
        img_pause = new Image("http://www.pianovirtuel.fr/Site_du_zero/siglepause.png");
        image_bouton = new ImageView(img_play);
        image_bouton.setX(4);
        image_bouton.setY(2);
        play_pause.getChildren().add(fond_bouton);
        play_pause.getChildren().add(image_bouton);
        play_pause.setTranslateX(4);
        play_pause.setTranslateY(4);
        play_pause.setCursor(Cursor.HAND);
        //Quand on clique sur le bouton play/pause, on démarre ou on arrête la vidéo
        play_pause.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                if(mediaplayer.getStatus() == MediaPlayer.Status.valueOf("PLAYING")){//pause
                    image_bouton.setImage(img_play);
                    mediaplayer.pause();
                }
                else{//play
                    image_bouton.setImage(img_pause);
                    mediaplayer.play();
                }
            }
        });
        fonctions.getChildren().add(play_pause);*/
        
        
        //
        zoneVideo.autosize();
        zoneVideo.setHgap(20);
        zoneVideo.setAlignment(Pos.CENTER);
        
        
        
        fond_video.getChildren().add(zoneVideo);
        this.getChildren().add(fond_video);
    }
}

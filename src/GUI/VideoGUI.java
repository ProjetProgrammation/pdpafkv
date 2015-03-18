package GUI;

import BDD.Video;
import Controller.SelectMedia;
import java.io.File;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.apache.commons.io.FilenameUtils;


/**
 *
 * @author Thibaut
 */
public class VideoGUI extends Parent {
    
    private final SelectMedia selMedia;
    private Video videoSelected;
    
    public VideoGUI(SelectMedia selectMedia) {
        this.selMedia=selectMedia;
        this.launchVideoGUI();
    }
    
    private void launchVideoGUI(){

        //Création + personnalisation FlowPane
        GridPane fond_video = new GridPane();
        fond_video.getStyleClass().add("box");
        //fond_video.setVgap(8);
        //fond_video.setHgap(4);
        //fond_video.autosize();
        //fond_son.setPrefWrapLength(300);
        //fond_son.setPadding(new Insets(30, 24, 30, 24));
        //fond_video.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2; -fx-border-color: #000000;");
        //fond_video.setAlignment(Pos.CENTER);

        //Création Button Play
        final Button playVideo = new Button("Preview face");
        playVideo.setDisable(true);
                
        GridPane zoneVideo = new GridPane();
        final ToggleGroup groupVideo = new ToggleGroup();
        ArrayList<RadioButton> listRB = new ArrayList<>();
        for (int i=0; i<10; i++){
            //Sélection d'un audio
            Video videoTmp = this.selMedia.selectVideo();
            //Création RadioButton avec son texte
            RadioButton tmpRB = new RadioButton("Face "+(i+1));
            //Définition de la classe de style
            tmpRB.getStyleClass().add("radio-button");
            //Ajout de l'objet audio dans tmpRB
            tmpRB.setUserData(videoTmp);
            //Ajout du tmpRB dans le groupe Toggle
            tmpRB.setToggleGroup(groupVideo);
            //Personnalisation de tmpRB
            tmpRB.setFocusTraversable(false);
            //Ajout tmpRB dans le GridPane
            if (i<5){
                zoneVideo.add(tmpRB, i, 0);
            }
            else
                zoneVideo.add(tmpRB, i-5, 2);
            listRB.add(tmpRB);
        }
                
        groupVideo.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (groupVideo.getSelectedToggle()!=null){
                    playVideo.setDisable(false);
                    videoSelected = (Video)groupVideo.getSelectedToggle().getUserData();
                }
            }
        });
        
        //Action du bouton
        playVideo.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                    playAction(videoSelected);                   
            }
        });
        
        //Personnalisation du GridPane
        /*zoneVideo.autosize();
        zoneVideo.setHgap(20);
        zoneVideo.setVgap(20);
        zoneVideo.setAlignment(Pos.CENTER);*/
        
        //Ajout à FlowPane
        fond_video.add(zoneVideo, 1, 1);
        fond_video.add(playVideo, 1, 2);
        fond_video.setAlignment(Pos.CENTER);
        this.getChildren().add(fond_video);
    }
    
    private void playAction(Video video){
        File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem(video.getFilePath()));
        MediaPlayer.load(f.getAbsolutePath());
    }
    
    public Video getVideoSelected() {
        return videoSelected;
    }   
}

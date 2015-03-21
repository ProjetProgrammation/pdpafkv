package GUI;

import BDD.Video;
import Controller.SelectMedia;
import java.io.File;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
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

        //Création boite
        FlowPane fond_video = new FlowPane();
        fond_video.getStyleClass().add("box");
        fond_video.setAlignment(Pos.CENTER);

        //Création du boutons de preview
        final Button playVideo = new Button("Preview face");
        playVideo.setDisable(true);
                
        GridPane zoneVideo = new GridPane();
        final ToggleGroup groupVideo = new ToggleGroup();
        ArrayList<RadioButton> listRB = new ArrayList<>();
        for (int i=0; i<10; i++){
            Video videoTmp = this.selMedia.selectVideo();
            RadioButton tmpRB = new RadioButton("Face "+(i+1));
            tmpRB.getStyleClass().add("radio-button");
            tmpRB.setUserData(videoTmp);
            tmpRB.setToggleGroup(groupVideo);
            tmpRB.setFocusTraversable(false);
            if (i<5){
                zoneVideo.add(tmpRB, i, 0);
            }
            else
                zoneVideo.add(tmpRB, i-5, 2);
            listRB.add(tmpRB);
        }
                
        //Activation du bouton de preview quand un bouton radio est sélectionné
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
        
        //Remplissage des boites pour l'organisation de l'interface
        fond_video.getChildren().add(zoneVideo);
        fond_video.getChildren().add(playVideo);
        fond_video.setAlignment(Pos.CENTER);
        this.getChildren().add(fond_video);
        fond_video.getStyleClass().add("div1");
    }
    
    private void playAction(Video video){
        File f = new File(System.getProperty("user.dir"),FilenameUtils.separatorsToSystem(video.getFilePath()));
        MediaPlayer.load(f.getAbsolutePath());
    }
    
    public Video getVideoSelected() {
        return videoSelected;
    }   
}

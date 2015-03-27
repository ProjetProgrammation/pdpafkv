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
 * Use this class to create toggleGroup of video files for the user interface.
 *
 * @author Jeremy
 */
public class VideoGUI extends Parent {

    private final SelectMedia selMedia;
    private Video videoSelected;

    /**
     * Allows to load the media file.
     *
     * @param selectMedia allow to load a entity of selectMedia which allow to
     * choose video files after in the bdd
     */
    public VideoGUI(SelectMedia selectMedia) {
        this.selMedia = selectMedia;
        this.launchVideoGUI();
    }

    private void launchVideoGUI() {

        //Components design
        FlowPane flowVideo = new FlowPane();
        flowVideo.getStyleClass().add("box");
        flowVideo.setAlignment(Pos.CENTER);

        final Button playVideo = new Button("Preview face");
        playVideo.setDisable(true);

        GridPane zoneVideo = new GridPane();

        final ToggleGroup groupVideo = new ToggleGroup();
        ArrayList<RadioButton> listRB = new ArrayList<>();
        //ToggleGroup Design
        for (int i = 0; i < 10; i++) {
            Video videoTmp = this.selMedia.selectVideo();
            RadioButton tmpRB = new RadioButton("Face " + (i + 1));
            tmpRB.getStyleClass().add("radio-button");
            tmpRB.setUserData(videoTmp);
            tmpRB.setToggleGroup(groupVideo);
            tmpRB.setFocusTraversable(false);
            if (i < 5) {
                zoneVideo.add(tmpRB, i, 0);
            } else {
                zoneVideo.add(tmpRB, i - 5, 2);
            }
            listRB.add(tmpRB);
        }

        //Events properties
        groupVideo.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (groupVideo.getSelectedToggle() != null) {
                    playVideo.setDisable(false);
                    videoSelected = (Video) groupVideo.getSelectedToggle().getUserData();
                }
            }
        });

        playVideo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playAction(videoSelected);
            }
        });

        //Add in FlowPane
        flowVideo.getChildren().add(zoneVideo);
        flowVideo.getChildren().add(playVideo);
        flowVideo.setAlignment(Pos.CENTER);
        this.getChildren().add(flowVideo);
        flowVideo.getStyleClass().add("div1");
    }

    /**
     * Plays the video file selected.
     *
     * @param video Video selected
     *
     */
    private void playAction(Video video) {
        File f = new File(System.getProperty("user.dir"), FilenameUtils.separatorsToSystem(video.getFilePath()));
        MediaPlayer.load(f.getAbsolutePath());
    }

    /**
     * Return the video file selected.
     * @return Video
     */
    public Video getVideoSelected() {
        return videoSelected;
    }
}

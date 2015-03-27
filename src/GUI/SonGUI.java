package GUI;

import BDD.Audio;
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
 * Use this class to create toggleGroup of audio files for interface.
 *
 * @author Jeremy
 */
public class SonGUI extends Parent {

    private final SelectMedia selMedia;
    private Audio audioSelected;

    /**
     * Allows to load the media file.
     *
     * @param selectMedia allow to load a entity of selectMedia which allow to
     * choose audio files after in the bdd
     */
    public SonGUI(SelectMedia selectMedia) {
        this.selMedia = selectMedia;
        this.launchSonGUI();
    }

    /**
     * Launches the toggleGroup.
     */
    private void launchSonGUI() {

        //Components design
        FlowPane flowAudio = new FlowPane();
        flowAudio.getStyleClass().add("box");
        flowAudio.setAlignment(Pos.CENTER);

        final Button playSound = new Button("Preview voice");
        playSound.setDisable(true);

        GridPane soundArea = new GridPane();

        final ToggleGroup groupAudio = new ToggleGroup();
        ArrayList<RadioButton> listRB = new ArrayList<>();

        //ToggleGroup Design
        for (int i = 0; i < 10; i++) {
            Audio audioTmp = this.selMedia.selectAudio();
            RadioButton tmpRB = new RadioButton("Voice " + (i + 1));
            tmpRB.getStyleClass().add("radio-button");
            tmpRB.setUserData(audioTmp);
            tmpRB.setToggleGroup(groupAudio);
            tmpRB.setFocusTraversable(false);
            if (i < 5) {
                soundArea.add(tmpRB, i, 0);
            } else {
                soundArea.add(tmpRB, i - 5, 2);
            }
            listRB.add(tmpRB);
        }

        //Events properties
        groupAudio.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (groupAudio.getSelectedToggle() != null) {
                    playSound.setDisable(false);
                    audioSelected = (Audio) groupAudio.getSelectedToggle().getUserData();
                }
            }
        });

        playSound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playAction(audioSelected);
            }
        });

        //Adding in flowPane
        flowAudio.getChildren().add(soundArea);
        flowAudio.getChildren().add(playSound);
        this.getChildren().add(flowAudio);
        flowAudio.getStyleClass().add("div1");
    }

    /**
     * Plays the audio file selected.
     *
     * @param audio Audio selected.
     *
     */
    private void playAction(Audio audio) {
        File f = new File(System.getProperty("user.dir"), FilenameUtils.separatorsToSystem(audio.getFilePath()));
        AudioPlayer.load(f.getAbsolutePath());
    }

    /**
     * Returns the audio file selected.
     * 
     * @return Audio
     */
    public Audio getAudioSelected() {
        return audioSelected;
    }

}

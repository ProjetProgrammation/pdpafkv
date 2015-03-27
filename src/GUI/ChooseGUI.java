package GUI;

import BDD.DataBase;
import BDD.Language;
import Result.User;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

/**
 * This class manages the user interface which determines the mode wanted by the user.
 *
 * @author Thibaut
 */
public class ChooseGUI {

    private final Stage stage;
    private final User userSel;
    private final BorderPane globalPane = new BorderPane();
    private TestGUI guiInterface;

    /**
     * 
     * Contructs a new ChooseGUI.
     * 
     * @param primaryStage The stage of the interface.
     * @param langSel The language choose by the user.
     * @param db The BDD which contains media.
     * @param user The user who run the application.
     */
    public ChooseGUI(Stage primaryStage, Language langSel, DataBase db, User user) {
        this.stage = primaryStage;
        this.userSel = user;
        this.launchChooseGUI(langSel, db);

    }

    /**
     * Launches the user interface.
     *
     * @param langSel The language which is selected previously.
     * @param db The database which contains media of the test.
     */
    private void launchChooseGUI(final Language langSel, final DataBase db) {

        //Components design
        Label titleTop = new Label("Prosodic Adventure".toUpperCase());
        Label titleButtons = new Label("What do you want to do?".toUpperCase());

        Button learnOption = new Button("Training".toUpperCase());
        Button testOption = new Button("Not training".toUpperCase());

        GridPane root = new GridPane();
        BorderPane paneButtons = new BorderPane();

        learnOption.setPrefSize(150, 50);
        testOption.setPrefSize(150, 50);
        root.setAlignment(Pos.CENTER);

        //Add style classes
        titleTop.getStyleClass().add("label-header");
        titleButtons.getStyleClass().add("label-header");

        //Add event on buttons
        learnOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                guiInterface = new TestGUI(stage, 5, langSel, db, userSel);
            }
        });

        testOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                guiInterface = new TestGUI(stage, 2, langSel, db, userSel);
            }
        });

        //Organize the interface
        root.add(titleButtons, 1, 1);
        root.add(paneButtons, 1, 2);

        paneButtons.setLeft(learnOption);
        paneButtons.setRight(testOption);

        globalPane.setTop(titleTop);
        globalPane.setCenter(root);

        //Add the contents in the scene
        Scene scene = new Scene(globalPane);

        //Load the font add link design with the style page
        Font.loadFont(UserGUI.class.getResource("HelveticaNeueLTStd-LtCn.ttf").toExternalForm(), 10);
        File f = new File(System.getProperty("user.dir"), FilenameUtils.separatorsToSystem("src/GUI/WindowsTheme.css"));
        scene.getStylesheets().clear();
        scene.getStylesheets().add(f.toURI().toString());

        //background elements
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        this.stage.setTitle("The Prosodic Adventure");
    }

}

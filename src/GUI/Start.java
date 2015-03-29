/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import com.google.gson.stream.JsonWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Thibaut
 */
public class Start extends Application {
        
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        //Create of the scene and a borderpane
        Scene scene = new Scene(new BorderPane());
        //Load the font add link design with the style page
        Font.loadFont(UserGUI.class.getResource("HelveticaNeueLTStd-LtCn.ttf").toExternalForm(), 10);
        File f = new File(FilenameUtils.separatorsToSystem("WindowsTheme.css"));
        scene.getStylesheets().clear();
        scene.getStylesheets().add(f.toURI().toString());
        //Configuration of the stage
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("The Prosodic Adventure");
        //Size of stage = size of the screen
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.show();
        
        //Création d'un objet UserGUI pour faire apparaitre interface
        new UserGUI(primaryStage);            
    }  

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);  
    }
    
}

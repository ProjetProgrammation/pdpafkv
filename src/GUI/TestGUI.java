package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class TestGUI extends Parent{
        
    private int nombre = 1;
    private int nbQuestion;
    private final Stage stage;
    private QuestionGUI question;
    private SonGUI son;
    private VideoGUI video;
    
    public TestGUI(Stage primaryStage, int nbQuest){
        
        this.stage=primaryStage;
        this.nbQuestion=nbQuest;
        this.launchTestGUI();

    }
    private void launchTestGUI(){
            
        QuestionGUI question = new QuestionGUI();
        SonGUI son = new SonGUI();
        VideoGUI video = new VideoGUI();
        final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(50);
        
   
        //Zone pour les boutons
        Image imageMix = new Image(getClass().getResourceAsStream("mixer8.png"));
        Image imageValid = new Image(getClass().getResourceAsStream("confirmation.png"));
        Button mix = new Button("Mix", new ImageView(imageMix));
        Button validate = new Button("Validate", new ImageView(imageValid));
        
        //slider
        
        final ProgressBar pb = new ProgressBar(0);
        final ProgressIndicator pi = new ProgressIndicator(0);
 
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                pb.setProgress(new_val.doubleValue()/50);
                pi.setProgress(new_val.doubleValue()/50);
            }
        });
        
        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(slider, pb, pi);
        
        GridPane root = new GridPane();
        
        root.add(question, 1, 1, 4, 1);
        root.add(video, 1, 2, 2, 4);
        root.add(son, 3, 2, 4, 3);
        root.add(mix, 3 , 5);
        root.add(validate, 6 , 5);
        root.add(hb, 4, 6 );
        root.setAlignment(Pos.CENTER);
                
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        
        this.stage.hide();
        this.stage.show();
        
    } 
}
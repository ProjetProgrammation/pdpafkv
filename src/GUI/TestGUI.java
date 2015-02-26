package GUI;

import BDD.Language;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TestGUI extends Parent{
        
    private int nombre = 1;
    private int nbQuestion;
    private final Stage stage;
    
    public TestGUI(Stage primaryStage, int nbQuest, Language langSel){
        
        this.stage=primaryStage;
        this.nbQuestion=nbQuest;
        this.launchTestGUI(langSel);

    }
    private void launchTestGUI(Language langSel){
            
        QuestionGUI question = new QuestionGUI(langSel);
        SonGUI son = new SonGUI(langSel);
        VideoGUI video = new VideoGUI(langSel);
        /*final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(50);*/
        
   
        //Zone pour les boutons
        Image imageMix = new Image(getClass().getResourceAsStream("mixer8.png"));
        Image imageValid = new Image(getClass().getResourceAsStream("confirmation.png"));
        Button mix = new Button("Mix");
        Button validate = new Button("Validate");
        
        //slider
        
        final ProgressBar pb = new ProgressBar(0);
        /*final ProgressIndicator pi = new ProgressIndicator(0);
 
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                pb.setProgress(new_val.doubleValue()/50);
                pi.setProgress(new_val.doubleValue()/50);
            }
        });*/
        
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(pb);
        
       // BorderPane mainborder = new BorderPane();        
        GridPane root = new GridPane();
        
        /*mainborder.autosize();
        mainborder.setTop(root);*/
        
        root.setHgap(20);
        root.setVgap(20);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(34);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33);
        root.getColumnConstraints().addAll(col1,col2,col3);
        root.setGridLinesVisible(true);
        
        root.setAlignment(Pos.CENTER);
        
        root.add(question,0,0,3,1);
        root.add(video,0,1,1,3);
        root.add(son,2,1,1,3);
        //root.add(hb,0,4);
        root.add(mix,1,4);
        root.add(validate,2,4);
        
        GridPane.setHalignment(question, HPos.CENTER);
        GridPane.setHalignment(son, HPos.CENTER);   
        GridPane.setHalignment(video, HPos.CENTER);
        GridPane.setHalignment(mix, HPos.CENTER);
        GridPane.setHalignment(validate, HPos.CENTER);
        
        
        Scene scene = new Scene(root,1400,800);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.sizeToScene();
        this.stage.hide();
        this.stage.show();
        
    } 
}
package GUI;

import BDD.DataBase;
import BDD.Language;
import Controller.MediaSelect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class TestGUI extends Parent{
        
    
    private int nombre = 1;
    private int nbQuestion;
    private final Stage stage;
    private MediaSelect media;
    private UserGUI user;
            
    public TestGUI(Stage primaryStage, int nbQuest, Language langSel, DataBase db,  UserGUI user){
        
        this.stage=primaryStage;
        this.nbQuestion=nbQuest;
        this.launchTestGUI(langSel,db);
        media = new MediaSelect(db,langSel);
        this.user = user;

    }
    private void launchTestGUI(Language langSel, DataBase db){
            
        final QuestionGUI question = new QuestionGUI(langSel,db);
        final SonGUI son = new SonGUI(langSel,db);
        VideoGUI video = new VideoGUI(langSel,db);
        
   
        //Zone pour les boutons
        Image imageMix = new Image(getClass().getResourceAsStream("mixer8.png"));
        ImageView imageValid = new ImageView(new Image(getClass().getResourceAsStream("confirmation.png")));
        Button mix = new Button("Mix", new ImageView(imageMix));
        Button validate = new Button("Validate", imageValid);
        mix.setStyle("-fx-font: 22 arial; -fx-base: #FFE082; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
        validate.setStyle("-fx-font: 22 arial; -fx-base: #FFE082; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
        
        //slider
        
        
        validate.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                String audio = son.audio();
                
                media.MediaSelect(user.nom(), user.prénom(), question.getText(),audio);
                VLC vlc = new VLC();
                vlc.load();
                vlc.run("..\\pdpafkv\\Video\\2013_3_19_S29_fr_L1_ADMI_B_ok.mp4");
                System.out.println("mediaselect effectué");
            }
        });
        
        
        
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
        
        //Definition des tailles des collones
        root.setHgap(20);
        root.setVgap(20);
        root.prefWidth(100.0);
        
        root.setPadding(new Insets(20, 20, 20, 20));
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33);        
        ColumnConstraints col2 = new ColumnConstraints(50);
        col2.setPercentWidth(10);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(45);
        root.getColumnConstraints().addAll(col1,col2,col3);
        root.setGridLinesVisible(false);
        root.setAlignment(Pos.CENTER);
        
        //Creation de la zone pour les bouton mix et valide et slider
        GridPane mixValid = new GridPane();
        mixValid.setHgap(20);
        mixValid.setVgap(20);
        mixValid.setPadding(new Insets(20, 20, 20, 20));
        mixValid.add(mix,0,0);
        mixValid.add(validate,1,0);       
        mixValid.setGridLinesVisible(false);
        
        //ajout des element au gridpane
        root.add(question,0,0,3,1);
        root.add(video,0,1,1,3);
        root.add(son,2,1,1,3);
        //root.add(hb,0,4);
        root.add(mixValid,2,4);
        root.setStyle("-fx-background-color: #FF8F00 ; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;-fx-border-color: #000000;");
        
        GridPane.setHalignment(question, HPos.CENTER);
        GridPane.setHalignment(son, HPos.CENTER);   
        GridPane.setHalignment(video, HPos.CENTER);
        GridPane.setHalignment(mix, HPos.CENTER);
        GridPane.setHalignment(validate, HPos.CENTER);
                
        //Scene scene = new Scene(root,1400,800);
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        
        this.stage.hide();
        this.stage.show();
        
    } 
}

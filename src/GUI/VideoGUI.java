/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Thibaut
 */
public class VideoGUI extends Parent {

    public VideoGUI() {
        this.launchVideoGUI();
    }
    
    private void launchVideoGUI(){
        
        //Zone pour les video
        Rectangle fond_video = new Rectangle();
        fond_video.setWidth(400);
        fond_video.setHeight(350);
        fond_video.setArcWidth(50);
        fond_video.setArcHeight(50);
        fond_video.setFill(Color.WHITE);
        fond_video.setStroke(Color.BLACK);
                
        this.getChildren().add(fond_video);
    }
    
}

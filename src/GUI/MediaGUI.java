/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.Parent;

/**
 *
 * @author Thibaut
 */
public class MediaGUI extends Parent {
    
    /*
            Media media;
        MediaPlayer mediaplayer;
        MediaView mediaview;
    
        ImageView image_bouton;
        Image img_play;
        Image img_pause;
    
    
     //ajout d'une video
        Group root = new Group();
         Objets Media, MediaPlayer et MediaView
        //Media
        media = new Media("http://www.pianovirtuel.fr/Site_du_zero/javafx.flv");
        
        //MediaPlayer
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setOnReady(new Runnable(){//dès que le lecteur est prêt, on set les start et stop times
            public void run() {
                mediaplayer.setStartTime(Duration.ZERO);
                mediaplayer.setStopTime(media.getDuration().subtract(Duration.valueOf("50")));
            }
        });
        mediaplayer.setOnEndOfMedia(new Runnable(){//dès qu'on arrive à la fin du média : stop
            public void run() {
                mediaplayer.stop();
            }
        });
        
        //MediaView
        mediaview = new MediaView(mediaplayer);
        mediaview.setFitWidth(426);
        mediaview.setFitHeight(240);
        root.getChildren().add(mediaview);

        
                /********  Objets graphiques de la barre des fonctions   
        Group fonctions = new Group();
        
        //création du fond de la barre de fonction
        Rectangle fond = new Rectangle(426,30, Paint.valueOf("#333333"));
        fond.setOpacity(0.5);
        fonctions.getChildren().add(fond);
        fonctions.setTranslateY(240-30);
        root.getChildren().add(fonctions);
        
               //création du bouton play/pause
        Group play_pause = new Group();
        Rectangle fond_bouton = new Rectangle(28,24,Paint.valueOf("#111111"));
        fond_bouton.setArcHeight(5);
        fond_bouton.setArcWidth(5);
        fond_bouton.setStroke(Paint.valueOf("#8FD8D8"));
        img_play = new Image("http://www.pianovirtuel.fr/Site_du_zero/sigleplay.png");
        img_pause = new Image("http://www.pianovirtuel.fr/Site_du_zero/siglepause.png");
        image_bouton = new ImageView(img_play);
        image_bouton.setX(4);
        image_bouton.setY(2);
        play_pause.getChildren().add(fond_bouton);
        play_pause.getChildren().add(image_bouton);
        play_pause.setTranslateX(4);
        play_pause.setTranslateY(4);
        play_pause.setCursor(Cursor.HAND);
        //Quand on clique sur le bouton play/pause, on démarre ou on arrête la vidéo
        play_pause.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                if(mediaplayer.getStatus() == MediaPlayer.Status.valueOf("PLAYING")){//pause
                    image_bouton.setImage(img_play);
                    mediaplayer.pause();
                }
                else{//play
                    image_bouton.setImage(img_pause);
                    mediaplayer.play();
                }
            }
        });
        fonctions.getChildren().add(play_pause);
    */
    
}

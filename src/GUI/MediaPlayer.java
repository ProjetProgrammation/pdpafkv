/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.jna.*;
import javax.swing.JFrame;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author Thibaut
 */
public class MediaPlayer {
   
    static private JFrame frame = new JFrame();
    static private EmbeddedMediaPlayerComponent emp;
    
    public static void load(String pathV){
        
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "E:\\Programme\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        MediaPlayerFactory mediaplayer = new MediaPlayerFactory();
        emp = new EmbeddedMediaPlayerComponent();

        frame.setContentPane(emp);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
     
        run(pathV);
    }
    
   private static void run(String path){
       emp.getMediaPlayer().playMedia(path);
       emp.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter(){

           @Override
           public void finished(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {
               mediaPlayer.stop();
               frame.dispose();
               //System.exit(0);
           }
           
       });
   }
}
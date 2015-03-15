/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import javax.swing.JFrame;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author Thibaut
 */
public class AudioPlayer {
    
    static private AudioMediaPlayerComponent ampc;
    
    public static void load(String pathV){        
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "E:\\Programme\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        ampc = new AudioMediaPlayerComponent();
        run(pathV);
    }
    
   private static void run(String path){
       ampc.getMediaPlayer().playMedia(path);
   }
    
}

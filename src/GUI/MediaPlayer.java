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
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * This class allow to load and play media files
 *
 * @author Thibaut
 */
public class MediaPlayer {

    static private final JFrame frame = new JFrame();
    static private EmbeddedMediaPlayerComponent empComponent;

    /**
     * Allow to load the media file
     *
     * @param pathV The path of the media file
     */
    public static void load(String pathV) {
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        empComponent = new EmbeddedMediaPlayerComponent();
        frame.setContentPane(empComponent);
        frame.setBounds(800, 500, 350, 200);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        run(pathV);
    }

    /**
     * This method allow to play the media file
     *
     * @param path The path of the media file
     */
    private static void run(String path) {
        empComponent.getMediaPlayer().playMedia(path);
        empComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {

            @Override
            public void finished(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                frame.dispose();
                //System.exit(0);
            }
        });
    }
}

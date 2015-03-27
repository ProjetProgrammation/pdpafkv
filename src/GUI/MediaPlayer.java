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
 * This class loads and plays media files.
 *
 * @author Thibaut
 */
public class MediaPlayer {

    static private final JFrame frame = new JFrame();
    static private EmbeddedMediaPlayerComponent empComponent;

    /**
     * Loads the media file.
     *
     * @param pathV Media's file path.
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
     * This method plays the media file.
     *
     * @param path Media's file path.
     */
    private static void run(String path) {
        empComponent.getMediaPlayer().playMedia(path);
        empComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {

            @Override
            public void finished(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                frame.dispose();
            }
        });
    }
}

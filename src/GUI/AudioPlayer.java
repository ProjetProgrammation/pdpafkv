/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import org.apache.commons.io.FilenameUtils;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * This class loads and plays audio files.
 *
 * @author Thibaut
 */
public class AudioPlayer {

    static private AudioMediaPlayerComponent ampc;

    /**
     * Loads the audio file.
     *
     * @param pathV Audio's file path.
     */
    public static void load(String pathV) {
        String path = System.getProperty("user.dir") + FilenameUtils.separatorsToSystem("\\VLC");
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), path);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        ampc = new AudioMediaPlayerComponent();
        run(pathV);
    }

    /**
     * Plays the audio file.
     *
     * @param path Audio's file path.
     */
    private static void run(String path) {
        ampc.getMediaPlayer().playMedia(path);
    }

}

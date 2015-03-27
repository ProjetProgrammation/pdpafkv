/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * This class allow to load and play audio files
 *
 * @author Thibaut
 */
public class AudioPlayer {

    static private AudioMediaPlayerComponent ampc;

    /**
     * Allow to load the audio file
     *
     * @param pathV The path of the audio file
     */
    public static void load(String pathV) {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "E:\\Programme\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        ampc = new AudioMediaPlayerComponent();
        run(pathV);
    }

    /**
     * Allow to play the audio file
     *
     * @param path The path of the audio file
     */
    private static void run(String path) {
        ampc.getMediaPlayer().playMedia(path);
    }

}

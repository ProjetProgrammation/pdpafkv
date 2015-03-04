/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.jna.NativeLibrary;
import java.awt.Canvas;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 *
 * @author Thibaut
 */
public class MediaPlayer extends JPanel {
    
    private EmbeddedMediaPlayerComponent mediaPlayerComponent=null;

    public MediaPlayer(){
        final String mrl = "E:\\Document\\Mes Documents\\Divers\\EDD.MOV";

        setLookAndFeel();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BasicEmbeddedMediaPlayerComponentTest().start(mrl);
            }
        });
    }
    
    private class BasicEmbeddedMediaPlayerComponentTest {

        public BasicEmbeddedMediaPlayerComponentTest() {
            NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "E:\\Programme\\VLC");
            //JPanel panel = new JPanel(new FlowLayout());

            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
            //panel.add(mediaPlayerComponent);
                     
        }

        private void start(String mrl) {
            // One line of vlcj code to play the media...
            mediaPlayerComponent.getMediaPlayer().playMedia(mrl);
        }
    }
    
    
    
    private static final void setLookAndFeel() {
        String lookAndFeelClassName = null;
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for(UIManager.LookAndFeelInfo lookAndFeel : lookAndFeelInfos) {
            if("Nimbus".equals(lookAndFeel.getName())) {
                lookAndFeelClassName = lookAndFeel.getClassName();
            }
        }
        if(lookAndFeelClassName == null) {
            lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        }
        try {
            UIManager.setLookAndFeel(lookAndFeelClassName);
        }
        catch(Exception e) {
            // Silently fail, it doesn't matter
        }
    }
}

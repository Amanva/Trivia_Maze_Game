//
//import javax.sound.sampled.*;
//import javax.swing.*;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class Music {
//    static String musicPath;
//    Clip myClip;
//
//    public void playMusic() {
//        try {
//            File music = new File("src/ - song.wav");
//            if(music.exists()) {
//                AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
//                myClip = AudioSystem.getClip();
//                myClip.open(audioInput);
//                myClip.start();
//                myClip.loop(Clip.LOOP_CONTINUOUSLY);
//            } else {
//                System.out.println("error");
//            }
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//}

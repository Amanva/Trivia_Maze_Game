package View;

import javax.swing.*;
import java.awt.*;
/**
 * Custom JFrame class to set background images and menus
 *
 * @author Sophia Young
 */
class ImageFrame extends JFrame {
    private final JPanel panel = new JPanel();
    Container con = getContentPane();

    public ImageFrame(){

        setTitle("Animal Trivia Game");
        setSize(728,550);
        setLocation(360,100);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground();
        setVisible(true);
    }


    public void setBackground(){
        JLabel background = new JLabel(new ImageIcon("src/Assets/startMenuBkgrnd.jpg"));
        con.add(background);
        con.setLayout(new FlowLayout());
        con.add(panel);
    }

    public void setButton(JButton theButton){
        Font but = new Font("Courier New" , Font.PLAIN , 20);
        theButton.setFont(but);
        panel.add(theButton);
        validate();
    }
    public void hideMenu() {
        panel.removeAll();
        validate();
    }
}
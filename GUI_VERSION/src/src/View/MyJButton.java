package View;

import javax.swing.*;
import java.awt.*;
/**
 * Custon JButton class to set font and size on all buttons
 *
 * @author Sophia Young
 */
public class MyJButton extends JButton {
    MyJButton (String str){
        super();
        setText(str);
        setFont(new Font("Courier New" , java.awt.Font.PLAIN , 20));
        setMaximumSize(new Dimension(60, 60));
    }
}

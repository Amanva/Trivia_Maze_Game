package View;

import javax.swing.*;
import java.awt.*;
/**
 * Custon JLabel class to set font and size on all labels
 *
 * @author Sophia Young
 */
public class MyJLabel  extends JLabel {
    MyJLabel(String str) {
        super();
        setText(str);
        setFont(new Font("Courier New" , java.awt.Font.PLAIN , 20));
    }
}

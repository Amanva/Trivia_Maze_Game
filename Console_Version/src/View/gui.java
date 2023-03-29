package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 * GUI class and main method for program
 *
 * @author Sophia Young
 */
public class gui {
    public static void main(String[] theArgs) {
        ImageFrame frame = new ImageFrame();
        JButton newGame = new JButton("New Game");
        JButton loadGame = new JButton("Load Game");
        JButton exit = new JButton("Exit");

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.hideMenu();
                JButton select = new JButton("Select Difficulty");
                select.setEnabled(false);
                JButton easy = new JButton("Easy");
                JButton medium = new JButton("Medium");
                JButton hard = new JButton("Hard");
                JButton extreme = new JButton("Extreme");
                frame.setButton(select);
                frame.setButton(easy);
                frame.setButton(medium);
                frame.setButton(hard);
                frame.setButton(extreme);
                easy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startNewGame(1);
                        frame.setVisible(false);
                    }
                });
                medium.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startNewGame(2);
                        frame.setVisible(false);
                    }
                });
                hard.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startNewGame(3);
                        frame.setVisible(false);
                    }
                });
                extreme.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startNewGame(4);
                        frame.setVisible(false);
                    }
                });
            }
        });
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("load game pressed");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        frame.setButton(newGame);
        frame.setButton(loadGame);
        frame.setButton(exit);
    }
    static JPanel newGameMenu() {
        JPanel panel = new JPanel();
        String[] diff = { "Easy", "Medium", "Hard", "Extreme"};
        JComboBox<String> chooseDifficulty = new JComboBox<String>(diff);
        panel.add(new JLabel("Select Difficulty"));
        panel.add(chooseDifficulty);
        return panel;
    }
    static void startNewGame(int theDifficulty) {
        JFrame gameFrame = new JFrame("Animal Trivia Model.Maze");
        gameFrame.setLayout(new FlowLayout());
        JPanel gamePanel = new JPanel();
        JPanel controlsPanel = new JPanel();
        controlPanel(controlsPanel);
        JLabel label = new JLabel();
        switch (theDifficulty) {
            case 1 -> label.setText("Easy Difficulty (5x5?)");
            case 2 -> label.setText("Medium Difficulty (7x7?)");
            case 3 -> label.setText("Hard Difficulty (10x10?)");
            case 4 -> label.setText("Extreme Difficulty (15x15?)");
        }
        gamePanel.add(label);
        gameFrame.add(gamePanel);
        gameFrame.add(controlsPanel);
        gameFrame.setLocation(360,100);
        gameFrame.setSize(800, 800);
        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }
    static void controlPanel(JPanel thePanel) {
        JButton up = new JButton();
        JButton down = new JButton();
        JButton left = new JButton();
        JButton right = new JButton();
        up.setIcon(new ImageIcon("src/Assets/arrowUp.jpg"));
        up.setMargin(new Insets(0, 0, 0, 0));
        up.setBorder(null);
        left.setIcon(new ImageIcon("src/Assets/arrowLeft.jpg"));
        left.setMargin(new Insets(0, 0, 0, 0));
        left.setBorder(null);
        down.setIcon(new ImageIcon("src/Assets/arrowDown.jpg"));
        down.setMargin(new Insets(0, 0, 0, 0));
        down.setBorder(null);
        right.setIcon(new ImageIcon("src/Assets/arrowRight.jpg"));
        right.setMargin(new Insets(0, 0, 0, 0));
        right.setBorder(null);
        thePanel.add(up);
        thePanel.add(down);
        thePanel.add(left);
        thePanel.add(right);
    }
}
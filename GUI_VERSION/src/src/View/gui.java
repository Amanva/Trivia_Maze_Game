package View;

import Model.UserFunctionality;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import static Model.UserFunctionality.getQuestionString;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 * GUI class and main method for program
 *
 * @author Sophia Young
 */
public class gui implements Serializable {
    static Clip myClip;
    static JFrame myGameFrame;
    static JPanel myGamePanel;

    static MyJLabel healthNum;
    static int health;

    public static void main(String[] theArgs) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        ImageFrame frame = new ImageFrame();
        JButton newGame = new JButton("New Game");
        JButton loadGame = new JButton("Load Game");
        JButton exit = new JButton("Exit");
        //start new game with difficulty selection
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.hideMenu();
                JButton select = new JButton("Select Difficulty");
                select.setEnabled(false);
                JButton easy = new JButton("Easy");
                JButton medium = new JButton("Medium");
                JButton hard = new JButton("Hard");
                frame.setButton(select);
                frame.setButton(easy);
                frame.setButton(medium);
                frame.setButton(hard);
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
            }
        });
        //load previously saved game
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserFunctionality.loadLastGame();
                startNewGame(UserFunctionality.getMaze().getDiff());
                frame.setVisible(false);
            }
        });
        //close program
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        playMusic();
        frame.setButton(newGame);
        frame.setButton(loadGame);
        frame.setButton(exit);
    }
    /**
     * plays audio file as background music
     */
    private static void playMusic() {
        try {
            File music = new File("src/Assets/City Blocks - TrackTribe.wav");
            if(music.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
                myClip = AudioSystem.getClip();
                myClip.open(audioInput);
                myClip.start();
                myClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("error");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * allows user to mute/ unmute background audio
     */
    private static void muteUnmute() {
        if(myClip.isActive()) {
            myClip.stop();
        } else {
            myClip.start();
        }
    }

    /**
     * starts new game with a given difficulty
     *
     * @param diff the difficulty of the new game
     */
    static void startNewGame(int diff) {
        myGameFrame = new JFrame("Animal Trivia Maze");
        myGameFrame.setLayout(new FlowLayout());
        myGameFrame.setSize(900, 600);
        myGameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        myGamePanel = new JPanel();
        myGamePanel.setLayout(new GridLayout(11, 11));

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(5, 3));
        setupControlPanel(controlsPanel);
        if(UserFunctionality.getMaze() == null) {
            UserFunctionality.createMaze();
        }
        UserFunctionality.getMaze().setDiff(diff);
        drawMaze();

        myGameFrame.add(myGamePanel);
        myGameFrame.add(controlsPanel);
        myGameFrame.setVisible(true);
    }
    /**
     * creates panel of buttons that allow for user controls and options
     *
     * @param thePanel the JPanel of buttons to add to the frame
     */
    static void setupControlPanel(JPanel thePanel) {
        JButton up = new JButton();
        JButton down = new JButton();
        JButton left = new JButton();
        JButton right = new JButton();
        MyJButton save = new MyJButton("Save");
        MyJButton load = new MyJButton("Load");
        MyJButton exit = new MyJButton("Exit");
        MyJButton mute = new MyJButton("Mute");
        MyJButton inst = new MyJButton("Help");
        MyJButton about = new MyJButton("About");
        JLabel blank = new JLabel(new ImageIcon("src/Assets/blank.jpg"));
        JLabel blank2 = new JLabel(new ImageIcon("src/Assets/blank.jpg"));
        JLabel blank3 = new JLabel(new ImageIcon("src/Assets/blank.jpg"));
        MyJLabel healthLabel = new MyJLabel("Health: ");
        healthNum = new MyJLabel("5");
        health = 5;
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
        up.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean moveOK = UserFunctionality.getMaze().checkDirectionForUser('U');
                if (moveOK) {
                    updatePlayerPos('U');
                }
            }
        });
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean moveOK = UserFunctionality.getMaze().checkDirectionForUser('L');
                if (moveOK) {
                    updatePlayerPos('L');
                }
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean moveOK = UserFunctionality.getMaze().checkDirectionForUser('R');
                if (moveOK) {
                    updatePlayerPos('R');
                }
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean moveOK = UserFunctionality.getMaze().checkDirectionForUser('D');
                if (moveOK) {
                    updatePlayerPos('D');
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserFunctionality.saveGame();
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserFunctionality.loadLastGame();
                drawMaze();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        mute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                muteUnmute();
            }
        });
        inst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Game Instruction Manual:\nSave Game: The save game feature will allow you to save your current position inside the maze.\nLoad game: The load game feature will allow you to start from the saved position. " +
                        "\nThe Difficulty: You are able to select the difficulty of the questions. \nChicken: represents YOU the character\nQuestions: Consist of multiple choice, short answer, and True or False." +
                        "\nThe questions will pop up at a random door and is never guaranteed to pop up at every door. The question must be answered for you to move on. If you decide to cancel the question then it will result in a life lost. A question can also pop up if you decide to go back in the direction you came from.\n\n" +
                        "The movement keys are displayed as arrows which will allow you to move around the maze in whichever direction you desire. \n" +
                        "\n");
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Animal Trivia maze Game\n You spawn as an animal in a maze and your goal is to get to the exit to escape the hungry hunters. To escape you need to answer the animal questions.");
            }
        });
        thePanel.add(blank);
        thePanel.add(up);
        thePanel.add(blank2);
        thePanel.add(left);
        thePanel.add(down);
        thePanel.add(right);
        thePanel.add(save);
        thePanel.add(load);
        thePanel.add(exit);
        thePanel.add(mute);
        thePanel.add(inst);
        thePanel.add(about);
        thePanel.add(healthLabel);
        thePanel.add(blank3);
        thePanel.add(healthNum);
    }
    /**
     * checks if player can move a given direction
     * then displays question if there is one
     * then calls for the updated maze to be drawn
     *
     * @param theDirection
     */
    private static void updatePlayerPos(char theDirection) {
        System.out.println(theDirection);
        if(UserFunctionality.getQuestionString(theDirection) != null || Objects.equals(getQuestionString(theDirection), "")) {
            System.out.println(getQuestionString(theDirection));
            String givenAns = JOptionPane.showInputDialog(UserFunctionality.getQuestionString(theDirection));
            while (!UserFunctionality.checkAnswer(givenAns, theDirection)) {
                health--;
                healthNum.setText(String.valueOf(health));
                healthNum.validate();
                givenAns = JOptionPane.showInputDialog(UserFunctionality.getQuestionString(theDirection));

            }
        }
        boolean moveOK = UserFunctionality.chooseDirectionGUI(theDirection);
        if (!moveOK) {
            JOptionPane.showMessageDialog(null, "You Win!");
            System.exit(1);
        }
        drawMaze();
    }
    /**
     * draws the maze from a 2D array with images
     * corresponding to the different letters in the array
     */
    static void drawMaze() {
        myGamePanel.removeAll();
        for (int i = 0; i < UserFunctionality.getMaze().getRows(); i++) {
            for(int j = 0; j < UserFunctionality.getMaze().getColumns(); j++) {
                char room = UserFunctionality.getMaze().getRoomType(i,j);
                JLabel background;
                if (room == 'W') {
                    background = new JLabel(new ImageIcon
                            ("src/Assets/wall.jpg"));
                } else if (room == 'R') {
                    background = new JLabel(new ImageIcon
                            ("src/Assets/room.jpg"));
                } else if (room == 'D') {
                    background = new JLabel(new ImageIcon
                            ("src/Assets/door.jpg"));
                } else if (room == 'E') {
                    background = new JLabel(new ImageIcon
                            ("src/Assets/exit.jpg"));
                } else if (room == 'S') {
                    background = new JLabel(new ImageIcon
                            ("src/Assets/enter.jpg"));
                } else if (room == 'P') {
                    background = new JLabel(new ImageIcon("src/Assets/player.jpg"));
                } else {
                    background = new JLabel(new ImageIcon
                            ("src/Assets/wall.jpg"));
                }
                myGamePanel.add(background);
            }
        }
        myGamePanel.validate();
    }
}
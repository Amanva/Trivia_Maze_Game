package Model;

import Controller.Player;

import javax.swing.*;
import java.io.*;
/**
 * User functionality to pass instructions and functionality
 * from the GUI to the Player and Maze
 *
 * @author Sophia Young, Aman Vahora, Arashpreet S. Pandher
 */
public class UserFunctionality implements Serializable {
    private static Maze myMaze;
    private static Player myPlayer;
    static String myQuestion;
    static String myAnswer;
    /**
     * Creates new maze of standard size and a
     * new player with standard health
     */
    public static void createMaze(){
        myMaze = new Maze(5,5);
        myPlayer = new Player(5);
        myMaze.generateMaze();
        myMaze.convertMazeToLarger();
    }
    /**
     * returns the current maze
     */
    public static Maze getMaze() {
        return myMaze;
    }

    /**
     * saves game to file "f.txt"
     */
    public static void saveGame() {
        try {
            FileOutputStream fout = new FileOutputStream("f.txt");
            ObjectOutputStream out=new ObjectOutputStream(fout);
            out.writeObject(myMaze);
            out.writeObject(myPlayer);
            out.writeObject(myQuestion);
            out.writeObject(myAnswer);
            out.flush();
            out.close();
            System.out.println("game saved");
        } catch(Exception ex){System.out.println("error saving");}
    }

    /**
     * loads last saved game from file "f.txt"
     */
    public static void loadLastGame() {
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("f.txt"));
            myMaze = (Maze) in.readObject();
            myPlayer = (Player) in.readObject();
            myQuestion = (String) in.readObject();
            myAnswer = (String) in.readObject();
            in.close();
            System.out.println("game loaded");
        }catch(Exception e){System.out.println(e);}
    }
    /**
     * Attempts to move player in direction chosen by the user
     *
     * @param theDirection direction the user wants to move
     * @return false if spot is the exit, else true
     */
    public static boolean chooseDirectionGUI(char theDirection) {
        myMaze.movePlayer(theDirection);
        char quit = myMaze.checkUserSpot();
        if (quit == 'Q') {
            System.out.println("You have reached the exit");
            return false;
        } else {
            return true;
        }

    }
    /**
     * gets question string of a question in the given direction
     *
     * @param theDirection direction the user wants to move
     * @return the question in string form
     */
    public static String getQuestionString(char theDirection) {
        if (!myMaze.displayQuestion(theDirection)) {
            myQuestion = myMaze.getMyQuestion();
            myAnswer = myMaze.getCorrectAnswer();
        }
        return myQuestion;
    }
    /**
     * checks if answer inputted by the user is correct
     *
     * @param givenAns
     * @param theDirection
     * @return
     */
    public static boolean checkAnswer(String givenAns, char theDirection) {
        if (myAnswer == null) {
            return true;
        }
        if (myAnswer.equalsIgnoreCase(givenAns)) {
            myMaze.questionAnswered(theDirection);
            myQuestion = null;
            myAnswer = null;
            return true;
        } else {
            myPlayer.decreaseHealth();
            if (!myPlayer.alive()) {
                JOptionPane.showMessageDialog(null, "You Lost :(");
                System.exit(2);
            }
            return false;
        }
    }

}
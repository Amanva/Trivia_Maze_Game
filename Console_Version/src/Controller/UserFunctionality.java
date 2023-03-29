/**
 * This class manages the all the functionality related the maze and the user.
 *
 * Trivia Maze Game
 * Aman Vahora, Arashpreet S. Pandher, Sophia Young
 * TCSS 360 Spring 2022
 */

package Controller;

import Model.Maze;
import Model.Player;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

/**
 * Class for user functionality to move in maze.
 */
public class UserFunctionality implements Serializable{
    private static Maze maze;
    private static Scanner scan = new Scanner(System.in);
    private static Player myPlayer;
    //    private static Music song;
    static String file;
    static Clip myClip;

    /**
     * Play the music
     */
    public static void playMusic() {
        try {
            File music = new File("C:/Users/pandh/IdeaProjects/Trivia/src/song.wav");
            if (music.exists()) {
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
    public static void main(String [] args){
        //create maze and generate
        createMaze();

    }

    /**
     * Create the maze
     */
    public static void createMaze(){
        maze = new Maze(5,5);
        myPlayer = new Player(5);
        maze.generateMaze();
        chooseDifficulty();
        playMusic();
        gamePlay();
    }

    /**
     * Choose the direction that the user can go along with save, load, quit, and mute.
     */
    public static void gamePlay(){
        boolean exit = false;
        outer: while(!exit) {
            maze.convertMazeToLarger();
            System.out.println("Choose one of the directions below Or Q to exit and M to mute music");
            System.out.println("Save by entering (S) and (X) for Load");
            System.out.println("HEALTH: " + myPlayer.getMyHealth());
            maze.displayDirections();
            char direction = Character.toUpperCase(scan.next().charAt(0));
            if(direction == 'M') {
                muteMusic();
            }
            else if (direction == 'S') {
                saveGame();
                System.out.println("Load previous game (X)");
                direction = Character.toUpperCase(scan.next().charAt(0));
                if (direction == 'X') {
                    loadLastGame();
                }
            } else if (direction == 'X') {
                loadLastGame();
                System.out.println("Choose one of the directions below Or Q to exit");
                System.out.println("Save by entering S and X for Load");
                maze.displayDirections();
            }
            else if(direction == 'Q'){
                break outer;
            }
            while (!checkIfMoveable(direction)) {
                System.out.println("Error: Choose one of the directions below");
                maze.displayDirections();
                direction = Character.toUpperCase(scan.next().charAt(0));
            }
            if (!maze.displayQuestion(direction)) {
                if (maze.checkTypeQuestionMaze(direction) == 0) {
                    System.out.print("Enter Answer: ");
                    char answer = scan.next().charAt(0);
                    while (!maze.answerMultipleChoice(answer)) {
                        myPlayer.decreaseHealth();
                        if (!myPlayer.alive()) {
                            System.out.println("You died");
                            System.out.println("Game Over");
                            break outer;
                        }
                        System.out.println("Re-Enter Answer: ");
                        answer = scan.next().charAt(0);
                    }
                    maze.questionAnswered(direction);
                } else if (maze.checkTypeQuestionMaze(direction) == 1) {
                    System.out.print("Enter Answer: ");
                    String answer = scan.next();
                    while (!maze.shortAnswer(answer)) {
                        myPlayer.decreaseHealth();
                        if (!myPlayer.alive()) {
                            System.out.println("You died");
                            System.out.println("Game Over");
                            break outer;
                        }
                        System.out.println("Re-Enter Answer: ");
                        System.out.println();
                        answer = scan.next();
                    }
                    maze.questionAnswered(direction);
                } else if (maze.checkTypeQuestionMaze(direction) == 2) {
                    System.out.print("Enter True or False: ");
                    String answer = scan.next();
                    while (!maze.shortAnswer(answer)) {
                        myPlayer.decreaseHealth();
                        if (!myPlayer.alive()) {
                            System.out.println("You died");
                            System.out.println("Game Over");
                            break outer;
                        }
                        System.out.println("Re-Enter Answer: ");
                        System.out.println();
                        answer = scan.next();
                    }
                    maze.questionAnswered(direction);
                }
            }

            maze.movePlayer(direction);
            char quit = maze.checkUserSpot();
            if (quit == 'Q') {
                System.out.println("You have reached the exit");
                exit = true;
            }
        }

    }

    /**
     * Mutes the music
     */
    private static void muteMusic() {
        if(myClip.isActive()){
            myClip.stop();
        }
        else{
            myClip.start();
        }
    }

    /**
     * Asks the user to choose the difficulty of the maze.
     */
    private static void chooseDifficulty() {
        System.out.println("Welcome to Animal Trivia \n Which difficulty would you like to choose \n easy = 1, medium = 2, hard = 3");
        int difficulty = scan.nextInt();
        if(difficulty == 1){
            maze.setDoor(1);
        }
        else if(difficulty == 2){
            maze.setDoor(2);
        }
        else if(difficulty == 3){
            maze.setDoor(3);
        }
        else{
            System.out.println("Incorrect value for difficulty");
        }
    }

    /**
     * Save the game to the file.
     */
    public static void saveGame(){
        try {
            FileOutputStream fout = new FileOutputStream("f.txt");
            ObjectOutputStream out=new ObjectOutputStream(fout);
            maze.saveMaze(out);
            //myPlayer.savePlayer(out);
            System.out.println("game saved");
        } catch(Exception ex){System.out.println("error saving");}
    }
    /**
     * Load the game that was save into a file
     */
    public static void loadLastGame() {
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("f.txt"));
            maze = new Maze(5, 5);
            maze.loadMaze(in);
            //myPlayer = new Player(5);
            //myPlayer.loadPlayer(in);
            in.close();
        }catch(Exception e){System.out.println(e);}
    }


    /**
     * Check if the direction is a moveable direction for the user.
     * @param theDirection the direction entered
     * @return If the direction is valid
     */
    public static boolean checkIfMoveable(char theDirection){
        return maze.checkDirectionForUser(theDirection);
    }


}
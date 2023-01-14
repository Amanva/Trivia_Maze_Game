/**
 * This class is responsible for setting up the question for the maze.
 *
 * Trivia Maze Game
 * Aman Vahora, Arashpreet S. Pandher, Sophia Young
 * TCSS 360 Spring 2022
 */
package Model;
public class Question {
    private final String myQuestion;
    private final String myAnswer;
    private final int myDifficulty;
    private final int myType;

    /**
     * Gets the type of the question
     * @return the question type
     */
    public int getMyType() {
        return myType;
    }

    /**
     * Constructor that contains the difficulty, question, answer, and type to be added into the arraylist.
     * @param theDifficulty The difficulty of the question
     * @param theQuestion The question in database
     * @param theAnswer The answer to the question
     * @param theType The type of the question
     */
    public Question(final int theDifficulty, final String theQuestion, final String theAnswer, final int theType){
        myDifficulty = theDifficulty;
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myType = theType;
    }

    /**
     * Getter for the question in string format
     * @return
     */
    public String getMyQuestion() {
        return myQuestion;
    }

    /**
     * getter for the answer in string format
     * @return
     */
    public String getMyAnswer() {
        return myAnswer;
    }


}
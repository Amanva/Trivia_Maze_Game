/**
 * This class is used to set up the question
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
     * This method gets the type
     * @return int
     */
    public int getMyType() {
        return myType;
    }

    /**
     * This is the constructor
     * @param theDifficulty  int difficulty
     * @param theQuestion string question
     * @param theAnswer string answer
     * @param theType  int type
     */
    public Question(final int theDifficulty, final String theQuestion, final String theAnswer, final int theType){
        myDifficulty = theDifficulty;
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myType = theType;
    }

    /**
     * Method to get questions
     * @return String question
     */
    public String getMyQuestion() {
        return myQuestion;
    }

    /**
     * method to get answer String
     * @return String
     */
    public String getMyAnswer() {
        return myAnswer;
    }

}
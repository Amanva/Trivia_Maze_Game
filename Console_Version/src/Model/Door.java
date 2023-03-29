package Model;

/**
 * This class sets up the Doors for each room for the questions
 *
 * Trivia Maze Game
 * Aman Vahora, Arashpreet S. Pandher, Sophai Young
 * TCSS 360 Spring 2022
 */

import java.io.Serializable;

public class Door  implements Serializable {
    private String myQuestion;
    private String myAnswer;
    private boolean myQuestionExist;
    private int myType;
    private boolean myQuestionAnswered;

    /**
     * A method to get question
     * @return  a String
     */
    public String getMyQuestion() {
        return myQuestion;
    }

    /**
     * A method to get answer
     * @return
     */
    public String getMyAnswer() {
        return myAnswer;
    }

    /**
     * This method checks if question exists
     * @return boolean
     */
    public boolean isMyQuestionExist() {
        return myQuestionExist;
    }

    /**
     * this method gets the type of question
     * @return int
     */
    public int getMyType() {
        return myType;
    }


    /**
     * Method returns if question is answered
     * @return boolean
     */
    public boolean isMyQuestionAnswered() {
        return myQuestionAnswered;
    }

    /**
     * This method sets the question answered
     * @param myQuestionAnswered boolean
     */
    public void setMyQuestionAnswered(boolean myQuestionAnswered) {
        this.myQuestionAnswered = myQuestionAnswered;
    }

    /**
     * Constructor
     * @param theQuestion string question
     * @param theAnswer string answer
     * @param theQuestionExist boolean
     * @param theType int value
     * @param theQuestionAnswered boolean
     */
    public Door(final String theQuestion, final String theAnswer, final boolean theQuestionExist, final int theType, final boolean theQuestionAnswered){
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myQuestionExist = theQuestionExist;
        myType = theType;
        myQuestionAnswered = theQuestionAnswered;
    }

}

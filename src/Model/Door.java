package Model;
import java.io.Serializable;

public class Door  implements Serializable {
    String question;
    String answer;
    boolean questionExist;
    int myType;
    boolean myQuestionAnswered;

    /**
     * get the type for the question
     * @return the question type
     */
    public int getMyType() {
        return myType;
    }

    /**
     * setter for if the question has been answered.
     * @param myQuestionAnswered if the question has been answered
     */
    public void setMyQuestionAnswered(boolean myQuestionAnswered) {
        this.myQuestionAnswered = myQuestionAnswered;
    }

    /**
     * Constructor for the Door class that contains the question, answer, type, and if answered.
     * @param theQuestion the question from arraylist
     * @param theAnswer the answer to the question
     * @param theQuestionExist if the question exists
     * @param theType the type to the question
     * @param theQuestionAnswered if the question has been answered by the user.
     */
    public Door(String theQuestion, String theAnswer, boolean theQuestionExist, int theType, boolean theQuestionAnswered){
        question = theQuestion;
        answer = theAnswer;
        questionExist = theQuestionExist;
        myType = theType;
        myQuestionAnswered = theQuestionAnswered;
    }

    /**
     * Get the answer string to the question
     * @return the answer string
     */
    public String getAnswer() {
        return answer;
    }

}

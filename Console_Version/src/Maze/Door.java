import java.io.Serializable;

public class Door  implements Serializable {
    String question;
    String answer;
    boolean questionExist;
    int myType;
    boolean myQuestionAnswered;

    public int getMyType() {
        return myType;
    }

    public boolean isMyQuestionAnswered() {
        return myQuestionAnswered;
    }

    public void setMyQuestionAnswered(boolean myQuestionAnswered) {
        this.myQuestionAnswered = myQuestionAnswered;
    }

    public Door(String theQuestion, String theAnswer, boolean theQuestionExist, int theType, boolean theQuestionAnswered){
        question = theQuestion;
        answer = theAnswer;
        questionExist = theQuestionExist;
        myType = theType;
        myQuestionAnswered = theQuestionAnswered;
    }
    public String getQuestion() {
        return question;
    }

    public boolean isQuestionExist() {
        return questionExist;
    }

    public void setQuestionExist(boolean questionExist) {
        this.questionExist = questionExist;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

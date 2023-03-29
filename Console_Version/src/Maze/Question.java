public class Question {
    private final String myQuestion;
    private final String myAnswer;
    private final int myDifficulty;
    private final int myType;

    public int getMyType() {
        return myType;
    }

    public Question(final int theDifficulty, final String theQuestion, final String theAnswer, final int theType){
        myDifficulty = theDifficulty;
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myType = theType;
    }

    public String getMyQuestion() {
        return myQuestion;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public int getMyDifficulty() {
        return myDifficulty;
    }

}
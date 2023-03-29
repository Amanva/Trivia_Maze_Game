/**
 * This class access the SQLite database and stores the questions depending on difficulty.
 *
 * Trivia Maze Game
 * Aman Vahora, Arashpreet S. Pandher, Sophia Young
 * TCSS 360 Spring 2022
 */

package Model;

import org.sqlite.SQLiteDataSource;

import java.io.Serializable;
import java.sql.*;
import java.util.*;


public class SQLQuestions implements Serializable {
    private final ArrayList<Question> questionList = new ArrayList<>();
    private static Question obj = null;
    private static final Random rand = new Random();

    /**
     * getter method for the question arraylist
     * @return the arraylist with questions
     */
    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    /**
     * Selects the difficulty of the questions depending on user.
     * @param theDifficulty the difficulty
     */
    public void selectDiff(final int theDifficulty){

        String sql = "SELECT * FROM QA WHERE Difficulty = ?";
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:Questions.db");
        try {
            Connection conn = ds.getConnection();
            PreparedStatement pstmt  = conn.prepareStatement(sql);
            pstmt.setInt(1,theDifficulty);
            ResultSet rs  = pstmt.executeQuery();

            while (rs.next()) {
                int diff = rs.getInt("Difficulty");
                String ques = rs.getString("Questions");
                String Ans = rs.getString("Answers");
                int type = rs.getInt("Valid");
                obj = new Question(diff, ques, Ans, type);
                questionList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * removes the question from the arraylist.
     * @param theValue the index to be removed
     */
    public void removeQuestion(int theValue){
        questionList.remove(theValue);

    }

    /**
     * Get a random value up to the arraylist size
     * @return the random value
     */
    public int getRandomvalue(){
        if(!questionList.isEmpty()){
            int randVal = rand.nextInt(questionList.size());
            return randVal;
        }
        else{
            return 0;
        }
    }

    /**
     * Get the type of the question
     * @param theValue the index of the arraylist
     * @return the type value of the question
     */
    public int getType(int theValue){
        theValue = questionList.get(theValue).getMyType();
        return theValue;
    }

    /**
     * get the answer for the question from arraylist
     * @param theValue the index
     * @return the answer string
     */
    public String getAnswer(int theValue){
        String answer = questionList.get(theValue).getMyAnswer();
        return answer;
    }

    /**
     * get the question from the arraylist
     * @param theValue the index
     * @return the question string
     */
    public String getQuestion(int theValue){
        String question = questionList.get(theValue).getMyQuestion();
        return question;
    }

}
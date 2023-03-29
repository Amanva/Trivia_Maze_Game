import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.*;


public class SQLQuestions {
    public final ArrayList<Question> questionList = new ArrayList<>();
    private static Question obj = null;
    private static final Random rand = new Random();
    private static String[] choice;
    private static String correctAns;


    public static void main( String args[] ) {

//        selectDiff(1);
//        multQuestion();

    }
    public Question selectDiff(final int theDifficulty){

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
        int val = rand.nextInt(questionList.size());

        return questionList.get(val);
    }

    public Question getterQuestionList(){
        int randVal = rand.nextInt(questionList.size());
        Question question = questionList.get(randVal);
        return question;
    }

    public void removeQuestion(int theValue){
        questionList.remove(theValue);

    }


    public int getRandomvalue(){
        if(!questionList.isEmpty()){
            int randVal = rand.nextInt(questionList.size());
            return randVal;
        }
        else{
            return 0;
        }
    }

    public int getType(int theValue){
        theValue = questionList.get(theValue).getMyType();
        return theValue;
    }
    public String getAnswer(int theValue){
        String answer = questionList.get(theValue).getMyAnswer();
        return answer;
    }


    public String getQuestion(int theValue){
        String question = questionList.get(theValue).getMyQuestion();
        return question;
    }

    public void multQuestion() {
        Scanner scanner = new Scanner(System.in);
        int randVal = rand.nextInt(questionList.size());
        String question = questionList.get(randVal).getMyQuestion();
        String ans = questionList.get(randVal).getMyAnswer();
        choice = ans.split(", ");
        correctAns = choice[0];
        Collections.shuffle(Arrays.asList(choice));
        System.out.println(question + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                "\nC. " + choice[2] + "\nD. " + choice[3]);

        System.out.println("Enter your choice:");
        String choose = scanner.nextLine();
        boolean correct = false;
        if(choose.equalsIgnoreCase("A")){
            if(choice[0].equals(correctAns)){
                correct = true;
            }
        }
        else if(choose.equalsIgnoreCase("B")){
            if(choice[1].equals(correctAns)){
                correct = true;
            }
        }
        else if(choose.equalsIgnoreCase("C")){
            if(choice[2].equals(correctAns)){
                correct = true;
            }
        }
        else if(choose.equalsIgnoreCase("D")){
            if(choice[3].equals(correctAns)){
                correct = true;
            }
        }
        if(correct == true){
            System.out.println("Correct Answer!");
        }
        else {
            System.out.println("Wrong Answer");
        }
        questionList.remove(randVal);

    }


}
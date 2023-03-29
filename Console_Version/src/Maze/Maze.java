import View.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Pattern;

public class Maze {
    private Room[][] myMaze;
    private int myRows;
    private int myColumns;
    private char[][] myArrayMaze;
    private int mySpawnColMaze;
    private int mySpawnRowMaze;
    private int mySpawnColLargeMaze;
    private int mySpawnRowLargeMaze;
    private String correctAnswer;
    private String[] choice;
    private int rand;
    SQLQuestions questions;



    public Maze(final int theRows, final int theColumns){
        myRows = theRows;
        myColumns = theColumns;
        myMaze = new Room[theRows][theColumns];
        setBlankMaze(theRows, theColumns);
    }



    public String getQuestion(){
        rand = questions.getRandomvalue();
        String question = questions.getQuestion(rand);
        return question;
    }

    public String getAnswer(){
        String answer = questions.getAnswer(rand);
        return answer;

    }


    public void setQuestion() {
        int random;
        questions = new SQLQuestions();
        questions.selectDiff(1);
        outer: for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myColumns; j++) {
                Random ran = new Random();
                random = ran.nextInt(2)+1;

                if (random == 1) {
                    if (!myMaze[i][j].isNorthWall()) {
                        Door door = new Door(getQuestion(), getAnswer(),true,checkTypeQuestion(), false);
                        myMaze[i][j].setMyQuestionNorth(door);
                        questions.removeQuestion(rand);
                        if(questions.questionList.size() == 0){
                            break outer;
                        }
                    } if (!myMaze[i][j].isWestWall()) {
                        Door door = new Door(getQuestion(), getAnswer(),true,checkTypeQuestion(), false);
                        myMaze[i][j].setMyQuestionWest(door);
                        questions.removeQuestion(rand);

                        if(questions.questionList.size() == 0){
                            break outer;
                        }
                    } if (!myMaze[i][j].isSouthWall()) {
                        Door door = new Door(getQuestion(), getAnswer(),true,checkTypeQuestion(), false);
                        myMaze[i][j].setMyQuestionSouth(door);
                        questions.removeQuestion(rand);
                        if(questions.questionList.size() == 0){
                            break outer;
                        }
                    } if (!myMaze[i][j].isEastWall()) {
                        Door door = new Door(getQuestion(), getAnswer(),true,checkTypeQuestion(), false);
                        myMaze[i][j].setMyQuestionEast(door);
                        questions.removeQuestion(rand);
                        if(questions.questionList.size() == 0){
                            break outer;
                        }
                    }
                }
            }
        }
        //put in question
    }

    private void setExit(){
        /*
        0,0 : 0,5 : 5,0 : 5,5. These corners need to be checked
        int distance = call getDistance method
        check distance and see if its valid.
        if true then set exit at spot. If not move to the next spot and recheck.
         */
        //0,0
        int distance = getDistance(0,0);
        if(checkDistance(distance)){
            myMaze[0][0].setMyExit(true);
        }
        else{
            distance = getDistance(0,4);
            myMaze[0][4].setMyExit(true);
        }
    }
    /**
     * check if distance is far enough from user spawn.
     */
    private boolean checkDistance(int theDistance){
        if(theDistance > 2){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * This method uses the manhattan distance formula to calculate distance between spawn and exit.
     * @param theRow
     * @param theColumn
     */
    private int getDistance(int theRow, int theColumn){
        //return formula distance
        int dist = Math.abs(mySpawnRowMaze - theRow) + Math.abs(mySpawnColMaze - theColumn);
        return dist;

    }

    private void setBlankMaze(int theRows, int theColumns){
        for(int i = 0; i < theRows; i++){
            for(int j = 0; j < theColumns; j++){
                Room room = new Room();
                myMaze[i][j] = room;
            }
        }
    }


    public int[] checkNeighbors(int x, int y){
        int[] arr = new int[4];
        int counter = 0;
        //N
        if ((x - 1 >= 0 && x - 1 < myRows) && myMaze[x - 1][y].Visited() != true) {
            myMaze[x][y].setDirectionNorth(true);
            arr[0] = counter;
        }
        else{
            arr[0] = -1;
        }
        counter++;
        //W
        if ((y - 1 >= 0 && y - 1 < myColumns) && myMaze[x][y - 1].Visited() != true) {
            myMaze[x][y].setDirectionWest(true);
            arr[1] = counter;
        }
        else{
            arr[1] = -1;
        }
        counter++;
        //S
        if ((x + 1 >= 0 && x + 1 < myRows) && (myMaze[x + 1][y].Visited() != true)) {
            myMaze[x ][y].setDirectionSouth(true);
            arr[2] = counter;
        }
        else{
            arr[2] = -1;
        }
        counter++;
        //E
        if ((y + 1 >= 0 && y + 1 < myColumns) && (myMaze[x][y + 1].Visited() != true)) {
            myMaze[x][y].setDirectionEast(true);
            arr[3] = counter;
        }
        else{
            arr[3] = -1;
        }

        return arr;
        //return array [0,-1,2,-1] containing directions valid.
    }



    public int convertArray(int[] arr){
        int spot = 0;
        int direction = -1;
        int[] arr2 = new int[4];
        int counter = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] >= 0){
                counter++;
                arr2[spot] = arr[i];
                spot++;
            }
            //arr2 = 0,2
        }
        //2
        int rand = (int) (Math.random()*counter);// 0 to 1.
        if(rand == 0){
            direction = arr2[0];
        }
        else if(rand == 1){
            direction = arr2[1];
        }
        else if(rand == 2){
            direction = arr2[2];
        }
        else{
            direction = arr2[3];
        }

        return direction;
    }

    //This method picks random direction and checks if it is valid. If direction is valid return that int val, else
    // return -1 saying all neighbors visited.
    public int chooseDirection(int x, int y, int[] arr){
        //get array and convert to the valid number directions
        //method if [T,F,T,F] == [0,-1,2,-1]
        //0 2, if( math.random 0 to 1)  0 2 3 == 0 1 2, if(math.random 0 to 2, if = 0 then 0, if 1 then 2, if 2 then 3
        int direction =convertArray(arr);// 2, but can only go N. [N,W,S,E] = [T,F,F,F]
        int result = -1;
        if(!myMaze[x][y].getDirectionEast() && !myMaze[x][y].getDirectionWest() && !myMaze[x][y].getDirectionSouth() && !myMaze[x][y].getDirectionNorth()){
            return -1;
        }
        if(direction == 0 ){
            if(myMaze[x][y].getDirectionNorth()){
                result = 0;
            }
            else{
                //1 to 3
                System.out.println("ERROR!!!!!!!!!!!!!!!!");
                direction = (int) (Math.random()*3)+1; //1 2 3
            }
        }
        if(direction == 1){
            if(myMaze[x][y].getDirectionWest()){
                result = 1;
            }
            else{
                System.out.println("ERROR!!!!!!!!!!!!!!!!");

                direction = (int) (Math.random()*2)+2; //2 3
            }
        }
        if(direction == 2){
            if(myMaze[x][y].getDirectionSouth()){
                result = 2;
            }
            else{
                System.out.println("ERROR!!!!!!!!!!!!!!!!");

                direction = 3;
            }
        }
        if(direction == 3){
            if(myMaze[x][y].getDirectionEast()){
                result = 3;
            }
            else{
                System.out.println("ERROR!!!!!!!!!!!!!!!!");

            }
        }



        return result;
    }
    public void generateMaze(){
        //set spawn points
        mySpawnRowMaze = (int) (Math.random()*myRows);
        mySpawnColMaze = mySpawnRowMaze;
        mySpawnColLargeMaze = mySpawnColMaze*2+1;
        mySpawnRowLargeMaze = mySpawnRowMaze*2+1;
        myMaze[mySpawnRowMaze][mySpawnColMaze].setVisited(true);
        myMaze[mySpawnRowMaze][mySpawnColMaze].setMySpawn(true);
        create(mySpawnRowMaze,mySpawnColMaze);
        setExit();
    }

    public void create(int x, int y){
        //check neighbors
        int[] arr = checkNeighbors(x,y);
        int direction  = chooseDirection(x,y,arr);

        if(direction == 0){
            myMaze[x - 1][y].setVisited(true);
            myMaze[x][y].setNorthWall(false);
            myMaze[x-1][y].setSouthWall(false);

            create(x - 1, y);

        }
        else if(direction == 1){
            myMaze[x][y-1].setVisited(true);
            myMaze[x][y].setWestWall(false);
            myMaze[x][y-1].setEastWall(false);

            create(x, y-1);

        }
        else if(direction == 2){
            myMaze[x+1][y].setVisited(true);
            myMaze[x][y].setSouthWall(false);
            myMaze[x+1][y].setNorthWall(false);

            create(x+1,y);

        }
        else if(direction == 3){
            myMaze[x][y+1].setVisited(true);
            myMaze[x][y].setEastWall(false);
            myMaze[x][y+1].setWestWall(false);

            create(x,y+1);

        }
        arr = checkNeighbors(x,y);
        boolean flag = false;
        for(int i = 0 ; i < arr.length; i++){
            if(arr[i] >-1){
                flag = true;
                break;
            }
        }
        if(flag == true){
            create(x,y);
        }
    }


    public void display(){
        for (int i = 0 ; i < myRows; i++){
            for (int j = 0; j < myColumns; j++){
                System.out.println(myMaze[i][j] );
            }
        }
    }

    public void displayDirections(){
        if(!myMaze[mySpawnRowMaze][mySpawnColMaze].isSouthWall()){
            System.out.println("Move down (D)");
        }
        if(!myMaze[mySpawnRowMaze][mySpawnColMaze].isEastWall()){
            System.out.println("Move Right (R)");
        }
        if(!myMaze[mySpawnRowMaze][mySpawnColMaze].isNorthWall()){
            System.out.println("Move Up (U)");
        }
        if(!myMaze[mySpawnRowMaze][mySpawnColMaze].isWestWall()){
            System.out.println("Move Left (L)");
        }
    }

    public boolean checkDirectionForUser(char theDirection){
        boolean flag = true;
        String direction = "" + theDirection;
        if(theDirection == 'D' && myMaze[mySpawnRowMaze][mySpawnColMaze].isSouthWall()){
            System.out.println("Invalid Direction, please re-enter.");
            flag = false;
        }
        else if(theDirection == 'R' && myMaze[mySpawnRowMaze][mySpawnColMaze].isEastWall()){
            System.out.println("Invalid Direction, please re-enter.");
            flag = false;
        }
        else if(theDirection == 'U' && myMaze[mySpawnRowMaze][mySpawnColMaze].isNorthWall()){
            System.out.println("Invalid Direction, please re-enter.");
            flag = false;

        }
        else if(theDirection == 'L' && myMaze[mySpawnRowMaze][mySpawnColMaze].isWestWall()){
            System.out.println("Invalid Direction, please re-enter.");
            flag = false;

        }
        else if(Pattern.compile("[^ULRDXSM]").matcher(direction).matches()){
            System.out.println("Invalid Direction, please re-enter.");
            flag = false;
        }
        return flag;

    }
    public void convertMazeToLarger(){
        myArrayMaze = new char[myRows*3 -( myRows-1)][myColumns*3 -(myColumns-1)];
        for(int i = 0; i < myColumns; i++){
            myArrayMaze[0][myColumns-1] = 'W';
        }
        for(int i = 0; i < myArrayMaze.length;i++){
            //left column
            myArrayMaze[i][0] = 'W';
            //bottom row
            myArrayMaze[myArrayMaze.length-1][i] = 'W';
            //top row
            myArrayMaze[0][i] = 'W';
            //right column
            myArrayMaze[i][myArrayMaze.length-1] = 'W';
        }
        //
        //
        int row = 0;
        int col = 0;
        for (int i = 1; row < myRows; i+=2) {
            for (int j = 1; col < myColumns; j += 2) {
                //9 spots should be used for a room
                //top North
                myArrayMaze[i][j] = 'R';
                if(myArrayMaze[i][j] == 'P'){
                    myArrayMaze[i][j] = 'P';
                }

                if (myMaze[row][col].isNorthWall()) {
                    myArrayMaze[i - 1][j] = 'W';
                } else {
                    myArrayMaze[i - 1][j] = 'D';
                }
                if (myMaze[row][col].isWestWall()) {
                    myArrayMaze[i][j - 1] = 'W';
                } else {
                    myArrayMaze[i][j - 1] = 'D';
                }
                if (myMaze[row][col].isEastWall()) {
                    myArrayMaze[i][j + 1] = 'W';
                } else {
                    myArrayMaze[i][j + 1] = 'D';
                }
                if (myMaze[row][col].isSouthWall()) {
                    myArrayMaze[i + 1][j] = 'W';
                } else {
                    myArrayMaze[i + 1][j] = 'D';

                }
                if(myMaze[row][col].isMySpawn()){
                    myArrayMaze[i][j] = 'P';
                }
                if(myMaze[row][col].getMyExit()){
                    myArrayMaze[i][j] = 'E';
                }
                col++;
            }
            col = 0;
            row++;

        }

        for(int i = 0; i < myArrayMaze.length; i++){
            for(int j = 0; j < myArrayMaze[i].length; j++){
                System.out.print(myArrayMaze[i][j] + " ");
            }
            System.out.println();
        }
    }
    public char checkUserSpot(){
        if(myMaze[mySpawnRowMaze][mySpawnColMaze].getMyExit() == myMaze[mySpawnRowMaze][mySpawnColMaze].isMySpawn()){
            return 'Q';
        }
        else{
            return 'C';
        }
    }

    public void questionAnswered(char theDirection){
        if(theDirection == 'U'){
            myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionNorth().setMyQuestionAnswered(true);
        }
        else if(theDirection == 'D'){
            myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionSouth().setMyQuestionAnswered(true);
        }
        else if(theDirection == 'L'){
            myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionWest().setMyQuestionAnswered(true);
        }
        else{
            myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionEast().setMyQuestionAnswered(true);
        }
    }

    public boolean displayQuestion(char theDirection){
        boolean flag = true;
        if(theDirection == 'U'){
            Door door = myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionNorth();
            if(door != null && !door.myQuestionAnswered) {
                flag = false;
                if(door.getMyType() == 0) {
                    String answer = door.getAnswer();
                    choice = answer.split(", ");
                    correctAnswer = choice[0];
                    Collections.shuffle(Arrays.asList(choice));
                    if (door.questionExist) {
                        System.out.println(door.question + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                                "\nC. " + choice[2] + "\nD. " + choice[3]);
                    }
                }
                else if(door.getMyType() == 1 || door.getMyType() == 2){
                    correctAnswer = door.getAnswer();
                    if(door.questionExist){
                        System.out.println(door.question);
                    }
                }

            }
        }
        else if(theDirection == 'L' ){
            Door door = myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionWest();
            if(door != null && !door.myQuestionAnswered) {
                flag = false;
                if(door.getMyType() == 0) {
                    String answer = door.getAnswer();
                    choice = answer.split(", ");
                    correctAnswer = choice[0];
                    Collections.shuffle(Arrays.asList(choice));
                    if (door.questionExist) {
                        System.out.println(door.question + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                                "\nC. " + choice[2] + "\nD. " + choice[3]);
                    }
                }
                else if(door.getMyType() == 1 || door.getMyType() == 2){
                    correctAnswer = door.getAnswer();
                    if(door.questionExist){
                        System.out.println(door.question);
                    }

                }
            }
        }
        else if(theDirection == 'D'){
            Door door = myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionSouth();
            if(door != null && !door.myQuestionAnswered) {
                flag = false;
                if(door.getMyType() == 0) {
                    String answer = door.getAnswer();
                    choice = answer.split(", ");
                    correctAnswer = choice[0];
                    Collections.shuffle(Arrays.asList(choice));
                    if (door.questionExist) {
                        System.out.println(door.question + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                                "\nC. " + choice[2] + "\nD. " + choice[3]);
                    }
                }
                else if(door.getMyType() == 1 || door.getMyType() == 2){
                    correctAnswer = door.getAnswer();
                    if(door.questionExist){
                        System.out.println(door.question);
                    }

                }
            }
        }
        else if(theDirection == 'R'){
            Door door = myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionEast();
            if(door != null && !door.myQuestionAnswered) {
                flag = false;
                if(door.getMyType() == 0) {
                    String answer = door.getAnswer();
                    choice = answer.split(", ");
                    correctAnswer = choice[0];
                    Collections.shuffle(Arrays.asList(choice));
                    if (door.questionExist) {
                        System.out.println(door.question + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                                "\nC. " + choice[2] + "\nD. " + choice[3]);
                    }
                }
                else if(door.getMyType() == 1 || door.getMyType() == 2){
                    correctAnswer = door.getAnswer();
                    if(door.questionExist){
                        System.out.println(door.question);
                    }

                }
            }
        }
        return flag;
    }

    public int checkTypeQuestion(){
        if(questions.getType(rand) == 0){
            return 0;
        }
        else if(questions.getType(rand) == 1){
            return 1;
        }
        else{
            return 2;
        }
    }

    public int checkTypeQuestionMaze(char theDirecton) {
        if (theDirecton == 'U') {
            if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionNorth().myType == 0) {
                return 0;
            } else if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionNorth().myType == 1) {
                return 1;
            } else {
                return 2;
            }
        } else if (theDirecton == 'D') {
            if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionSouth().myType == 0) {
                return 0;
            } else if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionSouth().myType == 1) {
                return 1;
            } else {
                return 2;
            }
        } else if (theDirecton == 'L') {
            if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionWest().myType == 0) {
                return 0;
            } else if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionWest().myType == 1) {
                return 1;
            } else {
                return 2;
            }
        } else{
            if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionEast().myType == 0) {
                return 0;
            } else if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionEast().myType == 1) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    public boolean shortAnswer(String theInput){
        boolean flag = true;
        if(!theInput.equalsIgnoreCase(correctAnswer)){
            System.out.println("Incorrect Answer");
            flag = false;
        }
        return flag;
    }


    public boolean answerMultipleChoice(char theAnswer){
        boolean flag = true;
        if(theAnswer == 'A'){
            if(correctAnswer != choice[0]){
                System.out.println("Incorrect Answer");
                flag = false;
            }
        }
        else if(theAnswer == 'B'){
            if(correctAnswer != choice[1]){
                System.out.println("Incorrect Answer");
                flag = false;
            }
        }
        else if(theAnswer == 'C'){
            if(correctAnswer != choice[2]){
                System.out.println("Incorrect Answer");
                flag = false;
            }
        }
        else if(theAnswer == 'D'){
            if(correctAnswer != choice[3]){
                System.out.println("Incorrect Answer");
                flag = false;
            }
        }
        return flag;

    }
    public void movePlayer(char theDirection){
        if(theDirection == 'R'){
            myArrayMaze[mySpawnRowLargeMaze][mySpawnColLargeMaze] = 'R';
            myMaze[mySpawnRowMaze][mySpawnColMaze].setMySpawn(false);
            //open question for door myArrayMaze[mySpawn][mySpawn-1]
            myArrayMaze[mySpawnRowLargeMaze][mySpawnColLargeMaze+2] = 'P';
            myMaze[mySpawnRowMaze][mySpawnColMaze+1].setMySpawn(true);
            mySpawnColMaze+=1;
            mySpawnColLargeMaze +=2;
//            System.out.println("Updated");
        }
        else if(theDirection == 'L'){
            myArrayMaze[mySpawnRowLargeMaze][mySpawnColLargeMaze] = 'R';
            myMaze[mySpawnRowMaze][mySpawnColMaze].setMySpawn(false);
            //open question for door myArrayMaze[mySpawn][mySpawn-1]
            myArrayMaze[mySpawnRowLargeMaze][mySpawnColLargeMaze-2] = 'P';
            myMaze[mySpawnRowMaze][mySpawnColMaze-1].setMySpawn(true);
            mySpawnColMaze-=1;
            mySpawnColLargeMaze -=2;
//            System.out.println("Updated");
        }
        else if(theDirection == 'D'){
            myArrayMaze[mySpawnRowLargeMaze][mySpawnColLargeMaze] = 'R';
            myMaze[mySpawnRowMaze][mySpawnColMaze].setMySpawn(false);
            //open question for door myArrayMaze[mySpawn][mySpawn-1]
            myArrayMaze[mySpawnRowLargeMaze+2][mySpawnColLargeMaze] = 'P';
            myMaze[mySpawnRowMaze+1][mySpawnColMaze].setMySpawn(true);
            mySpawnRowMaze+=1;
            mySpawnRowLargeMaze +=2;
//            System.out.println("Updated");
        }
        else if(theDirection == 'U'){
            myArrayMaze[mySpawnRowLargeMaze][mySpawnColLargeMaze] = 'R';
            myMaze[mySpawnRowMaze][mySpawnColMaze].setMySpawn(false);
            //open question for door myArrayMaze[mySpawn][mySpawn-1]
            myArrayMaze[mySpawnRowLargeMaze-2][mySpawnColLargeMaze] = 'P';
            myMaze[mySpawnRowMaze-1][mySpawnColMaze].setMySpawn(true);
            mySpawnRowMaze-=1;
            mySpawnRowLargeMaze -=2;
//            System.out.println("Updated");
        }
    }


    public void saveMaze(ObjectOutputStream theOut) throws IOException {
        theOut.writeObject(myMaze);
        theOut.writeObject(myRows);
        theOut.writeObject(myColumns);
        theOut.writeObject(myArrayMaze);
        theOut.writeObject(mySpawnColMaze);
        theOut.writeObject(mySpawnRowMaze);
        theOut.writeObject(mySpawnColLargeMaze);
        theOut.writeObject(mySpawnRowLargeMaze);
        theOut.flush();
        theOut.close();
    }

    public  void loadMaze(ObjectInputStream theIn) throws IOException, ClassNotFoundException {
        myMaze = (Room[][]) theIn.readObject();
        myRows = (int) theIn.readObject();
        myColumns = (int) theIn.readObject();
        myArrayMaze = (char[][]) theIn.readObject();
        mySpawnColMaze = (int) theIn.readObject();
        mySpawnRowMaze = (int) theIn.readObject();
        mySpawnColLargeMaze = (int) theIn.readObject();
        mySpawnRowLargeMaze = (int) theIn.readObject();
    }
}
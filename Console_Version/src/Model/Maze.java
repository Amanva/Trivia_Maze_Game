/**
 * This class has the functionality for the maze and is responsible for creating the maze
 *
 * Trivia Maze Game
 * Aman Vahora, Arashpreet S. Pandher, Sophia Young
 * TCSS 360 Spring 2022
 */

package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

public class Maze {
    private Room[][] myMaze;

    /**
     * setter for sql database
     * @param myQuestions SQLQuestions object
     */
    public void setMyQuestions(SQLQuestions myQuestions) {
        this.myQuestions = myQuestions;
    }

    private int myRows;
    private int myColumns;
    private char[][] myArrayMaze;
    private int mySpawnColMaze;
    private int mySpawnRowMaze;
    private int mySpawnColLargeMaze;
    private int mySpawnRowLargeMaze;
    private String myCorrectAnswer;
    private String[] choice;
    private int myRand;
    private SQLQuestions myQuestions;

    /**
     * Returns a room object for testing purposes
     * @return Room[][]
     */
    public Room[][] getMyMaze() {
        return myMaze;
    }

    /**
     * Returns a SQLQuestions object for testing purposes
     * @return SQLQuestions
     */
    public SQLQuestions getMyQuestions() {
        return myQuestions;
    }

    /**
     * The maze constructor that sets the size of the maze.
     * @param theRows the number of rows in maze
     * @param theColumns the number of columns in maze
     */
    public Maze(final int theRows, final int theColumns){
        myRows = theRows;
        myColumns = theColumns;
        myMaze = new Room[theRows][theColumns];
        setBlankMaze(theRows, theColumns);
        myQuestions = new SQLQuestions();
    }

    /**
     * Get random value for testing purposes
     * @return get int
     */
    public int getMyRand() {
        return myRand;
    }

    /**
     * This method gets a random question from the question arraylist
     * @return A string with the question
     */
    public String getQuestion(){
        myRand = myQuestions.getRandomvalue();
        String question = myQuestions.getQuestion(myRand);
        return question;
    }

    /**
     * This method gets the answer to the getQuestion() method
     * @return A string with the answer
     */
    public String getAnswer(){
        String answer = myQuestions.getAnswer(myRand);
        return answer;

    }

    /**
     * This method gets the rows for the maze
     * @return the number of rows
     */
    public int getMyRows() {
        return myRows;
    }



    /**
     * This method gets the columns for the maze
     * @return the number of columns
     */
    public int getMyColumns() {
        return myColumns;
    }



    /**
     * This method selects questions from the difficulty the user chooses and sets them within the maze array based on a certain percentage.
     * @param theDifficulty The difficulty of the questions
     */
    public void setDoor(final int theDifficulty) {

        myQuestions.selectDiff(theDifficulty);
        outer: for (int i = 0; i < myRows; i++) {
            for (int j = 0; j < myColumns; j++) {
                int ran;
                if(theDifficulty == 1){
                    ran = (int) ((Math.random() *(11 - 1))+1);
                    if(ran <= 5){
                        ran = 1;
                    }
                }
                else if(theDifficulty == 2){
                    ran = (int) ((Math.random() *(11 - 1))+1);
                    if(ran <= 7){
                        ran = 1;
                    }
                }
                else{
                    ran = (int) ((Math.random() *(11 - 1))+1);
                    if(ran <= 8){
                        ran = 1;
                    }
                }

                if (ran == 1) {
                    if (!myMaze[i][j].isNorthWall()) {
                        Door door = new Door(getQuestion(), getAnswer(),true,checkTypeQuestion(), false);
                        myMaze[i][j].setMyQuestionNorth(door);
                        myQuestions.removeQuestion(myRand);
                        if(myQuestions.getQuestionList().size() == 0){
                            break outer;
                        }
                    } if (!myMaze[i][j].isWestWall()) {
                        Door door = new Door(getQuestion(), getAnswer(),true,checkTypeQuestion(), false);
                        myMaze[i][j].setMyQuestionWest(door);
                        myQuestions.removeQuestion(myRand);

                        if(myQuestions.getQuestionList().size() == 0){
                            break outer;
                        }
                    } if (!myMaze[i][j].isSouthWall()) {
                        Door door = new Door(getQuestion(), getAnswer(),true,checkTypeQuestion(), false);
                        myMaze[i][j].setMyQuestionSouth(door);
                        myQuestions.removeQuestion(myRand);
                        if(myQuestions.getQuestionList().size() == 0){
                            break outer;
                        }
                    } if (!myMaze[i][j].isEastWall()) {
                        Door door = new Door(getQuestion(), getAnswer(),true,checkTypeQuestion(), false);
                        myMaze[i][j].setMyQuestionEast(door);
                        myQuestions.removeQuestion(myRand);
                        if(myQuestions.getQuestionList().size() == 0){
                            break outer;
                        }
                    }
                }
            }
        }
        //put in question
    }

    /**
     * This method sets the exit within the maze either in the top left or top right.
     */
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
    private boolean checkDistance(final int theDistance){
        if(theDistance > 2){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * This method uses the manhattan distance formula to calculate distance between spawn and exit.
     * @param theRow the number of rows
     * @param theColumn the number of columns
     */
    private int getDistance(final int theRow, final int theColumn){
        //return formula distance
        int dist = Math.abs(mySpawnRowMaze - theRow) + Math.abs(mySpawnColMaze - theColumn);
        return dist;

    }

    /**
     * This sets the blank maze of rooms according to the size
     * @param theRows the number of rows
     * @param theColumns the number of columns
     */
    private void setBlankMaze(final int theRows, final int theColumns){
        for(int i = 0; i < theRows; i++){
            for(int j = 0; j < theColumns; j++){
                Room room = new Room();
                myMaze[i][j] = room;
            }
        }
    }

    /**
     * Checks if the direction the maze can go to is a valid direction and that it isnt visited.
     * @param theX the row maze spawn for backtracking
     * @param theY the column maze spawn for backtracking
     * @return An array containing the valid directions
     */
    public int[] checkNeighbors(final int theX, final int theY){
        int[] arr = new int[4];
        int counter = 0;
        //N
        if ((theX - 1 >= 0 && theX - 1 < myRows) && myMaze[theX - 1][theY].isMyVisited() != true) {
            myMaze[theX][theY].setDirectionNorth(true);
            arr[0] = counter;
        }
        else{
            arr[0] = -1;
        }
        counter++;
        //W
        if ((theY- 1 >= 0 && theY- 1 < myColumns) && myMaze[theX][theY- 1].isMyVisited() != true) {
            myMaze[theX][theY].setDirectionWest(true);
            arr[1] = counter;
        }
        else{
            arr[1] = -1;
        }
        counter++;
        //S
        if ((theX + 1 >= 0 && theX + 1 < myRows) && (myMaze[theX + 1][theY].isMyVisited() != true)) {
            myMaze[theX][theY].setDirectionSouth(true);
            arr[2] = counter;
        }
        else{
            arr[2] = -1;
        }
        counter++;
        //E
        if ((theY+ 1 >= 0 && theY+ 1 < myColumns) && (myMaze[theX][theY+ 1].isMyVisited() != true)) {
            myMaze[theX][theY].setDirectionEast(true);
            arr[3] = counter;
        }
        else{
            arr[3] = -1;
        }

        return arr;
        //return arr[0,-1,2,-1] containing directions valid.
    }


    public char[][] getMyArrayMaze() {
        return myArrayMaze;
    }

    public void setMyArrayMaze(final char[][] myArrayMaze) {
        this.myArrayMaze = myArrayMaze;
    }

    public int getMySpawnColMaze() {
        return mySpawnColMaze;
    }

    public void setMySpawnColMaze(final int mySpawnColMaze) {
        this.mySpawnColMaze = mySpawnColMaze;
    }

    public int getMySpawnRowMaze() {
        return mySpawnRowMaze;
    }

    public void setMySpawnRowMaze(final int mySpawnRowMaze) {
        this.mySpawnRowMaze = mySpawnRowMaze;
    }


    public void setMySpawnColLargeMaze(final int mySpawnColLargeMaze) {
        this.mySpawnColLargeMaze = mySpawnColLargeMaze;
    }

    public int getMySpawnRowLargeMaze() {
        return mySpawnRowLargeMaze;
    }

    public void setMySpawnRowLargeMaze(final int mySpawnRowLargeMaze) {
        this.mySpawnRowLargeMaze = mySpawnRowLargeMaze;
    }

    /**
     * Converts the checkNeighbours array into a random direction the maze can go.
     * @param theArr the array with valid directions
     * @return the direction for maze
     */
    public int convertArray(final int[] theArr){
        int spot = 0;
        int direction = -1;
        int[] arr2 = new int[4];
        int counter = 0;
        for(int i = 0; i < theArr.length; i++){
            if(theArr[i] >= 0){
                counter++;
                arr2[spot] = theArr[i];
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

    /**
     * This method picks random direction and checks if it is valid. If direction is valid return that int val, else
     * @param theX the row maze spawn for backtracking
     * @param theY the column maze spawn for backtracking
     * @param theArr the array with valid directions
     * @return result saying all neighbors visited.
     */
    public int chooseDirection(final int theX, final int theY, final int[] theArr){
        //get array and convert to the valid number directions
        //method if [T,F,T,F] == [0,-1,2,-1]
        //0 2, if( math.random 0 to 1)  0 2 3 == 0 1 2, if(math.random 0 to 2, if = 0 then 0, if 1 then 2, if 2 then 3
        int direction =convertArray(theArr);// 2, but can only go N. [N,W,S,E] = [T,F,F,F]
        int result = -1;
        if(!myMaze[theX][theY].getDirectionEast() && !myMaze[theX][theY].getDirectionWest() && !myMaze[theX][theY].getDirectionSouth() && !myMaze[theX][theY].getDirectionNorth()){
            return -1;
        }
        if(direction == 0 ){
            if(myMaze[theX][theY].getDirectionNorth()){
                result = 0;
            }
            else{
                //1 to 3
                System.out.println("ERROR!!!!!!!!!!!!!!!!");
                direction = (int) (Math.random()*3)+1; //1 2 3
            }
        }
        if(direction == 1){
            if(myMaze[theX][theY].getDirectionWest()){
                result = 1;
            }
            else{
                System.out.println("ERROR!!!!!!!!!!!!!!!!");

                direction = (int) (Math.random()*2)+2; //2 3
            }
        }
        if(direction == 2){
            if(myMaze[theX][theY].getDirectionSouth()){
                result = 2;
            }
            else{
                System.out.println("ERROR!!!!!!!!!!!!!!!!");

                direction = 3;
            }
        }
        if(direction == 3){
            if(myMaze[theX][theY].getDirectionEast()){
                result = 3;
            }
            else{
                System.out.println("ERROR!!!!!!!!!!!!!!!!");

            }
        }



        return result;
    }

    /**
     * generate the maze with recursive backtracking
     */
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

    /**
     * Uses recursive backtracking to create a path that the user is able to go through.
     * @param theX the row maze spawn for backtracking
     * @param theY the column maze spawn for backtracking
     */
    public void create(final int theX, final int theY){
        //check neighbors
        int[] arr = checkNeighbors(theX,theY);
        int direction  = chooseDirection(theX,theY,arr);

        if(direction == 0){
            myMaze[theX - 1][theY].setVisited(true);
            myMaze[theX][theY].setNorthWall(false);
            myMaze[theX-1][theY].setSouthWall(false);

            create(theX - 1, theY);

        }
        else if(direction == 1){
            myMaze[theX][theY-1].setVisited(true);
            myMaze[theX][theY].setWestWall(false);
            myMaze[theX][theY-1].setEastWall(false);

            create(theX, theY-1);

        }
        else if(direction == 2){
            myMaze[theX+1][theY].setVisited(true);
            myMaze[theX][theY].setSouthWall(false);
            myMaze[theX+1][theY].setNorthWall(false);

            create(theX+1,theY);

        }
        else if(direction == 3){
            myMaze[theX][theY+1].setVisited(true);
            myMaze[theX][theY].setEastWall(false);
            myMaze[theX][theY+1].setWestWall(false);

            create(theX,theY+1);

        }
        arr = checkNeighbors(theX,theY);
        boolean flag = false;
        for(int i = 0 ; i < arr.length; i++){
            if(arr[i] >-1){
                flag = true;
                break;
            }
        }
        if(flag == true){
            create(theX,theY);
        }
    }

    /**
     * display the valid directions the user can go.
     */
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

    /**
     * check if the direction the user enters if invalid due to a wall.
     * @param theDirection the direction the user enters
     * @return A boolean for while loop
     */
    public boolean checkDirectionForUser(final char theDirection){
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
        else if(Pattern.compile("[^ULRDXSMQ]").matcher(direction).matches()){
            System.out.println("Invalid Direction, please re-enter.");
            flag = false;
        }
        return flag;

    }

    /**
     * Converts the size of the maze into a bigger maze.
     */
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
                if(myArrayMaze[i][j] == 'P'){
                    String color = "\u001B[31m";
                    String colorClose = "\u001B[0m";
                    System.out.print(color + myArrayMaze[i][j] + colorClose + " ");
                }
                else if(myArrayMaze[i][j] == 'E'){
                    String color = "\u001B[34m";
                    String colorClose = "\u001B[0m";
                    System.out.print(color + myArrayMaze[i][j] + colorClose + " ");
                }
                else{
                    System.out.print(myArrayMaze[i][j] + " ");

                }
            }
            System.out.println();
        }
    }

    /**
     * Check if the user has reached the exit and end the program
     * @return a char value for exiting
     */
    public char checkUserSpot(){
        if(myMaze[mySpawnRowMaze][mySpawnColMaze].getMyExit() == myMaze[mySpawnRowMaze][mySpawnColMaze].isMySpawn()){
            return 'Q';
        }
        else{
            return 'C';
        }
    }

    /**
     * Check if the question has been answered
     * @param theDirection the direction the user enters
     */
    public void questionAnswered(final char theDirection){
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

    /**
     * This method displays the question to the user depending on if it is multiple choice, short answer, and true or false.
     * @param theDirection the direction the user enters
     * @return a boolean value.
     */
    public boolean displayQuestion(final char theDirection){
        boolean flag = true;
        if(theDirection == 'U'){
            Door door = myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionNorth();
            if(door != null && !door.isMyQuestionAnswered()) {
                flag = false;
                if(door.getMyType() == 0) {
                    String answer = door.getMyAnswer();
                    choice = answer.split(", ");
                    myCorrectAnswer = choice[0];
                    Collections.shuffle(Arrays.asList(choice));
                    if (door.isMyQuestionExist()) {
                        System.out.println(door.getMyQuestion() + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                                "\nC. " + choice[2] + "\nD. " + choice[3]);
                    }
                }
                else if(door.getMyType() == 1 || door.getMyType() == 2){
                    myCorrectAnswer = door.getMyAnswer();
                    if(door.isMyQuestionExist()){
                        System.out.println(door.getMyQuestion());
                    }
                }

            }
        }
        else if(theDirection == 'L' ){
            Door door = myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionWest();
            if(door != null && !door.isMyQuestionAnswered()) {
                flag = false;
                if(door.getMyType() == 0) {
                    String answer = door.getMyAnswer();
                    choice = answer.split(", ");
                    myCorrectAnswer = choice[0];
                    Collections.shuffle(Arrays.asList(choice));
                    if (door.isMyQuestionExist()) {
                        System.out.println(door.getMyQuestion() + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                                "\nC. " + choice[2] + "\nD. " + choice[3]);
                    }
                }
                else if(door.getMyType() == 1 || door.getMyType() == 2){
                    myCorrectAnswer = door.getMyAnswer();
                    if(door.isMyQuestionExist()){
                        System.out.println(door.getMyQuestion());
                    }

                }
            }
        }
        else if(theDirection == 'D'){
            Door door = myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionSouth();
            if(door != null && !door.isMyQuestionAnswered()) {
                flag = false;
                if(door.getMyType() == 0) {
                    String answer = door.getMyAnswer();
                    choice = answer.split(", ");
                    myCorrectAnswer = choice[0];
                    Collections.shuffle(Arrays.asList(choice));
                    if (door.isMyQuestionExist()) {
                        System.out.println(door.getMyQuestion() + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                                "\nC. " + choice[2] + "\nD. " + choice[3]);
                    }
                }
                else if(door.getMyType() == 1 || door.getMyType() == 2){
                    myCorrectAnswer = door.getMyAnswer();
                    if(door.isMyQuestionExist()){
                        System.out.println(door.getMyQuestion());
                    }

                }
            }
        }
        else if(theDirection == 'R'){
            Door door = myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionEast();
            if(door != null && !door.isMyQuestionAnswered()) {
                flag = false;
                if(door.getMyType() == 0) {
                    String answer = door.getMyAnswer();
                    choice = answer.split(", ");
                    myCorrectAnswer = choice[0];
                    Collections.shuffle(Arrays.asList(choice));
                    if (door.isMyQuestionExist()) {
                        System.out.println(door.getMyQuestion() + "\n\nA. " + choice[0] + "\nB. " + choice[1] +
                                "\nC. " + choice[2] + "\nD. " + choice[3]);
                    }
                }
                else if(door.getMyType() == 1 || door.getMyType() == 2){
                    myCorrectAnswer = door.getMyAnswer();
                    if(door.isMyQuestionExist()){
                        System.out.println(door.getMyQuestion());
                    }

                }
            }
        }
        return flag;
    }

    /**
     * setter for rand value
     * @param myRand int value
     */
    public void setMyRand(int myRand) {
        this.myRand = myRand;
    }

    /**
     * check what type of question it is
     * @return the question type
     */
    public int checkTypeQuestion(){
        if(myQuestions.getType(myRand) == 0){
            return 0;
        }
        else if(myQuestions.getType(myRand) == 1){
            return 1;
        }
        else{
            return 2;
        }
    }

    /**
     * check the type of question that is displayed to the maze
     * @param theDirection the direction the user enters
     * @return the type of the question
     */
    public int checkTypeQuestionMaze(final char theDirection) {
        if (theDirection == 'U') {
            if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionNorth().getMyType() == 0) {
                return 0;
            } else if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionNorth().getMyType() == 1) {
                return 1;
            } else {
                return 2;
            }
        } else if (theDirection == 'D') {
            if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionSouth().getMyType() == 0) {
                return 0;
            } else if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionSouth().getMyType() == 1) {
                return 1;
            } else {
                return 2;
            }
        } else if (theDirection == 'L') {
            if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionWest().getMyType() == 0) {
                return 0;
            } else if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionWest().getMyType() == 1) {
                return 1;
            } else {
                return 2;
            }
        } else{
            if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionEast().getMyType() == 0) {
                return 0;
            } else if (myMaze[mySpawnRowMaze][mySpawnColMaze].getMyQuestionEast().getMyType() == 1) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    /**
     * Check if the user enters the correct answer
     * @param theInput the value the user enters
     * @return a boolean value if correct or not
     */
    public boolean shortAnswer(final String theInput){
        boolean flag = true;
        if(!theInput.equalsIgnoreCase(myCorrectAnswer)){
            System.out.println("Incorrect Answer");
            flag = false;
        }
        return flag;
    }

    /**
     * Getter for testing
     * @return String value
     */
    public String getMyCorrectAnswer() {
        return myCorrectAnswer;
    }

    /**
     * setter for testing
     * @param myCorrectAnswer String
     */
    public void setMyCorrectAnswer(String myCorrectAnswer) {
        this.myCorrectAnswer = myCorrectAnswer;
    }

    /**
     * Getter for array choice
     * @return String array
     */
    public String[] getChoice() {
        return choice;
    }

    /**
     * Setter for array choice
     * @param choice String array
     */
    public void setChoice(String[] choice) {
        this.choice = choice;
    }

    /**
     * check if the user selected the correct answer to the multiple choice question.
     * @param theAnswer the user answer
     * @return a boolean value
     */
    public boolean answerMultipleChoice(final char theAnswer){
        boolean flag = true;
        if(theAnswer == 'A'){
            if(myCorrectAnswer != choice[0]){
                System.out.println("Incorrect Answer");
                flag = false;
            }
        }
        else if(theAnswer == 'B'){
            if(myCorrectAnswer != choice[1]){
                System.out.println("Incorrect Answer");
                flag = false;
            }
        }
        else if(theAnswer == 'C'){
            if(myCorrectAnswer != choice[2]){
                System.out.println("Incorrect Answer");
                flag = false;
            }
        }
        else if(theAnswer == 'D'){
            if(myCorrectAnswer != choice[3]){
                System.out.println("Incorrect Answer");
                flag = false;
            }
        }
        return flag;

    }

    /**
     * Move the player in the direction the user selected and update the maze
     * @param theDirection the direction the user chooses
     */
    public void movePlayer(final char theDirection){
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

    /**
     * Save the maze using serialization
     * @param theOut The object for writing
     * @throws IOException
     */
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

    /**
     * Loads the maze by deserializing
     * @param theIn The object for reading
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadMaze(ObjectInputStream theIn) throws IOException, ClassNotFoundException {
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
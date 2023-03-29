//package Model;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MazeTest {
//    Maze maze = new Maze(5,5);
//
//
//
//    /**
//     * This method tests if setDoor, getQuestion, and getAnswer are being stored into maze correctly
//     */
//    @org.junit.jupiter.api.Test
//    void setDoor() {
//        maze.generateMaze();
//        maze.setDoor(1);
//        Room[][] myMaze;
//        SQLQuestions questions = new SQLQuestions();
//        questions.selectDiff(1);
//        ArrayList<Question> arraylist = questions.getQuestionList();
//
//        myMaze = maze.getMyMaze();
//
//        for(int i = 0; i < maze.getMyColumns();i++){
//            for(int j= 0; j < maze.getMyRows(); j++){
//                //check if questionlist is setting questions.
//                if(!myMaze[i][j].isEastWall() && myMaze[i][j].getMyQuestionEast() != null) {
//                    if (myMaze[i][j].getMyQuestionEast().isMyQuestionExist()) {
//                        Door question = myMaze[i][j].getMyQuestionEast();
//                        String doorQuestion = question.getMyQuestion();
//                        String doorAnswer = question.getMyAnswer();
//                        for (int l = 0; l < arraylist.size(); l++) {
//                            String quest = arraylist.get(l).getMyQuestion();
//                            String ans = arraylist.get(l).getMyAnswer();
//                            if (quest.equalsIgnoreCase(doorQuestion)) {
//                                assertEquals(quest, doorQuestion);
//                                assertEquals(ans, doorAnswer);
//                            }
//
//                        }
//                    }
//                }
//                if(!myMaze[i][j].isNorthWall() && myMaze[i][j].getMyQuestionNorth() != null) {
//                    if (myMaze[i][j].getMyQuestionNorth().isMyQuestionExist()) {
//                        Door question = myMaze[i][j].getMyQuestionNorth();
//                        String doorQuestion = question.getMyQuestion();
//                        for (int l = 0; l < arraylist.size(); l++) {
//                            String quest = arraylist.get(l).getMyQuestion();
//                            if (quest == doorQuestion) {
//                                assertEquals(quest, doorQuestion);
//                            }
//                        }
//                    }
//                }
//                if(!myMaze[i][j].isWestWall() && myMaze[i][j].getMyQuestionWest() != null) {
//                    if (myMaze[i][j].getMyQuestionWest().isMyQuestionExist()) {
//                        Door question = myMaze[i][j].getMyQuestionWest();
//                        String doorQuestion = question.getMyQuestion();
//                        for (int l = 0; l < arraylist.size(); l++) {
//                            String quest = arraylist.get(l).getMyQuestion();
//                            if (quest == doorQuestion) {
//                                assertEquals(quest, doorQuestion);
//                            }
//                        }
//                    }
//                }
//                if(!myMaze[i][j].isSouthWall() && myMaze[i][j].getMyQuestionSouth() != null) {
//                    if (myMaze[i][j].getMyQuestionSouth().isMyQuestionExist()) {
//                        Door question = myMaze[i][j].getMyQuestionSouth();
//                        String doorQuestion = question.getMyQuestion();
//                        for (int l = 0; l < arraylist.size(); l++) {
//                            String quest = arraylist.get(l).getMyQuestion();
//                            if (quest == doorQuestion) {
//                                assertEquals(quest, doorQuestion);
//                            }
//                        }
//                    }
//                }
//
//
//            }
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    void checkNeighbors() {
//        maze.generateMaze();
//        int[] result = maze.checkNeighbors(2,2);
//        int[] expected = {-1,-1,-1,-1};
//        assertEquals(Arrays.toString(expected), Arrays.toString(result));
//    }
//
//    @org.junit.jupiter.api.Test
//    void convertArray() {
//        int [] arr = {-1,1,-1,-1};
//        int [] arr2 = {-1,-1,-1,3};
//        int x = maze.convertArray(arr);
//        int y = maze.convertArray(arr2);
//        assertEquals(1,x);
//        assertEquals(3,y);
//    }
//
//    @org.junit.jupiter.api.Test
//    void chooseDirection() {
//        int [] arr2 = {-1,-1,-1,3};
//        int x = maze.chooseDirection(1,1,arr2);
//        assertEquals(-1,x);
//    }
//
//    /**
//     * This method check if all
//     */
//    @org.junit.jupiter.api.Test
//    void generateMaze() {
//        maze.generateMaze();
//    }
//
//    /**
//     * This test is to make sure all the rooms are visited in the maze for recursion
//     */
//    @org.junit.jupiter.api.Test
//    void create() {
//        maze.generateMaze();
//        Room[][] myMaze = maze.getMyMaze();
//
//        for (int i = 0; i < maze.getMyRows(); i++){
//            for(int j = 0; j < maze.getMyColumns(); j++){
//                assertEquals(true, myMaze[i][j].isMyVisited());
//            }
//        }
//
//    }
//
//
//
//    @org.junit.jupiter.api.Test
//    void checkDirectionForUser() {
//        assertEquals(false, maze.checkDirectionForUser('D'));
//        assertEquals(false, maze.checkDirectionForUser('R'));
//        assertEquals(false, maze.checkDirectionForUser('L'));
//        assertEquals(false, maze.checkDirectionForUser('U'));
//
//        Room[][] myMaze = maze.getMyMaze();
//        myMaze[0][0].setSouthWall(false);
//        assertEquals(true, maze.checkDirectionForUser('D'));
//    }
//
//    @org.junit.jupiter.api.Test
//    void convertMazeToLarger() {
//        maze.convertMazeToLarger();
//        char[][] myMaze = maze.getMyArrayMaze();
//        Room[][] myMazeSmaller = maze.getMyMaze();
//        for(int i = 0; i < maze.getMySpawnRowLargeMaze(); i++){
//            assertEquals('W', myMaze[i][0]);
//            //bottom row
//            assertEquals('W', myMaze[maze.getMySpawnRowMaze()-1][i]);
//            //top row
//            assertEquals('W', myMaze[0][i]);
////            //right column
//            assertEquals('W', myMaze[i][maze.getMySpawnRowMaze()-1]);
//        }
//        int row = 0;
//        int col = 0;
//        for (int i = 1; row < maze.getMyRows(); i+=2) {
//            for (int j = 1; col < maze.getMyColumns(); j += 2) {
//                //9 spots should be used for a room
//                //top North
//                assertEquals('R',myMaze[i][j]);
//                if(myMaze[i][j] == 'P'){
//                    assertEquals('P',myMaze[i][j]);
//                }
//
//                if (myMazeSmaller[row][col].isNorthWall()) {
//                    assertEquals('W',myMaze[i - 1][j]  );
//
//                } else {
//                    assertEquals('D', myMaze[i - 1][j]);
//
//                }
//                if (myMazeSmaller[row][col].isWestWall()) {
//                    assertEquals('W', myMaze[i][j - 1]);
//
//                } else {
//                    assertEquals('D', myMaze[i][j - 1]);
//                }
//                if (myMazeSmaller[row][col].isEastWall()) {
//                    assertEquals('W',myMaze[i][j + 1]);
//                } else {
//                    assertEquals('D', myMaze[i][j + 1]);
//                }
//                if (myMazeSmaller[row][col].isSouthWall()) {
//                    assertEquals('W', myMaze[i + 1][j] );
//
//                } else {
//                    assertEquals('D', myMaze[i + 1][j]);
//
//                }
//                if(myMazeSmaller[row][col].isMySpawn()){
//                    assertEquals('P', myMaze[i][j] );
//
//                }
//                if(myMazeSmaller[row][col].getMyExit()){
//                    assertEquals('E',myMaze[i][j] );
//                }
//                col++;
//            }
//            col = 0;
//            row++;
//
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    void checkUserSpot() {
//        maze.generateMaze();
//        Room[][] myMaze= maze.getMyMaze();
//        myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].setMySpawn(false);
//        maze.setMySpawnRowMaze(0);
//        maze.setMySpawnColMaze(0);
//        myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].setMySpawn(true);
//        if(myMaze[0][0].getMyExit()){
//            assertEquals('Q', maze.checkUserSpot());
//        }
//        else{
//            myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].setMySpawn(false);
//            maze.setMySpawnRowMaze(0);
//            maze.setMySpawnColMaze(4);
//            myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].setMySpawn(true);
//            assertEquals('Q', maze.checkUserSpot());
//        }
//    }
//
//    /**
//     * Checks if a question exists and hasnt been answered then displays it.
//     */
//    @org.junit.jupiter.api.Test
//    void displayQuestion() {
//        maze.generateMaze();
//        Room[][] myMaze= maze.getMyMaze();
//        maze.setDoor(3);
//        myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].setMySpawn(false);
//        maze.setMySpawnRowMaze(0);
//        maze.setMySpawnColMaze(0);
//        maze.setMySpawnColLargeMaze(0);
//        maze.setMySpawnRowLargeMaze(0);
//        myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].setMySpawn(true);
//
//
//
//        if(!myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].isEastWall() && myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionEast() != null) {
//            if (myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionEast().isMyQuestionExist()) {
//                myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionEast().setMyQuestionAnswered(true);
//                assertEquals(true, maze.displayQuestion('R'));
//            }
//        }
//        if(!myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].isNorthWall() && myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionNorth() != null) {
//            if (myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionNorth().isMyQuestionExist()) {
//                myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionNorth().setMyQuestionAnswered(true);
//                assertEquals(true, maze.displayQuestion('U'));
//            }
//        }
//        if(!myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].isWestWall() && myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionWest() != null) {
//            if (myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionWest().isMyQuestionExist()) {
//                myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionWest().setMyQuestionAnswered(true);
//                assertEquals(true, maze.displayQuestion('L'));
//
//            }
//        }
//        if(!myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].isSouthWall() && myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionSouth() != null) {
//            if (myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionSouth().isMyQuestionExist()) {
//                myMaze[maze.getMySpawnRowMaze()][maze.getMySpawnColMaze()].getMyQuestionSouth().setMyQuestionAnswered(true);
//                assertEquals(true, maze.displayQuestion('D'));
//            }
//        }
//
//    }
//
//    @org.junit.jupiter.api.Test
//    void checkTypeQuestion() {
//        maze.generateMaze();
//        SQLQuestions questions = new SQLQuestions();
//        questions.selectDiff(1);
//        maze.setMyQuestions(questions);
//        maze.setMyRand(1);
//        assertEquals(0, maze.checkTypeQuestion());
//
//    }
//
//    @org.junit.jupiter.api.Test
//    void checkTypeQuestionMaze() {
//        Room[][] myMaze= maze.getMyMaze();
//        Door door = new Door("Question", "answer", true, 0, false);
//        myMaze[0][0].setMyQuestionEast(door);
//        Door door2 = new Door("Question", "answer", true, 1, false);
//        myMaze[0][0].setMyQuestionNorth(door2);
//
//        assertEquals(0,maze.checkTypeQuestionMaze('R'));
//        assertEquals(1,maze.checkTypeQuestionMaze('U'));
//
//    }
//
//    @org.junit.jupiter.api.Test
//    void shortAnswer() {
//        Room[][] myMaze= maze.getMyMaze();
//        Door door = new Door("Question", "answer2", true, 0, false);
//        myMaze[0][0].setMyQuestionNorth(door);
//        maze.setMyCorrectAnswer("Answer2");
//        assertEquals(true, maze.shortAnswer("Answer2"));
//        maze.setMyCorrectAnswer("Answer");
//        assertEquals(false, maze.shortAnswer("Answer2"));
//        maze.setMyCorrectAnswer("answer2");
//        assertEquals(true, maze.shortAnswer("Answer2"));
//    }
//
//    @org.junit.jupiter.api.Test
//    void answerMultipleChoice() {
//        Room[][] myMaze= maze.getMyMaze();
//        String[] choice = {"answer","incorrect", "incorrect", "incorrect"};
//        maze.setChoice(choice);
//        maze.setMyCorrectAnswer("answer");
//        assertEquals(true,maze.answerMultipleChoice('A'));
//
//    }
//
//}
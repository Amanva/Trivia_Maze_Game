/**
 * This class sets up the room which will be in each spot of the maze.
 *
 * Trivia Maze Game
 * Aman Vahora, Arashpreet S. Pandher, Sophia Young
 * TCSS 360 Spring 2022
 */
package Model;

import java.io.Serializable;

public class Room implements Serializable {

    private boolean myVisited;
    private boolean myNorth;
    private boolean mySouth;
    private boolean myWest;
    private boolean myEast;

    private Door myQuestionNorth;
    private Door myQuestionWest;
    private Door myQuestionSouth;
    private Door myQuestionEast;
    //booleans for walls keep track of walls

    private boolean myNorthWall;
    private boolean mySouthWall;
    private boolean myEastWall;
    private boolean myWestWall;

    private boolean myExit;

    private boolean mySpawn;

    /**
     * getter for the North question
     * @return North question
     */
    public Door getMyQuestionNorth() {
        return myQuestionNorth;
    }
    /**
     * setter for the North question
     */
    public void setMyQuestionNorth(Door myQuestionNorth) {
        this.myQuestionNorth = myQuestionNorth;
    }

    /**
     * getter for the West question
     * @return West question
     */
    public Door getMyQuestionWest() {
        return myQuestionWest;
    }
    /**
     * setter for the West question
     */
    public void setMyQuestionWest(Door myQuestionWest) {
        this.myQuestionWest = myQuestionWest;
    }
    /**
     * getter for the South question
     * @return South question
     */
    public Door getMyQuestionSouth() {
        return myQuestionSouth;
    }
    /**
     * setter for the South question
     */
    public void setMyQuestionSouth(Door myQuestionSouth) {
        this.myQuestionSouth = myQuestionSouth;
    }
    /**
     * getter for the East question
     * @return East question
     */
    public Door getMyQuestionEast() {
        return myQuestionEast;
    }
    /**
     * setter for the East question
     */
    public void setMyQuestionEast(Door myQuestionEast) {
        this.myQuestionEast = myQuestionEast;
    }

    /**
     * Boolean for the spawn
     * @return If spawn
     */
    public boolean isMySpawn() {
        return mySpawn;
    }

    /**
     * Sets the spawn
     * @param mySpawn the boolean for spawn
     */
    public void setMySpawn(boolean mySpawn) {
        this.mySpawn = mySpawn;
    }

    /**
     * Getter for exit
     * @return the exit
     */
    public boolean getMyExit(){
        return myExit;
    }

    /**
     * setter for exit
     * @param theBool the boolean value
     */
    public void setMyExit(boolean theBool){
        myExit = theBool;
    }

    /**
     * Check if there is a North wall
     * @return If there is a wall
     */
    public boolean isNorthWall() {
        return myNorthWall;
    }

    /**
     * Set if there is a wall
     * @param theNorthWall If there is a north wall
     */
    public void setNorthWall(boolean theNorthWall) {
        myNorthWall = theNorthWall;
    }
    /**
     * Check if there is a South wall
     * @return If there is a wall
     */
    public boolean isSouthWall() {
        return mySouthWall;
    }
    /**
     * Set if there is a wall
     * @param theSouthWall If there is a South wall
     */
    public void setSouthWall(boolean theSouthWall) {
        mySouthWall = theSouthWall;
    }
    /**
     * Check if there is a East wall
     * @return If there is a wall
     */
    public boolean isEastWall() {
        return myEastWall;
    }
    /**
     * Set if there is a wall
     * @param theEastWall If there is a East wall
     */
    public void setEastWall(boolean theEastWall) {
        myEastWall = theEastWall;
    }
    /**
     * Check if there is a West wall
     * @return If there is a wall
     */
    public boolean isWestWall() {
        return myWestWall;
    }
    /**
     * Set if there is a wall
     * @param theWestWall If there is a West wall
     */
    public void setWestWall(boolean theWestWall) {
        myWestWall = theWestWall;
    }


    /**
     * Setter for north direction
     * @param theNorth If North direction is valid
     */
    public void setDirectionNorth(boolean theNorth){
        myNorth = theNorth;
    }
    /**
     * Setter for West direction
     * @param theWest If West direction is valid
     */
    public void setDirectionWest(boolean theWest){
        myWest = theWest;
    }
    /**
     * Setter for East direction
     * @param theEast If East direction is valid
     */
    public void setDirectionEast(boolean theEast){
        myEast = theEast;
    }
    /**
     * Setter for South direction
     * @param theSouth If South direction is valid
     */
    public void setDirectionSouth(boolean theSouth){
        mySouth = theSouth;
    }
    /**
     * Getter for North direction
     * @return the North direction
     */
    public boolean getDirectionNorth(){
        return myNorth;
    }
    /**
     * Getter for West direction
     * @return the West direction
     */
    public boolean getDirectionWest(){
        return myWest;
    }
    /**
     * Getter for East direction
     * @return the East direction
     */
    public boolean getDirectionEast(){
        return myEast;
    }
    /**
     * Getter for South direction
     * @return the South direction
     */
    public boolean getDirectionSouth(){
        return mySouth;
    }

    /**
     * Constructor for the Room class and sets each of the walls to true
     */
    public Room() {
        myEastWall = true;
        myWestWall = true;
        mySouthWall = true;
        myNorthWall = true;
        myVisited = false;
    }

    /**
     * Check if a spot in the maze has been visited
     * @return if visited
     */
    public boolean isMyVisited(){
        return myVisited;
    }

    /**
     * set true if the spot has been visited
     * @param theVisited if visited
     */
    public void setVisited(boolean theVisited){
        myVisited = theVisited;
    }


}
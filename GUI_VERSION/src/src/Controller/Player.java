/**
 * This class sets up the player for health
 *
 * Trivia Maze Game
 * Aman Vahora, Arashpreet S. Pandher, Sophia Young
 * TCSS 360 Spring 2022
 */
package Controller;

import java.io.Serializable;

public class Player implements Serializable {
    private int myHealth;

    /**
     * This is the constructor
     * @param theHealth int value
     */
    public Player(int theHealth) {
        myHealth = theHealth;
    }

    /**
     * This method gets the health
     * @return int
     */
    public int getMyHealth() {
        return myHealth;
    }

    /**
     * method sets the health
     * @param myHealth int value
     */
    public void setMyHealth(int myHealth) {
        this.myHealth = myHealth;
    }

    /**
     * Method decreases the health
     */
    public void decreaseHealth(){
        myHealth -=1;
    }

    /**
     * This method checks if alive or not
     * @return boolean
     */
    public boolean alive(){
        if(myHealth == 0){
            return false;
        }
        else {
            return true;
        }
    }
}

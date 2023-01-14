/**
 * This class is responsible
 *
 * Trivia Maze Game
 * Aman Vahora, Arashpreet S. Pandher, Sophia Young
 * TCSS 360 Spring 2022
 */
package Model;

import java.io.Serializable;

public class Player implements Serializable {
    private int myHealth;

    /**
     * Constructor which contains the health for the player
     * @param theHealth The health value
     */
    public Player(int theHealth) {
        myHealth = theHealth;
    }

    /**
     * Getter for the health
     * @return the health
     */
    public int getMyHealth() {
        return myHealth;
    }

    /**
     * decreases the health by 1 if called
     */
    public void decreaseHealth(){
        myHealth -=1;
    }

    /**
     * Check if the player is alive
     * @return if alive
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

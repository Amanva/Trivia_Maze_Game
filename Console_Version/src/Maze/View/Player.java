package View;

import java.io.Serializable;

public class Player implements Serializable {
    private int myHealth;

    public Player(int theHealth) {
        myHealth = theHealth;
    }

    public int getMyHealth() {
        return myHealth;
    }

    public void setMyHealth(int myHealth) {
        this.myHealth = myHealth;
    }

    public void decreaseHealth(){
        myHealth -=1;
    }

    public boolean alive(){
        if(myHealth == 0){
            return false;
        }
        else {
            return true;
        }
    }
}

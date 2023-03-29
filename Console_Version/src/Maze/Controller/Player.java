package Controller;

import java.awt.*;
import java.util.ArrayList;

class Player {
    ArrayList<Item> items = new ArrayList<Item>();
    int health;
    Point currentPosition;

    public Player() {
        health = 10;
        currentPosition = new Point(1, 1);
    }
    public void move(int theDirection) {

    }

}
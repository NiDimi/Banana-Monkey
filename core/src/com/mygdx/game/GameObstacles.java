package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

/**
 * abstract class for all the objects in the game
 * @author Nick Dimitiou
 */
public abstract class GameObstacles {
    //list because there will be one objects and inside the list there will be the coordinates of the objects
    protected ArrayList<Integer> obstacleXs = new ArrayList<>();// the x of the object that will be in the screen
    protected ArrayList<Integer> obstacleYs = new ArrayList<>();// the y of the object that will be in the screen
    protected ArrayList<Rectangle> obstacleRectangles;// the rectangle of the object that will be in screen

    protected Texture obstacleTexture;// the image of the object
    protected int obstacleCount;// the count that will determine if it is time to create a new object

    protected int availableWidth;// the available width that the object can spawn
    protected int availableHeight;// the available height that the object can spawn

    protected Random random;// a random to be used for a random spawn

    /**
     * constructor to inisialize the variables
     */
    public GameObstacles() {
        random = new Random();
        obstacleRectangles = new ArrayList<>();
    }

    /**
     *
     * @return the list of the Xs
     */
    public ArrayList<Integer> getObstacleXs() {
        return obstacleXs;
    }

    /**
     *
     * @return the list of the Ys
     */
    public ArrayList<Integer> getObstacleYs() {
        return obstacleYs;
    }

    /**
     *
     * @return the image of the object
     */
    public Texture getObstacleTexture() {
        return obstacleTexture;
    }

    /**
     *
     * @return the list of the rectangles
     */
    public ArrayList<Rectangle> getObstacleRectangles() {
        return obstacleRectangles;
    }

    /**
     * abstract method aim to create and object
     */
    public abstract void makeObstacle();

    /**
     * abstract method aim to manage the animation of the object
     * @param position of the object that the animation of will be managed
     */
    public abstract void obstacleManagement(int position);

}

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class for the banana objects that the monkey has to collect
 * to earn points
 * @author Nick Dimitriou
 */
public class Banana extends GameObstacles{

    /**
     * Constructor to set up the image for the banana
     * and the available width and the available height
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public Banana(int screenWidth, int screenHeight) {
        obstacleTexture = new Texture("banana.png");// setting the texture to the banana image that is on the assets folder
        this.availableWidth = screenWidth - obstacleTexture.getWidth();// the available width is the screen width minus the width of the texture
        this.availableHeight = screenHeight - obstacleTexture.getHeight();// the available height is the screen height minus the height of the texture
    }

    /**
     * method to spawn bananas, needs to be called by render
     */
    @Override
    public void makeObstacle() {
        // when this method is called 100 times, since it will be call by render so it runs about 4 time a second
        // spawn one banana to a random position
        if(obstacleCount < 100){
            obstacleCount++;
        } else {
            obstacleCount = 0 ;// reset the counter
            double height = random.nextDouble() * availableHeight;// get a random height
            obstacleYs.add((int) height);// add this random height to the arraylist that holds all the Ys
            obstacleXs.add( availableWidth);// since the width does not mater spawn the banana to its available width, to the end of the screen
        }
    }

    /**
     * method to managing the banana, the animation and the rectangle, useful for the collision detection
     * @param position the position of the X that will change
     */
    @Override
    public void obstacleManagement(int position) {
        obstacleXs.set(position, obstacleXs.get(position) - 7);// subtract seven so it will appear as it mover to the left
        obstacleRectangles.add(new Rectangle(obstacleXs.get(position), obstacleYs.get(position),
                obstacleTexture.getWidth(), obstacleTexture.getWidth()));// create a rectangle so it can check for collision with the player
    }
}

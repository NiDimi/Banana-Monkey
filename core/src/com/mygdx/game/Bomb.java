package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class for the bombs objects that the user will have to avoid
 * @author Nick Dimitriou
 */
public class Bomb extends GameObstacles{

    /**
     * Constructor to set up the image for the bomb
     * and the available width and the available height
     * @param availableWidth the width of the screen
     * @param availableHeight the height of the screen
     */
    public Bomb(int availableWidth, int availableHeight) {
        obstacleTexture = new Texture("bomb.png");// setting the texture to the bomb image that is on the assets folder
        this.availableWidth = availableWidth - obstacleTexture.getWidth();// the available width is the screen width minus the width of the texture
        this.availableHeight = availableHeight - obstacleTexture.getHeight();// the available height is the screen height minus the height of the texture
    }

    /**
     * method to spawn bomb, need to be called by render
     */
    @Override
    public void makeObstacle() {
        // when this method is called 250 times, since it will be call by render so it runs about 4 time a second
        // spawn one bomb to a random position
        if(obstacleCount < 250){
            obstacleCount++;
        } else {
            obstacleCount = 0 ;// reset the counter
            double height = random.nextDouble() * availableHeight;// get a random height
            obstacleYs.add((int) height);// add this random height to the arraylist that holds all the Ys
            obstacleXs.add(availableWidth);// since the width does not mater spawn the banana to its available width, to the end of the screen
        }
    }

    /**
     * method to managing the bomb, the animation and the rectangle, useful for the collision detection
     * @param position the position of the X that will change
     */
    @Override
    public void obstacleManagement(int position) {
        obstacleXs.set(position, obstacleXs.get(position) - 10);// subtract ten so it will appear as it mover to the left
        obstacleRectangles.add(new Rectangle(obstacleXs.get(position), obstacleYs.get(position),
                obstacleTexture.getWidth(), obstacleTexture.getWidth()));// create a rectangle so it can check for collision with the player
    }
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class for the up explosion that the exploding bomb produces
 * @author Nick Dimitriou
 */
public class UpExplosion extends GameObstacles {
    private int startingWidth;// the width that will start
    private int startingHeight;// the height that will star
    private boolean created;// if it created already or no

    /**
     * The constructor to set the image and the width and height the explosion will begin, the time that the explodingBomb disappear
     * @param startingWidth the width that the explosion will start
     * @param startingHeight the height that the explosion will start
     */
    public UpExplosion(int startingWidth, int startingHeight ) {
        obstacleTexture = new Texture("explode-up.png");// setting the texture to the explode-up image that is on the assets folder
        this.startingWidth = startingWidth;// set up the start width
        this.startingHeight = startingHeight;// set up the start height
        created = false;// is not yet created
    }

    /**
     * method to manage hte creation of the explosion
     */
    @Override
    public void makeObstacle() {
        if(!created) {// if it is created
            obstacleXs.add(startingWidth);// add it to the X
            obstacleYs.add(startingHeight);// add it to the Y
            created = true;// is created true
        }
    }

    /**
     * method to manage the explosions
     * @param position of the object that the animation of will be managed
     */
    @Override
    public void obstacleManagement(int position) {
        obstacleYs.set(position, obstacleYs.get(position) + 15);// add 15 so it will appears as it moves up
        obstacleRectangles.add(new Rectangle(obstacleXs.get(position), obstacleYs.get(position),
                obstacleTexture.getWidth(), obstacleTexture.getWidth()));// create a rectangle so it can check for collision with the player
    }
}

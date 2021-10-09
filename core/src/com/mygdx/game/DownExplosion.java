package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class for the down explosion that the exploding bomb produces it extends the upExplosion class
 * as there interactions are similar
 * @author Nick Dimitriou
 */
public class DownExplosion extends UpExplosion {

    /**
     * The constructor to set the image and the width and height the explosion will begin, the time that the explodingBomb disappear
     * @param startingWidth the width that the explosion will start
     * @param startingHeight the height that the explosion will start
     */
    public DownExplosion(int startingWidth, int startingHeight) {
        super(startingWidth, startingHeight);// calling the super to set the two variables
        obstacleTexture = new Texture("explode-down.png");// setting the texture to the explode-down image that is on the assets folder
    }

    /**
     * Overiding the obstacle management as it moves to a different direction
     * @param position the position of the Y that will change
     */
    @Override
    public void obstacleManagement(int position) {
        obstacleYs.set(position, obstacleYs.get(position) - 15);// subtract 15 so it will appear as it moves down
        obstacleRectangles.add(new Rectangle(obstacleXs.get(position), obstacleYs.get(position),
                obstacleTexture.getWidth(), obstacleTexture.getWidth()));// create a rectangle so it can check for collision with the player
    }
}

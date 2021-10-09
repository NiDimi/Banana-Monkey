package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 * Class for the Exploding Bomb that the monkey has to avoid, and randomly explodes
 * and creates to explosions going up and down
 * @author Nick Dimitriou
 */

public class ExplodingBomb extends GameObstacles {

    private Music explosionSound;// the sound that will play when the bomb explodes
    private ArrayList<UpExplosion> upExplosions;// a list to hold all the up explosions that were produced by the bombs
    private ArrayList<DownExplosion> downExplosions;// a list to hold all the down explosions that were produced by the bombs

    /**
     * Constructor to set up the image
     * the available width and height
     * and the sound
     * @param availableWidth the width of the screen
     * @param availableHeight the height of the screen
     */
    public ExplodingBomb(int availableWidth, int availableHeight) {
        obstacleTexture = new Texture("bomb2.png");// setting the texture to the bomb image that is on the assets folder
        this.availableWidth = availableWidth - obstacleTexture.getWidth();// the available width is the screen width minus the width of the texture
        this.availableHeight = availableHeight - obstacleTexture.getHeight();// the available height is the screen height minus the height of the texture
        upExplosions = new ArrayList<>();// initializing the array
        downExplosions = new ArrayList<>();// initializing the array
        explosionSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/explosions.mp3"));// setting up the sound
        explosionSound.setVolume(0.2f);// setting the volume of the sound
    }

    /**
     * method to spawn bombs in the center of the height, needs to be called by render
     */
    @Override
    public void makeObstacle() {
        // when this method is called 300 times, since it will be call by render so it runs about 4 time a second
        // spawn one bomb to a random position
        if(obstacleCount<300){
            obstacleCount++;
        } else {
            obstacleCount = 0;// reset the counter
            obstacleYs.add(availableHeight/2);// the bomb needs to be in the center of the height
            obstacleXs.add(availableWidth);// since the width does not mater spawn the bomb to its available width, to the end of the screen
        }
    }

    /**
     * method to managing the bomb, the animation and the rectangle, useful for the collision detection
     * @param position the position of the X that will change
     */
    @Override
    public void obstacleManagement(int position) {
        obstacleXs.set(position, obstacleXs.get(position) - 5);// subtract 5 so it will appear as it mover to the left
        obstacleRectangles.add(new Rectangle(obstacleXs.get(position),
                obstacleYs.get(position), obstacleTexture.getWidth(), obstacleTexture.getWidth()));// create a rectangle so it can check for collision with the player
        explosion();// call the explosion method that will decide if the bomb will explode or no
    }

    /**
     * method to randomly decide if the bomb will explode or not
     */
    private void explosion(){
        for (int i = 0; i < obstacleXs.size(); i++){// loop through all the bombs in the screen, as the size of y and x are the same it doest matter which one to choose
            int randomNumber = random.nextInt(300);// pick a random number between the 0 and 299
            if(randomNumber == 0 || obstacleXs.get(i) <= availableWidth/5){// if the random number is 0 explode or if it reached a point in the screen so it will explode for sure
                explosionSound.play();// play the exploding sound
                upExplosions.add(new UpExplosion(obstacleXs.get(i), obstacleYs.get(i)));// create an up explosion
                downExplosions.add((new DownExplosion(obstacleXs.get(i), obstacleYs.get(i))));// create an down explosion
                //remove the bomb as it has explode, is safe to remove cause only one bomb will always be in the screen
                getObstacleRectangles().remove(i);
                getObstacleXs().remove(i);
                getObstacleYs().remove(i);
            }
        }
    }

    /**
     *
     * @return all the up explosions
     */
    public ArrayList<UpExplosion> getUpExplosions() {
        return upExplosions;
    }

    /**
     *
     * @return all the down explosions
     */
    public ArrayList<DownExplosion> getDownExplosions() {
        return downExplosions;
    }
}

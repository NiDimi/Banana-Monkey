package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * class for the second level of the game
 * @author Nick Dimitriou
 */
public class Run2 extends Run1 {

    private int pause;// pause for the time that will take to lose again a life
    private Music hitGroundSound;// the sound for when the player hits the ground

    /**
     * constructor to initialize the level
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param batch the batch that all will be drawed on
     */
    public Run2(int screenWidth, int screenHeight, SpriteBatch batch) {
        super(screenWidth, screenHeight, batch);
        pause = 50;// start with 50 because to lose a life first
        hitGroundSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/hitGround.mp3"));
    }

    /**
     * method for when the player is playing
     * overiding the level 1 so to add more
     */
    @Override
    public void playing() {
        super.playing();// all the same as the level one

        if (pause == 50) {// if the pause is 50
            if (getMonkey().getPlayerY() < 50 || getMonkey().getPlayerY() > screenHeight-325) {// and the monkey hit the ground or ceiling lose one life
                getMonkey().loseOneLife();// lose one life
                hitGroundSound.play();// play the sound that hit the gorund
                pause = 0;
            }
        } else {// else add one
            pause ++;
        }
    }
}

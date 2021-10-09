package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * class for the third level of the game that builds on the first
 */
public class Level3 extends Level1 {

    /**
     * overriding the create method to change the backgorund and call the run3 that is the arena fro the second level
     */
    @Override
    public void create() {
        super.create();
        background = new Texture("background/background3.jpg");
        run = new Run3(width, height, batch);
        minScore = HighScore.getMin("level3");
        level = "level3";
    }
}

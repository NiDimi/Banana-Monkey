package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * class for the second level of the game that inherits and build on the first
 */
public class Level2 extends Level1 {
    /**
     * overiding the create method to change the backgorund and call the run2 that is the arena fro the second level
     */
    @Override
    public void create() {
        super.create();
        background = new Texture("background/background2.jpg");
        run = new Run2(width, height, batch);
        minScore = HighScore.getMin("level2");
        level = "level2";
    }
}

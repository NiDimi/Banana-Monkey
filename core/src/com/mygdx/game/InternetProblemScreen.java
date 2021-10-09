package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * screen for when their is no interent connection
 * @author Nick Dimitriou
 */
public class InternetProblemScreen extends ApplicationAdapter {
    private SpriteBatch batch;// the batch that the screen will appear on
    private Texture backGround;// the background of teh screen

    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();// set up the new banch
        backGround = new Texture("internet.png");// setting the background picture
    }

    @Override
    public void render() {
        super.render();
        batch.begin();
        batch.draw(backGround, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());// just displaying the background
        batch.end();
    }
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * class for the main menu displayed when the game begins
 */
public class MainMenu extends ApplicationAdapter {

    private SpriteBatch batch;// the batch for the menu
    // all the levels of the game as static because they need to be accessed from other classes
    static  Level1 level1;
    static Level2 level2;
    static Level3 level3;
    static HighScoreScreen highScoreScreen;
    //all the images that will be displayed in the menu
    private Texture background;
    private Texture level1Image;
    private Texture level3Image;
    private Texture level2Image;
    private Texture highScoreImage;
    //variables to know if a level is being played, static because it need to be accessed from other classes
    static Boolean inLevel1;
    static Boolean inLevel2;
    static Boolean inLevel3;
    static Boolean inHighScore;

    static  Music clickSound;// the sound that will play when the user click something in the menu


    /**
     * method to run when the class is first created
     * that initializes all the variables
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        // setting up the images for the menu
        background = new Texture("jungle.jpg");
        level1Image = new Texture("levels/level1.png");
        level2Image = new Texture("levels/level2.png");
        level3Image = new Texture("levels/level3.png");
        highScoreImage = new Texture("highscore.png");
        //setting up the variables for the first level
        level1 = new Level1();
        inLevel1 = false;
        //setting up the variables for the second level
        level2 = new Level2();
        inLevel2 = false;
        //setting up the variables for the third level
        level3 = new Level3();
        inLevel3 = false;
        //setting up the variables for the highscore screen
        highScoreScreen = new HighScoreScreen();
        inHighScore = false;

        clickSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/click.wav"));// setting up the click sound
    }

    /**
     * method that will repeat all the time that manages the animation on the screen
     */
    @Override
    public void render() {
        super.render();
        batch.begin();// starting the main sprite
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());// first draw the background
        //put all the levels in the correct positions
        batch.draw(level1Image, Gdx.graphics.getWidth() / 2 - level1Image.getWidth() / 2,
                (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.25));

        batch.draw(level2Image, Gdx.graphics.getWidth() / 2 - level1Image.getWidth() / 2,
                (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.5));

        batch.draw(level3Image, Gdx.graphics.getWidth() / 2 - level1Image.getWidth() / 2,
                (float) (Gdx.graphics.getHeight() - Gdx.graphics.getHeight() * 0.75));

        batch.draw(highScoreImage, 50, Gdx.graphics.getHeight()-100-highScoreImage.getHeight(), 250, 250);

        if (Gdx.input.justTouched()){// if the user taps the screen see if it cliked one of the levels
            int x = Gdx.input.getX();// get the x of the click
            int y = Gdx.graphics.getHeight()- Gdx.input.getY();// get the y of the click
            // if the click is in the level one position of image leaving a space of 400 in the width and 150 on the height
            if((x > (Gdx.graphics.getWidth()/2 - level1Image.getWidth()/2))
                    && (x < (Gdx.graphics.getWidth()/2 - level1Image.getWidth()/2)+400)
                    && (y > (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*0.25))
                    && (y < (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*0.25)+150)
                    && isPlaying()) {
                // that means that the user clicked the first level
                clickSound.play();// play the click sound
                level1.create();// create the first level
                inLevel1 = true;// set the variable to true because the user plays the first level
                // if the click is in the level two position of image leaving a space of 400 in the width and 150 on the height
            } else if ((x > (Gdx.graphics.getWidth()/2 - level1Image.getWidth()/2))
                && (x < (Gdx.graphics.getWidth()/2 - level1Image.getWidth()/2)+400)
                && (y > (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*0.25)-300)
                && (y < (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*0.25)-150) && isPlaying()){
                // that means that the user clicked the second level
                clickSound.play();// play the click sound
                level2.create();// create the second level
                inLevel2 = true;// set the variable to true because the user plays the second level
                // if the click is in the level three position of image leaving a space of 400 in the width and 150 on the height
            } else if ((x > (Gdx.graphics.getWidth()/2 - level1Image.getWidth()/2))
                    && (x < (Gdx.graphics.getWidth()/2 - level1Image.getWidth()/2)+400)
                    && (y > (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*0.25)-550)
                    && (y < (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()*0.25)-400) && isPlaying()){
                // that means that the user clicked the third level
                clickSound.play();// play the click sound
                level3.create();// create the third level
                inLevel3 = true;// set the variable to true because the user plays the third level
            } else if ((x > 100)
                    && (x < 400)
                    && (y > Gdx.graphics.getHeight()-100-highScoreImage.getHeight()-100)
                    && (y < Gdx.graphics.getHeight()-100-highScoreImage.getHeight() +200)
                    && isPlaying()){// that means the user clicked the highscore icon
                clickSound.play();// play the sound
                highScoreScreen.create();// create the highscore page
                inHighScore = true;// set the variable to true to start rendering that page
            }
        }
        //if the player click teh first level start the render of the first level
        if (inLevel1){
            level1.render();
        }
        //if the player click teh second level start the render of the second level
        if(inLevel2){
            level2.render();
        }
        //if the player click teh third level start the render of the third level
        if (inLevel3){
            level3.render();
        }
        //if the player click the high score icon start the render of the high score icon
        if(inHighScore){
            highScoreScreen.render();
        }

        batch.end();
    }

    /**
     *
     * @return if one of the levels are being played
     */
    private boolean isPlaying(){
        return !inLevel1 && !inLevel2 && !inLevel3 && !inHighScore;
    }

    /**
     * static method to return to the main menu and dispose the level that was being played
     */
    public static void returnToMenu(){
        //depeniding on which level was running dispose that
        if(inLevel1){
            level1.dispose();
        } else if(inLevel2){
            level2.dispose();
        } else if(inLevel3) {
            level3.dispose();
        } else if (inHighScore){
            highScoreScreen.dispose();
        }
        //set all levvels to false as it returns to the menu
        inLevel1 = false;
        inLevel2 = false;
        inLevel3 = false;
        inHighScore = false;
    }


}

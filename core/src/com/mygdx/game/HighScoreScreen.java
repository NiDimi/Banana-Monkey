package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.parse.ParseObject;
import java.util.List;

/**
 * class for the high score screen
 * @author Nick Dimitriou
 */
public class HighScoreScreen extends ApplicationAdapter {
    private SpriteBatch batch;// the batch that everything will be draw in
    private Texture background;// in the background image
    private int width;// the width of the screen
    private int height;// the height of the screen

    private List<ParseObject> users1;// all the users that will be depected for level 1
    private List<ParseObject> users2;// all the users that will be depected for level 2
    private List<ParseObject> users3;// all the users that will be depected for level 3

    private Texture backButton;// button for when the user want to go back to levels

    private BitmapFont fontForScores;// the font that will be used for the scores
    private BitmapFont fontForLevels;// the font that will be used for the levels

    /**
     * method to run when the class is created
     * to initialize all the variables
     */
    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();// create a new batch

        background = new Texture("jungle2.jpg");

        //getting the sizes of the screen
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        //loading the users
        users1 = HighScore.LoadUsers("level1");
        users2 = HighScore.LoadUsers("level2");
        users3 = HighScore.LoadUsers("level3");


        fontForScores = new BitmapFont(Gdx.files.internal("font/highscore2.fnt"));// font for the scores
        fontForScores.getData().setScale(2);

        fontForLevels = new BitmapFont(Gdx.files.internal("font/highscore.fnt"));// the font for the levels
        fontForLevels.getData().setScale(3);

        backButton = new Texture("back.png");// the back button

    }

    /**
     * method that will repeat all the time that manage the animation on the screen
     */
    @Override
    public void render() {
        batch.begin();// startiing the main spirte

        batch.draw(background,0,0,width,height);// drawing the background

        //setting the levels on the top
        fontForLevels.draw(batch, "Level 1", (float) (width*0.2)-200, height -50);
        fontForLevels.draw(batch, "Level 2", (float) (width * 0.5)-200, height-50);
        fontForLevels.draw(batch, "Level 3", (float) (width * 0.8)-200, height-50);



        //for the level one top users
        drawScores(users1, 0.2);

        //for the level two top users
        drawScores(users2,0.5);

        //for the level three top users
        drawScores(users3, 0.8);


        batch.draw(backButton,50,50,250,250 );// placing the back button
        backToLevelSelection();// checking if the user click the button


        batch.end();
    }

    private void drawScores(List<ParseObject> user, double widthPlace){
        for (int i = 0; i < user.size(); i++){
            String text = (i+1) + ")" + user.get(i).getString("Username") + " " + user.get(i).getInt("Score");// setting teh value
            fontForScores.draw(batch, text ,(float) (width * widthPlace)-200,height- (i+1) * 100 - 125);// placing it in the screen
        }

    }

    /**
     * method to handle what happens when the user click the back button
     */
    private void backToLevelSelection(){
        if (Gdx.input.justTouched()){// if the user press the screen
            int x = Gdx.input.getX();// get the x of the click
            int y = Gdx.graphics.getHeight()- Gdx.input.getY();// get the y of the click
            if ((x > 100)
                    && (x < 400)
                    && (y > 100)
                    && (y < 300)){// check the coordinates of the click
                MainMenu.clickSound.play();// play the click sound
                // sleep for one second so the sound can be played
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MainMenu.returnToMenu();// return to main menu
            }
        }
    }
}

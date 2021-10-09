package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

/**
 * class to manage the arena for the level one
 * @author Nick Dimitriou
 */
public class Run1 {
    protected Monkey monkey;// the monkey that the player will control
    private Banana banana;// the bananas that the player has to collect
    private Bomb bomb;// the bomb taht the player has to avoid

    protected int screenWidth;// the widht of the screen
    protected int screenHeight;// the height of the screen

    protected SpriteBatch batch;// the batch that all the arena will be one
    // the music for the game
    protected Music bombHit;// when a bomb hits the players
    private Music bananaSound;// when the player collects a banana
    private Music jumpingSound;// when the player jumps
    private Music gameOverSound;// when the player loses
    private Music marioSong;// the song that will played when the game is on

    /**
     * constructor for the level 1
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param batch the batch that all will be drawed on
     */
    public Run1(int screenWidth, int screenHeight, SpriteBatch batch) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.batch = batch;

        monkey = new Monkey(0.5, 0, screenHeight/2 , screenHeight, screenWidth, 0,
                screenWidth/5,3);// create a new monkey with 3 lives
        banana = new Banana(screenWidth, screenHeight);// create bananas
        bomb = new Bomb(screenWidth, screenHeight);// create bombs
        //initialize all the sounds
        bombHit = Gdx.audio.newMusic(Gdx.files.internal("sounds/hit.mp3"));
        bombHit.setVolume(0.5f);
        bananaSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/banana.wav"));
        bananaSound.setVolume(1.0f);
        jumpingSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/jump.wav"));
        jumpingSound.setVolume(1.0f);
        gameOverSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/gameOver.wav"));
        gameOverSound.setVolume(1.0f);
        marioSong = Gdx.audio.newMusic(Gdx.files.internal("sounds/marioSong.mp3"));
        marioSong.setVolume(0.1f);
    }

    /**
     * method to check for colissions with bananas
     * @return if there was a collision
     */
    private boolean checkForCollisionWithBananas(){
        for (int i = 0; i < banana.getObstacleRectangles().size(); i ++){// for all the banaas rectangles
            if (Intersector.overlaps(monkey.getPlayerRectangle(), banana.getObstacleRectangles().get(i))){// check if it intersects with the monkey rectangle
               // if it intersects remove the banana and return true
                banana.getObstacleRectangles().remove(i);
                banana.getObstacleXs().remove(i);
                banana.getObstacleYs().remove(i);
                return true;
            }
        }
        return false;// otherwise return false
    }

    /**
     * method to check for colissions with bombs
     * @return if there was a collision
     */
    private boolean checkForCollisionWithBomb(){
        for (int i = 0; i < bomb.getObstacleRectangles().size(); i ++){// for all the bombs rectangles
            if (Intersector.overlaps(monkey.getPlayerRectangle(), bomb.getObstacleRectangles().get(i))){// check if it intersects with the monkey
                // if it intersects remove the banana and return true
                bomb.getObstacleRectangles().remove(i);
                bomb.getObstacleXs().remove(i);
                bomb.getObstacleYs().remove(i);
                return true;
            }
        }
        return false;// otherwise return false
    }

    /**
     * make all the words for the objects in the screen
     * @param obstacle the object that the work will be made
     */
    protected void obstacleWork(GameObstacles obstacle){
        obstacle.getObstacleRectangles().clear();// first clear all the rectangles of that objects
        obstacle.makeObstacle();// and create an object
        for (int i = 0; i < obstacle.getObstacleXs().size(); i++){// for all the objects that should be in the screen
            //draw them
            batch.draw(obstacle.getObstacleTexture(), obstacle.getObstacleXs().get(i), obstacle.getObstacleYs().get(i));
            obstacle.obstacleManagement(i);// and manage their animation and rectangles
        }
    }

    /**
     * the method that manages everything when the player is playing
     */
    public void playing(){
        marioSong.play();// start the background song
        if (Gdx.input.justTouched()){// if the screen touches the screen
            if (Gdx.input.getY() <  screenHeight /4) {// if it is in the top of the screen
                monkey.setVelocityY(-15);// jump up
                jumpingSound.play();// and play the jump sound
            }
        }

        if (Gdx.input.isTouched()){
            if (Gdx.input.getX() > screenWidth - screenWidth/4){// if it touched on the right of the screen
                monkey.moveRight();// move right
            } else if (Gdx.input.getX() < screenWidth/4) {// if it touched on the left of the screen
                monkey.moveLeft();// move left
            }
        }
        // do the work for the bomb and banana
        obstacleWork(bomb);
        obstacleWork(banana);

        monkey.playerInGame();// manage the necesary movements for the monkey

        if (checkForCollisionWithBananas()){//check if it collide with a banana
            bananaSound.play();// play the banana sound
            Level1.score++;// and add one to score
        }

        if (checkForCollisionWithBomb()){// check if it collide with a bomb
            bombHit.play();// play the bomb sound
            monkey.loseOneLife();// lose one life

        }

        if(monkey.getLivesRemaining() == 0){// check if the monkey still has live
            Level1.gameState = 2;// if it dont set the state to game over state
            marioSong.stop();// stop the background song
            gameOverSound.play();// play the game over sound
        }
    }

    /**
     * method to manage for when the player loses
     */
    public void gameOver(){
        monkey.deathAnimation();// do the death animation

        if (Gdx.input.justTouched()){// check if the player touched the screen
            // get where the player touched the screen
            int x = Gdx.input.getX();
            int y = screenHeight- Gdx.input.getY();

            if((x > (screenWidth/2 - 250))
                    && (x < (Gdx.graphics.getWidth()/2 + 250)
                    && (y > (screenHeight-500))
                    && (y < (screenHeight-400) ))){// the coordinate of the restart button
                MainMenu.clickSound.play();// play the click sound
                // sleep for one second so the ssound can be played
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Level1.gameState = 1;// set the state to 1 meaning playing
                makeNewObstacles();// and make all the new obstacles
                Level1.score = 0;// reset the score
                Level1.added = false;
            } else if((x > (screenWidth/2 - 250))
                    && (x < (Gdx.graphics.getWidth()/2 + 250)
                    && (y > (screenHeight-800))
                    && (y < (screenHeight-700) ))){// the coordinates of the go back to main menu button
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

    /**
     *
     * @return the monkey in the screenn
     */
    public Monkey getMonkey() {
        return monkey;
    }

    /**
     * make all the new obstacles in the screen
     */
    protected void makeNewObstacles(){
        monkey = new Monkey(0.5, 0, screenHeight / 2, screenHeight,
                screenWidth, 0, screenWidth / 5, 3);// create a new monkey
        banana = new Banana(screenWidth, screenHeight);
        bomb = new Bomb(screenWidth, screenHeight);
    }
}

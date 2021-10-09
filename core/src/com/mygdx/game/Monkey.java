package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class to manage the monkey
 * @author Nick Dimitriou
 */
public class Monkey {
    protected Texture [] playerMoving;// a texture array to look like movement
    protected int playerState;// to know in which state the model is
    protected int pause = 0;// to form a little better the animation
    protected double gravity;// the speed that the player fall will multiply
    protected double velocityY;// the speed that the man will fall
    protected double velocityX;// the speed of moving horizontally
    protected int playerY; // the players height
    protected int playerX;// the player width
    protected int screenHeight;// the screens height
    protected int screenWidth;// the screens width
    private Rectangle playerRectangle;// a player rectangle to help with collisions
    private int pause2 = -1;// to form a little better the animation
    protected int livesRemaining;// how many lives the players has

    /**
     * Constructor for the monkey class to set up the variables
     * @param gravity the speed that the fall speed will multiply
     * @param velocityY the speed that the man will fall
     * @param playerY the players height that the monkey will begin
     * @param screenHeight the screens height
     * @param screenWidth the screens width
     * @param velocityX the speed of moving horizontally
     * @param playerX the players width that will begin
     * @param livesRemaining how many lifes the monkey will have
     */
    public Monkey(double gravity, double velocityY, int playerY, int screenHeight, int screenWidth, double velocityX, int playerX, int livesRemaining) {
        // setting the player with difference state for the animation
        playerMoving = new Texture[20];
        //running images
        playerMoving[0] = new Texture("move/run0.png");
        playerMoving[1] = new Texture("move/run1.png");
        playerMoving[2] = new Texture("move/run2.png");
        playerMoving[3] = new Texture("move/run3.png");
        playerMoving[4] = new Texture("move/run4.png");
        playerMoving[5] = new Texture("move/run5.png");
        playerMoving[6] = new Texture("move/run6.png");
        playerMoving[7] = new Texture("move/run7.png");

        //death images
        playerMoving[8] = new Texture("die/die1.png");
        playerMoving[9] = new Texture("die/die2.png");
        playerMoving[10] = new Texture("die/die3.png");
        playerMoving[11] = new Texture("die/die4.png");
        playerMoving[12] = new Texture("die/die5.png");
        playerMoving[13] = new Texture("die/die6.png");
        playerMoving[14] = new Texture("die/die7.png");
        playerMoving[15] = new Texture("die/die8.png");
        playerMoving[16] = new Texture("die/die9.png");
        playerMoving[17] = new Texture("die/die10.png");
        playerMoving[18] = new Texture("die/die11.png");
        playerMoving[19] = new Texture("die/die12.png");

        playerState = 0;// starting with the first position of the player
        this.gravity = gravity;// setting the gravity passed
        this.velocityY = velocityY;// setting the velocity passed regarding the Y axes
        this.velocityX = velocityX;// setting the velocity passed regarding the X axes
        this.playerY = playerY;// setting the player Y based on the screen that is used
        this.playerX = playerX ;// setting the starting position of the monkey
        this.screenHeight = screenHeight;// setting the screen height
        this.screenWidth = screenWidth;// setting the screen width
        this.livesRemaining = livesRemaining;// setting teh lives remaining
    }

    /**
     * set the velocity
     * @param velocityY the new velocity
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * subtract the fall velocity from the monkeys Y
     */
    protected void setPlayerY() {
        this.playerY -= velocityY;
    }

    /**
     * move left of the screen
     */
   public void moveLeft(){
        if(playerX - 15 > 0) {// firest check if there is still room in the screen to go left
            this.playerX -= 15;// and if there is space go left
        }
   }

    /**
     * move right of the screen
     */
    public void moveRight(){
        if (playerX + playerMoving[playerState].getWidth() < screenWidth) {//first check if there is still room in the screen to go right
            this.playerX += 15;// and if there is space go right
        }
    }

    /**
     * method to manage the falling
     */
    protected void falling(){
        this.velocityY += gravity;// add the gravity to the fall velocity
    }

    /**
     *
     * @return the rectangle of the monkey
     */
    public Rectangle getPlayerRectangle() {
        return playerRectangle;
    }

    /**
     *
     * @return the current image of the monkey
     */
    public Texture getPlayerMoving(){
        return playerMoving[playerState];
    }

    /**
     *
     * @return the monkeys height
     */
    public int getPlayerY() {
        return playerY;
    }

    /**
     *
     * @return the monkeys width
     */
    public int getPlayerX() {
        return playerX;
    }

    /**
     *
     * @return how many lives the monkey still has
     */
    public int getLivesRemaining() {
        return livesRemaining;
    }

    /**
     * subtract one from the lives remaining
     */
    public void loseOneLife(){
        livesRemaining--;
    }

    /**
     * check if the player reached the bootom of the screen
     */
    protected void checkForEndOfScreen(){
        if (playerY <= 0){// the player y is lower or equal to the bottom it means it will go under
            playerY = 0;// so set the playerY to 0
        }
    }

    /**
     * check if the player reached the top of the screen
     */
    private void checkForTopOfScreen(){
        // if the players height plus the height of the image is equal or greater that the screen height it reached the top
        if (playerY + playerMoving[playerState].getHeight() >= screenHeight){
            playerY = screenHeight - playerMoving[playerState].getHeight();// set the player height to the top of the screen
            setVelocityY(0);// and set the speed to zero it will start falling
        }
    }

    /**
     * manage the animation of the monkey
     */
    private void playerAnimation(){
        if (pause != 8){ // waiting until pause reaches 8 to chnage the movement so it would be a little slower
            pause++;
        } else {
            pause = 0;// setting again the pause to 0 for the next 8 loops in render
            if (playerState < 7){ // go to the next
                playerState++;
            } else { // but if it is 3 the next one is 0 so go to 0
                playerState = 0;
            }
        }
    }

    /**
     * manage all the necesary method that has to run when the monkey is in play
     */
    public void playerInGame(){
        playerAnimation();// do the animation
        falling();// manage the falling
        setPlayerY();// set the new play height
        checkForEndOfScreen();// check if it reached the bottom
        checkForTopOfScreen();// check if it reached the top
        // create a new rectangle to the place that the monkey is
        playerRectangle = new Rectangle(playerX, playerY,
                playerMoving[playerState].getWidth(), playerMoving[playerState].getHeight());
    }

    /**
     * manage the aniamtion for the monkey died
     */
    public void deathAnimation(){
        falling();// make the monkey fall so it reached the end
        checkForEndOfScreen();// and dont let it go under the screen
        setPlayerY();// adjust the height
        setVelocityY(10);// the speed that the monkey will fall
        if(playerState < 19) {// start the death animation that lies between 8 and 19 so if the playerstate is smaller that 19 so it has not end
            if (pause2 == -1) {// check if it starts the animation now
                playerState = 8;// and set the state to the first death image
            }
            if (pause2 != 2) { // waiting until pause reaches 2 to chnage the movement so it would be a little slower
                pause2++;
            } else {
                pause2 = 0;// setting again the pause to 0 for the next 8 loops in render
                playerState++;
            }
        }
    }
}

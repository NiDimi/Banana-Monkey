package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * class for the first level of the game that manages all the drawing in the screen
 * @author Nick Dimitriou
 */
public class Level1 extends ApplicationAdapter {
	protected SpriteBatch batch; // the basic batch before all the other sprites
	protected Texture background; // a texture for the background of the game
	private Texture heart;// a texture for the hearts
	private Texture gameOver;// a texture for the game over
	protected int width; // the width of the screen
	protected int height; // the height of the screen

	protected Run1 run;// the arena that this class will depict

	private BitmapFont font;// a font to display messages
	static int score;// the number of bananas the monkey will gather
	protected int minScore;
	protected String level;
	private Thread thread;

	/*gameState = 0 waiting to start
	  gameState = 1 playing
	  gameState = 2 lost
	 */
	static int gameState;
	static boolean added;//boolean to check if the user was added in the higherscore

	/**
	 * method to run when the class is first created
	 * that initializes all the variables
	 */
	@Override
	public void create () {
		// create a new sprite batch for all the other bratches to be in
		batch = new SpriteBatch();

		background = new Texture("background/background1.png");// pass the picture to the background texture
		heart = new Texture("heart.png");// pass the picture of the heart
		gameOver = new Texture("game-over.png");// pass the picture of the game over

		width = Gdx.graphics.getWidth(); // getting the width of the screen from the gdx library
		height = Gdx.graphics.getHeight();// getting the height of the screen from the gdx library

		run = new Run1(width, height, batch);// is the first level so create the first run

		font = new BitmapFont(Gdx.files.internal("font/game.fnt"), false);// optimize the font
		font.getData().setScale(3);// setting the size of the font

		score = 0;// the score will start at 0
		gameState = 0;// the 0 state as it wait to start
		level = "level1";
		minScore = HighScore.getMin("level1");
		added = false;
		setThread();
	}

	/**
	 * method that will repeat all the time that manages the animation on the screen
	 */
	@Override
	public void render () {
		thread.run();
	}

	/**
	 * method to dispose and free the memory when this level ends
	 */
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		background.dispose();
		heart.dispose();
		gameOver.dispose();
	}

	/**
	 * method that will be called when the game is awaiting to start
	 */
	private void starting(){
		// if the screen is touched set the game state to one meaning the game began
		if (Gdx.input.justTouched()){
			gameState = 1;
		}
	}

	/**
	 * method to set up the display of the hearts
	 */
	private void heartAnimation(){
		for (int i = 0; i < run.getMonkey().getLivesRemaining(); i ++){// run the numbre of hearts the player still has
			batch.draw(heart,width-150 - i *50, height-150);// and depict them in the top right
		}
	}

	/**
	 * method to add a user to the high score board for if he scored a top 5 score
	 */
	private void addUserToHighScoreBord() {
		if (!added) {
			added = true;// change the added to true so to not added again
			Gdx.input.getTextInput(new Input.TextInputListener() {
				@Override
				public void input(String text) {
					if (text.toCharArray().length > 7){
						text = text.substring(0,8);
						text+="..";
					}
					HighScore.saveScore(text, score, level);
				}

				@Override
				public void canceled() {
					HighScore.saveScore("?", score, level);
				}
			}, "You made the top 5!!!! \nInsert your name", " ", "Mysterious");
		}
	}

	/**
	 * method that allows to change the background
	 * @param background the location of the new background
	 */
	public void changeBackground(String background){
		this.background = new Texture(background);
	}

	private void setThread(){
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				batch.begin();// starting the main sprite
				batch.draw(background,0,0, width, height);// first draw the background

				if(gameState == 0){
					starting();// the method to begin the game
				} else if (gameState == 1){
					run.playing();// the method that runs when the player is playing
				} else if (gameState == 2){
					run.gameOver();// display the necesary things for the game over screen
					// and messages
					font.draw(batch, "Try again", width/2-250, height -400);
					font.draw(batch, "Choose level", width/2-350, height -700);

					if (score > minScore) {// see if the user manage to get in the top 5
						addUserToHighScoreBord();
					}
					batch.draw(gameOver,width/2- gameOver.getWidth()/2+75, height-300);// set the game over image
				}

				batch.draw(run.getMonkey()
						.getPlayerMoving(), run.getMonkey().getPlayerX(), run.getMonkey().getPlayerY());// draw the monkey that the player controls
				font.draw(batch, String.valueOf(score), 50, height-50);// display the score to the top left
				heartAnimation();// draw the hearts
				batch.end();// end this batch so it clears
			}
		});
	}

}

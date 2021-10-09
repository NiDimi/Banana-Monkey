package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

/**
 * class to manage the arena for the level three
 */
public class Run3 extends Run1 {
    private ExplodingBomb explodingBomb;// add the exploding bomb

    /**
     * constructor for the level 3
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param batch the batch that all will be drawed on
     */
    public Run3(int screenWidth, int screenHeight, SpriteBatch batch) {
        super(screenWidth, screenHeight, batch);
        explodingBomb = new ExplodingBomb(screenWidth, screenHeight);// create the explosing bombs
    }

    /**
     * the method that manages everything when the player is playing
     */
    @Override
    public void playing() {
        obstacleWork(explodingBomb);// do the work for the exploding bomb

        for(int i = 0; i < explodingBomb.getUpExplosions().size(); i++){// for all the explosions of the obstacle work
            obstacleWork(explodingBomb.getUpExplosions().get(i));// do the work for the up explosions
            obstacleWork(explodingBomb.getDownExplosions().get(i));// do the work for teh dwon explosions
        }

        if (checkForCollisionWithExplodingBomb()){// check for colision with the exploding bomb
            monkey.loseOneLife();// lose one life
            bombHit.play();// play the bomb sound
        }

        if(checkForCollisionWithUpExplosion()){// check for collision with the up explosions
            monkey.loseOneLife();// lose one life
            bombHit.play();// play the bomb sound
        }

        if(checkForCollisionWithDownExplosion()){// check for collision with the up explosions
            monkey.loseOneLife();// lose one life
            bombHit.play();// play the bomb sound
        }
        super.playing();// call the super for all the others
    }

    /**
     * method to check for collision with exploding bombs
     * @return if there was a collision
     */
    private boolean checkForCollisionWithExplodingBomb(){
        for (int i = 0; i < explodingBomb.getObstacleRectangles().size(); i ++){// for all the exploding bomb rectangles
            if (Intersector.overlaps(monkey.getPlayerRectangle(), explodingBomb.getObstacleRectangles().get(i))){// check if it intersects with the monkey rectangle
                // if it intersects remove the exploding bombe and return true
                explodingBomb.getObstacleRectangles().remove(i);
                explodingBomb.getObstacleXs().remove(i);
                explodingBomb.getObstacleYs().remove(i);
                return true;
            }
        }
        return false;// otherwise return fals
    }

    /**
     *  method to check for collision with up explosions
     * @return if there was a collision
     */
    private boolean checkForCollisionWithUpExplosion() {
        for (int i = 0; i < explodingBomb.getUpExplosions().size(); i++) {// for all the up explosions
            for (int j = 0; j < explodingBomb.getUpExplosions().get(i).getObstacleRectangles().size(); j++){// for all the explosions rectangles
                if (Intersector.overlaps(monkey.getPlayerRectangle(),
                        explodingBomb.getUpExplosions().get(i).getObstacleRectangles().get(j))){
                    // if it intersects remove the up explosion
                    explodingBomb.getUpExplosions().get(i).getObstacleRectangles().remove(j);
                    explodingBomb.getUpExplosions().get(i).getObstacleYs().remove(j);
                    explodingBomb.getUpExplosions().get(i).getObstacleXs().remove(j);
                    return true;
                }
            }
        }
        return false;// otherwise return fals
    }

    /**
     *  method to check for collision with down explosions
     * @return if there was a collision
     */
    private boolean checkForCollisionWithDownExplosion() {
        for (int i = 0; i < explodingBomb.getDownExplosions().size(); i++) {// for all the down explosions
            for (int j = 0; j < explodingBomb.getDownExplosions().get(i).getObstacleRectangles().size(); j++){// for all the explosions rectangles
                if (Intersector.overlaps(monkey.getPlayerRectangle(),
                        explodingBomb.getDownExplosions().get(i).getObstacleRectangles().get(j))){
                    // if it intersects remove the down explosion
                    explodingBomb.getDownExplosions().get(i).getObstacleRectangles().remove(j);
                    explodingBomb.getDownExplosions().get(i).getObstacleYs().remove(j);
                    explodingBomb.getDownExplosions().get(i).getObstacleXs().remove(j);
                    return true;
                }
            }
        }
        return false;// otherwise return fals
    }

    /**
     * method to make again all the objects in the screen
     * override it to add the exploding boomb
     */
    @Override
    protected void makeNewObstacles() {
        super.makeNewObstacles();
        explodingBomb = new ExplodingBomb(screenWidth, screenHeight);
    }
}

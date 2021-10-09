package com.mygdx.game;

import com.parse.*;

import java.util.List;

/**
 * Class to handle the online high score bord
 * @author Nick Dimitriou
 */
public class HighScore {

    private static int min = Integer.MAX_VALUE;

    /**
     * static class to save a new score
     * @param userName the user name of the person that did the score
     * @param score the score to be saved
     */
    public static void saveScore(String userName, int score, String tableName){
        // access the table scores
        ParseObject object = new ParseObject(tableName);
        // put the data in their respective columns
        object.put("Username", userName);
        object.put("Score", score);

        //saving in the background the data
        object.saveInBackground();

    }

    /**
     * get from the server all the data from the users
     * @return the data of the users
     */
    public static List<ParseObject> LoadUsers(String tableName) {
        ParseQuery<ParseObject>  query = new ParseQuery<ParseObject>(tableName);// creating a list to pass the data from the table
        try {
            query.orderByDescending("Score");// putting the scores in order
            query.setLimit(5);//getting only the top 5
            return query.find();// the users with their data
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method to get the fifth lower value to see if the user manage to get a score in the board
     * @return the lowest score
     */
    public static int getMin(String tableName){
        ParseQuery<ParseObject>  query = new ParseQuery<ParseObject>(tableName);// creating a list to pass the data from the table
        try {// the query may not find anything
            query.orderByDescending("Score");// putting the scores in order
            query.setLimit(5);//getting only the top 5
            List<ParseObject> listOfScores = query.find();
            //still need to do the loop because we dont know how many values are in the board
            if(listOfScores.size() < 5){
                min = 0;
            } else {
                min = listOfScores.get(4).getInt("Score");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (min == Integer.MAX_VALUE)// if the min didnt change
            return -1;// return -1
        return min;// else retun the lowest value
    }
}

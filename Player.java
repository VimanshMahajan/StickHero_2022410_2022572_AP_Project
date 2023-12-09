package com.example._2022410_2022572_stickherogame;

import javafx.scene.image.ImageView;

// SINGLETON DESIGN PATTERN USED, TO ENSURE ONLY ONE PLAYER OBJECT IS PRESENT THROUGHOUT THE GAME.
public class Player {
    private static Player instance = null;

    private Player(double position, double stickLength, int score, int currentScore) {
        this.position = position;
        this.stickLength = stickLength;
        this.score = score;
        this.currentScore = currentScore;
    }

    public static Player getInstance(){
        if(instance == null){

            instance = new Player(140,0,0,0);
        }
        return instance;
    }


    private double position;
    private double stickLength;
    private int score;
    private static int numTotalCherries;
    private int currentScore;
    private static int bestScore;
    private ImageView playerImage;

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public double getStickLength() {
        return stickLength;
    }

    public void setStickLength(double stickLength) {
        this.stickLength = stickLength;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int getNumTotalCherries() {
        return numTotalCherries;
    }

    public static void setNumTotalCherries(int numTotalCherries) {
        Player.numTotalCherries = numTotalCherries;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public static int getBestScore() {
        return bestScore;
    }

    public static void setBestScore(int bestScore) {
        Player.bestScore = bestScore;
    }

    // Updated constructor to include the ImageView parameter

    public ImageView getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(ImageView playerImage) {
        this.playerImage = playerImage;
    }

    public void movePlayer() {
        // Implementation for moving the player
    }

    public void extendStick() {
        // Implementation for extending the stick
    }

    public boolean fallPlayer() {
        return false;
    }

    public boolean flipPlayer() {
        return false;
    }

    public void collectCherry() {
        // Implementation for collecting cherry
    }

    public boolean revivePlayer() {
        return false;
    }

    public int updateHighScore() {
        return 0;
    }
}
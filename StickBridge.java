package com.example._2022410_2022572_stickherogame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import static com.example._2022410_2022572_stickherogame.HelloApplication.createScene3;
import static com.example._2022410_2022572_stickherogame.HelloApplication.stage;

public class StickBridge {
    //public static int stickbent=0;
    private Rectangle stick;
    private double initialHeight;
    private boolean isReleased;

    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean released) {
        isReleased = released;
    }

    public StickBridge(double initialHeight) {
        this.initialHeight = initialHeight;
        this.stick = new Rectangle(5, 0); // Fixed width of 5, initial height is 0
        this.isReleased = false;
    }

    public Rectangle getStick() {
        return stick;
    }

    public double getInitialHeight() {
        return initialHeight;
    }

    public void setInitialHeight(double initialHeight) {
        this.initialHeight = initialHeight;
    }

    // Method to increase stick height while space bar is pressed
    public void elongateStick() {
        if (!isReleased) {
            double currentHeight = stick.getHeight();
            double newY = stick.getY() - 10;
            double newHeight = currentHeight + 10;

            stick.setY(newY);
            stick.setHeight(newHeight);
        }
    }

    public void releaseStick() {
        stick.setHeight(stick.getHeight());
        Rotate rotatestick= new Rotate(90, 150, 500);
        stick.getTransforms().add(rotatestick);
        setReleased(true);
    }
    public void resetStick(){
        stick.setHeight(0);
        Rotate rotatestick= new Rotate(180, 150, 500);
        stick.getTransforms().add(rotatestick);

    }


    // Method to reset the released state, allowing elongation again
    public void resetReleasedState() {
        isReleased = false;
    }


    // Method to handle space bar press
    public void handleKeyPress(KeyCode keyCode) {
        if (keyCode == KeyCode.SPACE) {
            elongateStick();
        }
    }

    // Method to handle space bar release
    public void handleKeyRelease(KeyCode keyCode) {
        if (keyCode == KeyCode.SPACE) {
            releaseStick();
        }
    }
}

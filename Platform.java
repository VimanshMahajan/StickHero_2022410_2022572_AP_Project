package com.example._2022410_2022572_stickherogame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Platform {
    private double offset;
    private double width;
    private static final double FIXED_HEIGHT = 300;

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Platform(double offset, double width) {
        this.offset = offset;
        this.width = width;
    }

    public Rectangle spawnPlatform() {
        double platformX = Math.max(0, Math.min(offset, 800 - width)); // Adjusted to stay within screen bounds
        Rectangle platform = new Rectangle(platformX, 800 - FIXED_HEIGHT, width, FIXED_HEIGHT);
        platform.setFill(Color.BLACK);
        return platform;
    }
}

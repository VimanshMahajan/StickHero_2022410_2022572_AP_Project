package com.example._2022410_2022572_stickherogame;

import javafx.scene.image.ImageView;

public class Swords extends ImageView {
    private double position;

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public Swords(String swordImage) {
        super(swordImage);
    }

    public Swords() {

    }
}
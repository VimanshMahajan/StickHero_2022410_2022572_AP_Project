package com.example._2022410_2022572_stickherogame;

import javafx.scene.image.ImageView;

public class Cherry extends ImageView {
    private double position;

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public Cherry(String cherryImage) {
        super(cherryImage);
    }

}

package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Tools.Point2D;

public class Plate extends Actor {
    public Plate(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
        this.x = position.getX();
        this.y = position.getY();
    }
    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX(),position.getY(), A, B);
    }
    @Override
    public void update() {
    }
}

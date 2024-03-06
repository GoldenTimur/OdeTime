package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Tools.Point2D;

public class Boxes extends Actor{

    public Boxes(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
        this.x = position.getX();
        this.y = position.getY();
    }
    private boolean touch = false;

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX(),position.getY(), A, B);
    }

    @Override
    public void update() {
//        if (!touch && bounds.isContains(gameSc.getBox().getPosition(),gameSc.getBox().getBounds())){
//            touch = true;
//        }
    }

    public void walkBox(float x, float y) {
        if (!touch){
            this.position = new Point2D(this.position.getX() + x, this.position.getY() + y);
        }
    }

    public boolean isTouch() {
        return touch;
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
    }
}

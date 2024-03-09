package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Boxes extends Actor{

    public Boxes(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
        this.x = position.getX();
        this.y = position.getY();
    }
    private boolean touch = false;

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,this.x,this.y, A, B);
    }
    @Override
    public void update() {
        setBounds(new Square(A,B,this.position));
        this.x = position.getX();
        this.y = position.getY();
        if (!touch && bounds.isContainsInside(gameSc.getPlate().getBounds())){
            touch = true;
        }
    }

    public void walkBox(float x, float y) {
        if (!touch){
            position = new Point2D(position.getX() + x, position.getY() + y);
        }
    }

    public boolean isTouch() {
        return touch;
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
    }
}

package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Boxes extends Actor{

    public Boxes(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);

    }
    private boolean touch = false;
    private boolean touch1 = false;

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,this.x,this.y, A, B);
    }
    @Override
    public void update() {
        setBounds(new Square(A,B,this.position));
        this.x = position.getX();
        this.y = position.getY();
        if (!touch && bounds.isContainsInside(gameSc.getPlate().getBounds()) && GameSc.getA()==1){
            touch = true;
        }
        if (!touch1 && bounds.isContainsInside(gameSc.getPit().getBounds()) && GameSc.getA()==2){
            setImg(Main.Box5);
            touch1 = true;
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

    public boolean isTouch1() {
        return touch1;
    }

    public void setTouch1(boolean touch1) {
        this.touch1 = touch1;
    }
}

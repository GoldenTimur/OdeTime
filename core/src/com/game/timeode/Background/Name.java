package com.game.timeode.Background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Tools.Point2D;

public class Name extends GraphicsObj {
    private Point2D position;
    private float x,y;
    public Name(Texture img, Point2D point2D, float x, float y) {
        super(img);
        this.position = point2D;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX(),position.getY(),x,y);
    }

    @Override
    public void update() {

    }
}

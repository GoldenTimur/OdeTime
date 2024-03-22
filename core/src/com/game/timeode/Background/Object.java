package com.game.timeode.Background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Tools.Point2D;

public class Object extends GraphicsObj {

    private float A, B;
    private Point2D position;

    public Object(Texture img, Point2D point2D, float A, float B) {
        super(img);
        this.position = new Point2D(point2D);
        this.A = A;
        this.B = B;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX(),position.getY(),this.A,this.B);
    }

    @Override
    public void update() {

    }
}

package com.game.timeode.GraphicsObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Tools.Point2D;

public abstract class GraphicsObj {

    public GraphicsObj(Texture img) {
        this.img = img;
    }

    public Texture img;
    public Point2D position;
    public float x,y;

    public abstract void draw(SpriteBatch batch);
    public abstract void update();

    public void setImg(Texture img) {
        this.img = img;
    }

    public Point2D getPosition() {
        return position;
    }

    public void walk(float x, float y){
        position.add(x,y);
    }
}

package com.game.timeode.Background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Tools.Point2D;

public class Load extends GraphicsObj {
    private Point2D position;
    private float x,y;
    public Load(Texture img,Point2D position, float x, float y) {
        super(img);
        this.position = position;
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

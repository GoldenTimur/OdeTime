package com.game.timeode.Background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Main;
import com.game.timeode.Tools.Point2D;

public class Scene extends GraphicsObj {

    public Scene(Texture img, Point2D point2D,float x, float y) {
        super(img);
        this.x = x;
        this.y = y;
        this.position = point2D;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX(),position.getY(),x,y);
    }

    @Override
    public void update() {

    }

}

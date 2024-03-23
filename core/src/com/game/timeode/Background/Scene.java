package com.game.timeode.Background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Main;
import com.game.timeode.Tools.Point2D;

public class Scene extends GraphicsObj {
    private final float constX,constY;

    public Scene(Texture img, Point2D point2D,float x, float y) {
        super(img);
        this.x = x;
        this.y = y;
        constX = point2D.getX();
        constY = point2D.getY();
        this.position = point2D;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX(),position.getY(),x,y);
    }

    @Override
    public void update() {

    }

    public void setPosition(){
        this.position = new Point2D(constX,constY);
    }
}

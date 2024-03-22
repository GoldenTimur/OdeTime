package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class WallLiane extends Actor{
    public WallLiane(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
    }
    private boolean touch = false;

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,this.x,this.y, A, B);
    }
    @Override
    public void update() {
        if (gameSc.getBox().isTouch() && bounds.isContains(gameSc.getPlayer().getBounds())) {
            gameSc.getBox().setTouch(false);
            gameSc.setD(true);
        }
        switch (GameSc.getA()){
            case (1):
                setImg(Main.WallLiane1);
            case (2):
            case (4):
                setImg(Main.WallLiane2);
        }
        setBounds(new Square(A,B,this.position));
        this.x = position.getX();
        this.y = position.getY();
    }
}

package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Pit extends Actor{
    public Pit(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,this.x,this.y, A, B);
    }

    @Override
    public void update() {
        setBounds(new Square(A,B,this.position));
        this.x = position.getX();
        this.y = position.getY();
        switch (GameSc.getA()){
            case (2):
                setImg(Main.Pit1);
                break;
            case (4):
                setImg(Main.Pit2);
                break;
        }
    }
}

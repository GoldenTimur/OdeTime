package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Plate extends Actor {
    public Plate(Texture img, Point2D position, float speed, float A, float B) {
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
        if (GameSc.getA()==4 && !(img == Main.Plane2)){
            setPosition(18.2f*Main.WIDTH/10.5f, Main.HEIGHT/4.875f);
            setImg(Main.Plane2);
        }
    }
}

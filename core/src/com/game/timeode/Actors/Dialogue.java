package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Actors.Actor;
import com.game.timeode.Tools.Point2D;

public class Dialogue extends Actor {

    public Dialogue(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(img,this.x,this.y, A, B);
    }

    @Override
    public void update() {
        A = this.bounds.getA();
        B = this.bounds.getB();
        System.out.println(this.bounds);
    }
}

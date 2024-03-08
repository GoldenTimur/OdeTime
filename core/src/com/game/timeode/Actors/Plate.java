package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Plate extends Actor {
    public Plate(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
        this.x = position.getX();
        this.y = position.getY();
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
        System.out.println("Plate: "+this.x+" "+this.y+" "+position.getX()+" "+position.getY());
    }
}

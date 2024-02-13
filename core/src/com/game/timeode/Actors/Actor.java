package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Tools.Circle;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public abstract class Actor extends GraphicsObj {

    public Point2D position;
    public float speed,A,B;
    public Square bounds;
    public Point2D direction;

    public Actor(Texture img, Point2D position, float speed, float A, float B) {
        super(img);
        this.position = new Point2D(position);
        this.speed = speed;
        this.A = A;
        this.B = B;
        bounds = new Square(A, B, position);
        direction = new Point2D(0,0);
    }

    public void setDirection(Point2D dir){
        this.direction = dir;
    }
}

package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Circle;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public abstract class Actor extends GraphicsObj {
    public float speed,A,B;
    public Square bounds;
    public Point2D direction;
    public GameSc gameSc = Main.gameSc;
    public final float constX, constY;
    public Actor(Texture img, Point2D position, float speed, float A, float B) {
        super(img);
        this.position = new Point2D(position);
        this.speed = speed;
        this.x = position.getX();
        this.y = position.getY();
        this.constX = position.getX();
        this.constY = position.getY();
        this.A = A;
        this.B = B;
        bounds = new Square(A, B, position);
        direction = new Point2D(0,0);
    }

    public void setDirection(Point2D dir){
        this.direction = dir;
    }

    public void setPosition(Point2D pos) {
        this.position = new Point2D(position.getX()+pos.getX(),position.getY());;
    }
    public void setPosition(float x, float y) {
        this.position = new Point2D(x,y);
    }
    public void setPosition(){
        this.position = new Point2D(constX,constY);
    }

    public Square getBounds() {
        return bounds;
    }

    public void setBounds(Square bounds) {
        this.bounds = bounds;
    }
}

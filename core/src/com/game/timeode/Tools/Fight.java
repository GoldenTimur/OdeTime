package com.game.timeode.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Actors.Player;

public class Fight {
    public static boolean fighter;
    Texture CircleImg;
    Circle CircleBounds;
    float RCircle;
    private int pointer = -1;
    private boolean f = false;

    Point2D direction;

    public Fight(Texture cimg, Point2D point, float Size) {
        CircleImg = cimg;
        RCircle = Size/2;
        CircleBounds = new Circle(RCircle, point);
        direction = new Point2D(0,0);
    }

    public void draw(SpriteBatch batch){
        batch.draw(CircleImg,CircleBounds.pos.getX()-RCircle,CircleBounds.pos.getY()-RCircle,RCircle*2,RCircle*2);
    }

    public void update(float x, float y, boolean isDownTouch, int pointer){
        Point2D touch = new Point2D(x,y);


        if(CircleBounds.isContains(touch) && isDownTouch && this.pointer == -1 && f == false){
            this.pointer = pointer;
            fighter = true;
            f = true;
        }
        if (Player.h){
            this.pointer = -1;
            f = false;
            Player.h = false;
        }
    }

    public void returnStick(){
        direction.setPoint(0,0);
        fighter = false;
        pointer = -1;
    }
    public Point2D getDir(){
        return direction;
    }
}


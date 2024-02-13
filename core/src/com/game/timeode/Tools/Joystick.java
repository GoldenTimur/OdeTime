package com.game.timeode.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Joystick {
    Texture CircleImg, StickImg;
    Circle CircleBounds, StickBounds;
    float RCircle, RStick;
    private int pointer = -1;

    Point2D direction, point;
    public static boolean ler;

    public Joystick(Texture cimg, Texture simg, Point2D point, float Size) {
        CircleImg = cimg;
        StickImg = simg;
        RCircle = Size/2;
        RStick = RCircle/1.2f;
        CircleBounds = new Circle(RCircle, point);
        StickBounds = new Circle(RStick, point);
        direction = new Point2D(0,0);
        this.point = point;
    }

    public void draw(SpriteBatch batch){
        batch.draw(CircleImg,CircleBounds.pos.getX()-RCircle,CircleBounds.pos.getY()-RCircle,RCircle*2,RCircle*2);
        batch.draw(StickImg,StickBounds.pos.getX()-RStick,StickBounds.pos.getY()-RStick,RStick*2,RStick*2);
    }

    public void update(float x, float y, boolean isDownTouch, int pointer){
        Point2D touch = new Point2D(x,y);
        if(CircleBounds.isContains(touch) && isDownTouch && this.pointer == -1){
            this.pointer = pointer;
        }
        if(CircleBounds.Overlaps(StickBounds) && isDownTouch && pointer == this.pointer){
            atControl(new Point2D(x,y));
        }
        if(!isDownTouch && pointer == this.pointer){
            returnStick();
        }
        if (isDownTouch && pointer == this.pointer && !CircleBounds.isContains(touch)){
            StickBounds.pos.setPoint(StickBounds.pos);
        }
        if(StickBounds.pos.getX() > point.getX()){
            ler = true;
        } else if (StickBounds.pos.getX() < point.getX()) {
            ler = false;
        }
    }

    public void atControl(Point2D point){
        StickBounds.pos.setPoint(point);
        float dx = CircleBounds.pos.getX() - StickBounds.pos.getX();
        float dy = CircleBounds.pos.getY() - StickBounds.pos.getY();
        float dist = (float) Math.sqrt(dx*dx + dy*dy);

        direction.setPoint(-(dx/dist),-(dy/dist));

    }

    public void returnStick(){
        StickBounds.pos.setPoint(point);
        direction.setPoint(0,0);
        pointer = -1;
    }
    public Point2D getDir(){
        return direction;
    }
}

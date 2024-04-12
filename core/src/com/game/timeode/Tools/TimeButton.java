package com.game.timeode.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;

public class TimeButton {
    Texture CircleImg;
    Circle CircleBounds;
    float RCircle;
    Point2D direction;
    private int pointer = -1;
    TimePointer timePointer;
    public TimeButton(Texture cimg, Point2D point, float Size) {
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
        if(!TimePointerFlag && CircleBounds.isContainsFight(touch) && isDownTouch && this.pointer == -1){
            TimePointerFlag = true;
            this.pointer = pointer;
            Main.timeFlag = !Main.timeFlag;
            timePointer = new TimePointer();
            timePointer.start();
        }
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    private boolean TimePointerFlag = false;
    class TimePointer extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            TimePointerFlag = false;
            setPointer(-1);
        }
    }
}

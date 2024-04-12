package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Boxes extends Actor{


    public Boxes(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
    }
    private boolean touch = false;
    private boolean touch1 = false;
    private boolean touch2 = false;
    private boolean touch2_1 = false;
    WalkBox walkBox;
    private boolean walkBoxFlag = false;

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,this.x,this.y, A, B);
    }
    @Override
    public void update() {
        setBounds(new Square(A,B,this.position));
        this.x = position.getX();
        this.y = position.getY();
        if(!isTouch() && !touch2_1) {
            switch (GameSc.getA()) {
                case (1):
                    setImg(Main.Box1);
                    break;
                case (2):
                    setImg(Main.Box4);
                    break;
                case (4):
                    if (Main.timeFlag) {
                        setImg(Main.Box1);
                    }else {
                        setImg(Main.Box2);
                    }
                    break;
            }
        }

        if (!touch && bounds.isContainsInside(gameSc.getPlate().getBounds()) && GameSc.getA()==1){
            touch = true;
        }
        if (!touch1 && bounds.isContainsInside(gameSc.getPit().getBounds()) && GameSc.getA()==2){
            setImg(Main.Box5);
            touch1 = true;
        }
        if (!Main.timeFlag && !walkBoxFlag && bounds.isContainsInside(gameSc.getWater().getBounds()) && GameSc.getA()==4){
            walkBoxFlag = true;
            walkBox = new WalkBox();
            walkBox.start();
        }
        if (!touch2_1 && Main.timeFlag && bounds.isContainsInside(gameSc.getPit().getBounds()) && GameSc.getA()==4){
            setImg(Main.Box6);
            touch2_1 = true;
        }
        if (!touch2 && bounds.isContainsInside(gameSc.getPlate().getBounds()) && GameSc.getA()==4){
            touch2 = true;
        }
    }

    public void walkBox(float x, float y) {
        if (!isTouch() && !touch2_1){
            position = new Point2D(position.getX() + x, position.getY() + y);
        }
    }

    public boolean isTouch() {
        switch(GameSc.getA()){
            case (1):
                return touch;
            case (2):
                return touch1;
            case (4):
                return touch2;
        }
        return touch;
    }

    public void setTouch(boolean touch) {
        switch(GameSc.getA()){
            case (1):
                this.touch = touch;
                break;
            case (2):
                this.touch1 = touch;
                break;
            case (4):
                this.touch2 = touch;
                break;
        }
    }
    public void setTouch(boolean touch, int a) {
        switch(a){
            case (1):
                this.touch = touch;
                break;
            case (2):
                this.touch1 = touch;
                break;
            case (4):
                this.touch2 = touch;
                break;
        }
    }
    public void setTouch2(boolean a){
        this.touch2_1 = a;
    }
    class WalkBox extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            walkBox(0,-Main.HEIGHT/4.875f);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            walkBoxFlag = false;
        }
    }
}

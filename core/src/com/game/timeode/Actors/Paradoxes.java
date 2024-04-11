package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

import java.util.Random;

public class Paradoxes extends Actor{
    private boolean touch = false;
    public Paradoxes(Texture img,Point2D position, float speed, float A, float B, int I) {
        super(img, position, speed, A, B);
        Random random = new Random();
        System.out.println(A+" "+B);
        this.position = new Point2D(random.nextFloat()*Main.WIDTH,random.nextFloat()*Main.HEIGHT);
    }


    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,this.x,this.y, A, B);
    }

    private boolean removeFlag = false;
    private boolean StopFlag = false;

    @Override
    public void update() {
        setBounds(new Square(A, B, this.position));
        this.x = position.getX();
        this.y = position.getY();
        if(!touch && !removeFlag && bounds.isContains(gameSc.getPlayer().getBounds()) && Fight.fighter && GameSc.getA()==5){
            Player.I++;
            StopFlag = true;
            removeFlag = true;
            Stop stop = new Stop();
            stop.start();
//            gameSc.setA(-1);
        }

        if(!StopFlag) {
            if (gameSc.getPlayer().getPosition().getX() > this.position.getX()) {
                setPosition(new Point2D(speed, 0));
            } else {
                setPosition(new Point2D(-speed, 0));
            }
            if (gameSc.getPlayer().getPosition().getY() > this.position.getY()) {
                setPosition(new Point2D(0, speed));
            } else {
                setPosition(new Point2D(0, -speed));
            }
        }
//        for (Paradoxes paradoxes : gameSc.getPar()){
//            System.out.println(paradoxes);
//        }
    }

    public boolean isTouch() {
        return touch;
    }

    class Stop extends Thread{
        @Override
        public void run(){
            setImg(Main.Paradox2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            touch = true;
        }
    }
}

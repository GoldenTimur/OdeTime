package com.game.timeode.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Objects;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.Point2D;

import java.util.concurrent.TimeUnit;

public class Player extends Actor{

    private float health;
    Texture imgfig;
    boolean ThreadFlag = true;
    public static boolean h = false;
    private GameSc gameSc;
    private int x = 115,y = 225;

    public Player(Texture imgwalk, Texture imgfig, Point2D position, float speed, float A, float B, float health) {
        super(imgwalk, position, speed, A, B);
        this.health = health;
        this.imgfig = imgfig;
        this.gameSc = Main.gameSc;
    }

    @Override
    public void draw(SpriteBatch batch) {

        if (!Fight.fighter){
            x = 115;
            y = 225;
        }

        batch.draw(img,position.getX(),position.getY(), x, y);
    }

    @Override
    public void update() {
        if(position.getX()+A+Main.WIDTH/100 > Main.WIDTH){
            position.setX(Main.WIDTH-A-Main.WIDTH/100);
            if (gameSc.getX(gameSc.getScene().getPosition())>-Main.HEIGHT*2.75f) {
                gameSc.walk(-direction.getX() * speed, 0);
            }
        }
        if(position.getX()-Main.WIDTH/100 < 0){
            position.setX(Main.WIDTH/100);
            if (gameSc.getX(gameSc.getScene().getPosition())<0) {
                gameSc.walk(-direction.getX() * speed, 0);
            }
        }
        if(position.getY()+B+Main.HEIGHT/100 > Main.HEIGHT){
            position.setY(Main.HEIGHT-B-Main.HEIGHT/100);
        }
        if(position.getY()-Main.HEIGHT/100 < 0){
            position.setY(Main.HEIGHT/100);
        }

        if (!Fight.fighter) {
            position.add(direction.getX() * speed, direction.getY() * speed);
        }

        if(!Fight.fighter){
            walk(Main.actor1);
        }else {
            fight(Main.actor1, Main.actorFight1, Main.actorFight1_1, Main.actorFight2, Main.actorFight3);
        }
        if (Joystick.ler && !Fight.fighter){
            setImg(Main.actor1_1);
        }else if(!Fight.fighter) {
            setImg(Main.actor1);
        }
    }
    public void fight(Texture img, Texture img1, Texture img1_1, Texture img2, Texture img3){
        if (ThreadFlag){
            ThreadFlag = false;
            MyThreed threed = new MyThreed();
            threed.start();
        }


//        setImg(img2);
//        setImg(img3);
//        setImg(img);

    }
    public void walk(Texture img1){
        setImg(img1);
    }
    class MyThreed extends Thread{
        @Override
        public void run(){
            if (Joystick.ler){
                setImg(Main.actorFight1_1);
                x = 300;
                y = 200;
            }else {
                setImg(Main.actorFight1);
                setPosition(new Point2D(-185,0));
                x = 300;
                y = 200;
            }


            try {
                Thread.sleep(170);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (Joystick.ler){
                setImg(Main.actorFight2_1);
            }else {
                setImg(Main.actorFight2);
            }

            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (Joystick.ler){
                setImg(Main.actorFight3_1);
            }else {
                setImg(Main.actorFight3);
            }

            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!Joystick.ler) {
                setPosition(new Point2D(185, 0));
            }

            Fight.fighter = false;

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ThreadFlag = true;
            h = true;
        }
    }
}

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
import com.game.timeode.Tools.Square;

import java.util.concurrent.TimeUnit;

public class Player extends Actor{

    private float health;
    Texture imgfig;
    boolean ThreadFlag = true;
    public static boolean h = false;
    private float x,y;
    private final float x1,y1;


    public Player(Texture imgwalk, Texture imgfig, Point2D position, float speed, float A, float B, float health) {
        super(imgwalk, position, speed, A, B);
        this.health = health;
        this.imgfig = imgfig;
        this.x = A;
        this.y = B;
        this.x1 = A;
        this.y1 = B;

    }

    @Override
    public void draw(SpriteBatch batch) {

        if (!Fight.fighter){
            this.x = A;
            this.y = B;
        }

        batch.draw(img,position.getX(),position.getY(), x, y);
    }
    private boolean flagBox = true;

    @Override
    public void update() {
        setBounds(new Square(A,B,this.position));
        if(position.getX()+A+Main.WIDTH/45 > Main.WIDTH){
            position.setX(Main.WIDTH-A-Main.WIDTH/45);
            if (gameSc.ObjGetX(gameSc.getScene())>-Main.HEIGHT*2.75f) {
                gameSc.walk(gameSc.getScene(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getBox(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWallLiane(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPlate(),-direction.getX() * speed, 0);
            }
        }
        if(position.getX()-Main.WIDTH/45 < 0){
            position.setX(Main.WIDTH/45);
            if (gameSc.ObjGetX(gameSc.getScene())<0) {
                gameSc.walk(gameSc.getScene(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getBox(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWallLiane(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPlate(),-direction.getX() * speed, 0);
            }
        }
        if(position.getY()+B+Main.HEIGHT/90 > Main.HEIGHT){
            position.setY(Main.HEIGHT-B-Main.HEIGHT/90);
        }
        if(position.getY()-Main.HEIGHT/90 < 0){
            position.setY(Main.HEIGHT/90);
        }

        if (!Fight.fighter) {
            position.add(direction.getX() * speed, direction.getY() * speed);
        }

        if(!Fight.fighter){
            walk();
        }else {
            fight();
        }
        if (Joystick.ler && !Fight.fighter){
            setImg(Main.actor1_1);
        }else if(!Fight.fighter) {
            setImg(Main.actor1);
        }

        if (flagBox && Fight.fighter && bounds.isContains(gameSc.getBox().bounds)) {
            flagBox = false;
            if (Joystick.ler) {
                gameSc.getBox().walkBox(15.3f * gameSc.getBox().A / 16, 0);
            } else {
                gameSc.getBox().walkBox(-15.3f * gameSc.getBox().A / 16, 0);
            }
        }
        if (!gameSc.getBox().isTouch() && bounds.isContains(gameSc.getWallLiane().bounds)){
            stop();
        }
    }
    public void fight(){
        if (ThreadFlag){
            ThreadFlag = false;
            MyThreed threed = new MyThreed();
            threed.start();
        }


//        setImg(img2);
//        setImg(img3);
//        setImg(img);

    }
    public void walk(){
        setImg(Main.actor1);
    }
    public void stop(){
        position.setPoint(position.getX()-Main.WIDTH/270,position.getY());
    }
    class MyThreed extends Thread{
        @Override
        public void run(){
            if (Joystick.ler){
                setImg(Main.actorFight1_1);
                x = 2.7027f*A;
                y = 0.9259f*B;
                A = x;
                B = y;
            }else {
                setImg(Main.actorFight1);
                setPosition(new Point2D(-185,0));
                x = 2.7027f*A;
                y = 0.9259f*B;
                A = x;
                B = y;
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
            A = x1;
            B = y1;
            Fight.fighter = false;

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ThreadFlag = true;
            h = true;
            flagBox = true;
        }
    }
}

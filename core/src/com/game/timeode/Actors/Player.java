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
    private GameSc gameSc;
    private float x,y;

    public Player(Texture imgwalk, Texture imgfig, Point2D position, float speed, float A, float B, float health) {
        super(imgwalk, position, speed, A, B);
        this.health = health;
        this.imgfig = imgfig;
        this.gameSc = Main.gameSc;
        this.x = A;
        this.y = B;

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
        bounds = new Square(A,B,this.position);
        if(position.getX()+A+Main.WIDTH/45 > Main.WIDTH){
            position.setX(Main.WIDTH-A-Main.WIDTH/45);
            if (gameSc.ObjGetX(gameSc.getScene())>-Main.HEIGHT*2.75f) {
                gameSc.walk(gameSc.getScene(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getBox(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWallLiane(),-direction.getX() * speed, 0);
            }
        }
        if(position.getX()-Main.WIDTH/45 < 0){
            position.setX(Main.WIDTH/45);
            if (gameSc.ObjGetX(gameSc.getScene())<0) {
                gameSc.walk(gameSc.getScene(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getBox(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWallLiane(),-direction.getX() * speed, 0);
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
            walk(Main.actor1);
        }else {
            fight(Main.actor1, Main.actorFight1, Main.actorFight1_1, Main.actorFight2, Main.actorFight3);
        }
        if (Joystick.ler && !Fight.fighter){
            setImg(Main.actor1_1);
        }else if(!Fight.fighter) {
            setImg(Main.actor1);
        }

        if (flagBox && Fight.fighter && bounds.isContains(gameSc.getBox().getPosition(),gameSc.getBox().bounds)) {
            flagBox = false;
            if (Joystick.ler) {
                gameSc.getBox().walkBox(15.3f * gameSc.getBox().A / 16, 0);
            } else {
                gameSc.getBox().walkBox(-15.3f * gameSc.getBox().A / 16, 0);
            }
        }
        if (bounds.isContains(gameSc.getWallLiane().getPosition(),gameSc.getWallLiane().bounds)){
            stop();
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
    public void stop(){
        position.setPoint(position.getX()-Main.WIDTH/270,position.getY());
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
            flagBox = true;
        }
    }
}

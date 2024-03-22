package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Player extends Actor{

    private float health;
    Texture imgfig;
    boolean ThreadFlag = true;
    public static boolean h = false;
    private float a, b;
    private final float constA, constB;
    private boolean stopPlayer = false;
    private Dialogue dialogue;
    private Dialogue1 dialogue1;



    public Player(Texture imgwalk, Texture imgfig, Point2D position, float speed, float A, float B, float health) {
        super(imgwalk, position, speed, A, B);
        this.health = health;
        this.imgfig = imgfig;
        this.a = A;
        this.b = B;
        this.constA = A;
        this.constB = B;
        this.dialogue1 = new Dialogue1();
        dialogue = new Dialogue(Main.Empty,new Point2D(2*Main.WIDTH/12.5f,0),7,Main.WIDTH/1.5f,Main.HEIGHT/2.5f);
    }

    @Override
    public void draw(SpriteBatch batch) {

        if (!Fight.fighter){
            this.a = A;
            this.b = B;
        }

        batch.draw(img,position.getX(),position.getY(), a, b);
    }
    private boolean flagBox = true;

    @Override
    public void update() {
        setBounds(new Square(A,B,this.position));
        if(position.getX()+A+Main.WIDTH/45f > Main.WIDTH){
            position.setX(Main.WIDTH-A-Main.WIDTH/45f);
            if (gameSc.ObjGetX(gameSc.getScene())>-Main.HEIGHT*2.75f && !(position.getX()+A+Main.WIDTH/45f > Main.WIDTH)) {
                gameSc.walk(gameSc.getScene(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getBox(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWallLiane(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPlate(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPit(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWater(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getTime(),-direction.getX() * speed, 0);
            }
        }
        if(position.getX()-Main.WIDTH/45f < 0){
            position.setX(Main.WIDTH/45f);
            if (gameSc.ObjGetX(gameSc.getScene())<0 && !(position.getX()-Main.WIDTH/45f < 0)) {
                gameSc.walk(gameSc.getScene(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getBox(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWallLiane(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPlate(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPit(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWater(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getTime(),-direction.getX() * speed, 0);
            }
        }
        if(position.getY()+B+Main.HEIGHT/90f > Main.HEIGHT){
            position.setY(Main.HEIGHT-B-Main.HEIGHT/90f);
        }
        if(position.getY()-Main.HEIGHT/90f < 0){
            position.setY(Main.HEIGHT/90f);
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

        if (flagBox && Fight.fighter && bounds.isContains(gameSc.getBox().getBounds())) {
            flagBox = false;
            if (Joystick.ler) {
                gameSc.getBox().walkBox(15.3f * gameSc.getBox().A / 16, 0);
            } else {
                gameSc.getBox().walkBox(-15.3f * gameSc.getBox().A / 16, 0);
            }
        }
        if (bounds.isContains(gameSc.getPit().getBounds()) && !bounds.isContains(gameSc.getBox().getBounds()) && GameSc.getA()==2){
            stop();
        }
        if (!gameSc.getBox().isTouch() && bounds.isContains(gameSc.getWallLiane().bounds) && (GameSc.getA()==1 || GameSc.getA()==2 || GameSc.getA()==4)){
            stop();
        }

        if (bounds.isContains(gameSc.getTime().getBounds()) || GameSc.getA()==1){
            if (!Dialogue1Flag){
                Dialogue1Flag = true;
                dialogue1.start();
            }
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
        if(Joystick.ler){
            position.setPoint(position.getX()-Main.WIDTH/270f,position.getY());
        }else {
            position.setPoint(position.getX()+Main.WIDTH/270f,position.getY());
        }

    }
    public boolean isStopPlayer() {
        return stopPlayer;
    }
    class MyThreed extends Thread{
        @Override
        public void run(){
            if (Joystick.ler){
                setImg(Main.actorFight1_1);
                a = 2.7027f*A;
                b = 0.9259f*B;
                A = a;
                B = b;
            }else {
                setImg(Main.actorFight1);
                setPosition(new Point2D(-185,0));
                a = 2.7027f*A;
                b = 0.9259f*B;
                A = a;
                B = b;
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
            A = constA;
            B = constB;
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

    public Dialogue getDialogue() {
        return dialogue;
    }

    public void setDialogue1Flag() {
        Dialogue1Flag = false;
    }

    private boolean Dialogue1Flag = false;
    class Dialogue1 extends Thread{
        @Override
        public void run(){
            stopPlayer = true;
            switch (GameSc.getA()) {
                case (1):
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue1);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Empty);
                    stopPlayer = false;
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    stopPlayer = true;
                    dialogue.setImg(Main.Dialogue2);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case (3):
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue3);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue4);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue5);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue6);

                    try {
                    Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue7);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue8);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue9);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue10);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue11);
                    gameSc.getBox().setTouch(false);
                    gameSc.setD(true);
                    break;
            }
            dialogue.setImg(Main.Empty);
            stopPlayer = false;
        }
    }
}

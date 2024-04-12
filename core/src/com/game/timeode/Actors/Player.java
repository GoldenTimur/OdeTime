package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Again;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Player extends Actor{

    private float health;
    Texture imgfig;
    private boolean ThreadFlag = true;
    public static boolean h = false;
    private float a, b;
    private final float constA, constB;
    private boolean stopPlayer = false;
    private Dialogue dialogue;
    private Dialogue1 dialogue1;
    public static int I = 0;
    private MyThread thread;
    private Walk walk;


    public Player(Texture imgwalk, Texture imgfig, Point2D position, float speed, float A, float B, float health) {
        super(imgwalk, position, speed, A, B);
        this.health = health;
        this.imgfig = imgfig;
        this.a = A;
        this.b = B;
        this.constA = A;
        this.constB = B;
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
        if(!Fight.fighter && position.getX()+A+Main.WIDTH/3f > Main.WIDTH){
            position.setX(Main.WIDTH-A-Main.WIDTH/3f);
            if (gameSc.ObjGetX(gameSc.getScene())>-Main.HEIGHT*2.75f && !(position.getX()+A+Main.WIDTH/45f > Main.WIDTH)) {
                gameSc.walk(gameSc.getScene(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getBox(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWallLiane(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPlate(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPit(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWater(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getTime(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getParadox(),-direction.getX() * speed, 0);
                for (int i = 0; i<gameSc.getPar().size(); i++){
                    gameSc.walk(gameSc.getPar().get(i),-direction.getX() * speed, 0);
                }
            }
        }
        if(!Fight.fighter && position.getX()-Main.WIDTH/3f < 0){
            position.setX(Main.WIDTH/3f);
            if (gameSc.ObjGetX(gameSc.getScene())<0 && !(position.getX()-Main.WIDTH/45f < 0)) {
                gameSc.walk(gameSc.getScene(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getBox(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWallLiane(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPlate(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getPit(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getWater(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getTime(),-direction.getX() * speed, 0);
                gameSc.walk(gameSc.getParadox(),-direction.getX() * speed, 0);
                for (int i = 0; i<gameSc.getPar().size(); i++){
                    gameSc.walk(gameSc.getPar().get(i),-direction.getX() * speed, 0);
                }
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

        if (flagBox && Fight.fighter && bounds.isContains(gameSc.getBox().getBounds())) {
            flagBox = false;
            if (Joystick.ler) {
                gameSc.getBox().walkBox(15.3f * gameSc.getBox().A / 16, 0);
            } else {
                gameSc.getBox().walkBox(-15.3f * gameSc.getBox().A / 16, 0);
            }
        }
        if (bounds.isContains(gameSc.getPit().getBounds()) && !Fight.fighter && !bounds.isContains(gameSc.getBox().getBounds()) && (GameSc.getA()==2 || (GameSc.getA()==4 && Main.timeFlag))){
            stop();
        }
        if (!gameSc.getBox().isTouch() && !Fight.fighter && bounds.isContains(gameSc.getWallLiane().bounds) && (GameSc.getA()==1 || GameSc.getA()==2 || GameSc.getA()==4)){
            stop();
        }

        if (bounds.isContains(gameSc.getTime().getBounds()) || GameSc.getA()==1){
            if (!Dialogue1Flag && !Again.AgainFlag){
                dialogue1 = new Dialogue1();
                Dialogue1Flag = true;
                dialogue1.start();
            }
        }

//        System.out.println(I);
        if (I >= 50 && GameSc.getA()==5){
            gameSc.setA(-1);
        }

//        if (Dialogue1Flag2 && Again.AgainFlag && gameSc.isD() && dialogue1 != null){
//            Dialogue1Flag2 = false;
//            dialogue1.interrupt();
//        }
    }
//    public static boolean Dialogue1Flag2 = true;
    public void fight(){
        if (ThreadFlag && !walkFlag){
            ThreadFlag = false;
            thread = new MyThread();
            thread.start();
        }


//        setImg(img2);
//        setImg(img3);
//        setImg(img);

    }
    public void walk(){
        if(!walkFlag && Joystick.touchFlag && ThreadFlag) {
            walkFlag = true;
            walk = new Walk();
            walk.start();
        }else if(!Joystick.touchFlag){
            if (Joystick.ler && !Fight.fighter){
                setImg(Main.actor1_1);
            }else if(!Fight.fighter) {
                setImg(Main.actor1);
            }
        }
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

    public void setStopPlayer(boolean stopPlayer) {
        this.stopPlayer = stopPlayer;
    }

    class MyThread extends Thread{
        @Override
        public void run(){
            walk = new Walk();
            if (Joystick.ler) {
                setImg(Main.actorFight1_1);
                a = 2.7027f * A;
                b = 0.9259f * B;
                A = a;
                B = b;
            } else {
                setImg(Main.actorFight1);
                setPosition(new Point2D(-185, 0));
                a = 2.7027f * A;
                b = 0.9259f * B;
                A = a;
                B = b;
            }


            try {
                Thread.sleep(170);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (Joystick.ler) {
                setImg(Main.actorFight2_1);
            } else {
                setImg(Main.actorFight2);
            }

            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (Joystick.ler) {
                setImg(Main.actorFight3_1);
            } else {
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

            if (Joystick.ler) {
                setImg(Main.actor1_1);
            } else {
                setImg(Main.actor1);
            }

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

    private boolean walkFlag = false;
    class Walk extends Thread{
        @Override
        public void run(){
            synchronized (this) {
                thread = new MyThread();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor2_1);
                } else {
                    setImg(Main.actor2);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor3_1);
                } else {
                    setImg(Main.actor3);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor4_1);
                } else {
                    setImg(Main.actor4);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor5_1);
                } else {
                    setImg(Main.actor5);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor6_1);
                } else {
                    setImg(Main.actor6);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor7_1);
                } else {
                    setImg(Main.actor7);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor8_1);
                } else {
                    setImg(Main.actor8);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor9_1);
                } else {
                    setImg(Main.actor9);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Joystick.ler) {
                    setImg(Main.actor10_1);
                } else {
                    setImg(Main.actor10);
                }
                walkFlag = false;
            }
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
                    stopPlayer = false;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    stopPlayer = true;
                    dialogue.setImg(Main.Dialogue1);
                    try {
                        Thread.sleep(4000);
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
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue4);

                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue5);

                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue6);

                    try {
                    Thread.sleep(7000);
                    } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue7);

                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue8);

                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue9);

                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue10);

                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setImg(Main.Dialogue11);

                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialogue.setBounds(new Square(Main.WIDTH/1.5f,2*Main.HEIGHT/2.5f, dialogue.getPosition()));
                    dialogue.setImg(Main.qwerty);

                    try {
                        Thread.sleep(12000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    gameSc.getBox().setTouch(false);
                    gameSc.setD(true);
                    break;
            }
            dialogue.setBounds(new Square(Main.WIDTH/1.5f,Main.HEIGHT/2.5f, dialogue.getPosition()));
            dialogue.setImg(Main.Empty);
            stopPlayer = false;
        }
    }
}

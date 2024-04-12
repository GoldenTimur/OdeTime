package com.game.timeode.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.timeode.Actors.Boxes;
import com.game.timeode.Actors.Paradoxes;
import com.game.timeode.Actors.Pit;
import com.game.timeode.Actors.Plate;
import com.game.timeode.Actors.Player;
import com.game.timeode.Actors.Time;
import com.game.timeode.Actors.WallLiane;
import com.game.timeode.Actors.Water;
import com.game.timeode.Background.Load;
import com.game.timeode.Background.Name;
import com.game.timeode.Background.Start;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Main;
import com.game.timeode.Objects;
import com.game.timeode.Tools.Again;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.PlayButton;
import com.game.timeode.Tools.Point2D;

import com.game.timeode.Background.Scene;
import com.game.timeode.Tools.TimeButton;

import java.util.ArrayList;

public class GameSc extends Objects implements Screen  {
    Main main;
    Many many;
    public GameSc(Main main, Joystick joy, Fight fig, Player player, Scene scene, Scene scene2, PlayButton playButton, Name name, Start start, Load load, Boxes box, WallLiane wallLiane, Plate plate, Pit pit, Time time, Water water, Paradoxes paradox, Again again, TimeButton timeButton) {
        super(main,joy, fig, player, scene, scene2, playButton, name, start, load, box, wallLiane, plate, pit, time, water, paradox, again, timeButton);
        this.main = main;
        many = new Many();
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT-screenY;
                multitouch((int)screenX,(int)screenY,true,pointer);
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT-screenY;
                multitouch((int)screenX,(int)screenY,false,pointer);
                return false;
            }

            @Override
            public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                screenY = Main.HEIGHT-screenY;
                multitouch((int)screenX,(int)screenY,true,pointer);
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }
        });

        loadActors();
    }

    @Override
    public void render(float delta) {
        GameUpdate();
        ScreenUtils.clear(0.259f, 0.667f, 1, 1);
        Main.batch.begin();
        GameRender(Main.batch);
        Main.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void GameUpdate() {
        if (!player.isStopPlayer()){
            player.setDirection(joy.getDir());
            player.update();
        }
    }

    public static int a = 3;
    private boolean d = true;

    public void GameRender(SpriteBatch batch){
        if (PlayButton.play) {
            if (d) {
                load(batch);
            }else {
                switch (a) {
                    case (1):
                        level1(batch);
                        break;
                    case (2):
                        level2(batch);
                        break;
                    case (3):
                        level3(batch);
                        break;
                    case (4):
                        level4(batch);
                        break;
                    case (5):
                        level5(batch);
                        break;
                }
                player.draw(batch);
                joy.draw(batch);
                fig.draw(batch);
                again.draw(batch);
                timeButton.draw(batch);
                player.getDialogue().draw(batch);
            }


        } else {
            startWindow(batch);
        }
    }
    public void setA(){
        setAllPosition();
        player.setPosition(200,player.getPosition().getY());
        a++;
    }
    public void setA(int a){
        if (!stopAllFlag){
            Player.I = 0;
            stopAllFlag = true;
            StopAll stopAll = new StopAll();
            stopAll.start();
        }
    }
    public void onlySetA(int a){
        this.a = a;
    }

    public static int getA() {
        return a;
    }

    public void load(SpriteBatch batch){
        load.draw(batch);
        ThreadLoad threadLoad = new ThreadLoad();
        if (ThreadFlag) {
        threadLoad.start();
        }
    }
    public void startWindow(SpriteBatch batch){
        start.draw(batch);
        playButton.draw(batch);
        name.draw(batch);
    }
    public void level1(SpriteBatch batch){
        scene.draw(batch);
        plate.draw(batch);
        plate.update();
        box.draw(batch);
        box.update();
        if (!getBox().isTouch()){
            wallLiane.draw(batch);
        }
        wallLiane.update();
    }
    public void level2(SpriteBatch batch){
        scene2.draw(batch);
        pit.draw(batch);
        pit.update();
        box.draw(batch);
        box.update();
        wallLiane.update();
    }
    public void level3(SpriteBatch batch){
        scene.draw(batch);
        time.draw(batch);
        time.update();

    }
    public void level4(SpriteBatch batch){
        if(Main.timeFlag){
            scene.draw(batch);
            pit.draw(batch);
            pit.update();
            scene2.setPosition(scene.getPosition().getX(),scene.getPosition().getY());
            water.setPosition(pit.getPosition().getX(),pit.getPosition().getY());
        }else {
            scene2.draw(batch);
            water.draw(batch);
            water.update();
            scene.setPosition(scene2.getPosition().getX(),scene2.getPosition().getY());
            pit.setPosition(water.getPosition().getX(),water.getPosition().getY());
        }
//        System.out.println(pit.getPosition().getX());
//        System.out.println(pit.getPosition().getY());
//        System.out.println(water.getPosition().getX());
//        System.out.println(water.getPosition().getY());
        plate.draw(batch);
        plate.update();
        box.draw(batch);
        box.update();
        if (!getBox().isTouch() && Main.timeFlag){
            wallLiane.draw(batch);
        }
        wallLiane.update();

    }
    private boolean paradoxesFlag = false;
    public void level5(SpriteBatch batch){
        scene.draw(batch);
        while(i<1) {
            many.batch(batch);
            many.start();
            i++;
        }
        for (int i = 0; i < par.size(); i++){
            if (!par.get(i).isTouch()) {
                par.get(i).update();
                par.get(i).draw(batch);
            }
        }
        System.out.println(par.size());
    }
    private int i=0;

    public void  loadActors(){
        joy = new Joystick(Main.circle1,Main.circle2,new Point2D(0.15f*Main.WIDTH,0.25f*Main.HEIGHT),Main.HEIGHT/3f);
        player = new Player(Main.actor1,Main.actorFight1,new Point2D(Main.WIDTH/2f,Main.HEIGHT/2f),7,Main.WIDTH/20f,Main.HEIGHT/5f,20);
        fig = new Fight(Main.circle3,new Point2D(Main.WIDTH/1.125f,0.25f*Main.HEIGHT),Main.HEIGHT/3f);
        scene = new Scene(Main.Scene2,new Point2D(0,0), Main.HEIGHT*5.45f, Main.HEIGHT);
        scene2 = new Scene(Main.Scene1,new Point2D(0,0), Main.HEIGHT*5.45f, Main.HEIGHT);
        playButton = new PlayButton(Main.Play,new Point2D(21*Main.WIDTH/64f,Main.HEIGHT/20f),0.31f*Main.WIDTH,0.231f*Main.HEIGHT);
        name = new Name(Main.Name,new Point2D(Main.WIDTH/5f,3*Main.HEIGHT/4f),0.63f*Main.WIDTH,0.231f*Main.HEIGHT);
        start = new Start(Main.PlayOut,new Point2D(0,0),Main.WIDTH,Main.HEIGHT);
        load = new Load(Main.Load,new Point2D(0,0),Main.WIDTH,Main.HEIGHT);
        box = new Boxes(Main.Box1,new Point2D(11.7f*Main.WIDTH/10.5f,3*Main.HEIGHT/4.875f),10,Main.WIDTH/9.2f,Main.HEIGHT/4.5f);
        wallLiane = new WallLiane(Main.WallLiane1,new Point2D(Main.WIDTH/0.55f,-100),1,Main.WIDTH/20f,Main.HEIGHT*1.2f);
        plate = new Plate(Main.Plane1,new Point2D(15f*Main.WIDTH/10.5f,3*Main.HEIGHT/4.875f),10,Main.WIDTH/9.2f,Main.HEIGHT/4.5f);
        pit = new Pit(Main.Pit1,new Point2D(15f*Main.WIDTH/10.5f,0),10,Main.WIDTH/9.2f,Main.HEIGHT*1f);
        water = new Water(Main.Water1,new Point2D(14.95f*Main.WIDTH/10.5f,0),10,Main.WIDTH/9.2f,Main.HEIGHT*1f);
        time = new Time(Main.OldTime,new Point2D(15f*Main.WIDTH/10.5f,Main.HEIGHT/2.5f),7,2*Main.WIDTH/9.2f,2.25f*Main.HEIGHT/4.5f);
        paradox = new Paradoxes(Main.Paradox1,new Point2D(14.95f*Main.WIDTH/10.5f,Main.HEIGHT/4f),3,1.5f*Main.WIDTH/9.2f/2f,3*Main.HEIGHT/4.5f/2f,0);
        again = new Again(Main.AgainButton, new Point2D(19*Main.WIDTH/20f,9*Main.HEIGHT/10f), Main.HEIGHT/7f);
        timeButton = new TimeButton(Main.AgainButton, new Point2D(19*Main.WIDTH/20f,5*Main.HEIGHT/10f), Main.HEIGHT/7f);
    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer){
        for(int i = 0; i < 5; i++){
            joy.update(x,y,isDownTouch,pointer);
            fig.update(x, y, isDownTouch, pointer);
            playButton.update(x, y, isDownTouch, pointer);
            again.update(x, y, isDownTouch, pointer);
            timeButton.update(x, y, isDownTouch, pointer);
        }
    }
    public void walk(GraphicsObj a,float x,float y){
        a.walk(x,y);
    }
    public float ObjGetX(GraphicsObj a){
        Point2D b = a.getPosition();
        float d = b.getX();
        return d;
    }
    public void setAllPosition(){
        player.setDialogue1Flag();
        scene.setPosition();
        scene2.setPosition();
        box.setPosition();
        plate.setPosition();
        wallLiane.setPosition();
        pit.setPosition();
        water.setPosition();
        time.setPosition();
        player.setStopPlayer(false);
        paradox.setPosition();
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public boolean isD() {
        return d;
    }

    public void setX(Point2D a, float x){
        a.setX(x);
    }
    private boolean ThreadFlag = true;
    class ThreadLoad extends Thread{
        @Override
        public void run(){
            ThreadFlag = false;
            setA();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            d = false;
            ThreadFlag = true;
            Again.AgainFlag = false;
            again.setPointer(-1);
            Main.timeFlag = true;
            Plate.A4Flag = true;
            box.setTouch(false,1);
            box.setTouch(false,2);
            box.setTouch(false,4);

//            Player.Dialogue1Flag2 = true;
        }
    }

    private boolean stopAllFlag = false;
    class StopAll extends Thread{
        @Override
        public void run(){
            player.setStopPlayer(true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            onlySetA(-1);
            setA();
            PlayButton.play = false;
            stopAllFlag = false;
            d = true;
            Plate.A4Flag = true;
        }
    }
    private boolean ParFlag = true;
    private ArrayList<Paradoxes> par = new ArrayList<>();

    public ArrayList<Paradoxes> getPar() {
        return par;
    }

    class Many extends Thread{
        @Override
        public void run(){
            for (int i = 0; i<50; i++){
                par.add(par.size(),new Paradoxes(Main.Paradox1,paradox.position,paradox.speed,paradox.A,paradox.B,i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        private SpriteBatch batch;
        public void batch(SpriteBatch batch){
            this.batch = batch;
        }
    }
}
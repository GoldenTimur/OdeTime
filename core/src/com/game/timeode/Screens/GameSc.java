package com.game.timeode.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.timeode.Actors.Boxes;
import com.game.timeode.Actors.Pit;
import com.game.timeode.Actors.Plate;
import com.game.timeode.Actors.Player;
import com.game.timeode.Actors.WallLiane;
import com.game.timeode.Background.Load;
import com.game.timeode.Background.Name;
import com.game.timeode.Background.Start;
import com.game.timeode.GraphicsObj.GraphicsObj;
import com.game.timeode.Main;
import com.game.timeode.Objects;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.PlayButton;
import com.game.timeode.Tools.Point2D;

import com.game.timeode.Background.Scene;

public class GameSc extends Objects implements Screen  {
    Main main;
    public GameSc(Main main, Joystick joy, Fight fig, Player player, Scene scene, Scene scene2, PlayButton playButton, Name name, Start start, Load load, Boxes box, WallLiane wallLiane, Plate plate, Pit pit) {
        super(main,joy, fig, player, scene, scene2, playButton, name, start, load, box, wallLiane, plate, pit);
        this.main = main;
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
        player.setDirection(joy.getDir());
        player.update();
    }

    public static int a = 0;
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
    }
    public void level3(SpriteBatch batch){
        scene.draw(batch);
    }
    public void level4(SpriteBatch batch){

    }
    public void level5(SpriteBatch batch){

    }


    public void  loadActors(){
        joy = new Joystick(Main.circle1,Main.circle2,new Point2D(0.11f*Main.WIDTH,0.25f*Main.HEIGHT),Main.HEIGHT/3);
        player = new Player(Main.actor1,Main.actorFight1,new Point2D(Main.WIDTH/2,Main.HEIGHT/2),7,Main.WIDTH/20,Main.HEIGHT/5,20);
        fig = new Fight(Main.circle3,new Point2D(Main.WIDTH/1.125f,0.25f*Main.HEIGHT),Main.HEIGHT/3);
        scene = new Scene(Main.Scene2,new Point2D(0,0), Main.HEIGHT*5.45f, Main.HEIGHT);
        scene2 = new Scene(Main.Scene1,new Point2D(0,0), Main.HEIGHT*5.45f, Main.HEIGHT);
        playButton = new PlayButton(Main.Play,new Point2D(21*Main.WIDTH/64,Main.HEIGHT/20),0.31f*Main.WIDTH,0.231f*Main.HEIGHT);
        name = new Name(Main.Name,new Point2D(Main.WIDTH/5,3*Main.HEIGHT/4),0.63f*Main.WIDTH,0.231f*Main.HEIGHT);
        start = new Start(Main.PlayOut,new Point2D(0,0),Main.WIDTH,Main.HEIGHT);
        load = new Load(Main.Load,new Point2D(0,0),Main.WIDTH,Main.HEIGHT);
        box = new Boxes(Main.Box1,new Point2D(11.7f*Main.WIDTH/10.5f,3*Main.HEIGHT/4.875f),10,Main.WIDTH/9.2f,Main.HEIGHT/4.5f);
        wallLiane = new WallLiane(Main.WallLiane1,new Point2D(Main.WIDTH/0.5f,-100),1,Main.WIDTH/20,Main.HEIGHT*1.2f);
        plate = new Plate(Main.Plane1,new Point2D(15f*Main.WIDTH/10.5f,3*Main.HEIGHT/4.875f),10,Main.WIDTH/9.2f,Main.HEIGHT/4.5f);
        pit = new Pit(Main.Pit1,new Point2D(11.7f*Main.WIDTH/10.5f,0),10,Main.WIDTH/9.2f,Main.HEIGHT*1.2f);
    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer){
        for(int i = 0; i < 5; i++){
            joy.update(x,y,isDownTouch,pointer);
            fig.update(x, y, isDownTouch, pointer);
            playButton.update(x, y, isDownTouch, pointer);
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
        box.setPosition();
        plate.setPosition();
        wallLiane.setPosition();
        pit.setPosition();
    }

    public void setD(boolean d) {
        this.d = d;
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
        }
    }
}
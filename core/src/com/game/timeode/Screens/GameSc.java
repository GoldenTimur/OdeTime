package com.game.timeode.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.timeode.Actors.Boxes;
import com.game.timeode.Actors.Player;
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
    public GameSc(Main main, Joystick joy, Fight fig, Player player, Scene scene, PlayButton playButton, Name name, Start start, Load load, Boxes box) {
        super(joy, fig, player, scene, playButton, name, start, load, box);
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
        if (PlayButton.play){
            Main.batch.begin();
            GameRender(Main.batch);
            Main.batch.end();
        } else {
            Main.batch1.begin();
            GameRender(Main.batch1);
            Main.batch1.end();
        }


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
        box.update();
    }

    boolean d = false;
    public void GameRender(SpriteBatch batch){
        ThreadLoad threadLoad = new ThreadLoad();
        if (PlayButton.play) {
            load.draw(batch);
            if (!ThreadFlag) {
                threadLoad.start();
            }
            if (d) {
                scene.draw(batch);
                box.draw(batch);
                player.draw(batch);
                joy.draw(batch);
                fig.draw(batch);
            }
        }else {
            start.draw(batch);
            playButton.draw(batch);
            name.draw(batch);
        }
    }

    public void  loadActors(){
        joy = new Joystick(Main.circle1,Main.circle2,new Point2D(250,250),Main.HEIGHT/3);
        player = new Player(Main.actor1,Main.actorFight1,new Point2D(Main.WIDTH/2,Main.HEIGHT/2),7,Main.HEIGHT/10,Main.WIDTH/10,20);
        fig = new Fight(Main.circle3,new Point2D(1950,250),Main.HEIGHT/3);
        scene = new Scene(Main.Scene2,new Point2D(0,0), Main.HEIGHT*5, Main.HEIGHT);
        playButton = new PlayButton(Main.Play,new Point2D(21*Main.WIDTH/64,Main.HEIGHT/20),250, 700);
        name = new Name(Main.Name,new Point2D(Main.WIDTH/5,3*Main.HEIGHT/4),1400,250);
        start = new Start(Main.PlayOut,new Point2D(0,0),Main.WIDTH,Main.HEIGHT);
        load = new Load(Main.Load,new Point2D(0,0),Main.WIDTH,Main.HEIGHT);
        box = new Boxes(Main.Box1,new Point2D(7.77f*Main.WIDTH/10.5f,3*Main.HEIGHT/4.875f),10,Main.HEIGHT/4.5f,Main.WIDTH/10f);
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
    public void setX(Point2D a, float x){
        a.setX(x);
    }
    private boolean ThreadFlag = false;
    class ThreadLoad extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            d = true;
        }
    }
}
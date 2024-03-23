package com.game.timeode.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Actors.Player;
import com.game.timeode.Screens.GameSc;

public class PlayButton {
    public static boolean play = false;
    Texture SquareImg;
    Square SquareBounds;
    float ASquare, BSquare;
    private int pointer = -1;
    Point2D direction;
    boolean ThreedFlagLoad = false;

    public PlayButton(Texture simg, Point2D point, float A, float B) {
        SquareImg = simg;
        ASquare = A;
        BSquare = B;
        SquareBounds = new Square(ASquare, BSquare, point);
        direction = new Point2D(0,0);
    }

    public void draw(SpriteBatch batch){
        batch.draw(SquareImg,SquareBounds.pos.getX(),SquareBounds.pos.getY(),ASquare,BSquare);
    }

    public void update(float x, float y, boolean isDownTouch, int pointer){
        Point2D touch = new Point2D(x,y);


        if(SquareBounds.isContains(touch) && isDownTouch && this.pointer == -1 && play == false){
            this.pointer = pointer;
            play = true;

        }
        if (this.pointer != -1){
            this.pointer = -1;
        }
    }

    public boolean isThreedFlagLoad() {
        return ThreedFlagLoad;
    }

    public void setThreedFlagLoad(boolean threedFlagLoad) {
        ThreedFlagLoad = threedFlagLoad;
    }
}

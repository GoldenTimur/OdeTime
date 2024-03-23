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
//    private Point2D position;
    public Paradoxes(Texture img,Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
        Random random = new Random();
//        this.position = new Point2D(random.nextFloat(Main.WIDTH),random.nextFloat(Main.HEIGHT));
    }


    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,this.x,this.y, A, B);
    }

    @Override
    public void update() {
        setBounds(new Square(A,B,this.position));
        this.x = position.getX();
        this.y = position.getY();
        if(bounds.isContains(gameSc.getPlayer().getBounds()) && Fight.fighter && GameSc.getA()==5){
            setImg(Main.Paradox2);
            gameSc.setA(-1);
        }
    }
}

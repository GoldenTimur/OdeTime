package com.game.timeode.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.timeode.Main;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Point2D;
import com.game.timeode.Tools.Square;

public class Plate extends Actor {
    public Plate(Texture img, Point2D position, float speed, float A, float B) {
        super(img, position, speed, A, B);
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
        switch (GameSc.getA()){
            case (1):
                setImg(Main.Plane1);
                break;
            case (4):
                if(Main.timeFlag){
                    setImg(Main.Plane1);
                }else {
                    setImg(Main.Plane2);
                }

                break;
        }
        if (A4Flag && GameSc.getA()==4){
            A4Flag = false;
            setPosition(18.2f*Main.WIDTH/10.5f, Main.HEIGHT/4.875f);
        }
    }
    public static boolean A4Flag = true;
}

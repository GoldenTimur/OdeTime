package com.game.timeode.Tools;

public class Square {
    public Square(float a, float b, Point2D pos) {
        this.pos = new Point2D(pos);
        this.a = a;
        this.b = b;
    }
    float a,b;
    public Point2D pos;

    public boolean isContains(Point2D point){
        float w1 = pos.getX()+b;
        float w2 = pos.getX();
        float h1 = pos.getY()+a;
        float h2 = pos.getY();
        float p1 = point.getX();
        float p2 = point.getY();
        boolean q = false;
        if (p1<=w1 && p2<=h1 && p2>=h2 && p1>=w2){
            q = true;
        }
            return q;
    }

//    public boolean Overlaps(Circle c){
//        float dx = pos.getX() - c.pos.getX();
//        float dy = pos.getY() - c.pos.getY();
//        float dist = dx*dx+dy*dy;
//        float sumR = c.A+A;
//        return dist < sumR*sumR;
//    }

}

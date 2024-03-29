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
        float w1 = pos.getX()+a;
        float w2 = pos.getX();
        float h1 = pos.getY()+b;
        float h2 = pos.getY();
        float p1 = point.getX();
        float p2 = point.getY();
        boolean q = false;
        if (p1<=w1 && p2<=h1 && p2>=h2 && p1>=w2){
            q = true;
        }
            return q;
    }

    public Point2D getPos() {
        return pos;
    }

    public boolean isContains(Square bounds){
        Point2D point = bounds.getPos();
        float w1 = pos.getX()+a;
        float w2 = pos.getX();
        float h1 = pos.getY()+b;
        float h2 = pos.getY();
        float p1 = point.getX();
        float p2 = point.getY();
        float b1 = bounds.a;
        float b2 = bounds.b;
        boolean q = false;
        if (w2<=p1 && p1<=w1 && h2<=p2 && p2<=h1 || p1<=w2 && w2<=p1+b1 && p2<=h2 && h2<=p2+b2 ||   w2<=p1+b1 && p1+b1<=w1 && h2<=p2 && p2<=h1 || p1<=w1 && w1<=p1+b1 && p2<=h2 && h2<=p2+b2){
            q = true;
        }
        return q;
    }
    public boolean isContainsInside(Square bounds){
        Point2D point = bounds.getPos();
        float w1 = pos.getX()+a/2;
        float w2 = pos.getX();
        float h1 = pos.getY()+b/2;
        float h2 = pos.getY();
        float p1 = point.getX();
        float p2 = point.getY();
        float b1 = bounds.a/2;
        float b2 = bounds.b/2;
        boolean q = false;
        if (w2<=p1+b1 && p1+b1<=w1 && h2<=p2+b2 && p2+b2<=h1 || p1<=w1 && w1<=p1+b1*2 && p2<=h2 && h2<=p2+b2*2){
            q = true;
        }
        return q;
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }
}

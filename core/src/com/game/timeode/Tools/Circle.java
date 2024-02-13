package com.game.timeode.Tools;

public class Circle {
    public Circle(float A, Point2D pos) {
        this.pos = new Point2D(pos);
        this.A = A;
    }
    float A;
    public Point2D pos;

    public boolean isContains(Point2D point){
        float dx = pos.getX() - point.getX();
        float dy = pos.getY() - point.getY();
        return dx*dx+dy*dy <= A*A;
    }

    public boolean Overlaps(Circle c){
        float dx = pos.getX() - c.pos.getX();
        float dy = pos.getY() - c.pos.getY();
        float dist = dx*dx+dy*dy;
        float sumR = c.A+A;
        return dist < sumR*sumR;
    }

}

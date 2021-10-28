package com.px.tank;

import sun.security.util.ResourcesMgr;

import java.awt.*;

//子弹类
public class Bullet {

    private static final int SPEED = 8 ;

    private static int WIDTH = 5, HEIGHT = 5;

    private int x, y;

    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        //在tank类中，创建Tank
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        move();
    }


    private void move() {

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }
}

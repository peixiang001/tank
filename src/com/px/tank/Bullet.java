package com.px.tank;

import sun.security.util.ResourcesMgr;

import java.awt.*;

//子弹类
public class Bullet {

    private static final int SPEED = 8 ;

    private static int WIDTH = 5, HEIGHT = 5;

    private int x, y;

    private Dir dir;

    private TankFrame tf = null;

    //是否存活
    private boolean living = true;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    //画出子弹
    public void paint(Graphics g) {

        //删除子弹
        if (!living) {
            tf.bullets.remove(this);
        }
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

        //超过边界子弹子弹状态设置为false
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }
}

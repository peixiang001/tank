package com.px.tank;

import com.sun.org.apache.regexp.internal.REUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {

    private int x , y;
    //初始方向
    private  Dir dir = Dir.DOWN;
    //速度设置
    private static final int SPEED = 5;

    //是否移动
    private boolean moving = false;

    //窗口
    private TankFrame tf = null;

    public Tank() {
    }

    //将窗口TankFrame传到Tank中，拿到窗口，就可以拿到子弹
    public Tank(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public void paint(Graphics g) {
        //设置位置和大小
/*        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,40,40);
        g.setColor(c);*/
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }

        move();

    }

    private void move() {

        if (!moving) return;

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

    public void fire() {

        tf.bullets.add(new Bullet(this.x,this.y,this.dir,this.tf));

    }
}

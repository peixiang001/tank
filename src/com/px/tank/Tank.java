package com.px.tank;

import com.sun.org.apache.regexp.internal.REUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {

    //坐标位置
    private int x , y;
    //初始方向
    private  Dir dir = Dir.DOWN;
    //速度设置
    private static final int SPEED = 5;

    //是否移动
    private boolean moving = false;

    //窗口
    private TankFrame tf = null;


    private boolean living = true;

    //tank的宽高
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();


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
        if (!living) {
            tf.tanks.remove(this);
        }
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

        //计算子弹应该出现的位置，tank的位置，加上tank大小的一半，再减去子弹大小的一半，让子弹处于，tank中间？
        int bx = this.x + Tank.WIDTH/2 - Bullet.getWIDTH()/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.getHEIGHT()/2;


        tf.bullets.add(new Bullet(bx,by,this.dir,this.tf));

    }


    //消失
    public void die() {
        this.living = false;
    }
}

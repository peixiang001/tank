package com.px.tank;

import com.sun.org.apache.regexp.internal.REUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank {

    private Random random = new Random();
    //坐标位置
    private int x , y;
    //初始方向
    private  Dir dir = Dir.DOWN;
    //速度设置
    private static final int SPEED = 5;

    //是否移动
    private boolean moving = true;
    //窗口
    private TankFrame tf = null;

    private boolean living = true;
    //敌我tank,子弹区分
    private Group group = Group.GOOD;

    //tank的宽高
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();



    public Tank() {
    }

    //将窗口TankFrame传到Tank中，拿到窗口，就可以拿到子弹
    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        //设置位置和大小
/*        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,40,40);
        g.setColor(c);*/
        //!living = ! false = true 删除tank
        if (!living) {
            tf.tanks.remove(this);
        }
        switch (dir) {
            case LEFT:
                //判断是好tank还是坏tank
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);
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

        //当为敌tank时，自主发射
        if(this.group == Group.BAD && random.nextInt(100) > 95) this.fire();

        //随机方向
        if(this.group == Group.BAD && random.nextInt(100) > 95)
            //随机移动的方向
            randomDir();
    }

    private void randomDir() {
        //values将Dir变成Dir数组，取出下标位置
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {

        //计算子弹应该出现的位置，tank的位置，加上tank大小的一半，再减去子弹大小的一半，让子弹处于，tank中间？
        int bx = this.x + Tank.WIDTH/2 - Bullet.getWIDTH()/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.getHEIGHT()/2;

        tf.bullets.add(new Bullet(bx,by,this.dir,this.group,this.tf));

    }


    //消失
    public void die() {
        //设置为false，让tank消失
        this.living = false;
    }
}

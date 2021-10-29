package com.px.tank;

import sun.security.util.ResourcesMgr;

import java.awt.*;

//子弹类
public class Bullet {

    private static final int SPEED = 8 ;

    //子弹的宽高
    private static int WIDTH = ResourceMgr.bulletU.getWidth();
    private static int HEIGHT = ResourceMgr.bulletU.getHeight();

    private int x, y;

    private Dir dir;

    private TankFrame tf = null;
    //敌我tank子弹区分
    private Group group = Group.BAD;

    //是否存活
    private boolean living = true;

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }


    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int WIDTH) {
        Bullet.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        Bullet.HEIGHT = HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //画出子弹
    public void paint(Graphics g) {

        //删除子弹
        if (!living) {
            tf.bullets.remove(this);
        }
/*        Color c = g.getColor();
        g.setColor(Color.red);
        //在tank类中，创建Tank
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);*/

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }

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


    //打tank碰撞
    public void collideWith(Tank tank) {
        //判读是是否相同，是敌是友,友军就不会碰撞，直接返回
        if (this.group == tank.getGroup()) return;

        //Todo:用一个rect来记录子弹的位置
        //求子弹矩形
        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        //求tank矩形
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),tank.WIDTH,tank.HEIGHT);

        //是否相交
        if (rect1.intersects(rect2)) {
              tank.die();
              this.die();


            int ex = tank.getX()+ Tank.WIDTH/2 - Bullet.getWIDTH()/2;
            int ey = tank.getY()+ Tank.HEIGHT/2 - Bullet.getHEIGHT()/2;

            tf.explodes.add(new Explode(ex,ey,tf));
        }

    }

    private void die() {

        this.setLiving(false);
    }
}

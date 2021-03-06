package com.px.tank;

import com.sun.security.sasl.util.AbstractSaslImpl;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class TankFrame extends Frame {

     //this就是当前的TankFrame
     //自己tank
     Tank myTank = new Tank(200,200,Dir.DOWN,Group.GOOD,this);
     //爆炸图
     Explode explode = new Explode(100,100,this);
     //多个子弹
     List<Bullet> bullets = new ArrayList<>();
     //敌人
     List<Tank> tanks = new ArrayList<>();
     //多个爆炸
     List<Explode> explodes = new ArrayList<>();
     //设置游戏场景大小
     static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    //继承父类的方法
    public TankFrame() {
        //Frame图形窗口
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        //true设置可见
        setVisible(true);

        //监听键盘操作类
        this.addKeyListener(new MyKeyListener());

        //设置退出监听,图形右上角差号关闭
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    //解决游戏屏幕闪烁，双缓冲问题
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }



    //调用父类paint
    //窗口重置(关闭再打开)时自动调用，Graphics类似画笔，可画图
    @Override
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        //显示子弹数量
        g.drawString("子弹的数量：" + bullets.size(),10,60);
        //显示敌人数量
        g.drawString("敌tank的数量：" + tanks.size(),10,80);
        g.setColor(c);

        //在tank类中，创建(画出)Tank
        myTank.paint(g);

        //在bullet类中，创建(画出)bullet,使用集合可以创建多个子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        //画出地方tank
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        //碰撞,子弹打坦克
        for (int i = 0; i < bullets.size() ; i++) {
            for (int j = 0; j < tanks.size() ; j++) {
                //碰撞之后消失
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

        //爆炸
        for (int i = 0; i < explodes.size() ; i++) {
             explodes.get(i).panit(g);
        }
/*      //删除方式2，迭代器方式
        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
             Bullet b = iterator.next();
             if (!b.isLiving()) iterator.remove();
        }*/

/*        for (Bullet bullet : bullets) {
            bullet.paint(g);
        }*/


    }

    //键盘监听处理类
    class MyKeyListener extends KeyAdapter {
        //用boolean值代表键被按下true，反之抬起false,
        // 从而可以判断按键的上下左右的状态，通过状态再计算tank的移动位置
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        //按下调用
        @Override
        public void keyPressed(KeyEvent e) {
              int key = e.getKeyCode();
              //判断按的是那个方向，改为true状态
              switch (key) {
                  case KeyEvent.VK_LEFT:
                      bL = true;
                      setMainTankDir();
                      break;
                  case KeyEvent.VK_UP:
                      bU = true;
                      setMainTankDir();
                      break;
                  case KeyEvent.VK_RIGHT:
                      bR = true;
                      setMainTankDir();
                      break;
                  case KeyEvent.VK_DOWN:
                      bD = true;
                      setMainTankDir();
                      break;
                  case KeyEvent.VK_SPACE:
                      myTank.fire();
                  default:
                      System.out.println("default");
                      break;
              }

            //将被按下的方向状态传入
            //setMainTankDir();
        }

        //抬起调用
        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            //判断抬起的是哪个方向，改为false状态
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    setMainTankDir();
                    break;
                default:
                    break;
            }
        }


        //根据按键状态改变方向
        public void setMainTankDir() {
/*          Dir dir = myTank.getDir();
            if (bL) dir = Dir.LEFT;
            if (bU) dir = Dir.UP;
            if (bR) dir = Dir.RIGHT;
            if (bD) dir = Dir.DOWN;*/
            //当都为false时说明没有方向传入
            if (!bL && !bU && !bR && !bD){
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }

        }
    }

}

package com.px.tank;

import com.sun.security.sasl.util.AbstractSaslImpl;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.BlockingQueue;

public class TankFrame extends Frame {

    int x = 200 ;
    int y = 200 ;

    //继承父类的方法
    public TankFrame() {
        //Frame图形窗口
        setSize(800, 600);
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


    //调用父类paint
    //窗口重置(关闭再打开)时自动调用，Graphics类似画笔，可画图
    @Override
    public void paint(Graphics g) {
        //设置位置和大小
        g.fillRect(x,y,40,40);

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
              //判断按的是上下左右那个键
              switch (key) {
                  case KeyEvent.VK_LEFT:
                      bL = true;
                      x -=10;
                      break;
                  case KeyEvent.VK_UP:
                      bU = true;
                      y -= 10;
                      break;
                  case KeyEvent.VK_RIGHT:
                      bR = true;
                      x += 10;
                      break;
                  case KeyEvent.VK_DOWN:
                      bD = true;
                      y += 10;
                      break;
                  default:
                      System.out.println("default");
                      break;
              }
        }

        //抬起调用
        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            //判断按的是上下左右那个键
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;

                    break;
                case KeyEvent.VK_UP:
                    bU = false;

                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;

                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;

                    break;
                default:

                    break;
            }
        }
    }

}

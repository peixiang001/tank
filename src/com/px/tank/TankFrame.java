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

        //设置退出,图形右上角差号关闭
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
        System.out.println("paint");
        //设置位置和大小
        g.fillRect(x,y,40,40);
        x +=10;
        y += 10;
    }


    //键盘监听处理类
    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        //按下调用
        @Override
        public void keyPressed(KeyEvent e) {
           //repaint();//会调用paint
        }

        //抬起调用
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
        }
    }

}

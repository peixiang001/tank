package com.px.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

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

    //窗口重置时自动调用，Graphics g类似画笔，可画图
    @Override
    public void paint(Graphics g) {
        g.fillRect(200,200,40,40);
    }
}

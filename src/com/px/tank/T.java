package com.px.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {
    public static void main(String[] args) {
        //Frame图形窗口
        Frame f = new Frame();
        f.setSize(800,600);
        f.setResizable(false);
        f.setTitle("tank war");
        //true设置可见
        f.setVisible(true);

        //设置退出
        f.addWindowFocusListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


    }
}

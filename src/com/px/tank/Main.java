package com.px.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        //repaint会调用paint，窗口会自己重置，然后就会移动，改变位置
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }

}

package com.px.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();


        //敌方tank
        for (int i = 0; i < 5 ; i++) {
            tf.tanks.add(new Tank(50 +i*50,200,Dir.DOWN,Group.BAD,tf));
        }

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        //repaint会调用paint，窗口会自己重置刷新
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }

}

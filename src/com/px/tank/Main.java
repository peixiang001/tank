package com.px.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        //repaint调用paint，窗口重置，然后就会自己移动
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }

}

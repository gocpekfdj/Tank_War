package Tank_Big_War;

import javax.swing.*;
public class ZbjTankgame01 extends JFrame {
    MyPanel m = null;
    public static void main(String[]args){
        new ZbjTankgame01();

    }
    public ZbjTankgame01(){
        m = new MyPanel();
        new Thread(m).start();
        this.add(m);
        this.setSize(1000,750);
        //窗口可以监听键盘事件
        this.addKeyListener(m);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}

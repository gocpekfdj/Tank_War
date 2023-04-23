package Tank_Big_War;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
@SuppressWarnings({"all"})
//坦克大战的绘图区
//KeyListener是监听器，可以监听键盘上发生的事
public class MyPanel extends JPanel implements KeyListener,Runnable{
    Death_Knight death_knight = null;
    Vector<Enmy_Tank> V_enmy = new Vector<>();


    int enmy_size = 3;
    Vector<Bomb> bombs = new Vector();
    //
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;
    Image image5 = null;
    Image image6 = null;
    Image image7 = null;
    public MyPanel(){
        death_knight = new Death_Knight(100,100);

        for (int i = 0; i <enmy_size ; i++) {
            Enmy_Tank enmy =  new Enmy_Tank((i+1)*100,0,2);
            new Thread(enmy).start();
            Shot shot = new Shot(enmy.getX()+20,enmy.getY()+70,enmy.getDirect());
            enmy.shots.add(shot);
            new Thread(shot).start();
            V_enmy.add(enmy);

        }
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/Bomb1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/im2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/Bomb3.png"));
        image4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/im4.png"));
        image5 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/Bomb5.png"));
        image6 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/im6.png"));
        image7 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/im7.png"));
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.fillRect(0,0,1000,750);

        //画坦克---方法
//        for (int i = 0; i <10 ; i++) {
//            Draw_tank(death_knight.getX()+(i+1)*100,death_knight.getY(),g,0,0);
//        }
        for (int i = 0; i <death_knight.shots.size(); i++) {
            Shot shot = death_knight.shots.get(i);
            if(shot!=null&&shot.flag){
                g.fillOval(shot.x,death_knight.shot.y,6,6);
            }
            else
            {
                death_knight.shots.remove(shot);
            }
        }

        for(int i =0;i<bombs.size();i++){
            Bomb bomb = bombs.get(i);
            if(bomb.life>=12&&bomb.life<=14)
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            else if(bomb.life>=10)
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            else if(bomb.life>=8)
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            else if(bomb.life>=6)
                g.drawImage(image4,bomb.x,bomb.y,60,60,this);
            else if(bomb.life>=4)
                g.drawImage(image5,bomb.x,bomb.y,60,60,this);
            else if(bomb.life>=2)
                g.drawImage(image6,bomb.x,bomb.y,60,60,this);
            else if(bomb.life>0)
                g.drawImage(image7,bomb.x,bomb.y,60,60,this);
            bomb.lifeDown();
            if(bomb.life==0){
                bombs.remove(bomb);
            }

        }
        Draw_tank(death_knight.getX(),death_knight.getY(),g,death_knight.getDirect(),0);
        for (int i = 0; i <V_enmy.size() ; i++) {
            Enmy_Tank e = V_enmy.get(i);
            if(e.flag)
            {
                Draw_tank(e.getX(),e.getY(),g,e.getDirect(),1);
            }
            else
            {
                V_enmy.remove(e);
            }
            for(int j = 0;j < e.shots.size();j++){
                Shot shot = e.shots.get(j);
                if(shot.flag){
                    g.fillOval(shot.x,shot.y,6,6);
                }else
                {
                    e.shots.remove(shot);
                }
            }
        }
        }

    //x 坦克的左上角x坐标
    //y 坦克的左上角y坐标
    //g 画笔
    //direct 坦克方向
    //type   坦克类型
    public void Draw_tank(int x,int y,Graphics g,int direct,int type){
        switch (type){
            case 0://己方坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌方坦克
                g.setColor(Color.red);
                break;
        }
        switch (direct){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);//左轮
                g.fill3DRect(x+30,y,10,60,false);//右轮
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y-10,x+20,y+30);
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);//左轮
                g.fill3DRect(x,y+30,60,10,false);//右轮
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+80,y+20);
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);//左轮
                g.fill3DRect(x+30,y,10,60,false);//右轮
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+90,x+20,y+30);
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);//左轮
                g.fill3DRect(x,y+30,60,10,false);//右轮
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x-20,y+20);

                break;
                default:

        }
    }




    public  void hitTank(Shot shot,Enmy_Tank enmy_tank){
        if(shot.x>enmy_tank.getX()&&shot.x<enmy_tank.getX()+40
                &&shot.y>enmy_tank.getY()&&shot.y<enmy_tank.getY()+60)
        {
            shot.flag = false;
            enmy_tank.flag = false;
            Bomb bomb = new Bomb(enmy_tank.getX(), enmy_tank.getY());
            bombs.add(bomb);
        }

    }
//有字符输出时，该方法被触发
    @Override
    public void keyTyped(KeyEvent e) {

    }
//当按下某个键时，该方法被触发
    @Override
    public void keyPressed(KeyEvent e) {
       System.out.println((char)e.getKeyCode()+" 被按下了");
        if(e.getKeyCode() == KeyEvent.VK_S){

            death_knight.setDirect(2);
            if(death_knight.getY()+80<750)
            death_knight.MoveDown();
        }
        else if(e.getKeyCode() == KeyEvent.VK_W){
            death_knight.setDirect(0);
            if(death_knight.getY()-20>0)
            death_knight.MoveUp();
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            death_knight.setDirect(3);
            if(death_knight.getX()-20>0)
            death_knight.MoveLegft();
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            death_knight.setDirect(1);
            if(death_knight.getX()+80<1000)
            death_knight.MoveRight();
        }
        else if(e.getKeyCode() == KeyEvent.VK_J){
            //if(death_knight.shot==null||!death_knight.shot.flag)
            death_knight.shot_();
        }
        this.repaint();

    }
//当某个键松开时，该方法触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(20);
            }catch (InterruptedException e){

            }
            if(death_knight.shot!=null&&death_knight.shot.flag){
                for (int i = 0; i < V_enmy.size(); i++) {

                    Enmy_Tank enmy_tank = V_enmy.get(i);

                    hitTank(death_knight.shot,enmy_tank);

                }
            }
            this.repaint();
        }

    }
}

package Tank_Big_War;

import drawing.Tank_;

import java.util.Random;
import java.util.Vector;

public class Enmy_Tank extends tank implements Runnable{
    Vector<Shot> shots = new Vector();

    boolean flag = true;
    public Enmy_Tank(int x,int y,int direct) {
        super(x,y);
        setDirect(direct);
    }

    @Override
    public void run() {
        while(true){
            setDirect((new Random()).nextInt(3));
            switch (getDirect()){
                case 0 :
                    for(int i =0 ;i<30;i++)
                    {
                        if(getY()-20>0)
                        MoveUp();
                        try{
                            Thread.sleep(50);

                        }catch (InterruptedException e){

                        }

                    }
                    break;
                case 1:
                    for(int i =0 ;i<30;i++)
                    {
                        if(getX()+50<1000)
                        MoveRight();
                        try{
                            Thread.sleep(50);

                        }catch (InterruptedException e){

                        }

                    }

                    break;
                case 2:
                    for(int i =0 ;i<30;i++)
                    {
                        if(getY()+50<750)
                        MoveDown();
                        try{
                            Thread.sleep(50);

                        }catch (InterruptedException e){

                        }

                    }

                    break;
                case 3:
                    for(int i =0 ;i<30;i++)
                    {
                        if(getX()>0)
                        MoveRight();
                        try{
                            Thread.sleep(50);

                        }catch (InterruptedException e){

                        }

                    }

                    break;
            }

            if(flag==false){
                break;
            }
        }
    }
}


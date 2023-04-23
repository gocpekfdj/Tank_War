package Tank_Big_War;

import java.util.Vector;

public class Death_Knight extends tank{
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    public Death_Knight(int x,int y){
        super(x,y);
    }
    public void shot_(){
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX() + 20, getY() - 10, 0);
                break;
            case 1:
                shot = new Shot(getX() + 70, getY()+ 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() +70, 2);
                break;
            case 3:
                shot = new Shot(getX() -10, getY() +20, 3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }
    public Shot Ret_t(){
        return shot;
    }
}

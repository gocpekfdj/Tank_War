package Tank_Big_War;

public class Bomb {
    int x;
    int y;
    int life = 14;//生命周期
    boolean flag = true;

    public Bomb(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void lifeDown(){
        if(life>0)
        {
            life--;
        }
        else
        {
            flag = false;
        }
    }
}

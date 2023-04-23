package Tank_Big_War;

public class tank {
    private int x;
    private int y;
    private int direct;//


    public void MoveUp(){
        y-=3;
    }
    public void MoveDown(){
        y+=5;
    }
    public void MoveRight(){
        x+=3;
    }
    public void MoveLegft(){
        x-=3;
    }
    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public tank(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

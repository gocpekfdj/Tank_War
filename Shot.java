package Tank_Big_War;

public class Shot implements Runnable {
    int x;
    int y;
    int direct;
    boolean flag = true;
    public Shot(){

    }
    public Shot(int x,int y,int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    int speed = 10;

    @Override
    public void run() {
        while(true){
            switch (direct){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;

            }
            try{
                Thread.sleep(50);
            }catch (InterruptedException e){

            }
            System.out.println("x = "+x+",  y = "+y);
            if(!(x>0&&x<1000&&y>0&&y<750&&flag)){
                flag = false;
                break;
            }
        }
    }
}

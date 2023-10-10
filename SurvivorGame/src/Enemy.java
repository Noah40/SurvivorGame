import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {

    float distance, diffx, diffy;
    private int hp = 100;
    private Handler handler;

    public Enemy(float x, float y, ID id,Handler handler) {
        super(x, y, id);

        this.handler = handler;
        width = 32;
        height = 32;
    }

    @Override
    public void tick() {




        x+=velx;
        y+=vely;
        for (int i =0;i<handler.object.size();i++){
            GameObject tempobject = handler.object.get(i);

                if(tempobject.getId() == ID.PLAYER){
                    diffx = x-tempobject.getX();
                    diffy = y-tempobject.getY();
                    distance = (float)Math.sqrt((x-tempobject.getX())*(x-tempobject.getX())+(y-tempobject.getY())*(y-tempobject.getY()));

                }
                if(tempobject.getId() == ID.ARROW){
                    if(getBounds().intersects(tempobject.getBounds())){
                        handler.removeObject(tempobject);
                        Arrow temparrow = (Arrow) tempobject;
                        hp-= temparrow.getDamage();

                    }

                }
        }
        velx=((-1/distance)*diffx);
        vely=((-1/distance)*diffy);
        if(diffx>(Game.WIDTH/2)+50||diffy>(Game.HEIGHT/2)+50){
            handler.removeObject(this);
        }
        if(hp <= 0){
            Random random = new Random();
            int n = random.nextInt(10)+1;
            if(n>5){
                handler.addObject(new Exp(this.x,this.y,ID.EXP,handler));
            }
            handler.removeObject(this);

        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        if(hp!= 100){
            g.setColor(Color.MAGENTA);
        }

        g.fillRect((int) x, (int) y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}

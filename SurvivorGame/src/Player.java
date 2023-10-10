import java.awt.*;

public class Player extends GameObject{
    int timer = 0;
    int closest = 0;
    private Handler handler;
    private Camera cam;
    private HUD hud;
    private float area_magnifier = 1;
    private int arrow_cooldown_length = 60;
    private int arrow_cooldown_timer = 0;
    private boolean arrow_cooldown = false;
    public Player(float x, float y, ID id,Handler handler,HUD hud,Camera cam) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.cam = cam;


        width = 32;
        height=32;
    }

    private float MAX_SPEED = 5f;


    @Override
    public void tick() {
        if(arrow_cooldown_length<= 5){

            arrow_cooldown_length = 5;
        }
        if(vely >MAX_SPEED){
            vely = MAX_SPEED;
        }
        if(velx >MAX_SPEED){
            velx = MAX_SPEED;
        }
        collision();

        x+=velx;
        y+=vely;

        for (int i = 0;i<handler.object.size();i++){
            GameObject tempobject = handler.object.get(i);
            if(tempobject.getId()==ID.ENEMY){
               float distance = (float)Math.sqrt((x-tempobject.getX())*(x-tempobject.getX())+(y-tempobject.getY())*(y-tempobject.getY()));
               if(distance<200){

                   int mx = (int)(tempobject.getX()+16);
                   int my = (int)(tempobject.getY()+16);
                   if(!arrow_cooldown) {
                       handler.addObject(new Arrow(this.getX() + 16, this.getY() + 32, ID.ARROW, handler, mx, my, (int) (5*area_magnifier), (int) (5*area_magnifier),Game.arrow, (int) tempobject.getX(), (int) tempobject.getY()));
                       arrow_cooldown = true;
                   }
               }
            }


        }
        if(arrow_cooldown){
            arrow_cooldown_timer++;
            if(arrow_cooldown_timer>=arrow_cooldown_length){
                arrow_cooldown = false;
                arrow_cooldown_timer = 0;
            }
        }
    }

    public float getArea_magnifier() {
        return area_magnifier;
    }

    public void setArea_magnifier(float area_magnifier) {
        this.area_magnifier = area_magnifier;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y,width,height);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsBottom());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,width,height);
    }

    public Rectangle getBoundsTop() {

        return new Rectangle((int) x+(width/2)-((width/2)/2), (int) y,width/2,height/2);

    }

    public Rectangle getBoundsBottom() {
        return new Rectangle((int) x+(width/2)-((width/2)/2), (int) y+(height/2),width/2,height/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) x+width-5, (int) y+4,5,height-8);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y+4,5,height-8);
    }

    private void collision(){
        for(int i = 0; i<handler.object.size();i++){
            GameObject temp_object = handler.object.get(i);

            if(temp_object.getId()==ID.BLOCK){
                if(getBoundsTop().intersects(temp_object.getBounds())){
                    vely = 0;
                    y = temp_object.getY()+ temp_object.getHeight();


                }
                if(getBoundsBottom().intersects(temp_object.getBounds())){
                    vely = 0;
                    y = temp_object.getY()-height;



                }

                if(getBoundsRight().intersects(temp_object.getBounds())){
                    x = temp_object.getX()-temp_object.getWidth();
                    velx = 0;



                }
                if(getBoundsLeft().intersects(temp_object.getBounds())){
                    x = temp_object.getX()+temp_object.getWidth();
                    velx = 0;



                }




            }
            if(temp_object.getId()==ID.EXP){
                if(getBounds().intersects(temp_object.getBounds())){
                    handler.removeObject(temp_object);
                    hud.setXP((int) (hud.getXP()+1));


                }

            }

        }


    }

    public int getArrow_cooldown_length() {
        return arrow_cooldown_length;
    }

    public void setArrow_cooldown_length(int arrow_cooldown_length) {
        this.arrow_cooldown_length = arrow_cooldown_length;
    }
}

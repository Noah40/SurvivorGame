import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Arrow extends GameObject{

    private Handler handler;
    int mx,my;
    AffineTransform trans = new AffineTransform();
    int speed= 20;
    float initialx;
    float angle;
    int i = 0;
    float initialy;
    private int damage = 50;
    int targetx,targety;
    boolean rotated = false;
    BufferedImage img;
    AffineTransform identity = new AffineTransform();

    public Arrow(float x, float y, ID id,Handler handler,int mx,int my,int width,int height,BufferedImage img,int targetx,int targety) {
        super(x, y, id);
        this.mx = mx;
        this.my = my;
        this.initialx =  x;
        this.initialy =  y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        this.img = img;

        this.targetx = targetx;
        this.targety = targety;



    }

    @Override
    public void tick() {

        trans.setTransform(identity);
        if(!rotated) {
            //Point imageLocation = new Point((int) x, (int) y);
            float deltaY = y - (targety - 16);
            float deltaX = x - (targetx - 16);
            angle = (float) Math.atan2(deltaX, deltaY);
            angle = (float) Math.toRadians(Math.toDegrees(angle) + 90);
            if (angle < 0) {
                angle += (float) (Math.PI * 2);
            }
            rotated = true;
        }
        trans.translate(x-16,y-16);
        trans.scale(((double) width /img.getWidth())*10, ((double) height /img.getHeight())*10);

            trans.rotate(angle);




        velx = (mx-initialx)/speed;
        vely = (my-initialy)/speed;
        x+=velx;
        y+=vely;
    }

    @Override
    public void render(Graphics g) {


        Graphics2D g2d = (Graphics2D)g;








        g2d.drawImage(img, trans, null);


    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,width,height);
    }
}

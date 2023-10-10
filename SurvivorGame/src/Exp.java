import java.awt.*;

public class Exp extends GameObject{

    private Handler handler;
    public Exp(float x, float y, ID id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
        width = 18;
        height = 20;
    }

    @Override
    public void tick() {

    }

    private void collision(){




    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) x, (int) y,width,height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,width,height);
    }
}

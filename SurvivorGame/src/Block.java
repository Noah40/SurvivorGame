import java.awt.*;

public class Block extends GameObject{

    float x, y;
    ID id;
    public Block(float x, float y, ID id) {
        super(x, y, id);
        this.x = x;
        this.y = y;
        this.id = id;
        width = 32;
        height = 32;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) x, (int) y,width,height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,width,height);
    }
}

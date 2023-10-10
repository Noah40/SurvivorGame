import java.awt.*;

public class Button extends GameObject{

    Color color;
    Color text_color;
    String txt;
    Handler handler;
    WeaponEffect effect;



    public Button(float x, float y, ID id, Color color, Color text_color, int width, int height, String txt, Handler handler, WeaponEffect effect) {
        super(x, y, id);
        this.color = color;
        this.text_color = text_color;
        this.width = width;
        this.height = height;
        this.txt = txt;
        this.id = id;
        this.handler = handler;
        this.effect = effect;
        this.x = x-20;
        this.y = y-10;
    }

    @Override
    public void tick() {
        if(!Game.lvlup){
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y,width,height);
        g.setColor(text_color);
        g.setFont(new Font("TimesRoman",Font.PLAIN,15));
        g.drawString(txt, (int) x+5, (int) (y+(height/2)));
    }


    public WeaponEffect getEffect() {
        return effect;
    }

    public void setEffect(WeaponEffect effect) {
        this.effect = effect;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y,this.width,this.height);
    }
}

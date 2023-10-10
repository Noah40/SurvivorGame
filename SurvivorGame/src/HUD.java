import java.awt.*;

public class HUD {

    private float tempamount =0;
    private float nextAmount = 5;
    private float currentAmount =0;
    private float lvlProgress = 0;
    private int lvl = 1;

    public void tick(Handler handler,Camera camera){
       // System.out.println(currentAmount);
        if(lvl== 1) {
            tempamount = currentAmount;
        }

        lvlProgress = tempamount/nextAmount;
        if (tempamount>=nextAmount){
            nextAmount+=5;
            lvl+=1;
            System.out.println(lvl);
            tempamount = 0;
            Game.lvlup = true;
            handler.addObject(new Button(400, 300,ID.BUTTON,Color.GREEN,Color.BLACK,100,50,"Cooldown",handler,WeaponEffect.COOLDOWN));
            handler.addObject(new Button(400, 410,ID.BUTTON,Color.GREEN,Color.BLACK,100,50,"Area",handler,WeaponEffect.AREA));
            handler.addObject(new Button(400, 190,ID.BUTTON,Color.GREEN,Color.BLACK,100,50,"Damage",handler,WeaponEffect.DAMAGE));

        }


    }

    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect(1,2,Game.WIDTH-2,20);
        g.setColor(Color.BLUE);
        g.fillRect(2,3, (int) ((lvlProgress)*(Game.WIDTH-3)),19);
        if (Game.lvlup){
            g.setColor(Color.green);


        }






    }

    public float getXP(){
        return tempamount;

    }

    public void setXP(int amount){
        tempamount = amount;
        currentAmount = amount;


    }
}

import com.sun.management.GarbageCollectionNotificationInfo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    Handler handler;
    Player p1;
    Camera camera;
    public MouseInput(Handler handler,Camera camera,Player p1){
        this.handler = handler;
        this.camera = camera;
        this.p1 = p1;
    }
    @Override
    public void mousePressed(MouseEvent e) {

        for(int i = 0;i<handler.object.size();i++){
            GameObject tempobject = handler.object.get(i);
            if(tempobject.getId()==ID.BUTTON){
                if(mouseOver(e.getX(),e.getY(),(int)tempobject.getX(), (int) tempobject.getY(),tempobject.getWidth(),tempobject.getHeight())) {
                    if (((Button) tempobject).getEffect()==WeaponEffect.COOLDOWN) {
                        p1.setArrow_cooldown_length(p1.getArrow_cooldown_length() - 10);
                    }
                    if (((Button) tempobject).getEffect()==WeaponEffect.AREA) {
                        p1.setArea_magnifier((float) (p1.getArea_magnifier()+0.1));
                    }

                        Game.lvlup = false;
                        System.out.println("Should be working");

                }

            }


        }


    }


    private boolean mouseOver(int mx,int my,int x,int y,int width,int height){
        if(mx>x&&mx<x+width){
            if (my>y&&my<y+height){
                return true;
            } else{
                return false;
            }


        } else{
            return false;
        }



    }



}

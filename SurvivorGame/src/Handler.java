import java.awt.*;
import java.util.ArrayList;

public class Handler {

    public ArrayList<GameObject> object = new ArrayList<GameObject>();
    public void tick(){
        for(int i = 0;i<object.size();i++){
            GameObject temp_object = object.get(i);
            if(Game.lvlup&&temp_object.getId() == ID.BUTTON) {
                temp_object.tick();
            } else if(!Game.lvlup){
                temp_object.tick();

            }

        }
    }

    public void render(Graphics g){
        for(int i = 0;i<object.size();i++){
            GameObject tempObject = object.get(i);
            if(tempObject.getId()!=ID.BUTTON) {
                tempObject.render(g);
            }
        }

    }



    public void addObject(GameObject object){
    this.object.add(object);

    }
    public void removeObject(GameObject object){
        this.object.remove(object);

    }

    public void createLevel(){
        for(int i = 0;i<Game.WIDTH;i++){
            object.add(new Block(i,Game.HEIGHT-32,ID.BLOCK));

        }
        for(int i = 96;i<Game.WIDTH-(96*2);i++){
            object.add(new Block(i,Game.HEIGHT-128,ID.BLOCK));

        }

    }

}

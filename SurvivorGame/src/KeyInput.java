import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;

    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0;i<handler.object.size();i++){
            GameObject temp_object = handler.object.get(i);

            if(temp_object.getId() == ID.PLAYER){
                if(key == KeyEvent.VK_A){
                    temp_object.setVelx(-5);

                }

                if(key == KeyEvent.VK_D){
                    temp_object.setVelx(5);

                }

                if(key == KeyEvent.VK_W){
                    temp_object.setVely(-5);

                }

                if(key == KeyEvent.VK_S){
                    temp_object.setVely(5);

                }

                if(key == KeyEvent.VK_ESCAPE){
                    System.exit(0);

                }

            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();

        for(int i = 0;i<handler.object.size();i++){
            GameObject temp_object = handler.object.get(i);

            if(temp_object.getId() == ID.PLAYER){
                if(key == KeyEvent.VK_A){

                    temp_object.setVelx(0);

                }

                if(key == KeyEvent.VK_D){
                    temp_object.setVelx(0);

                }

                if(key == KeyEvent.VK_W){
                    temp_object.setVely(0);

                }

                if(key == KeyEvent.VK_S){
                    temp_object.setVely(0);

                }



            }

        }


    }
}

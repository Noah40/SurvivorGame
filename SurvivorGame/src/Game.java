import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    private Handler handler;
    int timer =0;

    private Camera camera;
    private Player p1;
    private HUD hud;
    public boolean paused = false;

    private Window window;
    private BufferedImage level;

    public static BufferedImage arrow;





    public static final int WIDTH = 800,HEIGHT = 600;
    private boolean running = false;
    public static boolean lvlup = false;
    public static final String TITLE = "Survivor Game";


    private Thread thread;

    public static void main(String[] args) {

        new Game();

    }

    public Game(){
        window = new Window(TITLE, WIDTH,HEIGHT,this);

    }

    public synchronized void start(){
        if(running){
            return;
        }

        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        thread = new Thread(this);
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void init(){
            handler = new Handler();
            camera = new Camera(0,0);
            BufferedImageLoader loader = new BufferedImageLoader();
            hud = new HUD();
            p1 = new Player((float) Game.WIDTH /2,50,ID.PLAYER,handler,hud,camera);
            handler.addObject(p1);

            level = loader.loadImage("/res/level2.png");
            arrow = loader.loadImage("/res/arrow.png");

            addKeyListener(new KeyInput(handler));
            addMouseListener(new MouseInput(handler,camera,p1));
            loadLevel(level);

    }

    private void tick(){

        //timer events
        if(!lvlup) {
            if (timer % (60 * 5) == 0) {
                handler.addObject(new Exp(100, 100, ID.EXP, handler));
                System.out.println("Xp Generated");
            }
            if (timer % (60 * 10) == 0) {
                for (int i = 0; i < 5; i++) {

                    handler.addObject(new Enemy(p1.getX() + (i * ((float) Game.WIDTH / 5)) - (float) Game.WIDTH / 2, p1.getY() - (Game.HEIGHT / 2), ID.ENEMY, handler));
                }


            }
        }
        if(!paused ) {
            handler.tick();
        }
        hud.tick(handler,camera);

        for (int i =0;i<handler.object.size();i++) {
            if (handler.object.get(i).getId() == ID.PLAYER) {
                camera.tick(handler.object.get(i));
            }

        }





        timer++;

    }

    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0,0,WIDTH,HEIGHT);
        g.fillRect(0,0,WIDTH,HEIGHT);
        Graphics2D g2d = (Graphics2D)g;
        hud.render(g);
        for(int i=0;i<handler.object.size();i++){
            GameObject tempobject = handler.object.get(i);
            if(tempobject.getId()==ID.BUTTON){
                tempobject.render(g);
            }
        }
        g2d.translate(camera.getX(),camera.getY());

        handler.render(g);

        g2d.translate(-camera.getX(),-camera.getY());


        g.dispose();
        bs.show();

    }


    private void loadLevel(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        for(int yy = 0;yy<height;yy++) {
            for (int xx = 0; xx < width; xx++) {
                int pixel = img.getRGB(xx,yy);
                int red = (pixel>>16)&0xff;
                int green = (pixel>>8)& 0xff;
                int blue = (pixel)&0xff;

                if(red == 255 && green == 0&&blue ==0){
                    handler.addObject(new Block(xx*32,yy*32,ID.BLOCK));

                }
                if(red == 0&&blue ==255&&green ==0){
                    handler.addObject(new Exp(xx*32,yy*32,ID.EXP,handler));
                }

            }
        }



    }
    @Override
    public void run() {
        init();
        requestFocus();
        long last_time = System.nanoTime();
        double amount_of_ticks = 60.0;
        double ns = 1000000000/amount_of_ticks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now -last_time)/ns;
            last_time = now;
            while(delta >= 1){
                tick();
                updates++;
                delta --;
            }

            render();
            frames++;
            if(System.currentTimeMillis()-timer > 1000){
                timer += 1000;
                window.getFrame().setTitle(    "FPS: "+frames+", Updates: "+updates);
                frames = 0;
                updates = 0;


            }

        }

    }
}
import javax.swing.*;
import java.awt.*;

public class Window {
        private String title;
        private JFrame frame = new JFrame();
    public Window(String title,int width,int height,Game game){


        game.setPreferredSize(new Dimension(width, height));
        game.setMaximumSize(new Dimension(width, height));
        game.setMinimumSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle(title);
        frame.setVisible(true);
        game.start();
    }

    public JFrame getFrame(){



        return frame;
    }
}

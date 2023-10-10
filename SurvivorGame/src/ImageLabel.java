import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public final class ImageLabel extends JLabel {


   public ImageLabel(BufferedImage img){
       bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
       Graphics2D g2d = bi.createGraphics();
       g2d.setColor(Color.RED);
       g2d.drawLine(25, 0, 25, 50);
       g2d.drawLine(25, 0, 0, 12);
       g2d.drawLine(25, 0, 50, 12);
       g2d.dispose();


    }
    private float angle = 0.0f; // in radians
    private Point imageLocation = new Point();
    private File imageFile = null;
    private Dimension imageSize = new Dimension(50, 50);
    private BufferedImage bi;

    private BufferedImage resizeImage(BufferedImage originalImage, int img_width, int img_height) {
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();

        return resizedImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bi == null) {
            return;
        }
        imageLocation = new Point(getWidth() / 2 - bi.getWidth() / 2, getHeight() / 2 - bi.getHeight() / 2);
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(angle, imageLocation.x + bi.getWidth() / 2, imageLocation.y + bi.getHeight() / 2);
        g2.drawImage(bi, imageLocation.x, imageLocation.y, null);
    }

    public void rotateImage(float angle) { // rotate the image to specific angle
        this.angle = (float) Math.toRadians(angle);
        repaint();
    }

    public void pointImageToPoint(Point target) {
        calculateAngle(target);
        repaint();
    }

    private void calculateAngle(Point target) {
        // calculate the angle from the center of the image
        float deltaY = target.y - (imageLocation.y + bi.getHeight() / 2);
        float deltaX = target.x - (imageLocation.x + bi.getWidth() / 2);
        angle = (float) Math.atan2(deltaY, deltaX);
        if (angle < 0) {
            angle += (Math.PI * 2);
        }
    }
}
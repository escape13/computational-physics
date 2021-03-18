import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;

import java.awt.*;

public class WorldRectangle implements Drawable {

    double left, top;
    double width, height;

    public WorldRectangle(double left, double top, double width, double height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public void draw(DrawingPanel panel, Graphics g) {
        g.setColor(Color.RED);
        int leftPixels = panel.xToPix(left);
        int topPixels = panel.yToPix(top);
        int widthPixels = (int) (panel.getXPixPerUnit() * width);
        int heightPixels = (int) (panel.getYPixPerUnit() * height);
        g.fillRect(leftPixels, topPixels, widthPixels, heightPixels);
    }
}

import org.opensourcephysics.controls.*;
import org.opensourcephysics.frames.*;



public class DrawingApp extends AbstractCalculation {

    DisplayFrame frame = new DisplayFrame("x", "y", "Rectangle");

    public DrawingApp() {

        frame.setPreferredMinMax(0, 10, 0, 10);
        frame.setBounds(300, 0, 600, 600);
    }

    public void calculate() {

        double left = control.getDouble("x");
        double top = control.getDouble("y");
        double width = control.getDouble("width");
        double height = control.getDouble("height");

        WorldRectangle rect = new WorldRectangle(left, top, width, height);
        frame.addDrawable(rect);

    }

    public void reset() {
        frame.clearDrawables();
        control.setValue("x", 0);
        control.setValue("y", 10);
        control.setValue("width", 10);
        control.setValue("height", 10);
    }

    public static void main(String[] args) {
        CalculationControl.createApp(new DrawingApp());
    }
}

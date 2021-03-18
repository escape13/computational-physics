import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

public class App extends AbstractSimulation {

    PlotFrame frame = new PlotFrame("x", "y", "Helium atom");
    Helium helium = new Helium();

    public void initialize() {
        double x1 = control.getDouble("x1");
        double vx1 = control.getDouble("vx1");
        double y1 = control.getDouble("y1");
        double vy1 = control.getDouble("vy1");
        double x2 = control.getDouble("x2");
        double vx2 = control.getDouble("vx2");
        double y2 = control.getDouble("y2");
        double vy2 = control.getDouble("vy2");

        helium.setState(new double[] {x1, vx1, y1, vy1, x2, vx2, y2, vy2, 0});

        stepsPerDisplay = 10;

        frame.setSquareAspect(true);
        frame.setBounds(0, 0, 500, 500);
        frame.setPreferredMinMax(-4, 4, -4, 4);
        frame.addDrawable(helium);
    }

    public void doStep() {
        helium.step();
    }

    public void reset() {
        control.setValue("x1", 3);
        control.setValue("vx1", 0);
        control.setValue("y1", 0);
        control.setValue("vy1", 0.4);
        control.setValue("x2", 1);
        control.setValue("vx2", 0);
        control.setValue("y2", 0);
        control.setValue("vy2", -1);
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new App());
    }
}

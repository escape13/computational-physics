import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.DisplayFrame;

public class App extends AbstractSimulation {
    DisplayFrame frame = new DisplayFrame("x", "y", "Planetary motion");
    Planet planet = new Planet();
    public void initialize() {

        double dt = control.getDouble("dt");

        stepsPerDisplay = (int) (0.1 / dt);
        planet.setStepSize(dt);

        double x1 = control.getDouble("x1");
        double vx1 = control.getDouble("vx1");
        double y1 = control.getDouble("y1");
        double vy1 = control.getDouble("vy1");
        double x2 = control.getDouble("x2");
        double vx2 = control.getDouble("vx2");
        double y2 = control.getDouble("y2");
        double vy2 = control.getDouble("vy2");
        planet.initialize(new double[] {x1 ,vx1, y1, vy1, x2, vx2, y2, vy2, 0});

        frame.setPreferredMinMax(-7, 7, -7, 7);
        frame.addDrawable(planet);
    }

    public void doStep() {
        planet.step();
    }

    public void reset() {
        control.setValue("dt", 0.01);
        control.setValue("x1", 1);
        control.setValue("vx1", 0);
        control.setValue("y1", 0);
        control.setValue("vy1", 6.28);
        control.setValue("x2", 5);
        control.setValue("vx2", 0);
        control.setValue("y2", 0);
        control.setValue("vy2", 14);
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new App());
    }
}


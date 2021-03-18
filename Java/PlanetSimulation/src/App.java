import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.DisplayFrame;

public class App extends AbstractSimulation {
    DisplayFrame frame = new DisplayFrame("x (AU)", "y (AU)", "Planet simulation");

    Planet planet = new Planet();

    public void initialize() {
        double x = control.getDouble("x");
        double vx = control.getDouble("vx");
        double y = control.getDouble("y");
        double vy = control.getDouble("vy");
        double dt = control.getDouble("dt");

        stepsPerDisplay = (int)(0.1 / dt);

        planet.setState(new double[] {x, vx, y, vy, 0});

        planet.setStepSize(dt);
        frame.addDrawable(planet);
        frame.setPreferredMinMax(-6, 6, -6, 6);
        frame.setSquareAspect(true);
        frame.setMessage("t = 0");
    }

    public void doStep() {
        planet.step();
        frame.setMessage("t = "+decimalFormat.format(planet.state[4]));
    }

    public void reset() {
        control.setValue("x", 1);
        control.setValue("vx", 0);
        control.setValue("y", 0);
        control.setValue("vy", 6.28);
        control.setValue("dt", 0.01);
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new App());
    }
}

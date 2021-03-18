import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

public class App extends AbstractSimulation {

    Planet planet = new Planet();
    PlotFrame frame = new PlotFrame("x", "y", "Solar Wind Orbit");

    public void initialize() {
        double x = control.getDouble("x");
        double vx = control.getDouble("vx");
        double y = control.getDouble("y");
        double vy = control.getDouble("vy");
        double dt = control.getDouble("dt");
        stepsPerDisplay = (int) (0.1 / dt);

        planet.setState(new double[] {x, vx, y, vy, 0});
        planet.setStepSize(dt);

        frame.addDrawable(planet);
        frame.setPreferredMinMax(-3, 3, -3, 3);
        frame.setSquareAspect(true);
        frame.setBounds(0, 0, 700, 700);

        frame.setMessage("t = 0 (years)");

    }

    public void doStep() {
        planet.step();
        frame.setMessage("t = "+decimalFormat.format(planet.state[4])+" (years)");
    }

    public void reset() {
        control.setValue("x", 2);
        control.setValue("vx", 0);
        control.setValue("y", 0);
        control.setValue("vy", 2*Math.PI*Math.sqrt(1/control.getDouble("x")));
        control.setValue("dt", 0.01);
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new App());
    }
}

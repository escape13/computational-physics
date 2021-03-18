import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.DisplayFrame;
import org.opensourcephysics.frames.PlotFrame;

public class App extends AbstractSimulation {
    DisplayFrame displayFrame = new DisplayFrame("x", "y", "Pendulum");
    PlotFrame plotFrame = new PlotFrame("t", "theta", "Angle versus Time");
    Pendulum pendulum = new Pendulum();
    double theta, omega;

    public void initialize() {
        pendulum.state[2] = 0;
        displayFrame.clearDrawables();

        theta = control.getDouble("initial theta");
        double dtheta = control.getDouble("initial angular velocity");
        omega = control.getDouble("omega squared");
        double gamma = control.getDouble("gamma");
        double dt = control.getDouble("dt");

        theta = theta * (Math.PI / 180D);

        stepsPerDisplay = (int)(0.1 / dt);

        pendulum.setState(omega, gamma, theta, dtheta);
        pendulum.setStepSize(dt);
        displayFrame.addDrawable(pendulum);
        displayFrame.setPreferredMinMax(-1, 1, -9.8 / omega - 0.5, 0.5);
        displayFrame.setBounds(0, 0, 450, 700);
        plotFrame.setBounds(450, 0, 400, 400);
    }

    public void doStep() {
        plotFrame.append(0, pendulum.state[2], pendulum.state[0]);
        pendulum.step();
    }

    public void reset() {
        control.setValue("initial theta", 20);
        control.setValue("initial angular velocity", 0);
        control.setValue("omega squared", 9);
        control.setValue("gamma", 0.5);
        control.setValue("dt", 0.01);
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new App());
    }
}

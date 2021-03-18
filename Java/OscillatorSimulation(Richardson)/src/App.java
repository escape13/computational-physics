import org.opensourcephysics.controls.*;
import org.opensourcephysics.frames.*;

public class App extends AbstractSimulation{

    DisplayFrame frame = new DisplayFrame("x", "", "Harmonic Motion Simulation");

    double t = 0, dt = 0.001;

    HarmonicParticle particle;

    public void initialize() {

        frame.clearDrawables();

        double x0 = control.getDouble("initial position");
        double v0 = control.getDouble("initial speed");
        double k = control.getDouble("spring constant");
        double m = control.getDouble("mass");

        frame.setPreferredMinMax(-x0 - 3, x0 + 3, -3, 3);

        particle = new HarmonicParticle(x0, v0, k, m);

        frame.addDrawable(particle);

        setStepsPerDisplay(100);

    }

    public void reset() {
        control.setValue("initial position", 5.0);
        control.setValue("initial speed", 0.0);
        control.setValue("spring constant", 1.0);
        control.setValue("mass", 1.0);
    }

    public void doStep() {
        particle.step(dt);
        t += dt;
    }


    public static void main(String[] args) {
        SimulationControl.createApp(new App());
    }
}

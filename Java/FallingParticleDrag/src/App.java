import org.opensourcephysics.controls.AbstractCalculation;
import org.opensourcephysics.controls.CalculationControl;
import org.opensourcephysics.frames.PlotFrame;

public class App extends AbstractCalculation {

    PlotFrame frame = new PlotFrame("t", "y", "position vs time");
    FallingParticle particle;

    public void calculate() {
        particle = new FallingParticle(control.getDouble("v terminal"));
        double y = control.getDouble("initial y");
        double v = control.getDouble("initial v");
        double dt = control.getDouble("dt");
        particle.setState(y, v);
        particle.setStepSize(dt);
        while (particle.state[0] > 0) {
            particle.step();
            frame.append(0, particle.state[2], particle.state[0]);
        }
        frame.setVisible(true);

    }

    public void reset() {
        control.setValue("initial y", 0.42);
        control.setValue("initial v", 0);
        control.setValue("dt", 0.01);
        control.setValue("v terminal", -0.67);
    }

    public static void main(String[] args) {
        CalculationControl.createApp(new App());
    }
}

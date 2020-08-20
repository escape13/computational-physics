import org.opensourcephysics.controls.*;
import org.opensourcephysics.numerics.*;

public class App extends AbstractCalculation{
    public void calculate() {
        double y0 = control.getDouble("initial position");
        double v0 = control.getDouble("initial speed");
        FallingParticleODE ball = new FallingParticleODE(y0, v0);
        ODESolver solver = new RK4(ball);
        solver.setStepSize(control.getDouble("dt"));
        while(ball.state[0] > 0) {
            solver.step();
        }
        control.println("t = " + ball.state[2]);
        control.println("y = " + ball.state[0]);
        control.println("v = " + ball.state[1]);
    }

    public void reset() {
        control.setValue("initial position", 1000.0);
        control.setValue("initial speed", 0.0);
        control.setValue("dt", 0.001);
    }

    public static void main(String[] args) {
        CalculationControl.createApp(new App());
    }
}

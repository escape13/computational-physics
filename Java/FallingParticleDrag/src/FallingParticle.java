import org.opensourcephysics.numerics.EulerRichardson;
import org.opensourcephysics.numerics.ODE;
import org.opensourcephysics.numerics.ODESolver;

public class FallingParticle implements ODE {
    final static double g = 9.8;
    double[] state = new double[3];
    private double vter;
    ODESolver solver = new EulerRichardson(this);

    public FallingParticle(double vter) {
        this.vter = vter;
    }

    public void step() {
        solver.step();
    }

    public void setState(double y, double v) {
        state[0] = y;
        state[1] = v;
        state[2] = 0;
    }

    public void setStepSize(double dt) {
        solver.setStepSize(dt);
    }

    public double[] getState() {
        return state;
    }

    public void getRate(double[] state, double[] rate) {
        rate[0] = state[1];
        rate[1] = -g * (1 - (state[1]*state[1])/(vter*vter));
        rate[2] = 1;
    }
}

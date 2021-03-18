import org.opensourcephysics.numerics.ODE;

public class FallingParticleODE implements ODE {

    final static double g = 9.8;
    double[] state = new double[3];

    public FallingParticleODE(double y, double v) {
        state[0] = y;
        state[1] = v;
        state[2] = 0;
    }

    public double[] getState() {
        return state;
    }

    public void getRate(double[] state, double[] rate) {
        rate[0] = state[1];
        rate[1] = -g;
        rate[2] = 1;
    }
}

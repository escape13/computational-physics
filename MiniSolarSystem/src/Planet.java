import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.numerics.ODE;
import org.opensourcephysics.numerics.ODESolver;
import org.opensourcephysics.numerics.RK4;
import org.opensourcephysics.numerics.RK45MultiStep;

import java.awt.*;

public class Planet implements ODE, Drawable{


    double GM = 4 * Math.PI * Math.PI;
    double GM1 = 0.04*GM;
    double GM2 = 0.001*GM;
    ODESolver solver = new RK4(this);
    double[] state = new double[9];
    Mass mass1 = new Mass(), mass2 = new Mass();

    public double[] getState() {
        return state;
    }

    public void getRate(double[] state, double[] rate) {
        double r1Squared = state[0] * state[0] + state[2] * state[2];
        double r1Cubed = r1Squared * Math.sqrt(r1Squared);
        double r2Squared = state[4] * state[4] + state[6] * state[6];
        double r2Cubed = r2Squared * Math.sqrt(r2Squared);
        double dx = state[4] - state[0];
        double dy = state[6] - state[2];
        double dr2 = dx * dx + dy * dy;
        double dr3 = dr2 * Math.sqrt(dr2);
        rate[0] = state[1];
        rate[2] = state[3];
        rate[4] = state[5];
        rate[6] = state[7];
        rate[1] = -((GM * state[0]) / (r1Cubed)) + ((GM1 * dx) / dr3);
        rate[3] = -((GM * state[2]) / (r1Cubed)) + ((GM1 * dy) / dr3);
        rate[5] = -((GM * state[4]) / (r2Cubed)) - ((GM2 * dx) / dr3);
        rate[7] = -((GM * state[6]) / (r2Cubed)) - ((GM2 * dy) / dr3);
        rate[8] = 1;
    }

    public void draw(DrawingPanel panel, Graphics g) {
        mass1.draw(panel, g);
        mass2.draw(panel, g);
    }

    public void setStepSize(double dt) {
        solver.setStepSize(dt);
    }

    public void step() {
        solver.step();
        mass1.setXY(state[0], state[2]);
        mass2.setXY(state[4], state[6]);
    }

    void initialize(double[] initState) {
        System.arraycopy(initState, 0, state, 0, initState.length);
        mass1.clear();
        mass2.clear();
        mass1.setXY(state[0], state[2]);
        mass2.setXY(state[4], state[6]);
    }


    class Mass extends Circle {
        Trail trail = new Trail();

        public void draw(DrawingPanel panel, Graphics g) {
            trail.draw(panel, g);
            super.draw(panel, g);
        }

        public void clear() {
            trail.clear();
        }

        public void setXY(double x, double y) {
            super.setXY(x, y);
            trail.addPoint(x, y);
        }
    }
}

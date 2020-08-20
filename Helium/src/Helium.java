import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.numerics.*;

import java.awt.*;

public class Helium implements ODE, Drawable {
    double[] state = new double[9];
    ODESolver solver = new RK45MultiStep(this);
    Electron electron1 = new Electron(); Electron electron2 = new Electron();

    public double[] getState() {
        return state;
    }


    public void setState(double[] initState) {
        System.arraycopy(initState, 0, state, 0, initState.length);
        electron1.clear();
        electron2.clear();
        electron1.setXY(state[0], state[2]);
        electron2.setXY(state[4], state[6]);
    }

    public void getRate(double[] state, double[] rate) {
        double r1Squared = state[0] * state[0] + state[2] * state[2];
        double r1Cubed = r1Squared * Math.sqrt(r1Squared);
        double r2Squared = state[4] * state[4] + state[6] * state[6];
        double r2Cubed = r2Squared * Math.sqrt(r2Squared);
        double dx = state[4] - state[0];
        double dy = state[6] - state[2];
        double r12Squared = dx*dx + dy*dy;
        double r12Cubed = r12Squared * Math.sqrt(r12Squared);

        rate[0] = state[1];
        rate[1] = -2 * state[0] / r1Cubed - dx / r12Cubed;
        rate[2] = state[3];
        rate[3] = -2 * state[2] / r1Cubed - dy / r12Cubed;
        rate[4] = state[5];
        rate[5] = -2 * state[4] / r2Cubed + dx / r12Cubed;
        rate[6] = state[7];
        rate[7] = -2 * state[6] / r2Cubed + dy / r12Cubed;
        rate[8] = 1;
    }

    public void step() {
        solver.step();
        electron1.setXY(state[0], state[2]);
        electron2.setXY(state[4], state[6]);
    }

    public void draw(DrawingPanel panel, Graphics g) {
        electron1.draw(panel, g);
        electron2.draw(panel, g);
    }

    class Electron extends Circle {
        Trail trail = new Trail();

        public void draw(DrawingPanel panel, Graphics g) {
            g.setColor(Color.BLUE);
            super.draw(panel, g);
            g.setColor(Color.black);
            trail.draw(panel, g);
        }

        void clear() {
            trail.clear();
        }

        public void setXY(double x, double y) {
            trail.addPoint(x, y);
            super.setXY(x, y);
        }
    }
}

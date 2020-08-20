import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.numerics.ODE;
import org.opensourcephysics.numerics.ODESolver;
import org.opensourcephysics.numerics.RK4;

import java.awt.*;

public class Planet implements ODE, Drawable {
    double[] state = new double[5];
    ODESolver solver = new RK4(this);
    int pixRadius = 6;
    final static double GM = 4 * Math.PI * Math.PI;
    Mass mass = new Mass();

    public double[] getState(){
        return state;
    }

    public void getRate(double[] state, double[] rate) {
        double rSquared = state[0]*state[0] + state[2]*state[2];
        double rCubed = rSquared * Math.sqrt(rSquared);
        rate[0] = state[1];
        rate[1] = -GM * state[0] / rCubed + Math.abs(GM * 0.03 / rSquared);
        rate[2] = state[3];
        rate[3] = -GM * state[2] / rCubed;
        rate[4] = 1;
    }

    public void setState(double[] initState) {
        System.arraycopy(initState, 0, state, 0, initState.length);
        mass.clear();
        mass.setXY(state[0], state[2]);
    }

    public void setStepSize(double dt) {
        solver.setStepSize(dt);
    }

    public void step() {
        solver.step();
        mass.setXY(state[0], state[2]);
    }

    public void draw(DrawingPanel panel, Graphics g) {
        mass.draw(panel, g);
        g.setColor(Color.RED);
        int xSun = panel.xToPix(0);
        int ySun = panel.yToPix(0);
        g.fillOval(xSun - 4, ySun - 4, 8, 8);
    }

    class Mass extends Circle {

        Trail trail = new Trail();

        public void draw(DrawingPanel panel, Graphics g) {
            trail.draw(panel, g);
            super.draw(panel, g);
        }

        void clear() {
            trail.clear();
        }

        public void setXY(double x, double y) {
            super.setXY(x, y);
            trail.addPoint(x, y);
        }
    }

}

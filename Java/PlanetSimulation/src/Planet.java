import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.numerics.*;

import java.awt.*;

public class Planet implements ODE, Drawable {
    final static double GM = 4D * Math.PI * Math.PI;
    ODESolver solver = new RK4(this);
    Circle circle = new Circle();
    Trail trail = new Trail();
    double[] state = new double[5];

    public double[] getState() {
        return state;
    }

    public void getRate(double[] state, double[] rate) {
        double r2 = (state[0] * state[0]) + (state[2] * state[2]);
        double r3 = r2 * Math.sqrt(r2);
        rate[0] = state[1];
        rate[1] = -(GM * state[0]) / r3;
        rate[2] = state[3];
        rate[3] = -(GM * state[2]) / r3;
        rate[4] = 1;
    }

    public void step() {
        solver.step();
        trail.addPoint(state[0], state[2]);
    }

    public void setState(double[] initState) {
        System.arraycopy(initState, 0, state, 0, initState.length);
        trail.clear();
    }

    public void setStepSize(double dt) {
        solver.setStepSize(dt);
    }

    public void draw(DrawingPanel panel, Graphics g) {
        circle.setXY(state[0], state[2]);
        circle.draw(panel, g);
        trail.draw(panel, g);
    }

}

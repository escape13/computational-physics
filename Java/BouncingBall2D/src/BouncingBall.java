import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.numerics.*;

import java.awt.*;

public class BouncingBall implements ODE, Drawable {
    public double[] state = new double[] {0, 0, 0, 0, 0};
    int pixRadius = 6;
    final static double g = 9.8;
    final static double wall = 10.0;
    ODESolver solver = new EulerRichardson(this);

    public double[] getState() {
        return state;
    }

    public void getRate(double[] state, double[] rate) {
        rate[0] = state[1];
        rate[1] = 0;
        rate[2] = state[3];
        rate[3] = -g;
        rate[4] = 1;
    }

    public void setState(double x, double y, double vx, double vy) {
        state[0] = x;
        state[1] = vx;
        state[2] = y;
        state[3] = vy;
        state[4] = 0;

    }

    public void setStepSize(double dt) {
        solver.setStepSize(dt);
    }

    public void step() {
        if (state[0] > wall) {
            state[1] = -Math.abs(state[1]);
        } else if (state[0] < -wall) {
            state[1] = Math.abs(state[1]);
        }
        if (state[2] < 0) {
            state[3] = Math.abs(state[3]);
        }
        solver.step();
    }

    public void draw(DrawingPanel panel, Graphics g) {
        int xpix = panel.xToPix(state[0]);
        int ypix = panel.yToPix(state[2]);
        g.setColor(Color.RED);
        g.fillOval(xpix - pixRadius, ypix - pixRadius, 2*pixRadius, 2*pixRadius);

    }
}
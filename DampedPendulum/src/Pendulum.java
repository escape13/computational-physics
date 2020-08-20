import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.numerics.ODE;
import org.opensourcephysics.numerics.ODESolver;
import org.opensourcephysics.numerics.RK4;

import java.awt.*;

public class Pendulum implements ODE, Drawable {
    int pixRadius = 6;
    double[] state = new double[] {0, 0, 0}; // theta, dtheta/dt, t
    double omegaSquared, gamma;
    ODESolver solver = new RK4(this);

    public double[] getState() {
        return state;
    }

    public void getRate(double[] state, double[] rate) {
        rate[0] = state[1];
        rate[1] = -omegaSquared * Math.sin(state[0]) - gamma * state[1];
        rate[2] = 1;
    }

    public void setState(double omegaSquared, double gamma, double theta, double dtheta) {
        this.omegaSquared = omegaSquared;
        this.gamma = gamma;
        state[0] = theta;
        state[1] = dtheta;
    }

    public void setStepSize(double dt) {
        solver.setStepSize(dt);
    }

    public void step() {
        solver.step();
    }

    public void draw(DrawingPanel panel, Graphics g) {
        g.setColor(Color.BLACK);
        int xpivot = panel.xToPix(0);
        int ypivot = panel.yToPix(0);
        int xpix = panel.xToPix(9.8D / omegaSquared * Math.sin(state[0]));
        int ypix = panel.yToPix(-9.8D / omegaSquared * Math.cos(state[0]));
        g.drawLine(xpivot, ypivot,xpix, ypix);
        g.setColor(Color.RED);
        g.fillOval(xpix - pixRadius, ypix - pixRadius, 2*pixRadius, 2*pixRadius);
    }
}

import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.numerics.EulerRichardson;
import org.opensourcephysics.numerics.ODE;

import java.awt.*;

public class Projectile implements ODE, Drawable {
    static final double g = 9.8;
    double[] state = new double[5];
    int pixRadius = 6;
    EulerRichardson solver = new EulerRichardson(this);

    public void setState(double x, double vx, double y, double vy) {
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
        solver.step();
    }


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

    public void draw(DrawingPanel panel, Graphics g) {
        int xpix = panel.xToPix(state[0]);
        int ypix = panel.yToPix(state[2]);
        g.setColor(Color.RED);
        g.fillOval(xpix - pixRadius, ypix - pixRadius, 2*pixRadius, 2*pixRadius);

        g.setColor(Color.green);
        int xmin = panel.xToPix(-100);
        int xmax = panel.xToPix(100);
        int y0 = panel.yToPix(0);
        g.drawLine(xmin, y0, xmax, y0);

    }
}

import org.opensourcephysics.controls.*;
import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;

public class FallingParticleApp extends AbstractCalculation {

    PlotFrame xFrame = new PlotFrame("t", "x", "x(t)");
    PlotFrame vFrame = new PlotFrame("t", "v", "v(t)");
    public void calculate() {


        double x0 = control.getDouble("Initial position");
        double v0 = control.getDouble("Initial velocity");
        double dt = control.getDouble("Time step");
        Particle ball = new FallingParticle(x0, v0, dt);
        while(ball.x > 0) {
            ball.euler();
        }
        for(int i = 0; i <= ball.t * 200; ++i) {
            double temp = i * 0.005;
            xFrame.append(0, temp, x0 + v0 * temp - 0.5 * 9.8 * Math.pow(temp, 2));
        }
        for(int i = 0; i <= ball.t * 200; ++i) {
            double temp = i * 0.005;
            vFrame.append(0, temp, v0 - 9.8 * temp);
        }

        xFrame.setVisible(true);
        xFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        vFrame.setVisible(true);
        vFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        control.println("Falling time = " + ball.t);
        control.println("Euler Method results:");
        control.println("x = " + ball.x + " v = " + ball.v);
        control.println("Analytical results");
        control.println("x = " + ball.xAnalytic() + " v = " + ball.vAnalytic());


    }

    public void reset() {
        control.setValue("Initial position", 10.0);
        control.setValue("Initial velocity", 0.0);
        control.setValue("Time step", 0.01);
    }

    public static void main(String[] args) {
        CalculationControl.createApp(new FallingParticleApp());
    }
}

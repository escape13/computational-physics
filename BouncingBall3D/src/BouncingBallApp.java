import org.opensourcephysics.controls.*;
import org.opensourcephysics.frames.*;

import java.util.Random;

public class BouncingBallApp extends AbstractSimulation {
    Display3DFrame mainFrame = new Display3DFrame("Bouncing ball simulation");

    PlotFrame pFrame = new PlotFrame("time", "kinetic energy", "Kinetic energy over time");
    PlotFrame kFrame = new PlotFrame("time", "potential energy", "Potential energy over time");
    PlotFrame totalFrame = new PlotFrame("time","mechanic energy", "Total mechanic energy over time");
    PlotFrame errorFrame = new PlotFrame("time", "error, %", "Error over time");

    Random r = new Random();

    double t, dt; double kE, pE, totalE;
    BouncingBall[] ball;

    public void initialize() {
        t = 0;

        mainFrame.setBounds(0, 0, 450, 450);
        mainFrame.setPreferredMinMax(-100, 100, -100, 100, 0, 100);

        pFrame.setBounds(0, 450, 450, 225);
        kFrame.setBounds(0, 675, 450, 225);
        totalFrame.setBounds(450, 0, 495, 450);
        errorFrame.setBounds(945, 0, 495, 450);

        int n = control.getInt("Quantity");
        double v = control.getDouble("Velocity");

        totalE = 0.5 * n * v * v * 5.3 * Math.pow(10, -26);


        ball = new BouncingBall[n];

        for(int i = 0; i < n; ++i) {
            double theta = Math.PI * (-1.0 + 2 * r.nextDouble());
            double phi = Math.PI * Math.random();
            ball[i] = new BouncingBall(0, 0, 0, v * Math.sin(theta) * Math.cos(phi), v * Math.sin(theta) * Math.sin(phi), v * Math.cos(theta));
            mainFrame.addElement(ball[i]);
        }
    }

    public void doStep() {
        kE = 0; pE = 0;
        for(int i = 0; i < ball.length; ++i) {
            ball[i].step(dt);
            kE += ball[i].kineticEnergy;
            pE += ball[i].potentialEnergy;
        }

        kFrame.append(0, t, kE);
        pFrame.append(0, t, pE);
        totalFrame.append(0, t, kE + pE);
        errorFrame.append(0, t, Math.abs(kE + pE - totalE) / totalE * 100);

        t += dt;
    }

    public void reset() {
        control.setAdjustableValue("Time step", 0.001);
        control.setAdjustableValue("steps per display", 100);
        control.setValue("Quantity", 10);
        control.setValue("Velocity", 15.0);
    }

    public void startRunning() {
        dt = control.getDouble("Time step");
        setStepsPerDisplay(control.getInt("steps per display"));
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new BouncingBallApp());
    }
}
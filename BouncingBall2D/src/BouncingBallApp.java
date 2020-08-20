import org.opensourcephysics.controls.*;
import org.opensourcephysics.frames.*;

public class BouncingBallApp extends AbstractSimulation {

    DisplayFrame frame = new DisplayFrame("x", "y", "Bouncing Ball Application");
    PlotFrame frameKinetic = new PlotFrame("t", "Kinetic energy", "Kinetic energy over time");
    PlotFrame framePotential = new PlotFrame("t", "Potential energy", "Potential energy over time");
    PlotFrame frameError = new PlotFrame("t", "Error", "Error over time");

    public double t, dt, kin, pot, total, e0;

    BouncingBall[] balls;

    public void initialize() {

        frame.setPreferredMinMax(-10, 10, 0, 10);
        frame.setBounds(0, 0, 720, 450);
        frameKinetic.setBounds(0, 450, 360, 360);
        framePotential.setBounds(360, 450, 360, 360);
        frameError.setBounds(720, 0, 360, 360);
        frameError.setPreferredMinMax(0, 4.1, 0,0.000000000000000000001);

        t = 0;
        dt = 0.01;

        stepsPerDisplay = 10;
        frame.clearDrawables();

        int n = control.getInt("number of balls");
        double v = control.getDouble("initial speed");

        e0 = 0.5 * n * Math.pow(v, 2);

        balls = new BouncingBall[n];

        for (int i = 0; i < n; ++i) {
            double theta = Math.PI * Math.random();
            BouncingBall ball = new BouncingBall();
            balls[i] = ball;
            balls[i].setState(0, 0, v * Math.cos(theta), v * Math.sin(theta));
            balls[i].setStepSize(dt);
            frame.addDrawable(balls[i]);
        }

    }

    public void reset() {
        control.setValue("number of balls", 40);
        control.setValue("initial speed", 10);
        setStepsPerDisplay(100);
    }

    public void doStep() {
        kin = 0; pot = 0; total = 0;
        for (int i = 0; i < balls.length; ++i) {
            kin += 0.5 * ((Math.pow(balls[i].state[1], 2) + Math.pow(balls[i].state[3], 2)));
            pot += 9.8 * balls[i].state[2];
            balls[i].step();
        }
        total = kin + pot;
        frameKinetic.append(0, balls[0].state[4], kin);
        framePotential.append(0, balls[0].state[4], pot);
        frameError.append(0, balls[0].state[4], Math.abs((total - e0))/e0);
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new BouncingBallApp());
    }


}
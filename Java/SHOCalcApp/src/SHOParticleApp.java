import org.opensourcephysics.controls.*;
import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;
import java.text.DecimalFormat;

public class SHOParticleApp extends AbstractCalculation {

    private PlotFrame xFrame = new PlotFrame("t", "x(t)", "Position");
    private PlotFrame vFrame = new PlotFrame("t", "v(t)", "Velocity");

    private DecimalFormat decimal = new DecimalFormat("#0.000");

    public void calculate() {

        double x0 = control.getDouble("Initial position");
        double v0 = control.getDouble("Initial velocity");
        double k = control.getDouble("Spring constant");
        double m = control.getDouble("Particle mass");
        double dt = control.getDouble("Time step");

        double omega = Math.sqrt(k/m);

        Particle mass = new SHOParticle(x0, v0, omega, dt);

        while (mass.x > 0) {
            mass.step();
        }

        for(int i = 0; i <= mass.t * 4000; ++i) {
            double t0 = i * 0.001;
            xFrame.append(0, t0, x0 * Math.cos(omega * t0) + (v0 / omega) * Math.sin(omega * t0));
        }

        for(int i = 0; i <= mass.t * 4000; ++i) {
            double t0 = i * 0.001;
            vFrame.append(0, t0, -x0 * omega * Math.sin(omega * t0) + v0 * Math.cos(omega * t0));
        }

        xFrame.setVisible(true);
        xFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        xFrame.setBounds(0, 0, 700, 450);

        vFrame.setVisible(true);
        vFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vFrame.setBounds(0, 450, 700, 450);


        control.println("Calculations done.");
        control.println("Final time: " + decimal.format(mass.t));
        control.println("Euler results: ");
        control.println("x = " + decimal.format(mass.x) + " v = " + decimal.format(mass.v));
        control.println();
        control.println("Analytical results: ");
        control.println("x = " + decimal.format(mass.xAnalytic()) + " v = " + decimal.format(mass.vAnalytic()));

    }

    public void reset() {

        control.setValue("Initial position", 10.0);
        control.setValue("Initial velocity", 0.0);
        control.setValue("Spring constant", 1.0);
        control.setValue("Particle mass", 1.0);
        control.setValue("Time step", 0.01);

    }

    public static void main(String[] args) {
        CalculationControl.createApp(new SHOParticleApp());

    }
}

import org.opensourcephysics.frames.*;
import org.opensourcephysics.controls.*;

public class App extends AbstractSimulation {

    Projectile projectile = new Projectile();

    PlotFrame plotFrame = new PlotFrame("t", "x, y", "Position versus time");
    PlotFrame animationFrame = new PlotFrame("x", "y", "Trajectory");

    public App() {
        plotFrame.setXYColumnNames(0, "t", "x");
        plotFrame.setXYColumnNames(1, "t", "y");
        animationFrame.addDrawable(projectile);
    }

    public void initialize() {
        double x = control.getDouble("initial x");
        double y = control.getDouble("initial y");
        double vx = control.getDouble("initial vx");
        double vy = control.getDouble("initial vy");
        double dt = control.getDouble("dt");
        projectile.setState(x, vx, y, vy);
        projectile.setStepSize(dt);
        double size = (vx * vx + vy * vy) / 7.5;
        animationFrame.setPreferredMinMax(0, size, -0.1, size);
        double stepsperdisplay = 0.1 / dt;
        stepsPerDisplay = (int) stepsperdisplay;
;    }

    public void doStep() {
        plotFrame.append(0, projectile.state[4], projectile.state[0]);
        plotFrame.append(1, projectile.state[4], projectile.state[2]);
        animationFrame.append(0, projectile.state[0], projectile.state[2]);
        projectile.step();
        if (projectile.state[2] < 0) {
            stopSimulation();
        }
    }

    public void reset() {
        control.setValue("initial x", 0);
        control.setValue("initial vx", 10);
        control.setValue("initial y", 0);
        control.setValue("initial vy", 10);
        control.setValue("dt",0.01);
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new App());
    }
}

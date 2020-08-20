public class SHOParticle extends Particle {
    private double x0, v0, omega, dt;

    public SHOParticle(double x0, double v0, double omega, double dt) {
        this.x0 = x0;
        this.v0 = v0;
        this.omega = omega;
        this.dt = dt;
        x = x0;
        v = v0;
        t = 0;
    }



    public void step() {
        x += v * dt;
        v -= Math.pow(omega, 2) * x * dt;
        t += dt;
    }

    public double xAnalytic() {
        return x0 * Math.cos(omega * t) + (v0 / omega) * Math.sin(omega * t);
    }

    public double vAnalytic() {
        return -x0 * omega * Math.sin(omega * t) + v0 * Math.cos(omega * t);
    }
}

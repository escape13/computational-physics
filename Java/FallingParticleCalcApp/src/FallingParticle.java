public class FallingParticle extends Particle {
    private double x0, v0, dt;
    static final double g = 9.8;

    public FallingParticle(double x0, double v0, double dt) {
        this.x0 = x0;
        this.v0 = v0;
        this.dt = dt;
        x = x0;
        v = v0;
        t = 0;
    }

    public void euler() {
        x += v * dt;
        v -= g * dt;
        t += dt;
    }

    public double xAnalytic() {
        return x0 + v0 * t - 0.5 * g * t * t;
    }

    public double vAnalytic() {
        return v0 - g * t;
    }
}

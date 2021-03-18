abstract class Particle {
    double x, v, t, dt;

    abstract protected void step();
    abstract protected double xAnalytic();
    abstract protected double vAnalytic();
}
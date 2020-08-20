abstract public class Particle {

    double x, v, t, dt;

    public Particle() {

    }

    abstract protected void euler();
    abstract protected double xAnalytic();
    abstract protected double vAnalytic();
}

import org.opensourcephysics.display.Circle;

public class HarmonicParticle extends Circle {

    private double x, v, k, m, a, a1, a2, a3, a4, v1, v2, v3, v4;

    public HarmonicParticle(double x0, double v0, double k, double m) {
       this.x = x0;
       this.v = v0;
       this.k = k;
       this.m = m;


       pixRadius = 7;

       setXY(x0, 0);

   }

   public void step(double dt) {
        a1 = -k * x / m;
        a2 = -k * (x + 0.5 * dt * dt * a1) / m;
        a3 = -k * (x + 0.5 * dt * dt * a2) / m;
        a4 = -k * (x + dt * dt * a3) / m;

        v += dt * (a1 + 2*a2 + 2*a3 + a4) / 6;

        v1 = v;
        v2 = v1 + 0.5 * dt * a1;
        v3 = v1 + 0.5 * dt * a2;
        v4 = v1 + dt * a4;

        x += dt * (v1 + 2*v2 + 2*v3 + v4) / 6;


        setXY(x, 0);

   }

}

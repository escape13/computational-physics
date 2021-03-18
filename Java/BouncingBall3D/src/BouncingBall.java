import org.opensourcephysics.display3d.simple3d.ElementCircle;

public class BouncingBall extends ElementCircle {

    final static double g = 9.8;
    final static double wall = 100;
    final static int a = 5;

    double kineticEnergy, potentialEnergy;

    private double x, y, z, vx, vy, vz;

    public BouncingBall(double x, double y, double z, double vx, double vy, double vz) {
        this.x = x; this.y = y; this.z = z;
        this.vx = vx; this.vy = vy; this.vz = vz;

        setSizeXYZ(a, a, a);
        setXYZ(x, y, z);
    }

    public void step(double dt) {

        x += vx * dt;
        y += vy * dt;
        z += vz * dt; vz -= g * dt;

        if(x > wall - 0.5) {
            vx = -Math.abs(vx);
        } else if (x < -wall + 0.5) {
            vx = Math.abs(vx);
        }

        if(y > wall - 0.5) {
            vy = -Math.abs(vy);
        } else if (y < -wall + 0.5) {
            vy = Math.abs(vy);
        }

        if(z < 0.5) {
            vz = Math.abs(vz);
        }

        if(z > wall - 0.5) {
            vz = -Math.abs(vz);
        }

        kineticEnergy = 0.5 * (vx * vx + vy * vy + vz * vz) * 5.3 * Math.pow(10, -26);
        potentialEnergy = g * z * 5.3 * Math.pow(10, -26);

        setXYZ(x, y, z);
    }
}
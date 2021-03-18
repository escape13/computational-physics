public class Complex {
    private double real = 0;
    private double imag = 0;

    public Complex() {
        this(0,0);
    }

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public void conjugate() {
        imag = -imag;
    }

    public Complex add(Complex a) {
        Complex sum = new Complex();
        sum.real = real + a.real;
        sum.imag = imag + a.imag;
        return sum;
    }

    public Complex mutiply(Complex a) {
        Complex product = new Complex();
        product.real = (real * a.real) - (imag * a.imag);
        product.imag = (real * a.imag) + (imag * a.real);
        return product;
    }

    public String toString() {
        if (imag >= 0) {
            return real + " + " + Math.abs(imag) + "i";
        } else {
            return real + " - " + Math.abs(imag) + "i";
        }
    }
}

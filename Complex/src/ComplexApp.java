public class ComplexApp {
    public static void main(String[] args) {
        Complex a = new Complex(3.0, 2.0);
        Complex b = new Complex(1.0, -4.0);

        System.out.println(a);
        System.out.println(b);

        Complex sum = a.add(b);
        System.out.println(sum);

        Complex product = a.mutiply(b);
        System.out.println(product);

        a.conjugate();
        System.out.println(a);
    }
}
public class TangentMethod {
    final double a = 0.0, b = 10.0;

    private double (double x, double y) {
        return 9 * Math.pow(x, 2.0) - y;
    }
    //        return 3 * Math.pow(x, 3.0) - x * y + y * y - 2 * y + 1;
    private double funDrugiegoStopniaPoX(double x) {
        return 18 * x;
    }
    private double funTrzeciegoStopniaPoX() {return 18;}

    private double funPierwszegoStopniaPoY(double y, double x) {
        return -x + 2 * y - 2;
    }

    private double funDrugiegoStopniaPoY() {
        return 2;
    }
    private double funTrzeciegoStopniaPoY() { return 0; }

    public double calculateStycznychPoX(double y) {
        if (funDrugiegoStopniaPoX(a) * funDrugiegoStopniaPoX(b) < 0 &&
                funTrzeciegoStopniaPoX() * funTrzeciegoStopniaPoX() < 0) {
            throw new RuntimeException("Warunki zbiezności nie zostały spełnione!");
        }

        double xn = funTrzeciegoStopniaPoX() * funPierwszegoStopniaPoX(a, y) >= 0 ? a : b;

        double xn1 = xn - (funPierwszegoStopniaPoX(xn, y) / funDrugiegoStopniaPoX(xn));

        while (warunekStopuPoX(xn, xn1, y)) {
            xn = xn1;
            xn1 = xn - (funPierwszegoStopniaPoX(xn, y) / funDrugiegoStopniaPoX(xn));
        }

        System.out.println("xs: " + funPierwszegoStopniaPoX(xn1, y));
        return xn1;
    }

    public double calculateStycznychPoY(double x) {
        if (funDrugiegoStopniaPoY() * funDrugiegoStopniaPoY() < 0 &&
                funTrzeciegoStopniaPoY() * funTrzeciegoStopniaPoY() < 0) {
            throw new RuntimeException("Warunki zbiezności nie zostały spełnione!");
        }

        double xn = funTrzeciegoStopniaPoY() * funPierwszegoStopniaPoY(a, x) >= 0 ? a : b;

        double xn1 = xn - (funPierwszegoStopniaPoY(xn, x) / funDrugiegoStopniaPoY());

        while (warunekStopuPoY(xn, xn1, x)) {
            xn = xn1;
            xn1 = xn - (funPierwszegoStopniaPoY(xn, x) / funDrugiegoStopniaPoY());
        }

        return xn1;
    }

    private boolean warunekStopuPoX(double xn, double xn1, double y) {
        return Math.abs(funPierwszegoStopniaPoX(xn1, y)) >= 0.00000001 && Math.abs(xn1 - xn) >= 0.00000001;
    }

    private boolean warunekStopuPoY(double xn, double xn1, double x) {
        return Math.abs(funPierwszegoStopniaPoY(xn1, x)) >= 0.00000001 && Math.abs(xn1 - xn) >= 0.00000001;
    }
}

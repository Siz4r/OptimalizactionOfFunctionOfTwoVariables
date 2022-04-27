import static FunctionOfTwoVariables.FunctionAndDeriratives.*;

public class TangentMethod {
    final double a = 0.0, b = 10.0;


    private double funTrzeciegoStopniaPoX() {return 18;}

    private double funTrzeciegoStopniaPoY() { return 0; }

    public double calculateStycznychPoX(double y) {
        if (doubleDerirativeAfterX(a, y) * doubleDerirativeAfterX(b, y) < 0 &&
                funTrzeciegoStopniaPoX() * funTrzeciegoStopniaPoX() < 0) {
            throw new RuntimeException("Warunki zbiezności nie zostały spełnione!");
        }

        double xn = funTrzeciegoStopniaPoX() * derirativeAfterX(a, y) >= 0 ? a : b;

        double xn1 = xn - (derirativeAfterX(xn, y) / doubleDerirativeAfterX(xn, y));

        while (warunekStopuPoX(xn, xn1, y)) {
            xn = xn1;
            xn1 = xn - (derirativeAfterX(xn, y) / doubleDerirativeAfterX(xn, y));
        }

        return xn1;
    }

    public double calculateStycznychPoY(double x) {
        if (doubleDerirativeAfterY(x, a) * doubleDerirativeAfterY(x, b) < 0 &&
                funTrzeciegoStopniaPoY() * funTrzeciegoStopniaPoY() < 0) {
            throw new RuntimeException("Warunki zbiezności nie zostały spełnione!");
        }

        double yn = funTrzeciegoStopniaPoY() * derirativeAfterY(x, a) >= 0 ? a : b;

        double yn1 = yn - (derirativeAfterY(x, yn) / doubleDerirativeAfterY(x, yn));

        while (warunekStopuPoY(yn, yn1, x)) {
            yn = yn1;
            yn1 = yn - (derirativeAfterY(x, yn) / doubleDerirativeAfterY(x, yn));
        }

        return yn1;
    }

    private boolean warunekStopuPoX(double xn, double xn1, double y) {
        return Math.abs(derirativeAfterX(xn1, y)) >= 0.00000001 && Math.abs(xn1 - xn) >= 0.00000001;
    }

    private boolean warunekStopuPoY(double xn, double xn1, double x) {
        return Math.abs(derirativeAfterY(x, xn1)) >= 0.00000001 && Math.abs(xn1 - xn) >= 0.00000001;
    }
}

package FunctionOfTwoVariables;

public class FunctionAndDeriratives {
    private final static double h = 0.00001;

    public static double fun(double x, double y) {
        return 3 * Math.pow(x, 3.0) - x * y + y * y - 2 * y + 1;
    }

    public static double derirativeAfterX(double x, double y) {
        return (fun(x + h, y) - fun(x, y)) / h;
    }

    public static double derirativeAfterY(double x, double y) {
        return (fun(x, y + h) - fun(x, y)) / h;
    }

    public static double derirativeAfterXAndY(double x, double y) {
        return (fun(x + h, y + h) -
                fun(x + h, y) -
                fun(x, y + h) + fun(x, y)) / (h * h);
    }

    public static double doubleDerirativeAfterX(double x, double y) {
        return (fun(x + 2 * h, y) -
                2 * fun(x + h, y) +
                fun(x, y)) / (h * h);
    }

    public static double doubleDerirativeAfterY(double x, double y) {
        return (fun(x, y + 2 * h) -
                2 * fun(x, y + h) +
                fun(x, y)) / (h * h);
    }
}

public class FunctionOfTwoVariablesCommand {
    private final double x, y, epsilon;

    public FunctionOfTwoVariablesCommand(double x, double y, double epsilon) {
        this.x = x;
        this.y = y;
        this.epsilon = epsilon;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getEpsilon() {
        return epsilon;
    }
}

import vector.Vector;

public class FunctionOfTwoVariablesCommand {
    private final double epsilon;
    private final Vector startingPoint;

    public FunctionOfTwoVariablesCommand(double epsilon, Vector startingPoint) {
        this.epsilon = epsilon;
        this.startingPoint = startingPoint;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public Vector getStartingPoint() {
        return startingPoint;
    }
}

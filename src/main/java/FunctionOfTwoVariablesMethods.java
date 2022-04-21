import static FunctionOfTwoVariables.FunctionAndDeriratives.derirativeAfterX;
import static FunctionOfTwoVariables.FunctionAndDeriratives.derirativeAfterY;

abstract class FunctionOfTwoVariablesMethods {
    FunctionOfTwoVariablesCommand command;
    int iteracje = 0;

    public FunctionOfTwoVariablesMethods(FunctionOfTwoVariablesCommand command) {
        this.command = command;
    }

    abstract Vector calculate();

    boolean stopCondition(Vector v1, Vector v2) {
        var x = derirativeAfterX(v2.getX(), v2.getY());
        var y = derirativeAfterY(v2.getX(), v2.getY());

        return (Math.abs(v2.getX() - v1.getX()) <= command.getEpsilon() &&
                Math.abs(v2.getY() - v1.getY()) <= command.getEpsilon()) ||(Math.abs(x) <= command.getEpsilon() && Math.abs(y) <= command.getEpsilon());
    }

    boolean stopCondition(Vector v) {
        return Math.abs(v.getX()) < command.getEpsilon() && Math.abs(v.getY()) < command.getEpsilon();
    }
}

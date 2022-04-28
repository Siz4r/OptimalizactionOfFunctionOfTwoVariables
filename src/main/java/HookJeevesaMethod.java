import vector.Vector;

import static FunctionOfTwoVariables.FunctionAndDeriratives.*;

public class HookJeevesaMethod extends FunctionOfTwoVariablesMethods {
    private final Vector[] ksi =
            {new Vector(1, 0), new Vector(0, 1)};

    private final double stepChangeFactor = 0.9;
    private final double e = 1.0;
    private final int n = 1;

    public HookJeevesaMethod(FunctionOfTwoVariablesCommand command) {
        super(command);
    }

    @Override
    Vector calculate() {
        var vectorJ1 = new Vector(command.getStartingPoint());
        var vecotrJ2 = new Vector(command.getStartingPoint());
        var valueOfFunctionInFirstBasePoint = fun(vectorJ1);
        int j = 0;

        do {
            var valueOfFunctionInSecondBasePoint = fun(vecotrJ2);
            var vectorJ = new Vector(vectorJ1.getX() + e * ksi[j].getX(),
                    vectorJ1.getY() + e * ksi[j].getY());

            var temp = fun(vectorJ);

            if (temp < valueOfFunctionInFirstBasePoint) {
                valueOfFunctionInFirstBasePoint = temp;
            } else {
                vectorJ = new Vector(vectorJ1.getX() - 2 * e * ksi[j].getX(),
                        vectorJ1.getY() - 2 * e * ksi[j].getY());
                temp = fun(vectorJ1);
                if (temp < valueOfFunctionInFirstBasePoint) {
                    valueOfFunctionInFirstBasePoint = temp;
                } else {
                    vectorJ = new Vector(vectorJ1.getX() + e * ksi[j].getX(),
                            vectorJ1.getY() + e * ksi[j].getY());
                }
            }

            j = j != n ? 1 : 0;
        } while (true);
    }
}

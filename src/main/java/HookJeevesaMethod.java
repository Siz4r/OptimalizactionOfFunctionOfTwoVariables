import vector.Vector;

import static FunctionOfTwoVariables.FunctionAndDeriratives.*;

public class HookJeevesaMethod extends FunctionOfTwoVariablesMethods {
    private final Vector[] ksi =
            {new Vector(1, 0), new Vector(0, 1)};

    private double e = 0.5;

    public HookJeevesaMethod(FunctionOfTwoVariablesCommand command) {
        super(command);
    }

    @Override
    Vector calculate() {
        int j = 0; //krok 1
        var vector0 = new Vector(command.getStartingPoint());
        var vectorB = new Vector(command.getStartingPoint());
        var vectorB0 = new Vector(command.getStartingPoint());

        do {
            var valueOfFunctionInFirstBasePoint = fun(vector0);
            var valueOfFunctionInSecondBasePoint = fun(vectorB); //krok 1
            iteracje++;

            var vectorJ = moveVectorToRight(j, vector0);

            var f = fun(vectorJ);

            if (f < valueOfFunctionInFirstBasePoint) {
                valueOfFunctionInFirstBasePoint = f;
            } else {
                vectorJ = new Vector(vectorJ.getX() - 2 * e * ksi[j].getX(),
                        vectorJ.getY() - 2 * e * ksi[j].getY());

                f = fun(vectorJ);

                if (f < valueOfFunctionInFirstBasePoint) {
                    valueOfFunctionInFirstBasePoint = f;
                } else {
                    vectorJ = moveVectorToRight(j, vectorJ);
                }

            }

            vector0 = vectorJ;

            if (j == 1) {
                if (valueOfFunctionInSecondBasePoint > valueOfFunctionInFirstBasePoint) {
                    vectorB0 = vectorB;
                    vectorB = vectorJ;

                    vector0 = new Vector(2 * vectorB.getX() - vectorB0.getX(),
                            2 * vectorB.getY() - vectorB0.getY());

                } else if (e > command.getEpsilon()){
                    e *= 0.5;
                    vector0 = vectorB;
                } else {
                    System.out.println("Liczba iteracji: " + iteracje);
                    return vector0;
                }
            }

            j = j != 1 ? 1 : 0;
        } while (true);
    }

    private Vector moveVectorToRight(int j, Vector vector0) {
        return new Vector(vector0.getX() + e * ksi[j].getX(),
                vector0.getY() + e * ksi[j].getY());
    }
}

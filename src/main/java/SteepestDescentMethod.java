import static FunctionOfTwoVariables.FunctionAndDeriratives.*;

public class SteepestDescentMethod extends FunctionOfTwoVariablesMethods{

    public SteepestDescentMethod(FunctionOfTwoVariablesCommand command) {
        super(command);
    }

    @Override
    public Vector calculate() {
        var vectorBefore = new Vector(command.getStartingPoint());
        var vectorAfter = new Vector(command.getStartingPoint());

        do {
            iteracje++;
            vectorBefore.setY(vectorAfter.getY());
            vectorBefore.setX(vectorAfter.getX());
            var x = vectorBefore.getX();
            var y = vectorBefore.getY();

            var vectorOfDeriratives = new Vector(
                    derirativeAfterX(x, y),
                    derirativeAfterY(x, y));

            var matrix = new Matrix(doubleDerirativeAfterX(x, y),
                    derirativeAfterXAndY(x, y),
                    derirativeAfterXAndY(x, y),
                    doubleDerirativeAfterY(x, y));

            var fraction = calculateFraction(vectorOfDeriratives, matrix);

            vectorAfter.setX(x - fraction * vectorOfDeriratives.getX());
            vectorAfter.setY(y - fraction * vectorOfDeriratives.getY());

        }while (!stopCondition(vectorBefore, vectorAfter));

        System.out.println("Liczba iteracji: " + iteracje);
        return vectorAfter;
    }

    private double calculateFraction(Vector v, Matrix m) {
        var meter = v.getX() * v.getX() + v.getY() * v.getY();
        var denominator = (v.getX() * m.getLeftTopCorner()
                                + v.getY() * m.getLeftBottomCorner()) * v.getX() +
                        (v.getX() * m.getRightTopCorner()
                                + v.getY() * m.getRightBottomCorner()) * v.getY();

        return meter / denominator;
    }
}

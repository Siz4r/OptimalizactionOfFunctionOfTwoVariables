import static FunctionOfTwoVariables.FunctionAndDeriratives.*;

public class NewtonsMethod {
    private final FunctionOfTwoVariablesCommand command;
    private int iteracje = 1;

    public NewtonsMethod(FunctionOfTwoVariablesCommand command) {
        this.command = command;
    }

    public Vector calculate() {
        var vectorBefore = new Vector(command.getStartingPoint().getX(), command.getStartingPoint().getY());
        var vectorAfter = new Vector(command.getStartingPoint().getX(), command.getStartingPoint().getY());

        while (!stopCondition(vectorBefore, vectorAfter) || iteracje == 1) {
            vectorBefore = vectorAfter;
            var matrix = new Matrix(doubleDerirativeAfterX(vectorBefore.getX(), vectorBefore.getY()),
                derirativeAfterXAndY(vectorBefore.getX(), vectorBefore.getY()),
                derirativeAfterXAndY(vectorBefore.getX(), vectorBefore.getY()),
                doubleDerirativeAfterY(vectorBefore.getX(), vectorBefore.getY()));

            var vectorOfDeriratives = new Vector(derirativeAfterX(
                    vectorBefore.getX(), vectorBefore.getY()),
                    derirativeAfterY(vectorBefore.getX(), vectorBefore.getY()));

            matrix.inverseMatrix();

            vectorAfter = new Vector(
                    matrix.getLeftTopCorner() * vectorOfDeriratives.getX() + matrix.getRightTopCorner() * vectorOfDeriratives.getY(),
                    matrix.getLeftBottomCorner() * vectorOfDeriratives.getX() + matrix.getRightBottomCorner() * vectorOfDeriratives.getY());

            vectorAfter.setX(vectorAfter.getX() * calculateInverseMatrixFraction(matrix));
            vectorAfter.setY(vectorAfter.getY() * calculateInverseMatrixFraction(matrix));

            iteracje++;
        }

        return new Vector(vectorBefore.getX() - vectorAfter.getX(),
                vectorBefore.getY() - vectorAfter.getY());
    }

    private boolean stopCondition(Vector v1, Vector v2) {
        return Math.abs(v2.getX() - v1.getX()) < command.getEpsilon() &&
                Math.abs(v2.getY() - v1.getY()) < command.getEpsilon();
    }

    private double calculateInverseMatrixFraction(Matrix m) {
        return 1 / (m.getLeftTopCorner() * m.getRightBottomCorner() - m.getLeftBottomCorner() * m.getRightTopCorner());
    }
}

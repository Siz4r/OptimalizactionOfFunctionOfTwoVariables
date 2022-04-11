import static FunctionOfTwoVariables.FunctionAndDeriratives.*;

public class NewtonsMethod {
    private final FunctionOfTwoVariablesCommand command;
    private int iteracje = 0;

    public NewtonsMethod(FunctionOfTwoVariablesCommand command) {
        this.command = command;
    }

    public Vector calculate() {
        var vectorBefore = new Vector(command.getStartingPoint().getX(), command.getStartingPoint().getY());
        var vectorAfter = new Vector(command.getStartingPoint().getX(), command.getStartingPoint().getY());

        do {
            vectorBefore = vectorAfter;
//            System.out.println("Vector before start loop: " + vectorBefore);
            var matrix = new Matrix(doubleDerirativeAfterX(vectorBefore.getX(), vectorBefore.getY()),
                derirativeAfterXAndY(vectorBefore.getX(), vectorBefore.getY()),
                derirativeAfterXAndY(vectorBefore.getX(), vectorBefore.getY()),
                doubleDerirativeAfterY(vectorBefore.getX(), vectorBefore.getY()));

//            System.out.println(matrix);

            var vectorOfDeriratives = new Vector(derirativeAfterX(
                    vectorBefore.getX(), vectorBefore.getY()),
                    derirativeAfterY(vectorBefore.getX(), vectorBefore.getY()));

//            System.out.println(vectorOfDeriratives);

            matrix.inverseMatrix();

            vectorAfter = new Vector(
                    matrix.getLeftTopCorner() * vectorOfDeriratives.getX() + matrix.getRightTopCorner() * vectorOfDeriratives.getY(),
                    matrix.getLeftBottomCorner() * vectorOfDeriratives.getX() + matrix.getRightBottomCorner() * vectorOfDeriratives.getY());


            vectorAfter.setX(vectorAfter.getX() * calculateInverseMatrixFraction(matrix));
            vectorAfter.setY(vectorAfter.getY() * calculateInverseMatrixFraction(matrix));

//            if (!stopCondition(vectorBefore, vectorAfter)) {
//                isDone = true;
//            }

            vectorAfter.setX(vectorBefore.getX() - vectorAfter.getX());
            vectorAfter.setY(vectorBefore.getY() - vectorAfter.getY());

            iteracje++;
        } while (!stopCondition(vectorBefore, vectorAfter));

        System.out.println("Liczba iteracji: " + iteracje);

        return vectorAfter;
    }

    private boolean stopCondition(Vector v1, Vector v2) {
        var x = derirativeAfterX(v2.getX(), v2.getY());
        var y = derirativeAfterY(v2.getX(), v2.getY());

        return (Math.abs(v2.getX() - v1.getX()) <= command.getEpsilon() &&
                Math.abs(v2.getY() - v1.getY()) <= command.getEpsilon()) ||(Math.abs(x) <= command.getEpsilon() && Math.abs(y) <= command.getEpsilon());
    }

    private double calculateInverseMatrixFraction(Matrix m) {
        return 1 / (m.getLeftTopCorner() * m.getRightBottomCorner() - m.getLeftBottomCorner() * m.getRightTopCorner());
    }
}

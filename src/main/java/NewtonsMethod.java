import static FunctionOfTwoVariables.FunctionAndDeriratives.*;

public class NewtonsMethod extends FunctionOfTwoVariablesMethods{
    private int iteracje = 0;

    public NewtonsMethod(FunctionOfTwoVariablesCommand command) {
        super(command);
    }

    public Vector calculate() {
        var vectorBefore = new Vector(command.getStartingPoint());
        var vectorAfter = new Vector(command.getStartingPoint());

        do {
            vectorBefore.setX(vectorAfter.getX());
            vectorBefore.setY(vectorAfter.getY());

            double x = vectorBefore.getX(), y = vectorBefore.getY();

            var matrix = new Matrix(doubleDerirativeAfterX(x, y),
                derirativeAfterXAndY(x, y),
                derirativeAfterXAndY(x, y),
                doubleDerirativeAfterY(x, y));


            var vectorOfDeriratives = new Vector(derirativeAfterX(x, y),
                                                derirativeAfterY(x, y));

            matrix.inverseMatrix();

            vectorAfter = new Vector(
                    matrix.getLeftTopCorner() * vectorOfDeriratives.getX() + matrix.getRightTopCorner() * vectorOfDeriratives.getY(),
                    matrix.getLeftBottomCorner() * vectorOfDeriratives.getX() + matrix.getRightBottomCorner() * vectorOfDeriratives.getY());


            vectorAfter.setX(vectorAfter.getX() * calculateInverseMatrixFraction(matrix));
            vectorAfter.setY(vectorAfter.getY() * calculateInverseMatrixFraction(matrix));


            vectorAfter.setX(x - vectorAfter.getX());
            vectorAfter.setY(y - vectorAfter.getY());

            iteracje++;
        } while (!stopCondition(vectorBefore, vectorAfter));

        System.out.println("Metoda Newtona");
        System.out.println("Liczba iteracji: " + iteracje);

        return vectorAfter;
    }

    private double calculateInverseMatrixFraction(Matrix m) {
        return 1 / (m.getLeftTopCorner() * m.getRightBottomCorner() - m.getLeftBottomCorner() * m.getRightTopCorner());
    }
}

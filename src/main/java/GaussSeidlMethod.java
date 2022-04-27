import static FunctionOfTwoVariables.FunctionAndDeriratives.*;

public class GaussSeidlMethod extends FunctionOfTwoVariablesMethods{
    private final TangentMethod tangentMethod = new TangentMethod();

    public GaussSeidlMethod(FunctionOfTwoVariablesCommand command) {
        super(command);
    }

    @Override
    Vector calculate() {
        var vector = new Vector(command.getStartingPoint());
        var x = vector.getX();
        var y = vector.getY();
        do {
            iteracje++;
            x = tangentMethod.calculateStycznychPoX(y);
            y = tangentMethod.calculateStycznychPoY(x);
            vector.setX(derirativeAfterX(x, y));
            vector.setY(derirativeAfterY(x, y));
        } while(!stopCondition(vector));


        System.out.println("Metoda Gaussa");
        System.out.println("Liczba iteracji: " + iteracje);
        return new Vector(x, y);
    }
}

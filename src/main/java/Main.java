import java.util.List;

public class Main {
    public static void main(String[] args) {
        var command  = new FunctionOfTwoVariablesCommand(

                0.0000001, new Vector(10, 10)
        );

        var methods = List.of(new NewtonsMethod(command),
                new SteepestDescentMethod(command),
                new GaussSeidlMethod(command));

        for (var method :
                methods) {
            System.out.println(method.calculate());
            System.out.println("---------");
        }
    }
}

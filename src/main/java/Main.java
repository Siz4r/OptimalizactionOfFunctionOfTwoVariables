import java.util.List;

public class Main {
    public static void main(String[] args) {
        var command  = new FunctionOfTwoVariablesCommand(
                0.00001, new Vector(3, 2)
        );

        var methods = List.of(new NewtonsMethod(command), new SteepestDescentMethod(command));

        for (var method :
                methods) {
            System.out.println(method.calculate());
            System.out.println("---------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        var newtons = new NewtonsMethod(new FunctionOfTwoVariablesCommand(
                0.00001, new Vector(3, 2)
        ));

        System.out.println(newtons.calculate());
    }
}

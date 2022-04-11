public class Main {
    public static void main(String[] args) {
        var newtons = new NewtonsMethod(new FunctionOfTwoVariablesCommand(
                0.1, new Vector(10, 12)
        ));

        Object str = "Hello world!";
        var str2 = str;

        System.out.println(str + " | " + str2);

        System.out.println(newtons.calculate());
    }
}

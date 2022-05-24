import vector.Vector;

import java.util.ArrayList;
import java.util.List;

public class Simplex extends FunctionOfTwoVariablesMethods{
    public Simplex(FunctionOfTwoVariablesCommand command) {
        super(command);
    }

    private final ArrayList<Double>  cjs = new ArrayList<>(List.of(1.0,2.0,0.0,0.0));
    private final ArrayList<Double>  ci = new ArrayList<>(List.of(0.0, 0.0));
    private final ArrayList<Double>  x3s = new ArrayList<>(List.of(1.0,1.0,1.0,0.0,10.0,10.0));
    private final ArrayList<Double> x4s = new ArrayList<>(List.of(-2.0, 1.0, 0.0, 1.0, 4.0,0.0));
    private final ArrayList<ArrayList<Double>> simpleMatrix = new ArrayList<>();

    private void fillMatrix() {
        simpleMatrix.add(0, cjs);
        simpleMatrix.add(1, x3s);
        simpleMatrix.add(2, x4s);
        for (int i = 1; i <= ci.size(); i++) {
            simpleMatrix.get(i).add(0, ci.get(i - 1));
        }
    }

    private double fun(double x, double y) {
        return x + 2 * y;
    }

    private double limitation1(double x, double y, double z) {
        return x + y + z;
    }

    private double limitation2(double x, double y, double z) {
        return -2 * x + y + z;
    }

    @Override
    Vector calculate() {

        var outputs = new ArrayList<Double>();
        fillMatrix();
        do {

            System.out.println("-------------------------");
            System.out.println("Iteracja nr: " + ++iteracje);
            printMatrix();
            outputs.clear();
            for (int i = 0; i < cjs.size(); i++) {
                var sum = 0.0;
                for (int j = 1; j <= ci.size(); j++) {
                    sum += simpleMatrix.get(j).get(i + 1) * simpleMatrix.get(j).get(0);
                }
                outputs.add(cjs.get(i) - sum);

            }
            if (areAllValuesNotPositive(outputs)) {
                return new Vector(simpleMatrix.get(1).get(simpleMatrix.get(1).size() - 2), simpleMatrix.get(2).get(simpleMatrix.get(2).size() - 2));
            }




            var indexOfBiggestValue1 = findMax(outputs);

            for (int i = 1; i <= 2; i++) {
                simpleMatrix.get(i).set(6, simpleMatrix.get(i).get(simpleMatrix.get(i).size() - 2) / simpleMatrix.get(i).get(indexOfBiggestValue1 + 1));
                System.out.println("Index: " + i + " := " + simpleMatrix.get(i));
            }

            var indexOfBiggestValue2 = findPositiveMin(new ArrayList<>(List.of(
                    simpleMatrix.get(1).get(simpleMatrix.get(1).size() - 1),
                    simpleMatrix.get(2).get(simpleMatrix.get(2).size() - 1))));

            System.out.println("Cross at matrix is: ["+indexOfBiggestValue1+"]["+indexOfBiggestValue2+"]");

            var valueAtCross = simpleMatrix.get(indexOfBiggestValue2 + 1).get(indexOfBiggestValue1 + 1);


            var row = simpleMatrix.get(indexOfBiggestValue2 + 1);

            for (int i = 1; i < simpleMatrix.get(indexOfBiggestValue2 + 1).size(); i++) {
                row.set(i, simpleMatrix.get(indexOfBiggestValue2 + 1).get(i) / valueAtCross);
            }

            System.out.println("Value at cross: " + valueAtCross);

            simpleMatrix.get(indexOfBiggestValue2 + 1).set(0, simpleMatrix.get(0).get(indexOfBiggestValue2));
            ci.set(indexOfBiggestValue2, ci.get(indexOfBiggestValue2));
            for (int i = 1; i < simpleMatrix.size(); i++) {
                if (i != indexOfBiggestValue2 + 1) {
                    var valueAtColumn = simpleMatrix.get(i).get(indexOfBiggestValue1 + 1);
                    for (int j = 1; j < simpleMatrix.get(i).size(); j++){
//                        System.out.println(simpleMatrix.get(i).get(j) - valueAtCross * );
                        simpleMatrix.get(i).set(j, simpleMatrix.get(i).get(j) - valueAtColumn * row.get(j));
                    }
                }
            }

        } while (true);
    }

    private boolean areAllValuesNotPositive(ArrayList<Double> values) {
        for (var v :
                values) {
            if (v > 0.0) return false;
        }
        return true;
    }

    private void printMatrix() {
        for (var array :
                simpleMatrix) {
            for (var v :
                    array) {
                System.out.print("|" + v);
            }
            System.out.println("|");
        }
    }

    private int findMax(ArrayList<Double> values) {
        Double max = Double.MIN_VALUE;
        for (var v :
                values) {
            if (v > max) max = v;
        }
        return values.indexOf(max);
    }

    private int findPositiveMin(ArrayList<Double> values) {
        Double min = Double.MAX_VALUE;
        for (var v :
                values) {
            if (v < min && v > 0) min = v;
        }
        return values.indexOf(min);
    }
}

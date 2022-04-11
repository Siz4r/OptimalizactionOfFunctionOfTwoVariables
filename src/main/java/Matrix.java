import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
    private final ArrayList<Double> firstRow;
    private final ArrayList<Double> secondRow;

    public Matrix(double x1, double y1, double x2, double y2) {
        this.firstRow = new ArrayList<>(Arrays.asList(x1, y1));
        this.secondRow = new ArrayList<>(Arrays.asList(x2, y2));
    }

    public double getLeftTopCorner() {
        return firstRow.get(0);
    }

    public double getLeftBottomCorner() {
        return firstRow.get(1);
    }

    public double getRightTopCorner() {
        return secondRow.get(0);
    }

    public double getRightBottomCorner() {
        return secondRow.get(1);
    }

    public void inverseMatrix() {
        double a = getLeftTopCorner(), d = getRightBottomCorner();

        firstRow.set(0, d);
        firstRow.set(1, -firstRow.get(1));
        secondRow.set(1, a);
        secondRow.set(0, -secondRow.get(0));
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "firstRow=" + firstRow +
                ", secondRow=" + secondRow +
                '}';
    }
}

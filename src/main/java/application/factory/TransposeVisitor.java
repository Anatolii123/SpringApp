package application.factory;

public class TransposeVisitor implements UnaryOperationVisitor {

    public Operations[][] visit(Matrix operand) {
        Operations[][] a = operand.getMatrix();
        System.out.println("Транспонированная матрица");
        for (int i = 0; i < operand.getA(); i++) {
            for (int j = 0; j < operand.getB(); j++) {
                if (j > i) {
                    Operations temp = a[i][j];
                    a[i][j] = a[j][i];
                    a[j][i] = temp;
                }
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        return a;
    }
}

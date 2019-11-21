package application.factory;

import application.exceptions.NotConsistentException;

public class MatrixMultiplicator implements MatrixOperationWithCheck {

    public Operations[][] perform(Matrix operand1, Matrix operand2){
        Operations[][] a = operand1.getMatrix();
        Operations[][] b = operand2.getMatrix();

        Operations[][] s = new Operations[operand1.getA()][operand2.getB()];

        System.out.println("Произведение матриц");
        for (int i = 0; i < operand1.getA(); i++) {
            for (int j = 0; j < operand2.getB(); j++) {
                for (int k = 0; k < operand1.getB(); k++) {
                    s[i][j] = (s[i][j] == null) ? a[i][k].mult(b[k][j]) : s[i][j].add(a[i][k].mult(b[k][j]));
                }
                System.out.print(s[i][j] + "\t");
            }
            System.out.println();
        }
        return s;
    }

    @Override
    public void checkArguments(Matrix first, Matrix second) throws NotConsistentException {
        if (!(first.getB() == second.getA())) {
            throw new NotConsistentException("Матрицы несогласованы: число столбцов первой матрицы - " +
                    first.getB() + ". " + "Число строк второй матрицы - " + second.getA() + ".");
        }
    }
}

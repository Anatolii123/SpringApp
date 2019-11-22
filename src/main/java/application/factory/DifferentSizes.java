package application.factory;

import application.exceptions.DifferentSizesException;

public abstract class DifferentSizes implements MatrixOperationWithCheck {
    public void checkArguments(Matrix first, Matrix second) throws DifferentSizesException {
        if ((first.getA() != second.getA()) || (first.getB() != second.getB())) {
            throw new DifferentSizesException("Матрицы разных размерностей. " +
                    "Размерность первой матрицы - " + first.getA() + " x " + first.getB() + ". " +
                    "Размерность второй матрицы - " + second.getA() + " x " + second.getB() + ".");
        }
    }
}

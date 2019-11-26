package application.factory;

@FunctionalInterface
public interface FunctionalCalcMatrices {
    Long[][] calculate(Matrix matrix1, Matrix matrix2);
}

package application.factory;

public interface MatrixOperationWithCheck extends MatrixOperation {
    void checkArguments(Matrix first, Matrix second);
}

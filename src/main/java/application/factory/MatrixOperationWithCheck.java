package application.factory;

import application.exceptions.DifferentSizesException;
import application.exceptions.NotConsistentException;

public interface MatrixOperationWithCheck extends MatrixOperation {
    void checkArguments(Matrix first, Matrix second) throws DifferentSizesException, NotConsistentException;
}

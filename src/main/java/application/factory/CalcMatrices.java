package application.factory;

import application.controller.CalcRequest;

@FunctionalInterface
public interface CalcMatrices {
    Long[][] calcMatrices(CalcRequest request, MatrixOperationWithCheck matrixOperation) throws Exception;
}

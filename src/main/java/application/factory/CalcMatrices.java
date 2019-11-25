package application.factory;

import application.controller.CalcRequest;

public interface CalcMatrices {
    public Long[][] calcMatrices(CalcRequest request,MatrixOperationWithCheck matrixOperation) throws Exception;
}

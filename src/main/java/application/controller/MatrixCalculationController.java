package application.controller;

import application.MatrixCalc;
import application.exceptions.DifferentSizesException;
import application.factory.*;
import org.springframework.web.bind.annotation.*;

import java.util.function.Consumer;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/Calc")
public class MatrixCalculationController {

    private CalcMatrices co2 = (calcRequest, matrixOperationWithCheck) ->
            new MatrixCalc().performOperation(matrixOperationWithCheck, calcRequest.getMatrix1(), calcRequest.getMatrix2());

    @PostMapping(value = "/Add")
    public Long[][] sumMatrices(@RequestBody CalcRequest request) {
        FunctionalCalcMatrices calculator = (matrix1, matrix2) -> {
            Long[][] result = new Long[matrix1.getA()][matrix2.getB()];
            for (int i = 0; i < matrix1.getA(); i++) {
                for (int j = 0; j < matrix2.getB(); j++) {
                    result[i][j] = ((MyLong) matrix1.getMatrix()[i][j].add(matrix2.getMatrix()[i][j])).getValue(); // криво, но для сокрости вот так
                }
            }
            return result;
        };
        Consumer<CalcRequest> checker = calcRequest -> {
            if (calcRequest.getMatrix1().getA() != calcRequest.getMatrix2().getA())
            throw new DifferentSizesException();
        };
        checker.accept(request);
        return calculator.calculate(request.getMatrix1(), request.getMatrix1());
    }

    @PostMapping(value = "/Substract")
    public Long[][] subMatrices(@RequestBody CalcRequest request) throws Exception {
        return co2.calcMatrices(request,new MatrixSubstractor());
    }

    @PostMapping(value = "/Multiply")
    public Long[][] multMatrices(@RequestBody CalcRequest request) throws Exception {
        return co2.calcMatrices(request,new MatrixMultiplicator());
    }
}

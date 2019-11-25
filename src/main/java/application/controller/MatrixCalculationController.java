package application.controller;

import application.exceptions.DifferentSizesException;
import application.exceptions.NotConsistentException;
import application.factory.*;
import org.springframework.web.bind.annotation.*;
import application.MatrixCalc;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/Calc")
public class MatrixCalculationController {


    public Long[][] calcMatrices(CalcRequest request, MatrixOperationWithCheck matrixOperation) throws Exception {
        return new MatrixCalc().performOperation(matrixOperation, request.getMatrix1(), request.getMatrix2());
    }


    @PostMapping(value = "/Add")
    public Long[][] sumMatrices(@RequestBody CalcRequest request) throws Exception {
        MatrixOperation mo = (m1, m2) -> {
            return null;
        };
        return calcMatrices(request, new MatrixSummator());
    }

    @PostMapping(value = "/Substract")
    public Long[][] subMatrices(@RequestBody CalcRequest request) throws Exception {
        return calcMatrices(request, new MatrixSubstractor());
    }

    @PostMapping(value = "/Multiply")
    public Long[][] multMatrices(@RequestBody CalcRequest request) throws Exception {
        return calcMatrices(request, new MatrixMultiplicator());
    }
}

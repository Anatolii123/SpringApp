package application.controller;

import application.MatrixCalc;
import application.factory.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/Calc")
public class MatrixCalculationController {

    public Long[][] calcMatrices(CalcRequest request, MatrixOperationWithCheck matrixOperation) throws Exception {
        return new MatrixCalc().performOperation(matrixOperation, request.getMatrix1(), request.getMatrix2());
    }

    CalcMatrices co2 = (c1,m1) -> {
        return calcMatrices(c1,m1);
    };

    @PostMapping(value = "/Add")
    public Long[][] sumMatrices(@RequestBody CalcRequest request) throws Exception {
        return co2.calcMatrices(request,new MatrixSummator());
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

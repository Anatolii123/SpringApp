package application.controller;

import application.MatrixCalc;
import application.factory.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/Calc")
public class MatrixCalculationController {

    CalcMatrices co2 = (calcRequest,matrixOperationWithCheck) -> {
        return new MatrixCalc().performOperation(matrixOperationWithCheck, calcRequest.getMatrix1(), calcRequest.getMatrix2());
    };

    @PostMapping(value = "/Add")
    public Long[][] sumMatrices(@RequestBody CalcRequest request) throws Exception {
        return co2.calcMatrices(request,new MatrixSummator());
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

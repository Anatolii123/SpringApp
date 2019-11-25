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

    CalcMatrices co2 = (m1) -> {
        return calcMatrices(new CalcRequest(),m1);
    };

//    MatrixOperation mo2 = (m1, m2) -> {
//        return new MatrixSubstractor().perform(m1,m2);
//    };
//    MatrixOperation mo3 = (m1, m2) -> {
//        return new MatrixMultiplicator().perform(m1,m2);
//    };

    @PostMapping(value = "/Add")
    public Long[][] sumMatrices(@RequestBody CalcRequest request) throws Exception {
        return co2.calcMatrices(new MatrixSummator());
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

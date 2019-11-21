package application.controller;

import application.factory.Matrix;
import application.factory.MatrixMultiplicator;
import application.factory.MatrixSubstractor;
import application.factory.MatrixSummator;
import org.springframework.web.bind.annotation.*;
import application.MatrixCalc;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/Calc")
public class MatrixCalculationController {

    @PostMapping(value = "/Add")
    public List<Matrix> sumMatrices(@RequestBody CalcRequest request, HttpSession session) {
        List<Matrix> result = new ArrayList<>();
        MatrixCalc matrixCalc = new MatrixCalc();
        //matrixCalc.performOperation(session, new MatrixSummator(), request.getMatrix1(), request.getMatrix2());
        return result;
    }

    @PostMapping(value = "/Subsctract", params = {"matrix1","matrix2"})
    public List<Matrix> subMatrices(@RequestParam("matrix1") List<Matrix> matrix1, @RequestParam("matrix2") List<Matrix> matrix2,
                                    HttpSession session) {
        List<Matrix> result = new ArrayList<>();
        MatrixCalc matrixCalc = new MatrixCalc();
        matrixCalc.performOperation(session, new MatrixSubstractor(), matrix1, matrix2);

        result.add(new Matrix());
        return result;
    }

    @PostMapping(value = "/Multiplicate", params = {"matrix1","matrix2"})
    public List<Matrix> multMatrices(@RequestParam("matrix1") List<Matrix> matrix1, @RequestParam("matrix2") List<Matrix> matrix2,
                                    HttpSession session) {
        List<Matrix> result = new ArrayList<>();
        MatrixCalc matrixCalc = new MatrixCalc();
        matrixCalc.performOperation(session, new MatrixMultiplicator(), matrix1, matrix2);
        return result;
    }
}

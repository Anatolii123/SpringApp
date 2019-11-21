package application.controller;

import application.factory.*;
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
    public Long[][] sumMatrices(@RequestBody CalcRequest request, HttpSession session) {
        List<Matrix> matrix1 = new ArrayList<>();
        matrix1.add(request.getMatrix1());
        List<Matrix> matrix2 = new ArrayList<>();
        matrix2.add(request.getMatrix2());
        Long[][] result = new Long[request.getMatrix1().getA()][request.getMatrix2().getB()];
        new MatrixCalc().performOperation(session, new MatrixSummator(), matrix1, matrix2);
        Operations[][] res = new Operations[request.getMatrix1().getA()][request.getMatrix2().getB()];
        for (int i = 1; i <= request.getMatrix1().getA(); i++) {
            for (int j = 1; j <= request.getMatrix2().getB(); j++) {
               result[i-1][j-1] = Long.parseLong(session.getAttribute("m3" + i + j).toString());
            }
        }
        Matrix resultMatrix = new Matrix();
        resultMatrix.setMatrix(res);
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

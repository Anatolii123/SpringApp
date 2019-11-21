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
        for (int i = 1; i <= request.getMatrix1().getA(); i++) {
            for (int j = 1; j <= request.getMatrix2().getB(); j++) {
               result[i-1][j-1] = Long.parseLong(session.getAttribute("m3" + i + j).toString());
            }
        }
        return result;
    }

    @PostMapping(value = "/Subsctract")
    public Long[][] subMatrices(@RequestBody CalcRequest request, HttpSession session) {
        List<Matrix> matrix1 = new ArrayList<>();
        matrix1.add(request.getMatrix1());
        List<Matrix> matrix2 = new ArrayList<>();
        matrix2.add(request.getMatrix2());
        Long[][] result = new Long[request.getMatrix1().getA()][request.getMatrix2().getB()];
        new MatrixCalc().performOperation(session, new MatrixSubstractor(), matrix1, matrix2);
        for (int i = 1; i <= request.getMatrix1().getA(); i++) {
            for (int j = 1; j <= request.getMatrix2().getB(); j++) {
                result[i-1][j-1] = Long.parseLong(session.getAttribute("m3" + i + j).toString());
            }
        }
        return result;
    }

    @PostMapping(value = "/Multiplicate")
    public Long[][] multMatrices(@RequestBody CalcRequest request, HttpSession session) {
        List<Matrix> matrix1 = new ArrayList<>();
        matrix1.add(request.getMatrix1());
        List<Matrix> matrix2 = new ArrayList<>();
        matrix2.add(request.getMatrix2());
        Long[][] result = new Long[request.getMatrix1().getA()][request.getMatrix2().getB()];
        new MatrixCalc().performOperation(session, new MatrixSubstractor(), matrix1, matrix2);
        for (int i = 1; i <= request.getMatrix1().getA(); i++) {
            for (int j = 1; j <= request.getMatrix2().getB(); j++) {
                result[i-1][j-1] = Long.parseLong(session.getAttribute("m3" + i + j).toString());
            }
        }
        return result;
    }
}

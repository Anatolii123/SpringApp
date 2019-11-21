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


    public Long[][] calcMatrices(CalcRequest request, HttpSession session, MatrixOperationWithCheck matrixOperation) throws Exception {
        if ((matrixOperation instanceof MatrixSummator ||
                matrixOperation instanceof MatrixSubstractor) &&
                (!(request.getMatrix1().getA() == request.getMatrix2().getA()) ||
                        !(request.getMatrix1().getB() == request.getMatrix2().getB()))) {
            session.setAttribute("CalcError","Матрицы разных размерностей. " +
                    "Размерность первой матрицы - " + request.getMatrix1().getA() + " x " + request.getMatrix2().getA() + ". " +
                    "Размерность второй матрицы - " + request.getMatrix1().getB() + " x " + request.getMatrix2().getB() + ".");
            throw new Exception(session.getAttribute("CalcError").toString());
        } else if (matrixOperation instanceof MatrixMultiplicator &&
                !(request.getMatrix1().getB() == request.getMatrix2().getA())) {
            session.setAttribute("CalcError","Матрицы несогласованы: число столбцов первой матрицы - " +
                    request.getMatrix1().getB() + ". " +
                    "Число строк второй матрицы - " + request.getMatrix2().getA() + ".");
            throw new Exception(session.getAttribute("CalcError").toString());
        }
        List<Matrix> matrix1 = new ArrayList<>();
        matrix1.add(request.getMatrix1());
        List<Matrix> matrix2 = new ArrayList<>();
        matrix2.add(request.getMatrix2());
        Long[][] result = new Long[request.getMatrix1().getA()][request.getMatrix2().getB()];
        new MatrixCalc().performOperation(session, matrixOperation, matrix1, matrix2);
        for (int i = 1; i <= request.getMatrix1().getA(); i++) {
            for (int j = 1; j <= request.getMatrix2().getB(); j++) {
                result[i-1][j-1] = Long.parseLong(session.getAttribute("m3" + i + j).toString());
            }
        }
        return result;
    }


    @PostMapping(value = "/Add")
    public Long[][] sumMatrices(@RequestBody CalcRequest request, HttpSession session) throws Exception {
        return calcMatrices(request, session, new MatrixSummator());
    }

    @PostMapping(value = "/Substract")
    public Long[][] subMatrices(@RequestBody CalcRequest request, HttpSession session) throws Exception {
        return calcMatrices(request, session, new MatrixSubstractor());
    }

    @PostMapping(value = "/Multiply")
    public Long[][] multMatrices(@RequestBody CalcRequest request, HttpSession session) throws Exception {
        return calcMatrices(request, session, new MatrixMultiplicator());
    }
}

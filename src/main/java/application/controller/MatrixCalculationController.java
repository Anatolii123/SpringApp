package application.controller;

import application.exceptions.DifferentSizesException;
import application.factory.*;
import org.springframework.web.bind.annotation.*;
import application.MatrixCalc;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/Calc")
public class MatrixCalculationController {


    public Long[][] calcMatrices(CalcRequest request, HttpSession session, MatrixOperationWithCheck matrixOperation) throws Exception {
//        if ((matrixOperation instanceof MatrixSummator ||
//                matrixOperation instanceof MatrixSubstractor) &&
//                (!(request.getMatrix1().getA() == request.getMatrix2().getA()) ||
//                        !(request.getMatrix1().getB() == request.getMatrix2().getB()))) {
//            session.setAttribute("CalcError","Матрицы разных размерностей. " +
//                    "Размерность первой матрицы - " + request.getMatrix1().getA() + " x " + request.getMatrix2().getA() + ". " +
//                    "Размерность второй матрицы - " + request.getMatrix1().getB() + " x " + request.getMatrix2().getB() + ".");
//            throw new Exception(session.getAttribute("CalcError").toString());
//        } else if (matrixOperation instanceof MatrixMultiplicator &&
//                !(request.getMatrix1().getB() == request.getMatrix2().getA())) {
//            session.setAttribute("CalcError","Матрицы несогласованы: число столбцов первой матрицы - " +
//                    request.getMatrix1().getB() + ". " +
//                    "Число строк второй матрицы - " + request.getMatrix2().getA() + ".");
//            throw new Exception(session.getAttribute("CalcError").toString());
//        }
        return new MatrixCalc().performOperation(matrixOperation, request.getMatrix1(), request.getMatrix2());
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

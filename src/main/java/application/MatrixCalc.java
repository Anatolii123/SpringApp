package application;

import application.exceptions.DifferentSizesException;
import application.exceptions.NotConsistentException;
import application.factory.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MatrixCalc {

    public String setMatrixToString(String matrix, String matrixRows, String matrixColumns, HttpServletRequest request, int matrixNumb) {
        String rows = request.getParameter(matrixRows);
        String columns = request.getParameter(matrixColumns);
        matrix += rows;
        matrix += columns;
        for (int i = 1; i <= Integer.parseInt(rows); i++) {
            for (int j = 1; j <= Integer.parseInt(columns); j++) {
                matrix += request.getParameter(matrixNumb + String.valueOf(i) + j) + " ";
            }
        }
        return matrix;
    }

    public List<Matrix> createSingleMatrixList(HttpServletRequest request, HttpSession session,
                                               MatrixReaderServletImpl matrixReaderFromServlet, int matrixNumb) {
        session.setAttribute("matrix" + matrixNumb + "_rows", request.getParameter("matrix" + matrixNumb + "_rows"));
        session.setAttribute("matrix" + matrixNumb + "_columns", request.getParameter("matrix" + matrixNumb + "_columns"));
        String matrix = "";
        matrix = setMatrixToString(matrix, "matrix" + matrixNumb + "_rows",
                "matrix" + matrixNumb + "_columns", request,matrixNumb);
        List<Matrix> singleMatrixList = matrixReaderFromServlet.readMatrix(matrix);
        return singleMatrixList;
    }

    public Long[][] performOperation(MatrixOperationWithCheck matrixOperationWithCheck, Matrix matrix1, Matrix matrix2) throws NotConsistentException, DifferentSizesException {
        Long[][] result = new Long[matrix1.getA()][matrix2.getB()];
        matrixOperationWithCheck.checkArguments(matrix1,matrix2);
        Operations[][] operation = matrixOperationWithCheck.perform(matrix1,matrix2);
        for (int i = 1; i <= operation.length; i++) {
            for (int j = 1; j <= operation[0].length; j++) {
                result[i-1][j-1] = Long.parseLong(operation[i-1][j-1].toString());
            }
        }
        return result;
    }

    public void doPost(HttpServletRequest request, HttpSession session) {
        MatrixReaderServletImpl matrixReaderFromServlet = new MatrixReaderServletImpl();
        List<Matrix> firstMatrix = createSingleMatrixList(request,session,matrixReaderFromServlet,1);
        List<Matrix> secondMatrix = createSingleMatrixList(request,session,matrixReaderFromServlet,2);

        if ((request.getParameter("Operation").equals("Sum") ||
                request.getParameter("Operation").equals("Sub")) &&
                (!request.getParameter("matrix1_rows").equals(request.getParameter("matrix2_rows")) ||
                !request.getParameter("matrix1_columns").equals(request.getParameter("matrix2_columns")))) {
            session.setAttribute("CalcError","Матрицы разных размерностей. " +
                    "Размерность первой матрицы - " + request.getParameter("matrix1_rows") + "x" + request.getParameter("matrix2_rows") + ". " +
                    "Размерность второй матрицы - " + request.getParameter("matrix1_columns") + "x" + request.getParameter("matrix2_columns") + ".");
            return;
        } else if (request.getParameter("Operation").equals("Mult") &&
                !request.getParameter("matrix1_columns").equals(request.getParameter("matrix2_rows"))) {
            session.setAttribute("CalcError","Матрицы несогласованы: число столбцов первой матрицы - " +
                    request.getParameter("matrix1_columns") + ". " +
                    "Число строк второй матрицы - " + request.getParameter("matrix2_rows") + ".");
            return;
        }
//        else if((request.getParameter("Operation").equals("Sum"))) {
//            MatrixSummator matrixSummator = new MatrixSummator();
//            performOperation(session,matrixSummator,firstMatrix,secondMatrix);
//            return;
//        } else if ((request.getParameter("Operation").equals("Sub"))) {
//            MatrixSubstractor matrixSubstractor = new MatrixSubstractor();
//            performOperation(session,matrixSubstractor,firstMatrix,secondMatrix);
//            return;
//        } else if ((request.getParameter("Operation").equals("Mult"))) {
//            MatrixMultiplicator matrixMultiplicator = new MatrixMultiplicator();
//            performOperation(session,matrixMultiplicator,firstMatrix,secondMatrix);
//            return;
//        }
    }
}

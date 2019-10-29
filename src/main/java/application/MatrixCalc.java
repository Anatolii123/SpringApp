package application;

import application.factory.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MatrixCalc")
public class MatrixCalc extends HttpServlet {

    public String setMatrixToString(String matrix, String matrixRows, String matrixColumns, HttpServletRequest request, int matrixNumb) {
        String rows = request.getParameter(matrixRows);
        String columns = request.getParameter(matrixColumns);
        matrix += rows;
        matrix += columns;
        for (int i = 1; i <= Integer.parseInt(rows); i++) {
            for (int j = 1; j <= Integer.parseInt(columns); j++) {
                matrix += request.getParameter(String.valueOf(matrixNumb) + String.valueOf(i) + String.valueOf(j)) + " ";
            }
        }
        return matrix;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("matrix1_rows", request.getParameter("matrix1_rows"));
        request.getSession().setAttribute("matrix1_columns", request.getParameter("matrix1_columns"));
        request.getSession().setAttribute("matrix2_rows", request.getParameter("matrix2_rows"));
        request.getSession().setAttribute("matrix2_columns", request.getParameter("matrix2_columns"));
        String matrix1 = new String();
        String matrix2 = new String();
        matrix1 = setMatrixToString(matrix1, "matrix1_rows", "matrix1_columns", request,1);
        matrix2 = setMatrixToString(matrix2, "matrix2_rows", "matrix2_columns", request,2);
        MatrixReaderServletImpl matrixReader_from_servlet = new MatrixReaderServletImpl();
        List<Matrix> firstMatrix = matrixReader_from_servlet.readMatrix(matrix1);
        List<Matrix> secondMatrix = matrixReader_from_servlet.readMatrix(matrix2);
        if ((request.getParameter("Operation").equals("Sum") ||
                request.getParameter("Operation").equals("Sub")) &&
                (!request.getParameter("matrix1_rows").equals(request.getParameter("matrix2_rows")) ||
                !request.getParameter("matrix1_columns").equals(request.getParameter("matrix2_columns")))) {
            request.getSession().setAttribute("CalcError","Матрицы разных размерностей. " +
                    "Размерность первой матрицы - " + request.getParameter("matrix1_rows") + "x" + request.getParameter("matrix2_rows") + ". " +
                    "Размерность второй матрицы - " + request.getParameter("matrix1_columns") + "x" + request.getParameter("matrix2_columns") + ".");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
            return;
        } else if (request.getParameter("Operation").equals("Mult") &&
                !request.getParameter("matrix1_columns").equals(request.getParameter("matrix2_rows"))) {
            request.getSession().setAttribute("CalcError","Матрицы несогласованы: число столбцов первой матрицы - " +
                    request.getParameter("matrix1_columns") + ". " +
                    "Число строк второй матрицы - " + request.getParameter("matrix2_rows") + ".");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
            return;
        } else if((request.getParameter("Operation").equals("Sum"))) {
            request.getSession().setAttribute("CalcError",null);
            MatrixSummator matrixSummator = new MatrixSummator();
            Operations[][] sum = matrixSummator.perform(firstMatrix.get(0),secondMatrix.get(0));
            for (int i = 1; i <= sum.length; i++) {
                for (int j = 1; j <= sum[0].length; j++) {
                    request.getSession().setAttribute("m3" + i + j,sum[i-1][j-1]);
                }
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
            return;
        } else if ((request.getParameter("Operation").equals("Sub"))) {
            request.getSession().setAttribute("CalcError",null);
            MatrixSubstractor matrixSubstractor = new MatrixSubstractor();
            Operations[][] sub = matrixSubstractor.perform(firstMatrix.get(0),secondMatrix.get(0));
            for (int i = 1; i <= sub.length; i++) {
                for (int j = 1; j <= sub[0].length; j++) {
                    request.getSession().setAttribute("m3" + i + j,sub[i-1][j-1]);
                }
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
            return;
        } else if ((request.getParameter("Operation").equals("Mult"))) {
            request.getSession().setAttribute("CalcError",null);
            MatrixMultiplicator matrixMultiplicator = new MatrixMultiplicator();
            Operations[][] mult = matrixMultiplicator.perform(firstMatrix.get(0),secondMatrix.get(0));
            for (int i = 1; i <= mult.length; i++) {
                for (int j = 1; j <= mult[0].length; j++) {
                    request.getSession().setAttribute("m3" + i + j,mult[i-1][j-1]);
                }
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
            return;
        }

        request.getSession().setAttribute("CalcError",request.getParameter("111") + request.getParameter("112"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

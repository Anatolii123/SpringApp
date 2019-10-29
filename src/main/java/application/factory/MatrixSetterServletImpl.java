package application.factory;

import java.util.ArrayList;
import java.util.List;

public class MatrixSetterServletImpl implements MatrixSetter {

    private String matrix;

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }



    @Override
    public void setMatrixTo(String m, List<Matrix> matrices2) {
        List<String> lines = new ArrayList<String>();
        for (int j = 0; j < matrices2.get(0).getA(); j++) {
            lines.add(readInternal(matrices2.get(0)).get(j));
            matrix += readInternal(matrices2.get(0)).get(j);
        }
    }

    @Override
    public List<String> readInternal(Matrix matrix) {
        List<String> lines = new ArrayList<String>();
        String[] String_Array = new String[matrix.getMatrix().length];
        for (int i = 0; i < String_Array.length; i++) {
            for (int j = 0; j < matrix.getMatrix()[0].length; j++) {
                if (j == 0) {
                    String_Array[i] = ((Integer)(((MyDouble)matrix.getMatrix()[i][j]).value.intValue())).toString();
                    continue;
                }
                if (j < matrix.getMatrix()[0].length) {
                    String_Array[i] = String_Array[i]+((Integer)(((MyDouble)matrix.getMatrix()[i][j]).value.intValue())).toString();
                    continue;
                }
            }
        }
        for (int i=0;i<String_Array.length;i++) {
            lines.add(String_Array[i]);
        }
        return lines;
    }
}

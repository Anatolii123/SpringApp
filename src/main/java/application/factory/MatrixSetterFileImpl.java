package application.factory;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MatrixSetterFileImpl implements MatrixSetter {

    @Override
    public void setMatrixTo(String filepath, List<Matrix> matrices2) throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter(filepath));
        for (int i = 0; i < matrices2.size(); i++) {
            for (int j = 0; j < matrices2.get(i).getA(); j++) {
                out.write(readInternal(matrices2.get(i)).get(j));
                out.write("\n");
            }
            if (i < matrices2.size()-1) {
                out.write("\n");
            }
        }
        out.close();
    }

    @Override
    public List<String> readInternal(Matrix matrix) {
        List<String> lines = new ArrayList<String>();
        String[] String_Array = new String[matrix.getMatrix().length];
        for (int i = 0; i < String_Array.length; i++) {
            for (int j = 0; j < matrix.getMatrix()[0].length; j++) {
                if (j == 0) {
                    String_Array[i] = ((Integer)(((MyDouble)matrix.getMatrix()[i][j]).value.intValue())).toString()+",";
                    continue;
                }
                if (j < matrix.getMatrix()[0].length-1) {
                    String_Array[i] = String_Array[i]+((Integer)(((MyDouble)matrix.getMatrix()[i][j]).value.intValue())).toString()+",";
                    continue;
                }
                String_Array[i] = String_Array[i]+((Integer)(((MyDouble)matrix.getMatrix()[i][j]).value.intValue())).toString();
            }
        }
        for (int i=0;i<String_Array.length;i++) {
           lines.add(String_Array[i]);
        }
        return lines;
    }

}

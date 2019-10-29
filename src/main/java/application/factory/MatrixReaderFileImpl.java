package application.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MatrixReaderFileImpl implements MatrixReader {

    @Override
    public List<Matrix> readMatrix(String filepath) throws Exception {
        String line;
        List<String> lines = new ArrayList<String>();
        List<List> matrices = new ArrayList<List>();
        List<Matrix> matrices2 = new ArrayList<Matrix>();
        Object[] linesAsArray;
        BufferedReader br = new BufferedReader(new FileReader(filepath));

        while ((line = br.readLine()) != null) {
            if (line.length() == 0) {
                matrices.add(new ArrayList<String>(lines));
                lines.clear();
                continue;
            }
            lines.add(line);
        }
        matrices.add(new ArrayList<String>(lines));
        for (int i = 0; i < matrices.size(); i++) {
            linesAsArray = matrices.get(i).toArray(new String[matrices.get(i).size()]);
            matrices2.add(setInternal(matrices.get(i), linesAsArray));
            System.out.println();
        }
        System.out.println(matrices.size());
        br.close();
        return matrices2;
    }

    @Override
    public Matrix setInternal(List<String> lines, Object[] linesAsArray) {
        Matrix mat = new Matrix();
        if (linesAsArray.length != 0) {
            String String_Array[] = new String[linesAsArray.length];
            for (int i=0;i<String_Array.length;i++)
                String_Array[i]=linesAsArray[i].toString();
            int[][] matrix = new int[lines.size()][String_Array[0].split(",").length];
            Operations[][] matrix2 = new Operations[matrix.length][matrix[0].length];
            for(int i = 0; i < lines.size(); i++) {
                for(int j = 0; j < String_Array[0].split(",").length; j++) {
                    matrix[i][j] = Integer.parseInt(String_Array[i].split(",")[j]);
                    matrix2[i][j] = new MyDouble((double) matrix[i][j]);
                    System.out.print(matrix[i][j] + "\t");
                }
                System.out.println();
            }
            mat.setMatrix(matrix2);
            mat.setA(matrix.length);
            mat.setB(matrix[0].length);
        }
        return mat;
    }
}

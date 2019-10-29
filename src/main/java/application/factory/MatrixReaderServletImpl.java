package application.factory;

import java.util.ArrayList;
import java.util.List;

public class MatrixReaderServletImpl implements MatrixReader {

    public List<Integer> divideRows(String matrix) {
        List<Integer> borders = new ArrayList<Integer>();
        for(int i = 0; i < matrix.length(); i++){
            if(matrix.charAt(i) == ' ') {
                borders.add(i);
            }
        }
        return borders;
    }

    @Override
    public List<Matrix> readMatrix(String matrix) {
        int rows = Character.getNumericValue(matrix.charAt(0));
        int columns = Character.getNumericValue(matrix.charAt(1));
        String line;
        line = matrix.substring(2,matrix.length());
        List<String> lines = new ArrayList<String>();
        List<Matrix> matrices2 = new ArrayList<Matrix>();
        Object[] linesAsArray;
        for (int i = 0; i < rows; i++) {
            if (i == 0) {
                lines.add(line.substring(0,divideRows(line).get(columns-1)));
                continue;
            }
            lines.add(line.substring(divideRows(line).get(columns*i-1)+1,divideRows(line).get(columns*(i+1)-1)));
        }
        linesAsArray = lines.toArray(new String[lines.size()]);
        matrices2.add(setInternal(lines, linesAsArray));
        return matrices2;
    }

    @Override
    public Matrix setInternal(List<String> lines, Object[] linesAsArray) {
        Matrix mat = new Matrix();
        if (linesAsArray.length != 0) {
            String String_Array[] = new String[linesAsArray.length];
            for (int i=0;i<String_Array.length;i++)
                String_Array[i]=linesAsArray[i].toString();
            int[][] matrix = new int[lines.size()][String_Array[0].split(" ").length];
            Operations[][] matrix2 = new Operations[matrix.length][matrix[0].length];
            for(int i = 0; i < lines.size(); i++) {
                for(int j = 0; j < String_Array[0].split(" ").length; j++) {
                    matrix[i][j] = Integer.parseInt(String_Array[i].split(" ")[j]);
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

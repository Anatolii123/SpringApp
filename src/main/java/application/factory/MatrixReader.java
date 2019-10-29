package application.factory;

import java.util.List;

public interface MatrixReader {
    public List<Matrix> readMatrix(String string) throws Exception;
    public Matrix setInternal(List<String> lines, Object[] linesAsArray);
}

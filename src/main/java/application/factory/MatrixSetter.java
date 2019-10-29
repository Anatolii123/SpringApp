package application.factory;

import java.util.List;

public interface MatrixSetter {
    public void setMatrixTo(String filepath, List<Matrix> matrices2) throws Exception;
    public List<String> readInternal(Matrix matrix);
}

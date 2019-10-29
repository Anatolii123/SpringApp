package application.factory;

public class MatrixMaker {
    private MatrixBuilder matrixBuilder;

    public MatrixMaker(MatrixBuilder matrixBuilder) {
        this.matrixBuilder = matrixBuilder;
    }

    public Matrix make(int a, int b) {
        if (a == 0) {
            throw new NullPointerException("");
        }
        if (b == 0) {
            throw new NullPointerException("");
        }
        if (matrixBuilder == null) {
            throw new NullPointerException("");
        }

        matrixBuilder.setA(a);
        matrixBuilder.setB(b);
        return matrixBuilder.toMatrix();
    }
}

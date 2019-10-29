package application.factory;

public class MatrixBuilder {
    private ContentCreator creator;
    private Integer a;
    private Integer b;

    public MatrixBuilder(ContentCreator creator) { // todo настраиваем класс-билдер при создании
        this.creator = creator;
    }

    public MatrixBuilder setA(Integer a) {
        this.a = a;
        return this;
    }
    public MatrixBuilder setB(Integer b) {
        this.b = b;
        return this;
    }

    public Matrix toMatrix(){
        Matrix result = new Matrix();
        if (creator == null) {
            throw new NullPointerException("");
        }
        result.setMatrix(creator.create(a, b));
        result.setA(a);
        result.setB(b);
        return result;
    }
}

package application.factory;

public abstract class ParamMatrix<T extends Number>  {
    private int a;
    private int b;
    private MyNumeric<T>[][] content;

    public ParamMatrix(){}

    public ParamMatrix(int a, int b, String[] args) {
        this.a = a;
        this.b = b;
        createContent(a, b, args);
    }

    /**
     * Метод формирует двумерный массив content заполняя его данными нужного типа
     */
    public abstract MyNumeric<T>[][] createContent(int a, int b, String[] args);

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public MyNumeric<T>[][] getContent() {
        return content;
    }

    public void setContent(MyNumeric<T>[][] content) {
        this.content = content;
    }

    /**
     * Метод прибавляет к матрице другую матрицу, переданную в operand
     * @param operand матрица, которую нужно пибавить
     * @return матрица, представляющая собой сумму
     */
    public ParamMatrix<T> add(ParamMatrix<T> operand) {
        System.out.println("Сумма матриц");
        for (int i = 0; i < operand.getA(); i++) {
            for(int j = 0; j < operand.getB(); j++){
                this.getContent()[i][j].setValue(this.getContent()[i][j].add(operand.getContent()[i][j]));
                System.out.print(this.getContent()[i][j].value + "\t");
            }
            System.out.println();
        }
        return this;
    }
}

package application.factory;

public class MultiplicationVisitor implements Visitor {

    private MyLong myLong = new MyLong(10L);
    private MyDouble myDouble = new MyDouble(10.0);
    private Matrix operand;
    private MyLong longOperand;
    private MyDouble doubleOperand;

    private MatrixOperation mult = new MatrixMultiplicator();

    public MultiplicationVisitor(Matrix operand) {
        this.operand = operand;
    }
    public MultiplicationVisitor(MyLong longOperand) {
        this.longOperand = longOperand;
    }
    public MultiplicationVisitor(MyDouble doubleOperand) {
        this.doubleOperand = doubleOperand;
    }

    public void visit(Matrix firstOperand) {
        mult.perform(firstOperand, operand);
    }

    public MyLong visit(MyLong operand) {
        MyLong result = (MyLong) longOperand.mult(operand);
        return result;
    }

    public MyDouble visit(MyDouble operand) {
        MyDouble result = (MyDouble) doubleOperand.mult(operand);
        return result;
    }
}

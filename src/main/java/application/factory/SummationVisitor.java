package application.factory;

public class SummationVisitor implements Visitor {
    private MyLong myLong = new MyLong(10L);
    private MyDouble myDouble = new MyDouble(10.0);
    private Matrix operand;
    private MyLong longOperand;
    private MyDouble doubleOperand;

    private MatrixOperation sum = new MatrixSummator();

    public SummationVisitor(Matrix operand) {
        this.operand = operand;
    }
    public SummationVisitor(MyLong longOperand) {
        this.longOperand = longOperand;
    }
    public SummationVisitor(MyDouble doubleOperand) {
        this.doubleOperand = doubleOperand;
    }

    public void visit(Matrix firstOperand) {
        sum.perform(firstOperand, operand);
    }

    public MyLong visit(MyLong operand) {
        MyLong result = (MyLong) longOperand.add(operand);
        return result;
    }

    public MyDouble visit(MyDouble operand) {
        MyDouble result = (MyDouble) doubleOperand.add(operand);
        return result;
    }
}

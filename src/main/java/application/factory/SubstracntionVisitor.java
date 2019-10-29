package application.factory;

public class SubstracntionVisitor implements Visitor {

    private MyLong myLong = new MyLong(10L);
    private MyDouble myDouble = new MyDouble(10.0);
    private Matrix operand;
    private MyLong longOperand;
    private MyDouble doubleOperand;

    private MatrixOperation sub = new MatrixSubstractor();

    public SubstracntionVisitor(Matrix operand) {
        this.operand = operand;
    }
    public SubstracntionVisitor(MyLong longOperand) {
        this.longOperand = longOperand;
    }
    public SubstracntionVisitor(MyDouble doubleOperand) {
        this.doubleOperand = doubleOperand;
    }

    public void visit(Matrix firstOperand) {
        sub.perform(firstOperand, operand);
    }

    public MyLong visit(MyLong operand) {
        MyLong result = (MyLong) longOperand.sub(operand);
        return result;
    }

    public MyDouble visit(MyDouble operand) {
        MyDouble result = (MyDouble) doubleOperand.sub(operand);
        return result;
    }
}

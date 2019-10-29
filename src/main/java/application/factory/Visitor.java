package application.factory;

public interface Visitor {
    void visit(Matrix firstOperand);
    MyLong visit(MyLong operand);
    MyDouble visit(MyDouble operand);
}

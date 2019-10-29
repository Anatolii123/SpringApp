package application.factory;

public interface UnaryOperationVisitor {
    //транспонировать
    Operations[][] visit(Matrix operand);
}

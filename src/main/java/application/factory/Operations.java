package application.factory;

public interface Operations {

    Operations add(Operations operations);
    Operations sub(Operations operations);
    Operations mult(Operations operations);
    Operations accept(Visitor visitor);
}

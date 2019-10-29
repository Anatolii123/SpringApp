package application.factory;

public abstract class MyNumeric<T extends Number> implements Operations {
    protected T value; // inject

    public abstract Operations add(Operations operations);

    public abstract Operations sub(Operations operations);

    public abstract Operations mult(Operations operations);

    public abstract Operations accept(Visitor visitor);

    public abstract void setValue(Operations value);
}

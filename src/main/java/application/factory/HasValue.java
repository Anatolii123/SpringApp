package application.factory;

public interface HasValue<T extends Number> {
    T getValue();
    void setValue(T value);

}

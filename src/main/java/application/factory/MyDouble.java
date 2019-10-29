package application.factory;

public class MyDouble extends MyNumeric<Double> {

    public MyDouble(Double value) {
        this.value = value;
    }

    public Operations add(Operations operations) {
        if(!(operations instanceof MyDouble)){
            throw new ArithmeticException("Operation is not possible");
        }
        MyDouble that = (MyDouble) operations;
        MyDouble result = new MyDouble(this.value + that.value);
        return result;
    }

    public Operations sub(Operations operations) {
        if(!(operations instanceof MyDouble)){
            throw new ArithmeticException("Operation is not possible");
        }
        MyDouble that = (MyDouble) operations;
        MyDouble result = new MyDouble(this.value - that.value);
        return result;
    }

    public Operations mult(Operations operations) {
        if(!(operations instanceof MyDouble)){
            throw new ArithmeticException("Operation is not possible");
        }
        MyDouble that = (MyDouble) operations;
        MyDouble result = new MyDouble(this.value * that.value);
        return result;
    }

    public Operations accept(Visitor visitor) {
        return this;
    }

    public void setValue(Operations value) {
        if (value == null) {
            throw new NullPointerException("Null Object");
        }
        if(!(value instanceof MyDouble) && !(value instanceof MyLong)){
            throw new ArithmeticException("Value is not an instance of MyDouble and MyLong");
        }
        this.value = ((MyDouble) value).value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}

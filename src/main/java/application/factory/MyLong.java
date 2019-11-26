package application.factory;

public class MyLong extends MyNumeric<Long> {

    public MyLong(Long value) {
        this.value = value;
    }
    public MyLong(){
        super();
    }

    public Operations add(Operations operations) {
        if(!(operations instanceof MyLong)){
            throw new ArithmeticException("Operation is not defined");
        }
        MyLong that = (MyLong) operations;
        MyLong result = new MyLong(this.value + that.value);
        return result;
    }

    public Operations sub(Operations operations) {
        if(!(operations instanceof MyLong)){
            throw new ArithmeticException("Operation is not possible");
        }
        MyLong that = (MyLong) operations;
        MyLong result = new MyLong(this.value - that.value);
        return result;
    }

    public Operations mult(Operations operations) {
        if(!(operations instanceof MyLong)){
            throw new ArithmeticException("Operation is not possible");
        }
        MyLong that = (MyLong) operations;
        MyLong result = new MyLong(this.value * that.value);
        return result;
    }

    public MyLong accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public void setLong(Long value) {
        this.value = value;
    }

    public void setValue(Operations value) {
        if (value == null) {
            throw new NullPointerException("Null Object");
        }
        if(!(value instanceof MyLong)){
            throw new ArithmeticException("Value is not an instance of MyLong");
        }
        this.value = ((MyLong) value).value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public Long getValue(){
        return this.value;
    }
}

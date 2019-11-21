package application.factory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as=MyLong.class)
public interface Operations {

    Operations add(Operations operations);
    Operations sub(Operations operations);
    Operations mult(Operations operations);
    Operations accept(Visitor visitor);
}

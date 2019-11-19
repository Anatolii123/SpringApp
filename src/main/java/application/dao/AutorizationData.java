package application.dao;

import java.math.BigInteger;
import java.util.Date;

public class AutorizationData {
    private String salt;
    private Date date;
    private BigInteger key;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigInteger getKey() {
        return key;
    }

    public void setKey(BigInteger key) {
        this.key = key;
    }
}

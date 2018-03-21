package igye.springhibernate.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Measurement {
    protected String name;
    protected String symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
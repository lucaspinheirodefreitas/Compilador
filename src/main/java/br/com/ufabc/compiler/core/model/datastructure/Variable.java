package br.com.ufabc.compiler.core.model.datastructure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Variable extends Symbol {
    public static final int NUMBER  = 0;
    public static final int TEXT    = 1;

    private int type;
    private String value;
    private boolean referenced = false;
    private boolean used = false;

    public Variable(String name, int type, String value) {
        super(name);
        this.type  = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package br.com.ufabc.compiler.core.model.datastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Symbol {
    protected String name;

    @Override
    public String toString() {
        return "Symbol [name=" + name + "]";
    }
}

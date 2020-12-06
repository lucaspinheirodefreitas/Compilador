package br.com.ufabc.compiler.core.model.datastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class SymbolTable {
    // @todo -> validar o comportamento do @Getter no caso de hashMap
    // @todo -> entender o funcionamento do HashMap.
    private HashMap<String, Symbol> map;

    public SymbolTable() {
        map = new HashMap<String, Symbol>();
    }

    public void add(Symbol symbol) {
        map.put(symbol.getName(), symbol);
    }

    public boolean exists(String symbolName) {
        return map.get(symbolName) != null;
    }
}

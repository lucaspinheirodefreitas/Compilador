package br.com.ufabc.compiler.core.model.datastructure;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class SymbolTable {
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

    public ArrayList<Symbol> getAll() {
        ArrayList<Symbol> lista = new ArrayList<Symbol>();
        for (Symbol symbol : map.values()) {
            lista.add(symbol);
        }
        return lista;
    }
}

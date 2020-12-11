package br.com.ufabc.compiler.core.structure;

import br.com.ufabc.compiler.core.model.datastructure.SymbolTable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Program {
    private SymbolTable varTable;
    private ArrayList<AbstractCommand> commands;
    private String programName;

    public void generateTarget() {

    }
}

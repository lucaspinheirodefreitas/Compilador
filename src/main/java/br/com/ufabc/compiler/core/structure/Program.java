package br.com.ufabc.compiler.core.structure;

import br.com.ufabc.compiler.core.model.datastructure.Symbol;
import br.com.ufabc.compiler.core.model.datastructure.SymbolTable;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

@Getter
@Setter
public class Program {
    private SymbolTable varTable;
    private ArrayList<AbstractCommand> commands;
    private String programName;

    public void generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("import java.util.Scanner;\n");
        str.append("public class MainClass{ \n");
        str.append("  public static void main(String args[]){\n ");
        str.append("      Scanner _key = new Scanner(System.in);\n");
        for (Symbol symbol: varTable.getAll()) {
            str.append(symbol.generateJavaCode()+"\n");
        }
        for (AbstractCommand command: commands) {
            str.append(command.generateJavaCode()+"\n");
        }
        str.append("  }");
        str.append("}");

        try {
            FileWriter fr = new FileWriter(new File("MainClass.java"));
            fr.write(str.toString());
            fr.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

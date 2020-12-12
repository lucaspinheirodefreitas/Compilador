package br.com.ufabc.compiler.core.structure;

import br.com.ufabc.compiler.core.model.datastructure.Variable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandRead extends AbstractCommand {
    private String id;
    private Variable variable;

    @Override
    public String generateJavaCode() {
        return id +"= _key." + (variable.getType()==variable.NUMBER? "nextDouble();": "nextLine();");
    }

    @Override
    public String toString() {
        return "CommandRead{" +
                "id='" + id + '\'' +
                ", variable=" + variable +
                '}';
    }
}

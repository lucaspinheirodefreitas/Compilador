package br.com.ufabc.compiler.core.structure;

import br.com.ufabc.compiler.core.model.datastructure.Variable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommandAssign extends AbstractCommand {
    private String id;
    private String expression;

    @Override
    public String generateJavaCode() {
        return id + " = "+expression+";";
    }

    @Override
    public String toString() {
        return "CommandAssign{" +
                "idAssing='" + id + '\'' +
                ", expression='" + expression + '\'' +
                '}';
    }
}

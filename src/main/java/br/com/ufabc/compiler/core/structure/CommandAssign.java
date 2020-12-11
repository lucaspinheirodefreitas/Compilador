package br.com.ufabc.compiler.core.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommandAssign extends AbstractCommand {
    private String id;
    private String expression;

    @Override
    public String generateJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "CommandAssign{" +
                "idAssing='" + id + '\'' +
                ", expression='" + expression + '\'' +
                '}';
    }
}

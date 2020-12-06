package br.com.ufabc.compiler.core.structure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandWrite extends AbstractCommand {
    private String id;

    @Override
    public String generateJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "CommandWrite{" +
                "id='" + id + '\'' +
                '}';
    }
}

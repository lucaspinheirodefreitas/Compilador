package br.com.ufabc.compiler.core.structure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandRead extends AbstractCommand {
    private String id;

    @Override
    public String generateJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "CommandRead{" +
                "id='" + id + '\'' +
                '}';
    }
}

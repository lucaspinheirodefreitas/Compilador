package br.com.ufabc.compiler.core.structure;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class CommandLoop extends AbstractCommand{
    private String condition;
    private ArrayList<AbstractCommand> loop;

    @Override
    public String generateJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "CommandLoop{" +
                "condition='" + condition + '\'' +
                ", loop=" + loop +
                '}';
    }
}

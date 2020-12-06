package br.com.ufabc.compiler.core.structure;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class CommandConditional extends AbstractCommand {
    private String condition;
    private ArrayList<AbstractCommand> trueCondition;
    private ArrayList<AbstractCommand> falseCondition;

    @Override
    public String generateJavaCode() {
        return null;
    }

    @Override
    public String toString() {
        return "CommandConditional{" +
                "condition='" + condition + '\'' +
                ", trueCondition=" + trueCondition +
                ", falseCondition=" + falseCondition +
                '}';
    }
}

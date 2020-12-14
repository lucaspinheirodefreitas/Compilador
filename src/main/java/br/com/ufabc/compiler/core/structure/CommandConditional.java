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
        StringBuilder str = new StringBuilder();
        str.append("if ("+condition+") {\n");
        for (AbstractCommand cmd: trueCondition) {
            str.append(cmd.generateJavaCode() + "\n");
        }
        str.append("}");
        if (falseCondition.size() > 0) {
            str.append("else {\n");
            for (AbstractCommand cmd: falseCondition) {
                str.append(cmd.generateJavaCode() + "\n");
            }
            str.append("}\n");

        }
        return str.toString();
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

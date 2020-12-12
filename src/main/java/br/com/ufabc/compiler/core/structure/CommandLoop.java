package br.com.ufabc.compiler.core.structure;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class CommandLoop extends AbstractCommand{
    private String condition;
    private ArrayList<AbstractCommand> loop;

    @Override
    public String generateJavaCode() {

        StringBuilder str = new StringBuilder();
        str.append("while ("+condition+") {\n");
        for (AbstractCommand cmd: loop) {
            str.append(cmd.generateJavaCode() + "\n");
        }
        str.append("}");
        return str.toString();
    }

    @Override
    public String toString() {
        return "CommandLoop{" +
                "condition='" + condition + '\'' +
                ", loop=" + loop +
                '}';
    }
}

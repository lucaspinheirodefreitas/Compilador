package br.com.ufabc.compiler;

import br.com.ufabc.compiler.core.exception.SemanticException;
import br.com.ufabc.compiler.core.model.datastructure.Variable;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Scanner;

public class Compiler {
    public static void main(String[] args) {
        try {
            grammar.LangLexer lexer;
            grammar.LangParser parser;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite 0 para que seja realizada a leitura do arquivo com extensão uf e avaliada sua gramática. Caso queira realizar validações informando instruções via linha de comando, digite outra tecla.");
            String opcao = scanner.nextLine();
            String line, dados = "";
            if (opcao.equals("0")) {
                lexer = new grammar.LangLexer(CharStreams.fromFileName("program.uf"));
            } else {
                System.out.println("Entre com o código fonte que deseja a seguir.");
                line = scanner.nextLine();
                dados+=line + " ";
                while(!line.equals("fimprog;")) {
                    line = scanner.nextLine();
                    dados+=line;
                }
                lexer = new grammar.LangLexer(CharStreams.fromString(dados));
            }

            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            parser = new grammar.LangParser(tokenStream);
            parser.executable();
            parser.listCommands();
            parser.generateCode();
            warningGenerator(parser);
        } catch (SemanticException ex) {
            System.err.println("Semantic Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static void warningGenerator(grammar.LangParser parser) {
        System.out.println("\n==============================> AVISOS <==============================\n");
        verifyUsedVariable(parser);
        System.out.println("\n==============================> ****** <==============================\n");
    }


    public static void verifyUsedVariable(grammar.LangParser parser) {
        parser.getSymbolTable().getMap().forEach((key, value) -> {
            if (!((Variable) value).isReferenced() && !((Variable) value).isUsed())
                System.out.println("A variável " + key + " foi declarada, não teve nenhum valor atribuído e não foi referenciada em nenhum ponto.");
            else if (!((Variable) value).isUsed())
                System.out.println("A variável " + key + " foi declarada e não teve nenhum valor atribuído.");
            else if(!((Variable) value).isReferenced())
                System.out.println("A variável " + key + " foi declarada e não referenciada em nenhum ponto.");
        });

    }
}

package br.com.ufabc.compiler;

import br.com.ufabc.compiler.core.exception.SemanticException;
import org.antlr.v4.runtime.CharStream;
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
        } catch (SemanticException ex) {
            System.err.println("Semantic Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}

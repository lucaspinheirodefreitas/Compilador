package br.com.ufabc.compiler;

import br.com.ufabc.compiler.core.exception.SemanticException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Compiler {
    public static void main(String[] args) {
        try {
            grammar.LangLexer lexer;
            grammar.LangParser parser;

            lexer = new grammar.LangLexer(CharStreams.fromFileName("program.uf"));
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

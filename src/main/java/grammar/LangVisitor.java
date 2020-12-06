// Generated from /home/mohammed/Documentos/UFABC/Compiladores/Compilador/Lang.g4 by ANTLR 4.8
package grammar;

    import br.com.ufabc.compiler.core.model.datastructure.Symbol;
    import br.com.ufabc.compiler.core.model.datastructure.Variable;
    import br.com.ufabc.compiler.core.model.datastructure.SymbolTable;
    import br.com.ufabc.compiler.core.structure.AbstractCommand;
    import br.com.ufabc.compiler.core.structure.Program;
    import br.com.ufabc.compiler.core.structure.CommandRead;
    import br.com.ufabc.compiler.core.structure.CommandWrite;
    import br.com.ufabc.compiler.core.structure.CommandAssign;
    import br.com.ufabc.compiler.core.structure.CommandConditional;
    import br.com.ufabc.compiler.core.exception.SemanticException;
    import java.util.ArrayList;
    import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LangParser#executable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutable(LangParser.ExecutableContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec(LangParser.DecContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#declare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclare(LangParser.DeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(LangParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#bloco}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloco(LangParser.BlocoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(LangParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#cmdread}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdread(LangParser.CmdreadContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#cmdwrite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdwrite(LangParser.CmdwriteContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#cmdassign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdassign(LangParser.CmdassignContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#cmdcond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdcond(LangParser.CmdcondContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#cmdloop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdloop(LangParser.CmdloopContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(LangParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#precedencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrecedencia(LangParser.PrecedenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(LangParser.TermContext ctx);
}
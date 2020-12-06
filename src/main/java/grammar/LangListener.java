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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangParser}.
 */
public interface LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangParser#executable}.
	 * @param ctx the parse tree
	 */
	void enterExecutable(LangParser.ExecutableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#executable}.
	 * @param ctx the parse tree
	 */
	void exitExecutable(LangParser.ExecutableContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(LangParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(LangParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#declare}.
	 * @param ctx the parse tree
	 */
	void enterDeclare(LangParser.DeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#declare}.
	 * @param ctx the parse tree
	 */
	void exitDeclare(LangParser.DeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(LangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(LangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(LangParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(LangParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(LangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(LangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmdread}.
	 * @param ctx the parse tree
	 */
	void enterCmdread(LangParser.CmdreadContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmdread}.
	 * @param ctx the parse tree
	 */
	void exitCmdread(LangParser.CmdreadContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmdwrite}.
	 * @param ctx the parse tree
	 */
	void enterCmdwrite(LangParser.CmdwriteContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmdwrite}.
	 * @param ctx the parse tree
	 */
	void exitCmdwrite(LangParser.CmdwriteContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmdassign}.
	 * @param ctx the parse tree
	 */
	void enterCmdassign(LangParser.CmdassignContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmdassign}.
	 * @param ctx the parse tree
	 */
	void exitCmdassign(LangParser.CmdassignContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmdcond}.
	 * @param ctx the parse tree
	 */
	void enterCmdcond(LangParser.CmdcondContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmdcond}.
	 * @param ctx the parse tree
	 */
	void exitCmdcond(LangParser.CmdcondContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmdloop}.
	 * @param ctx the parse tree
	 */
	void enterCmdloop(LangParser.CmdloopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmdloop}.
	 * @param ctx the parse tree
	 */
	void exitCmdloop(LangParser.CmdloopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cmdexp}.
	 * @param ctx the parse tree
	 */
	void enterCmdexp(LangParser.CmdexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cmdexp}.
	 * @param ctx the parse tree
	 */
	void exitCmdexp(LangParser.CmdexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(LangParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(LangParser.TermContext ctx);
}
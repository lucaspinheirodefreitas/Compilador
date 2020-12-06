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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, OP=13, CP=14, OK=15, CK=16, SC=17, VIR=18, 
		OPER=19, OPEREL=20, ATTR=21, ID=22, NUMBER=23, WS=24;
	public static final int
		RULE_executable = 0, RULE_dec = 1, RULE_declare = 2, RULE_type = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdread = 6, RULE_cmdwrite = 7, RULE_cmdassign = 8, 
		RULE_cmdcond = 9, RULE_cmdloop = 10, RULE_cmdexp = 11, RULE_expr = 12, 
		RULE_term = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"executable", "dec", "declare", "type", "bloco", "cmd", "cmdread", "cmdwrite", 
			"cmdassign", "cmdcond", "cmdloop", "cmdexp", "expr", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'declare'", "'number'", "'text'", 
			"'boolean'", "'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", 
			"'('", "')'", "'{'", "'}'", "';'", "','", null, null, "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "OP", "CP", "OK", "CK", "SC", "VIR", "OPER", "OPEREL", "ATTR", 
			"ID", "NUMBER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private int _type;
	    private String _name;
	    private String _value;
	    private String _readId;
	    private String _writeId;
	    private String _exprId;
	    private String _exprContent;
	    private String _exprDecision;

	    private Symbol symbol;
	    private SymbolTable symbolTable = new SymbolTable();
	    private Program program = new Program();
	    private ArrayList<AbstractCommand> commands;
	    private Stack<ArrayList<AbstractCommand>> stackCommands = new Stack<ArrayList<AbstractCommand>>();
	    private ArrayList<AbstractCommand> trueCondition;
	    private ArrayList<AbstractCommand> falseCondition;


	    private void variableVerifyExists(String name) {
	        _name  = name;
	        _value = null;
	        symbol = new Variable(_name, _type, _value);
	        if(!symbolTable.exists(_name)) {
	            System.out.println(symbol);
	            symbolTable.add(symbol);
	        } else {
	            throw new SemanticException("Symbol -> " + _name + " already declared.");
	        }
	    }

	    private void variableVerifyNotExists(String name) {
	        _name  = name;
	        _value = null;
	        symbol = new Variable(_name, _type, _value);
	        if(!symbolTable.exists(_name)) {
	            throw new SemanticException("Symbol -> " + _name + " not declared.");
	        }
	    }

	    public void listCommands() {
	        for(AbstractCommand cmd : program.getCommands()) {
	            System.out.println(cmd);
	        }
	    }

	public LangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ExecutableContext extends ParserRuleContext {
		public DecContext dec() {
			return getRuleContext(DecContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ExecutableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_executable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExecutable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExecutable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitExecutable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExecutableContext executable() throws RecognitionException {
		ExecutableContext _localctx = new ExecutableContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_executable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			dec();
			setState(30);
			bloco();
			setState(31);
			match(T__1);

			                    program.setCommands(stackCommands.pop());
			                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecContext extends ParserRuleContext {
		public List<DeclareContext> declare() {
			return getRuleContexts(DeclareContext.class);
		}
		public DeclareContext declare(int i) {
			return getRuleContext(DeclareContext.class,i);
		}
		public DecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecContext dec() throws RecognitionException {
		DecContext _localctx = new DecContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				declare();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclareContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(LangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(LangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(LangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(LangParser.VIR, i);
		}
		public DeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareContext declare() throws RecognitionException {
		DeclareContext _localctx = new DeclareContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__2);
			setState(40);
			type();
			setState(41);
			match(ID);

			                                    variableVerifyExists(_input.LT(-1).getText());
			                                
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(43);
				match(VIR);
				setState(44);
				match(ID);

				                                    variableVerifyExists(_input.LT(-1).getText());
				                                
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(T__3);
				_type = Variable.NUMBER;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(T__4);
				_type = Variable.TEXT;
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				match(T__5);
				_type = Variable.BOOLEAN;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBloco(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitBloco(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			                    commands = new ArrayList<AbstractCommand>();
			                    stackCommands.push(commands);
			                
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				cmd();
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdreadContext cmdread() {
			return getRuleContext(CmdreadContext.class,0);
		}
		public CmdwriteContext cmdwrite() {
			return getRuleContext(CmdwriteContext.class,0);
		}
		public CmdassignContext cmdassign() {
			return getRuleContext(CmdassignContext.class,0);
		}
		public CmdcondContext cmdcond() {
			return getRuleContext(CmdcondContext.class,0);
		}
		public CmdloopContext cmdloop() {
			return getRuleContext(CmdloopContext.class,0);
		}
		public CmdexpContext cmdexp() {
			return getRuleContext(CmdexpContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				cmdread();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				cmdwrite();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(69);
				cmdassign();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(70);
				cmdcond();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(71);
				cmdloop();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(72);
				cmdexp();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdreadContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LangParser.OP, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode CP() { return getToken(LangParser.CP, 0); }
		public TerminalNode SC() { return getToken(LangParser.SC, 0); }
		public CmdreadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdread; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmdread(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmdread(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCmdread(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdreadContext cmdread() throws RecognitionException {
		CmdreadContext _localctx = new CmdreadContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdread);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__6);
			setState(76);
			match(OP);
			setState(77);
			match(ID);

			                                    variableVerifyNotExists(_input.LT(-1).getText());
			                                    _readId = _input.LT(-1).getText();
			                                
			setState(79);
			match(CP);
			setState(80);
			match(SC);

			                                    CommandRead command = new CommandRead(_readId);
			                                    stackCommands.peek().add(command);
			                                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdwriteContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LangParser.OP, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode CP() { return getToken(LangParser.CP, 0); }
		public TerminalNode SC() { return getToken(LangParser.SC, 0); }
		public CmdwriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdwrite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmdwrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmdwrite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCmdwrite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdwriteContext cmdwrite() throws RecognitionException {
		CmdwriteContext _localctx = new CmdwriteContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdwrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__7);
			setState(84);
			match(OP);
			setState(85);
			match(ID);

			                                     variableVerifyNotExists(_input.LT(-1).getText());
			                                     _writeId = _input.LT(-1).getText();
			                                
			setState(87);
			match(CP);
			setState(88);
			match(SC);

			                                    CommandWrite command = new CommandWrite(_writeId);
			                                    stackCommands.peek().add(command);
			                                
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdassignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(LangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(LangParser.SC, 0); }
		public CmdassignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdassign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmdassign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmdassign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCmdassign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdassignContext cmdassign() throws RecognitionException {
		CmdassignContext _localctx = new CmdassignContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdassign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(ID);

			                                    variableVerifyNotExists(_input.LT(-1).getText());
			                                    _exprId = _input.LT(-1).getText();
			                                
			setState(93);
			match(ATTR);

			                                    _exprContent = "";
			                                
			setState(95);
			expr();

			                                    CommandAssign command = new CommandAssign(_exprId, _exprContent);
			                                    stackCommands.peek().add(command);
			                                
			setState(97);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdcondContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LangParser.OP, 0); }
		public TerminalNode OPEREL() { return getToken(LangParser.OPEREL, 0); }
		public TerminalNode CP() { return getToken(LangParser.CP, 0); }
		public List<TerminalNode> OK() { return getTokens(LangParser.OK); }
		public TerminalNode OK(int i) {
			return getToken(LangParser.OK, i);
		}
		public List<TerminalNode> CK() { return getTokens(LangParser.CK); }
		public TerminalNode CK(int i) {
			return getToken(LangParser.CK, i);
		}
		public List<TerminalNode> ID() { return getTokens(LangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(LangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(LangParser.NUMBER, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdcondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdcond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmdcond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmdcond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCmdcond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdcondContext cmdcond() throws RecognitionException {
		CmdcondContext _localctx = new CmdcondContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdcond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__8);
			setState(100);
			match(OP);
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(101);
				match(ID);

				                                    variableVerifyNotExists(_input.LT(-1).getText());
				                                
				}
				break;
			case NUMBER:
				{
				setState(103);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			                                    _exprContent  += _input.LT(-1).getText();
			                                    _exprDecision += _input.LT(-1).getText();
			                                
			setState(107);
			match(OPEREL);
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(108);
				match(ID);

				                                  variableVerifyNotExists(_input.LT(-1).getText());
				                                
				}
				break;
			case NUMBER:
				{
				setState(110);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			                                    _exprContent  += _input.LT(-1).getText();
			                                    _exprDecision += _input.LT(-1).getText();
			                                
			setState(114);
			match(CP);
			setState(115);
			match(T__9);
			setState(116);
			match(OK);

			                                    commands = new ArrayList<AbstractCommand>();
			                                    stackCommands.push(commands);
			                                
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118);
				cmd();
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << ID))) != 0) );
			setState(123);
			match(CK);

			                                    trueCondition = stackCommands.pop();
			                                
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(125);
				match(T__10);
				setState(126);
				match(OK);

				                                    commands = new ArrayList<AbstractCommand>();
				                                    stackCommands.push(commands);
				                                
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(128);
					cmd();
					}
					}
					setState(131); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << ID))) != 0) );
				setState(133);
				match(CK);

				                                    falseCondition = stackCommands.pop();
				                                    CommandConditional  cmd = new CommandConditional(_exprDecision, trueCondition, falseCondition);
				                                    stackCommands.peek().add(cmd);
				                                
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdloopContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LangParser.OP, 0); }
		public TerminalNode OPEREL() { return getToken(LangParser.OPEREL, 0); }
		public TerminalNode CP() { return getToken(LangParser.CP, 0); }
		public TerminalNode OK() { return getToken(LangParser.OK, 0); }
		public TerminalNode CK() { return getToken(LangParser.CK, 0); }
		public List<TerminalNode> ID() { return getTokens(LangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(LangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(LangParser.NUMBER, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdloopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdloop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmdloop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmdloop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCmdloop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdloopContext cmdloop() throws RecognitionException {
		CmdloopContext _localctx = new CmdloopContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdloop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T__11);
			setState(139);
			match(OP);
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(140);
				match(ID);

				                                        variableVerifyNotExists(_input.LT(-1).getText());
				                                    
				}
				break;
			case NUMBER:
				{
				setState(142);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(145);
			match(OPEREL);
			setState(149);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(146);
				match(ID);

				                                                                variableVerifyNotExists(_input.LT(-1).getText());
				                                                            
				}
				break;
			case NUMBER:
				{
				setState(148);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(151);
			match(CP);
			setState(152);
			match(OK);
			setState(154); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153);
				cmd();
				}
				}
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__11) | (1L << ID))) != 0) );
			setState(158);
			match(CK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdexpContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(LangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CmdexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmdexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmdexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCmdexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdexpContext cmdexp() throws RecognitionException {
		CmdexpContext _localctx = new CmdexpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(ID);
			setState(161);
			match(ATTR);
			setState(162);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> OPER() { return getTokens(LangParser.OPER); }
		public TerminalNode OPER(int i) {
			return getToken(LangParser.OPER, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			term();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPER) {
				{
				{
				setState(165);
				match(OPER);

				                            _exprContent += _input.LT(-1).getText();
				                        
				setState(167);
				term();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(LangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(LangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(LangParser.NUMBER, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_term);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(176); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(176);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ID:
						{
						setState(173);
						match(ID);

						                     variableVerifyNotExists(_input.LT(-1).getText());
						                 
						}
						break;
					case NUMBER:
						{
						setState(175);
						match(NUMBER);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(178); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u00b7\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\6\3&\n\3\r\3\16\3\'\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4\64"+
		"\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5>\n\5\3\6\3\6\6\6B\n\6\r\6\16"+
		"\6C\3\7\3\7\3\7\3\7\3\7\3\7\5\7L\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\5\13k\n\13\3\13\3\13\3\13\3\13\3\13\5\13r\n\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\6\13z\n\13\r\13\16\13{\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\6\13\u0084\n\13\r\13\16\13\u0085\3\13\3\13\3\13\5\13\u008b\n"+
		"\13\3\f\3\f\3\f\3\f\3\f\5\f\u0092\n\f\3\f\3\f\3\f\3\f\5\f\u0098\n\f\3"+
		"\f\3\f\3\f\6\f\u009d\n\f\r\f\16\f\u009e\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\7\16\u00ab\n\16\f\16\16\16\u00ae\13\16\3\17\3\17\3\17\6"+
		"\17\u00b3\n\17\r\17\16\17\u00b4\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\2\2\2\u00bd\2\36\3\2\2\2\4%\3\2\2\2\6)\3\2\2\2\b=\3\2\2\2\n"+
		"?\3\2\2\2\fK\3\2\2\2\16M\3\2\2\2\20U\3\2\2\2\22]\3\2\2\2\24e\3\2\2\2\26"+
		"\u008c\3\2\2\2\30\u00a2\3\2\2\2\32\u00a6\3\2\2\2\34\u00b2\3\2\2\2\36\37"+
		"\7\3\2\2\37 \5\4\3\2 !\5\n\6\2!\"\7\4\2\2\"#\b\2\1\2#\3\3\2\2\2$&\5\6"+
		"\4\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\5\3\2\2\2)*\7\5\2\2*"+
		"+\5\b\5\2+,\7\30\2\2,\62\b\4\1\2-.\7\24\2\2./\7\30\2\2/\61\b\4\1\2\60"+
		"-\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62"+
		"\3\2\2\2\65\66\7\23\2\2\66\7\3\2\2\2\678\7\6\2\28>\b\5\1\29:\7\7\2\2:"+
		">\b\5\1\2;<\7\b\2\2<>\b\5\1\2=\67\3\2\2\2=9\3\2\2\2=;\3\2\2\2>\t\3\2\2"+
		"\2?A\b\6\1\2@B\5\f\7\2A@\3\2\2\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\13\3\2"+
		"\2\2EL\5\16\b\2FL\5\20\t\2GL\5\22\n\2HL\5\24\13\2IL\5\26\f\2JL\5\30\r"+
		"\2KE\3\2\2\2KF\3\2\2\2KG\3\2\2\2KH\3\2\2\2KI\3\2\2\2KJ\3\2\2\2L\r\3\2"+
		"\2\2MN\7\t\2\2NO\7\17\2\2OP\7\30\2\2PQ\b\b\1\2QR\7\20\2\2RS\7\23\2\2S"+
		"T\b\b\1\2T\17\3\2\2\2UV\7\n\2\2VW\7\17\2\2WX\7\30\2\2XY\b\t\1\2YZ\7\20"+
		"\2\2Z[\7\23\2\2[\\\b\t\1\2\\\21\3\2\2\2]^\7\30\2\2^_\b\n\1\2_`\7\27\2"+
		"\2`a\b\n\1\2ab\5\32\16\2bc\b\n\1\2cd\7\23\2\2d\23\3\2\2\2ef\7\13\2\2f"+
		"j\7\17\2\2gh\7\30\2\2hk\b\13\1\2ik\7\31\2\2jg\3\2\2\2ji\3\2\2\2kl\3\2"+
		"\2\2lm\b\13\1\2mq\7\26\2\2no\7\30\2\2or\b\13\1\2pr\7\31\2\2qn\3\2\2\2"+
		"qp\3\2\2\2rs\3\2\2\2st\b\13\1\2tu\7\20\2\2uv\7\f\2\2vw\7\21\2\2wy\b\13"+
		"\1\2xz\5\f\7\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\7\22"+
		"\2\2~\u008a\b\13\1\2\177\u0080\7\r\2\2\u0080\u0081\7\21\2\2\u0081\u0083"+
		"\b\13\1\2\u0082\u0084\5\f\7\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2"+
		"\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088"+
		"\7\22\2\2\u0088\u0089\b\13\1\2\u0089\u008b\3\2\2\2\u008a\177\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\25\3\2\2\2\u008c\u008d\7\16\2\2\u008d\u0091\7\17"+
		"\2\2\u008e\u008f\7\30\2\2\u008f\u0092\b\f\1\2\u0090\u0092\7\31\2\2\u0091"+
		"\u008e\3\2\2\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0097\7\26"+
		"\2\2\u0094\u0095\7\30\2\2\u0095\u0098\b\f\1\2\u0096\u0098\7\31\2\2\u0097"+
		"\u0094\3\2\2\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\7\20"+
		"\2\2\u009a\u009c\7\21\2\2\u009b\u009d\5\f\7\2\u009c\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2"+
		"\2\2\u00a0\u00a1\7\22\2\2\u00a1\27\3\2\2\2\u00a2\u00a3\7\30\2\2\u00a3"+
		"\u00a4\7\27\2\2\u00a4\u00a5\5\32\16\2\u00a5\31\3\2\2\2\u00a6\u00ac\5\34"+
		"\17\2\u00a7\u00a8\7\25\2\2\u00a8\u00a9\b\16\1\2\u00a9\u00ab\5\34\17\2"+
		"\u00aa\u00a7\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad"+
		"\3\2\2\2\u00ad\33\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\7\30\2\2\u00b0"+
		"\u00b3\b\17\1\2\u00b1\u00b3\7\31\2\2\u00b2\u00af\3\2\2\2\u00b2\u00b1\3"+
		"\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\35\3\2\2\2\22\'\62=CKjq{\u0085\u008a\u0091\u0097\u009e\u00ac\u00b2\u00b4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
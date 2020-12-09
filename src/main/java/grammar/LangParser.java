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
    import br.com.ufabc.compiler.core.structure.CommandLoop;
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
		T__9=10, T__10=11, OP=12, CP=13, OK=14, CK=15, SC=16, VIR=17, OPERPREC=18, 
		OPER=19, OPEREL=20, ATTR=21, ID=22, NUMBER=23, WS=24;
	public static final int
		RULE_executable = 0, RULE_dec = 1, RULE_declare = 2, RULE_type = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdread = 6, RULE_cmdwrite = 7, RULE_cmdassign = 8, 
		RULE_cmdcond = 9, RULE_cmdloop = 10, RULE_expr = 11, RULE_prec = 12, RULE_precedencia = 13, 
		RULE_term = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"executable", "dec", "declare", "type", "bloco", "cmd", "cmdread", "cmdwrite", 
			"cmdassign", "cmdcond", "cmdloop", "expr", "prec", "precedencia", "term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'declare'", "'number'", "'text'", 
			"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", "'('", 
			"')'", "'{'", "'}'", "';'", "','", null, null, null, "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"OP", "CP", "OK", "CK", "SC", "VIR", "OPERPREC", "OPER", "OPEREL", "ATTR", 
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
	    private String _exprDecision="";
	    private String _joker;

	    private Symbol symbol;
	    private SymbolTable symbolTable = new SymbolTable();
	    private Program program = new Program();
	    private ArrayList<AbstractCommand> commands;
	    private Stack<ArrayList<AbstractCommand>> stackCommands = new Stack<ArrayList<AbstractCommand>>();
	    private ArrayList<AbstractCommand> trueCondition;
	    private ArrayList<AbstractCommand> falseCondition;
	    private ArrayList<AbstractCommand> loop;
	    private ArrayList<Integer> types = new ArrayList<Integer>();
	    private ArrayList<String> names = new ArrayList<String>();


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
	        if(!symbolTable.exists(_name)) {
	            throw new SemanticException("Symbol -> " + _name + " not declared.");
	        }
	    }

	    private void assignmentVerifyType() {
	    //@todo -> a solução não é efetiva e gera nullpointerexception :: preciso revisar e ajustar para talvez passar a pegar os dados da symbolTable ou de algum outro ponto.
	    // exemplo de caso que gera null pointer é: t:=3+44;
	        _name       = names.get(0);
	        symbol      = symbolTable.getMap().get(_name);
	        _type    = ((Variable) symbol).getType();
	        types.add(_type);
	        for(int i=1; i<names.size(); i++) {
	            _name      = names.get(i);
	            if(!symbolTable.exists(_name)) {
	                _type = Variable.NUMBER;
	            } else {
	                symbol  = symbolTable.getMap().get(_name);
	                _type   = ((Variable) symbol).getType();
	            }
	            types.add(_type);
	        }

	        if(!verifyAllEqual()) {
	            for(int i=1; i<types.size(); i++) {
	                if(types.get(0) != types.get(i)) {
	                    throw new SemanticException("Incompatible types - variable: " + names.get(i) + " as type -> " + (types.get(i)==0 ? "NUMBER" : "TEXT")
	                                                + " and cannot be converted to expected type -> " + (types.get(0)==0 ? "NUMBER." : "TEXT."));
	                }
	            }

	        }
	    }

	    private void setVariableUsed(String name) {
	        symbol      = symbolTable.getMap().get(_name);
	        ((Variable) symbol).setUsed(true);
	    }

	    private void setVariableValue(String name, String value) {
	        symbol      = symbolTable.getMap().get(_name);
	        ((Variable) symbol).setValue(value);
	    }

	    private void setVariableReferenced(String name) {
	        symbol      = symbolTable.getMap().get(_name);
	        ((Variable) symbol).setReferenced(true);
	    }

	    public SymbolTable getSymbolTable() {
	        return symbolTable;
	    }

	    public boolean verifyAllEqual() {
	        return types
	                    .stream()
	                    .distinct()
	                    .count() <= 1;
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
			setState(30);
			match(T__0);
			setState(31);
			dec();
			setState(32);
			bloco();
			setState(33);
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
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36);
				declare();
				}
				}
				setState(39); 
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
			setState(41);
			match(T__2);
			setState(42);
			type();
			setState(43);
			match(ID);

			                                    variableVerifyExists(_input.LT(-1).getText());
			                                
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(45);
				match(VIR);
				setState(46);
				match(ID);

				                                    variableVerifyExists(_input.LT(-1).getText());
				                                
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
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
				setState(55);
				match(T__3);
				_type = Variable.NUMBER;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(T__4);
				_type = Variable.TEXT;
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
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
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				cmdread();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				cmdwrite();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(69);
				cmdassign();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(70);
				cmdcond();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 5);
				{
				setState(71);
				cmdloop();
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

	public static class CmdreadContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LangParser.OP, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
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
			setState(74);
			match(T__5);
			setState(75);
			match(OP);
			setState(76);
			term();

			                                    _readId = _input.LT(-1).getText();
			                                
			setState(78);
			match(CP);
			setState(79);
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
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
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
			setState(82);
			match(T__6);
			setState(83);
			match(OP);
			setState(84);
			term();

			                                     _writeId = _input.LT(-1).getText();
			                                
			setState(86);
			match(CP);
			setState(87);
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
			setState(90);
			match(ID);

			                                    variableVerifyNotExists(_input.LT(-1).getText());
			                                    _exprId = _input.LT(-1).getText();
			                                    setVariableUsed(_exprId); //@todo -> passivel de melhoria em conjunto com o método: setVariableValue
			                                    names.add(_exprId);
			                                
			setState(92);
			match(ATTR);

			                                    _exprContent = "";
			                                
			setState(94);
			expr();

			                                    setVariableValue(_exprId, _exprContent);
			                                    CommandAssign command = new CommandAssign(_exprId, _exprContent);
			                                    assignmentVerifyType();
			                                    stackCommands.peek().add(command);
			                                
			setState(96);
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
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
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
			setState(98);
			match(T__7);
			setState(99);
			match(OP);
			setState(100);
			term();
			setState(101);
			match(OPEREL);
			setState(102);
			term();

			                                    _exprDecision = "";
			                                    _exprDecision += _input.LT(-3).getText();
			                                    _exprDecision += _input.LT(-2).getText();
			                                    _exprDecision += _input.LT(-1).getText();
			                                
			setState(104);
			match(CP);
			setState(105);
			match(T__8);
			setState(106);
			match(OK);

			                                    commands = new ArrayList<AbstractCommand>();
			                                    stackCommands.push(commands);
			                                
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108);
				cmd();
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
			setState(113);
			match(CK);

			                                    trueCondition = stackCommands.pop();
			                                
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
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
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
				setState(123);
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
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode OPEREL() { return getToken(LangParser.OPEREL, 0); }
		public TerminalNode CP() { return getToken(LangParser.CP, 0); }
		public TerminalNode OK() { return getToken(LangParser.OK, 0); }
		public TerminalNode CK() { return getToken(LangParser.CK, 0); }
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
			setState(128);
			match(T__10);
			setState(129);
			match(OP);
			setState(130);
			term();
			setState(131);
			match(OPEREL);
			setState(132);
			term();

			                                                    _exprDecision = "";
			                                                    _exprDecision += _input.LT(-3).getText();
			                                                    _exprDecision += _input.LT(-2).getText();
			                                                    _exprDecision += _input.LT(-1).getText();
			                                                
			setState(134);
			match(CP);
			setState(135);
			match(OK);

			                                                                commands = new ArrayList<AbstractCommand>();
			                                                                stackCommands.push(commands);
			                                                            
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(137);
				cmd();
				}
				}
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
			setState(142);
			match(CK);

			                                                                           loop = stackCommands.pop();
			                                                                           CommandLoop cmd = new CommandLoop(_exprDecision, loop);
			                                                                           stackCommands.peek().add(cmd);
			                                                                        
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
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode OPER() { return getToken(LangParser.OPER, 0); }
		public PrecContext prec() {
			return getRuleContext(PrecContext.class,0);
		}
		public PrecedenciaContext precedencia() {
			return getRuleContext(PrecedenciaContext.class,0);
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
		enterRule(_localctx, 22, RULE_expr);
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				term();
				setState(146);
				match(OPER);

				                                    _exprContent += _input.LT(-1).getText();
				                                
				setState(148);
				prec();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				precedencia();
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

	public static class PrecContext extends ParserRuleContext {
		public PrecedenciaContext precedencia() {
			return getRuleContext(PrecedenciaContext.class,0);
		}
		public TerminalNode OPERPREC() { return getToken(LangParser.OPERPREC, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterPrec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitPrec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitPrec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrecContext prec() throws RecognitionException {
		PrecContext _localctx = new PrecContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_prec);
		try {
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				precedencia();
				setState(154);
				match(OPERPREC);
				setState(155);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				precedencia();
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

	public static class PrecedenciaContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public PrecedenciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precedencia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterPrecedencia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitPrecedencia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitPrecedencia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrecedenciaContext precedencia() throws RecognitionException {
		PrecedenciaContext _localctx = new PrecedenciaContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_precedencia);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			term();
			names.add(_input.LT(-1).getText());
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
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(LangParser.NUMBER, 0); }
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
		enterRule(_localctx, 28, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(163);
				match(ID);

				                        _name=_input.LT(-1).getText();
				                        variableVerifyNotExists(_name);
				                        setVariableReferenced(_name);
				                    
				}
				break;
			case NUMBER:
				{
				setState(165);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u00ab\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\6\3(\n\3\r\3\16\3)\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\63\n\4\f"+
		"\4\16\4\66\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5>\n\5\3\6\3\6\6\6B\n\6\r\6"+
		"\16\6C\3\7\3\7\3\7\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13p\n\13\r\13\16\13"+
		"q\3\13\3\13\3\13\3\13\3\13\3\13\6\13z\n\13\r\13\16\13{\3\13\3\13\3\13"+
		"\5\13\u0081\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u008d\n\f"+
		"\r\f\16\f\u008e\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u009a\n\r\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u00a1\n\16\3\17\3\17\3\17\3\20\3\20\3\20\5\20"+
		"\u00a9\n\20\3\20\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\2\u00aa"+
		"\2 \3\2\2\2\4\'\3\2\2\2\6+\3\2\2\2\b=\3\2\2\2\n?\3\2\2\2\fJ\3\2\2\2\16"+
		"L\3\2\2\2\20T\3\2\2\2\22\\\3\2\2\2\24d\3\2\2\2\26\u0082\3\2\2\2\30\u0099"+
		"\3\2\2\2\32\u00a0\3\2\2\2\34\u00a2\3\2\2\2\36\u00a8\3\2\2\2 !\7\3\2\2"+
		"!\"\5\4\3\2\"#\5\n\6\2#$\7\4\2\2$%\b\2\1\2%\3\3\2\2\2&(\5\6\4\2\'&\3\2"+
		"\2\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\5\3\2\2\2+,\7\5\2\2,-\5\b\5\2-.\7"+
		"\30\2\2.\64\b\4\1\2/\60\7\23\2\2\60\61\7\30\2\2\61\63\b\4\1\2\62/\3\2"+
		"\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66\64\3\2"+
		"\2\2\678\7\22\2\28\7\3\2\2\29:\7\6\2\2:>\b\5\1\2;<\7\7\2\2<>\b\5\1\2="+
		"9\3\2\2\2=;\3\2\2\2>\t\3\2\2\2?A\b\6\1\2@B\5\f\7\2A@\3\2\2\2BC\3\2\2\2"+
		"CA\3\2\2\2CD\3\2\2\2D\13\3\2\2\2EK\5\16\b\2FK\5\20\t\2GK\5\22\n\2HK\5"+
		"\24\13\2IK\5\26\f\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2"+
		"K\r\3\2\2\2LM\7\b\2\2MN\7\16\2\2NO\5\36\20\2OP\b\b\1\2PQ\7\17\2\2QR\7"+
		"\22\2\2RS\b\b\1\2S\17\3\2\2\2TU\7\t\2\2UV\7\16\2\2VW\5\36\20\2WX\b\t\1"+
		"\2XY\7\17\2\2YZ\7\22\2\2Z[\b\t\1\2[\21\3\2\2\2\\]\7\30\2\2]^\b\n\1\2^"+
		"_\7\27\2\2_`\b\n\1\2`a\5\30\r\2ab\b\n\1\2bc\7\22\2\2c\23\3\2\2\2de\7\n"+
		"\2\2ef\7\16\2\2fg\5\36\20\2gh\7\26\2\2hi\5\36\20\2ij\b\13\1\2jk\7\17\2"+
		"\2kl\7\13\2\2lm\7\20\2\2mo\b\13\1\2np\5\f\7\2on\3\2\2\2pq\3\2\2\2qo\3"+
		"\2\2\2qr\3\2\2\2rs\3\2\2\2st\7\21\2\2t\u0080\b\13\1\2uv\7\f\2\2vw\7\20"+
		"\2\2wy\b\13\1\2xz\5\f\7\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3"+
		"\2\2\2}~\7\21\2\2~\177\b\13\1\2\177\u0081\3\2\2\2\u0080u\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\25\3\2\2\2\u0082\u0083\7\r\2\2\u0083\u0084\7\16\2"+
		"\2\u0084\u0085\5\36\20\2\u0085\u0086\7\26\2\2\u0086\u0087\5\36\20\2\u0087"+
		"\u0088\b\f\1\2\u0088\u0089\7\17\2\2\u0089\u008a\7\20\2\2\u008a\u008c\b"+
		"\f\1\2\u008b\u008d\5\f\7\2\u008c\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\7\21"+
		"\2\2\u0091\u0092\b\f\1\2\u0092\27\3\2\2\2\u0093\u0094\5\36\20\2\u0094"+
		"\u0095\7\25\2\2\u0095\u0096\b\r\1\2\u0096\u0097\5\32\16\2\u0097\u009a"+
		"\3\2\2\2\u0098\u009a\5\34\17\2\u0099\u0093\3\2\2\2\u0099\u0098\3\2\2\2"+
		"\u009a\31\3\2\2\2\u009b\u009c\5\34\17\2\u009c\u009d\7\24\2\2\u009d\u009e"+
		"\5\30\r\2\u009e\u00a1\3\2\2\2\u009f\u00a1\5\34\17\2\u00a0\u009b\3\2\2"+
		"\2\u00a0\u009f\3\2\2\2\u00a1\33\3\2\2\2\u00a2\u00a3\5\36\20\2\u00a3\u00a4"+
		"\b\17\1\2\u00a4\35\3\2\2\2\u00a5\u00a6\7\30\2\2\u00a6\u00a9\b\20\1\2\u00a7"+
		"\u00a9\7\31\2\2\u00a8\u00a5\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\37\3\2\2"+
		"\2\16)\64=CJq{\u0080\u008e\u0099\u00a0\u00a8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
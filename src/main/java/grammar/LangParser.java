// Generated from /home/mohammed/Documentos/UFABC/Compiladores/Compilador/Lang.g4 by ANTLR 4.9
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
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, OP=12, CP=13, OK=14, CK=15, SC=16, VIR=17, OPERPREC=18, 
		OPER=19, OPEREL=20, ATTR=21, ID=22, IDTEXT=23, NUMBER=24, WS=25;
	public static final int
		RULE_executable = 0, RULE_dec = 1, RULE_declare = 2, RULE_type = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdread = 6, RULE_cmdwrite = 7, RULE_cmdassign = 8, 
		RULE_cmdcond = 9, RULE_cmdloop = 10, RULE_expr = 11, RULE_prec = 12, RULE_precedencia = 13, 
		RULE_vazio = 14, RULE_term = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"executable", "dec", "declare", "type", "bloco", "cmd", "cmdread", "cmdwrite", 
			"cmdassign", "cmdcond", "cmdloop", "expr", "prec", "precedencia", "vazio", 
			"term"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'declare'", "'numero'", "'texto'", 
			"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", "'('", 
			"')'", "'{'", "'}'", "';'", "','", null, null, null, "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"OP", "CP", "OK", "CK", "SC", "VIR", "OPERPREC", "OPER", "OPEREL", "ATTR", 
			"ID", "IDTEXT", "NUMBER", "WS"
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

	    private int verifyVariableType(String name) {
	        symbol      = symbolTable.getMap().get(name);
	        return ((Variable) symbol).getType();
	    }

	    private void assignmentVerifyType(CommandAssign command) {
	        String REGEX_NUMERIC_EXPRESSION = "(\\d+(\\.\\d+)?(\\*|\\/|\\+|\\-)?(\\d+\\.\\d+)?)*\\d*$";
	        String REGEX_TEXT               = "^([a-z]|[A-Z])+.*";
	        String REGEX_VARIABLE           = "^[a-z]([a-z] | [A-Z] | [0-9] | '_')*";
	        String REGEX_NUMBER             = "\\d+(\\.\\d+)?";

	        _type = verifyVariableType(command.getId());

	        String commands = command.getExpression();

	        boolean texto               = commands.matches(REGEX_TEXT);
	        boolean numericExpression   = commands.matches(REGEX_NUMERIC_EXPRESSION);
	        boolean variable            = false;
	        boolean numero              = false;

	        if(_type == 0 && numericExpression)
	            return;

	        String[] splitted = commands.split("[-+*/]");

	        for (String split: splitted) {
	            variable = split.matches(REGEX_VARIABLE);
	            numero   = split.matches(REGEX_NUMBER);
	            texto    = split.matches(REGEX_TEXT);

	            if(variable) {
	                if(symbolTable.exists(split)) {
	                    int type = verifyVariableType(split);
	                    if(!(type == _type)) {
	                        throw new SemanticException("Incompatible types - expected type -> " + (_type==0 ? "NUMBER." : "TEXT.")
	                        + "\nCannot be converted the " + (type==0 ? "NUMBER" : "TEXT") + " type of variable ["
	                        + split + "] to -> " + (_type==0 ? "NUMBER." : "TEXT."));
	                    }
	                } else if (!(texto && _type == 1)){
	                    throw new SemanticException("Incompatible types - expected type -> " + (_type==0 ? "NUMBER." : "TEXT.")
	                                            + "\nCannot be converted the value of ["
	                                            + split + "] to -> " + (_type==0 ? "NUMBER." : "TEXT."));
	                }
	            } else if (!variable && !numero) {
	                throw new SemanticException("Incompatible types - expected type -> "
	                + (_type==0 ? "NUMBER." : "TEXT.") + "\nCannot be converted UNRECOGNIZED TYPE to -> " + (_type==0 ? "NUMBER." : "TEXT."));
	            }
	        }

	        /*if(_type == 0 && texto || _type == 1 && numericExpression || !numericExpression && !texto) {
	            throw new SemanticException("Incompatible types - expected type -> " + (_type==0 ? "NUMBER." : "TEXT.") + "\nCannot be converted "
	                                        + (!numericExpression && !texto ? "UNRECOGNIZED TYPE" : (numericExpression ? "NUMBER" : "TEXT"))
	                                        + " to -> " + (_type==0 ? "NUMBER." : "TEXT."));
	        }*/
	    }

	    private void setVariableUsed(String name) {
	        symbol      = symbolTable.getMap().get(name);
	        ((Variable) symbol).setUsed(true);
	    }

	    private void setVariableValue(String name, String value) {
	        symbol      = symbolTable.getMap().get(name);
	        ((Variable) symbol).setValue(value);
	    }

	    private void setVariableReferenced(String name) {
	        symbol      = symbolTable.getMap().get(name);
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

	    public void generateCode() {
	        program.generateTarget();
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
			setState(32);
			match(T__0);
			setState(33);
			dec();
			setState(34);
			bloco();
			setState(35);
			match(T__1);

			                    program.setVarTable(symbolTable);
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
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				declare();
				}
				}
				setState(41); 
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
			setState(43);
			match(T__2);
			setState(44);
			type();
			setState(45);
			match(ID);

			                                    variableVerifyExists(_input.LT(-1).getText());
			                                
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(47);
				match(VIR);
				setState(48);
				match(ID);

				                                    variableVerifyExists(_input.LT(-1).getText());
				                                
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
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
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(T__3);
				_type = Variable.NUMBER;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
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
			                
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				cmd();
				}
				}
				setState(67); 
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
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				cmdread();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				cmdwrite();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				cmdassign();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				cmdcond();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
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
			setState(76);
			match(T__5);
			setState(77);
			match(OP);
			setState(78);
			match(ID);
			_readId = _input.LT(-1).getText(); setVariableUsed(_readId);
			setState(80);
			match(CP);
			setState(81);
			match(SC);

			                                    Variable variable = (Variable) symbolTable.getMap().get(_readId);
			                                    CommandRead command = new CommandRead(_readId, variable);
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
			setState(84);
			match(T__6);
			setState(85);
			match(OP);
			setState(86);
			match(ID);
			_writeId = _input.LT(-1).getText(); setVariableReferenced(_writeId);
			setState(88);
			match(CP);
			setState(89);
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
			setState(92);
			match(ID);

			                                    variableVerifyNotExists(_input.LT(-1).getText());
			                                    _exprId = _input.LT(-1).getText();
			                                    setVariableUsed(_exprId);
			                                
			setState(94);
			match(ATTR);

			                                    _exprContent = "";
			                                
			setState(96);
			expr();

			                                    setVariableValue(_exprId, _exprContent);
			                                    CommandAssign command = new CommandAssign(_exprId, _exprContent);
			                                    assignmentVerifyType(command);
			                                    stackCommands.peek().add(command);
			                                
			setState(98);
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
			setState(100);
			match(T__7);
			setState(101);
			match(OP);
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(102);
				match(ID);
				_name = _input.LT(-1).getText(); setVariableReferenced(_name);
				}
				break;
			case NUMBER:
				{
				setState(104);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(107);
			match(OPEREL);
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(108);
				match(ID);
				_name = _input.LT(-1).getText(); setVariableReferenced(_name);
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

			                                    _exprDecision = "";
			                                    _exprDecision += _input.LT(-3).getText();
			                                    _exprDecision += _input.LT(-2).getText();
			                                    _exprDecision += _input.LT(-1).getText();
			                                
			setState(114);
			match(CP);
			setState(115);
			match(T__8);
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

			                                    trueCondition = stackCommands.pop();
			                                
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(125);
				match(T__9);
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
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
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
			match(T__10);
			setState(139);
			match(OP);
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(140);
				match(ID);
				_name = _input.LT(-1).getText(); setVariableReferenced(_name);
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
				_name = _input.LT(-1).getText();
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

			                                                    _exprDecision = "";
			                                                    _exprDecision += _input.LT(-3).getText();
			                                                    _exprDecision += _input.LT(-2).getText();
			                                                    _exprDecision += _input.LT(-1).getText();
			                                                
			setState(152);
			match(CP);
			setState(153);
			match(OK);

			                                                                commands = new ArrayList<AbstractCommand>();
			                                                                stackCommands.push(commands);
			                                                            
			setState(156); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(155);
				cmd();
				}
				}
				setState(158); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__10) | (1L << ID))) != 0) );
			setState(160);
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
		public PrecContext prec() {
			return getRuleContext(PrecContext.class,0);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			prec();
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
		public TerminalNode OPER() { return getToken(LangParser.OPER, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrecedenciaContext precedencia() {
			return getRuleContext(PrecedenciaContext.class,0);
		}
		public VazioContext vazio() {
			return getRuleContext(VazioContext.class,0);
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
			setState(172);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				match(OPER);
				_exprContent += _input.LT(-1).getText();
				setState(167);
				expr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				precedencia();
				setState(169);
				expr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				vazio();
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
		public TerminalNode OPERPREC() { return getToken(LangParser.OPERPREC, 0); }
		public PrecContext prec() {
			return getRuleContext(PrecContext.class,0);
		}
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
			setState(178);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPERPREC:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(OPERPREC);
				_exprContent += _input.LT(-1).getText();
				setState(176);
				prec();
				}
				break;
			case ID:
			case IDTEXT:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				term();
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

	public static class VazioContext extends ParserRuleContext {
		public VazioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vazio; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterVazio(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitVazio(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitVazio(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VazioContext vazio() throws RecognitionException {
		VazioContext _localctx = new VazioContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_vazio);
		try {
			enterOuterAlt(_localctx, 1);
			{
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
		public TerminalNode IDTEXT() { return getToken(LangParser.IDTEXT, 0); }
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
		enterRule(_localctx, 30, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(182);
				match(ID);
				_name = _input.LT(-1).getText(); setVariableReferenced(_name);
				}
				break;
			case IDTEXT:
				{
				setState(184);
				match(IDTEXT);
				}
				break;
			case NUMBER:
				{
				setState(185);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_exprContent += _input.LT(-1).getText();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00c1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\6\3*\n\3\r\3\16\3+\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\4\65\n\4\f\4\16\48\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5@\n\5\3\6\3\6\6\6"+
		"D\n\6\r\6\16\6E\3\7\3\7\3\7\3\7\3\7\5\7M\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\5\13l\n\13\3\13\3\13\3\13\3\13\5\13r\n\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\6\13z\n\13\r\13\16\13{\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\6\13\u0084\n\13\r\13\16\13\u0085\3\13\3\13\3\13\5\13\u008b"+
		"\n\13\3\f\3\f\3\f\3\f\3\f\5\f\u0092\n\f\3\f\3\f\3\f\3\f\5\f\u0098\n\f"+
		"\3\f\3\f\3\f\3\f\3\f\6\f\u009f\n\f\r\f\16\f\u00a0\3\f\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00af\n\16\3\17\3\17\3\17\3\17"+
		"\5\17\u00b5\n\17\3\20\3\20\3\21\3\21\3\21\3\21\5\21\u00bd\n\21\3\21\3"+
		"\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\2\2\u00c5\2"+
		"\"\3\2\2\2\4)\3\2\2\2\6-\3\2\2\2\b?\3\2\2\2\nA\3\2\2\2\fL\3\2\2\2\16N"+
		"\3\2\2\2\20V\3\2\2\2\22^\3\2\2\2\24f\3\2\2\2\26\u008c\3\2\2\2\30\u00a5"+
		"\3\2\2\2\32\u00ae\3\2\2\2\34\u00b4\3\2\2\2\36\u00b6\3\2\2\2 \u00bc\3\2"+
		"\2\2\"#\7\3\2\2#$\5\4\3\2$%\5\n\6\2%&\7\4\2\2&\'\b\2\1\2\'\3\3\2\2\2("+
		"*\5\6\4\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\5\3\2\2\2-.\7\5\2\2"+
		"./\5\b\5\2/\60\7\30\2\2\60\66\b\4\1\2\61\62\7\23\2\2\62\63\7\30\2\2\63"+
		"\65\b\4\1\2\64\61\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679"+
		"\3\2\2\28\66\3\2\2\29:\7\22\2\2:\7\3\2\2\2;<\7\6\2\2<@\b\5\1\2=>\7\7\2"+
		"\2>@\b\5\1\2?;\3\2\2\2?=\3\2\2\2@\t\3\2\2\2AC\b\6\1\2BD\5\f\7\2CB\3\2"+
		"\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\13\3\2\2\2GM\5\16\b\2HM\5\20\t\2I"+
		"M\5\22\n\2JM\5\24\13\2KM\5\26\f\2LG\3\2\2\2LH\3\2\2\2LI\3\2\2\2LJ\3\2"+
		"\2\2LK\3\2\2\2M\r\3\2\2\2NO\7\b\2\2OP\7\16\2\2PQ\7\30\2\2QR\b\b\1\2RS"+
		"\7\17\2\2ST\7\22\2\2TU\b\b\1\2U\17\3\2\2\2VW\7\t\2\2WX\7\16\2\2XY\7\30"+
		"\2\2YZ\b\t\1\2Z[\7\17\2\2[\\\7\22\2\2\\]\b\t\1\2]\21\3\2\2\2^_\7\30\2"+
		"\2_`\b\n\1\2`a\7\27\2\2ab\b\n\1\2bc\5\30\r\2cd\b\n\1\2de\7\22\2\2e\23"+
		"\3\2\2\2fg\7\n\2\2gk\7\16\2\2hi\7\30\2\2il\b\13\1\2jl\7\32\2\2kh\3\2\2"+
		"\2kj\3\2\2\2lm\3\2\2\2mq\7\26\2\2no\7\30\2\2or\b\13\1\2pr\7\32\2\2qn\3"+
		"\2\2\2qp\3\2\2\2rs\3\2\2\2st\b\13\1\2tu\7\17\2\2uv\7\13\2\2vw\7\20\2\2"+
		"wy\b\13\1\2xz\5\f\7\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2"+
		"\2}~\7\21\2\2~\u008a\b\13\1\2\177\u0080\7\f\2\2\u0080\u0081\7\20\2\2\u0081"+
		"\u0083\b\13\1\2\u0082\u0084\5\f\7\2\u0083\u0082\3\2\2\2\u0084\u0085\3"+
		"\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0088\7\21\2\2\u0088\u0089\b\13\1\2\u0089\u008b\3\2\2\2\u008a\177\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b\25\3\2\2\2\u008c\u008d\7\r\2\2\u008d\u0091"+
		"\7\16\2\2\u008e\u008f\7\30\2\2\u008f\u0092\b\f\1\2\u0090\u0092\7\32\2"+
		"\2\u0091\u008e\3\2\2\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0097"+
		"\7\26\2\2\u0094\u0095\7\30\2\2\u0095\u0098\b\f\1\2\u0096\u0098\7\32\2"+
		"\2\u0097\u0094\3\2\2\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a"+
		"\b\f\1\2\u009a\u009b\7\17\2\2\u009b\u009c\7\20\2\2\u009c\u009e\b\f\1\2"+
		"\u009d\u009f\5\f\7\2\u009e\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e"+
		"\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\7\21\2\2"+
		"\u00a3\u00a4\b\f\1\2\u00a4\27\3\2\2\2\u00a5\u00a6\5\32\16\2\u00a6\31\3"+
		"\2\2\2\u00a7\u00a8\7\25\2\2\u00a8\u00a9\b\16\1\2\u00a9\u00af\5\30\r\2"+
		"\u00aa\u00ab\5\34\17\2\u00ab\u00ac\5\30\r\2\u00ac\u00af\3\2\2\2\u00ad"+
		"\u00af\5\36\20\2\u00ae\u00a7\3\2\2\2\u00ae\u00aa\3\2\2\2\u00ae\u00ad\3"+
		"\2\2\2\u00af\33\3\2\2\2\u00b0\u00b1\7\24\2\2\u00b1\u00b2\b\17\1\2\u00b2"+
		"\u00b5\5\32\16\2\u00b3\u00b5\5 \21\2\u00b4\u00b0\3\2\2\2\u00b4\u00b3\3"+
		"\2\2\2\u00b5\35\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\37\3\2\2\2\u00b8\u00b9"+
		"\7\30\2\2\u00b9\u00bd\b\21\1\2\u00ba\u00bd\7\31\2\2\u00bb\u00bd\7\32\2"+
		"\2\u00bc\u00b8\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be"+
		"\3\2\2\2\u00be\u00bf\b\21\1\2\u00bf!\3\2\2\2\22+\66?ELkq{\u0085\u008a"+
		"\u0091\u0097\u00a0\u00ae\u00b4\u00bc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
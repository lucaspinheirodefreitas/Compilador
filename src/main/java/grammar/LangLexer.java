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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, OP=12, CP=13, OK=14, CK=15, SC=16, VIR=17, OPERPREC=18, 
		OPER=19, OPEREL=20, ATTR=21, ID=22, NUMBER=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "OP", "CP", "OK", "CK", "SC", "VIR", "OPERPREC", "OPER", 
			"OPEREL", "ATTR", "ID", "NUMBER", "WS"
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


	public LangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00b4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\5\25\u0098\n\25\3\26\3\26\3\26\3\27\3\27\7\27\u009f\n"+
		"\27\f\27\16\27\u00a2\13\27\3\30\6\30\u00a5\n\30\r\30\16\30\u00a6\3\30"+
		"\3\30\6\30\u00ab\n\30\r\30\16\30\u00ac\5\30\u00af\n\30\3\31\3\31\3\31"+
		"\3\31\2\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\t\4\2,,"+
		"\61\61\4\2--//\4\2>>@@\3\2c|\6\2\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\""+
		"\2\u00bb\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5<\3\2\2\2\7E\3\2\2\2\tM\3\2\2\2"+
		"\13T\3\2\2\2\rY\3\2\2\2\17^\3\2\2\2\21f\3\2\2\2\23i\3\2\2\2\25o\3\2\2"+
		"\2\27u\3\2\2\2\31~\3\2\2\2\33\u0080\3\2\2\2\35\u0082\3\2\2\2\37\u0084"+
		"\3\2\2\2!\u0086\3\2\2\2#\u0088\3\2\2\2%\u008a\3\2\2\2\'\u008c\3\2\2\2"+
		")\u0097\3\2\2\2+\u0099\3\2\2\2-\u009c\3\2\2\2/\u00a4\3\2\2\2\61\u00b0"+
		"\3\2\2\2\63\64\7r\2\2\64\65\7t\2\2\65\66\7q\2\2\66\67\7i\2\2\678\7t\2"+
		"\289\7c\2\29:\7o\2\2:;\7c\2\2;\4\3\2\2\2<=\7h\2\2=>\7k\2\2>?\7o\2\2?@"+
		"\7r\2\2@A\7t\2\2AB\7q\2\2BC\7i\2\2CD\7=\2\2D\6\3\2\2\2EF\7f\2\2FG\7g\2"+
		"\2GH\7e\2\2HI\7n\2\2IJ\7c\2\2JK\7t\2\2KL\7g\2\2L\b\3\2\2\2MN\7p\2\2NO"+
		"\7w\2\2OP\7o\2\2PQ\7d\2\2QR\7g\2\2RS\7t\2\2S\n\3\2\2\2TU\7v\2\2UV\7g\2"+
		"\2VW\7z\2\2WX\7v\2\2X\f\3\2\2\2YZ\7n\2\2Z[\7g\2\2[\\\7k\2\2\\]\7c\2\2"+
		"]\16\3\2\2\2^_\7g\2\2_`\7u\2\2`a\7e\2\2ab\7t\2\2bc\7g\2\2cd\7x\2\2de\7"+
		"c\2\2e\20\3\2\2\2fg\7u\2\2gh\7g\2\2h\22\3\2\2\2ij\7g\2\2jk\7p\2\2kl\7"+
		"v\2\2lm\7c\2\2mn\7q\2\2n\24\3\2\2\2op\7u\2\2pq\7g\2\2qr\7p\2\2rs\7c\2"+
		"\2st\7q\2\2t\26\3\2\2\2uv\7g\2\2vw\7p\2\2wx\7s\2\2xy\7w\2\2yz\7c\2\2z"+
		"{\7p\2\2{|\7v\2\2|}\7q\2\2}\30\3\2\2\2~\177\7*\2\2\177\32\3\2\2\2\u0080"+
		"\u0081\7+\2\2\u0081\34\3\2\2\2\u0082\u0083\7}\2\2\u0083\36\3\2\2\2\u0084"+
		"\u0085\7\177\2\2\u0085 \3\2\2\2\u0086\u0087\7=\2\2\u0087\"\3\2\2\2\u0088"+
		"\u0089\7.\2\2\u0089$\3\2\2\2\u008a\u008b\t\2\2\2\u008b&\3\2\2\2\u008c"+
		"\u008d\t\3\2\2\u008d(\3\2\2\2\u008e\u008f\7?\2\2\u008f\u0098\7?\2\2\u0090"+
		"\u0091\7#\2\2\u0091\u0098\7?\2\2\u0092\u0093\7@\2\2\u0093\u0098\7?\2\2"+
		"\u0094\u0095\7>\2\2\u0095\u0098\7?\2\2\u0096\u0098\t\4\2\2\u0097\u008e"+
		"\3\2\2\2\u0097\u0090\3\2\2\2\u0097\u0092\3\2\2\2\u0097\u0094\3\2\2\2\u0097"+
		"\u0096\3\2\2\2\u0098*\3\2\2\2\u0099\u009a\7<\2\2\u009a\u009b\7?\2\2\u009b"+
		",\3\2\2\2\u009c\u00a0\t\5\2\2\u009d\u009f\t\6\2\2\u009e\u009d\3\2\2\2"+
		"\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1.\3"+
		"\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5\t\7\2\2\u00a4\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00ae\3\2"+
		"\2\2\u00a8\u00aa\7\60\2\2\u00a9\u00ab\t\7\2\2\u00aa\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2"+
		"\2\2\u00ae\u00a8\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\60\3\2\2\2\u00b0\u00b1"+
		"\t\b\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\b\31\2\2\u00b3\62\3\2\2\2\t\2"+
		"\u0097\u009e\u00a0\u00a6\u00ac\u00ae\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
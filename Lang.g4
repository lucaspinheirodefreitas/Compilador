grammar Lang;
@header {
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
}

@members {
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

    private void assignmentVerifyType(CommandAssign command) {
        String REGEX_NUMBER = "(\\d+\\.?\\d*(\\*|\\/|\\+|\\-)?\\d*\\.?\\d*)*[0-9]*$";
        String REGEX_TEXT = "^([a-z]|[A-Z])+.*";

        _name       = command.getId();
        symbol      = symbolTable.getMap().get(_name);
        _type    = ((Variable) symbol).getType();

        String commands = command.getExpression();

        boolean texto  = commands.matches(REGEX_TEXT);
        boolean numero = commands.matches(REGEX_NUMBER);

        if(_type == 0 && texto || _type == 1 && numero || !numero && !texto) {
            throw new SemanticException("Incompatible types - expected type -> " + (_type==0 ? "NUMBER." : "TEXT.") + "\nCannot be converted "
                                        + (!numero && !texto ? "UNRECOGNIZED TYPE" : (numero ? "NUMBER" : "TEXT"))
                                        + " to -> " + (_type==0 ? "NUMBER." : "TEXT."));
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
}
executable  : 'programa' dec bloco 'fimprog;'
                {
                    program.setCommands(stackCommands.pop());
                }
            ;

dec         : (declare)+
            ;

declare     : 'declare' type ID {
                                    variableVerifyExists(_input.LT(-1).getText());
                                }
                        (VIR ID {
                                    variableVerifyExists(_input.LT(-1).getText());
                                })* SC
            ;

type        : 'numero'  {_type = Variable.NUMBER;}
            | 'texto'    {_type = Variable.TEXT;}
            ;

bloco       :   {
                    commands = new ArrayList<AbstractCommand>();
                    stackCommands.push(commands);
                } (cmd)+
            ;

cmd         :
                 cmdread
                |cmdwrite
                |cmdassign
                |cmdcond
                |cmdloop
            ;

cmdread     : 'leia'    OP (term | ID   {_name = _input.LT(-1).getText(); setVariableReferenced(_name);})
                                        {_readId = _input.LT(-1).getText();}
                        CP SC   {
                                    CommandRead command = new CommandRead(_readId);
                                    stackCommands.peek().add(command);
                                }
            ;

cmdwrite    : 'escreva' OP (term | ID   {_name = _input.LT(-1).getText(); setVariableReferenced(_name);})
                                        {_writeId = _input.LT(-1).getText();}
                        CP SC   {
                                    CommandWrite command = new CommandWrite(_writeId);
                                    stackCommands.peek().add(command);
                                }
            ;

cmdassign   :           ID      {
                                    variableVerifyNotExists(_input.LT(-1).getText());
                                    _exprId = _input.LT(-1).getText();
                                    setVariableUsed(_exprId);
                                }
                        ATTR    {
                                    _exprContent = "";
                                }
                        expr    {
                                    setVariableValue(_exprId, _exprContent);
                                    CommandAssign command = new CommandAssign(_exprId, _exprContent);
                                    assignmentVerifyType(command);
                                    stackCommands.peek().add(command);
                                }
                        SC
            ;

cmdcond     : 'se'  OP (ID {_name = _input.LT(-1).getText(); setVariableReferenced(_name);} | NUMBER)
                    OPEREL (ID {_name = _input.LT(-1).getText(); setVariableReferenced(_name);} | NUMBER)
                                {
                                    _exprDecision = "";
                                    _exprDecision += _input.LT(-3).getText();
                                    _exprDecision += _input.LT(-2).getText();
                                    _exprDecision += _input.LT(-1).getText();
                                }
                                CP 'entao' OK
                                {
                                    commands = new ArrayList<AbstractCommand>();
                                    stackCommands.push(commands);
                                }
                                (cmd)+ CK
                                {
                                    trueCondition = stackCommands.pop();
                                }
              ('senao' OK
                                {
                                    commands = new ArrayList<AbstractCommand>();
                                    stackCommands.push(commands);
                                }
              (cmd)+ CK
                                {
                                    falseCondition = stackCommands.pop();
                                    CommandConditional  cmd = new CommandConditional(_exprDecision, trueCondition, falseCondition);
                                    stackCommands.peek().add(cmd);
                                }
              )?
            ;

cmdloop     : 'enquanto'    OP (ID {_name = _input.LT(-1).getText(); setVariableReferenced(_name);} | NUMBER)
                            OPEREL (ID {_name = _input.LT(-1).getText();} | NUMBER) {
                                                    _exprDecision = "";
                                                    _exprDecision += _input.LT(-3).getText();
                                                    _exprDecision += _input.LT(-2).getText();
                                                    _exprDecision += _input.LT(-1).getText();
                                                } CP OK     {
                                                                commands = new ArrayList<AbstractCommand>();
                                                                stackCommands.push(commands);
                                                            } (cmd)+ CK {
                                                                           loop = stackCommands.pop();
                                                                           CommandLoop cmd = new CommandLoop(_exprDecision, loop);
                                                                           stackCommands.peek().add(cmd);
                                                                        }
            ;

expr        : prec
            ;

prec        : OPER {_exprContent += _input.LT(-1).getText();} expr | precedencia expr | vazio
            ;

precedencia : OPERPREC {_exprContent += _input.LT(-1).getText();} prec | term
            ;

vazio       :
            ;

term        : (IDTEXT | NUMBER) {_exprContent += _input.LT(-1).getText();}
            ;

OP          : '('
            ;
CP          : ')'
            ;
OK          : '{'
            ;
CK          : '}'
            ;
SC          : ';'
            ;
VIR         : ','
            ;
OPERPREC    : '*' | '/'
            ;
OPER        : '+' | '-'
            ;
OPEREL      : '==' | '!=' | '>=' | '<=' | '>' | '<'
            ;
ATTR        : ':='
            ;
ID          : [a-z]([a-z] | [A-Z] | [0-9] | '_')*
            ;
IDTEXT      : ([a-z]|[A-Z])([a-z] | [A-Z] | [0-9] | '_')*
            ;
NUMBER      : [0-9]+('.'[0-9]+)?
            ;
WS          : (' ' | '\t' | '\n' | '\r') -> skip
            ;
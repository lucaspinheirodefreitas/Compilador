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

type        : 'number'  {_type = Variable.NUMBER;}
            | 'text'    {_type = Variable.TEXT;}
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

cmdread     : 'leia'    OP term   {
                                    _readId = _input.LT(-1).getText();
                                }
                        CP SC   {
                                    CommandRead command = new CommandRead(_readId);
                                    stackCommands.peek().add(command);
                                }
            ;

cmdwrite    : 'escreva' OP term   {
                                     _writeId = _input.LT(-1).getText();
                                }
                        CP SC   {
                                    CommandWrite command = new CommandWrite(_writeId);
                                    stackCommands.peek().add(command);
                                }
            ;

cmdassign   :           ID      {
                                    variableVerifyNotExists(_input.LT(-1).getText());
                                    _exprId = _input.LT(-1).getText();
                                    setVariableUsed(_exprId); //@todo -> passivel de melhoria em conjunto com o método: setVariableValue
                                    names.add(_exprId);
                                }
                        ATTR    {
                                    _exprContent = "";
                                }
                        expr    {
                                    setVariableValue(_exprId, _exprContent);
                                    CommandAssign command = new CommandAssign(_exprId, _exprContent);
                                    assignmentVerifyType();
                                    stackCommands.peek().add(command);
                                }
                        SC
            ;

cmdcond     : 'se' OP term OPEREL term
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

cmdloop     : 'enquanto' OP term OPEREL term    {
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

expr        : /*prec OP prec*/
                                term OPER  {
                                    _exprContent += _input.LT(-1).getText();
                                } prec | precedencia
            ;

prec        : precedencia OPERPREC expr | precedencia
            ;

//    E = E + T | E - T
//    T = T * F | T / F
//    F = ID | NUM | ( E )

precedencia :   term  {names.add(_input.LT(-1).getText());}
            ;

term        : (ID   {
                        _name=_input.LT(-1).getText();
                        variableVerifyNotExists(_name);
                        setVariableReferenced(_name);
                    } | NUMBER)
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
NUMBER      : [0-9]+('.'[0-9]+)?
            ;
WS          : (' ' | '\t' | '\n' | '\r') -> skip
            ;
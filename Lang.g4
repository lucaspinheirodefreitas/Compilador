grammar Lang;
// parei com 1 hora da aula 10.
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
            | 'boolean' {_type = Variable.BOOLEAN;}
            ;

bloco       :   {
                    commands = new ArrayList<AbstractCommand>();
                    stackCommands.push(commands);
                } (cmd)+
            ;

cmd         :
                 cmdread    //{System.out.println("leitura");}
                |cmdwrite   //{System.out.println("escrita");}
                |cmdassign  //{System.out.println("atribuição");}
                |cmdcond    //{System.out.println("condicional");}
                |cmdloop
                |cmdexp     //{System.out.println("expressão");}
            ;

cmdread     : 'leia'    OP ID   {
                                    variableVerifyNotExists(_input.LT(-1).getText());
                                    _readId = _input.LT(-1).getText();
                                }
                        CP SC   {
                                    CommandRead command = new CommandRead(_readId);
                                    stackCommands.peek().add(command);
                                }
            ;

cmdwrite    : 'escreva' OP ID   {
                                     variableVerifyNotExists(_input.LT(-1).getText());
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
                                }
                        ATTR    {
                                    _exprContent = "";
                                }
                        expr    {
                                    CommandAssign command = new CommandAssign(_exprId, _exprContent);
                                    stackCommands.peek().add(command);
                                }
                        SC
            ;

cmdcond     : 'se' OP (ID       {
                                    variableVerifyNotExists(_input.LT(-1).getText());
                                } | NUMBER)
                                {
                                    _exprContent  += _input.LT(-1).getText();
                                    _exprDecision += _input.LT(-1).getText();
                                }
                                OPEREL (ID
                                {
                                  variableVerifyNotExists(_input.LT(-1).getText());
                                } | NUMBER)
                                {
                                    _exprContent  += _input.LT(-1).getText();
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

cmdloop     : 'enquanto' OP (ID     {
                                        variableVerifyNotExists(_input.LT(-1).getText());
                                    } | NUMBER) OPEREL (ID   {
                                                                variableVerifyNotExists(_input.LT(-1).getText());
                                                            } | NUMBER) CP OK (cmd)+ CK
            ;

cmdexp      : ID ATTR expr
            ;

expr        : term (OPER
                        {
                            _exprContent += _input.LT(-1).getText();
                        }
                    term)*
            ;

term        : (ID {
                     variableVerifyNotExists(_input.LT(-1).getText());
                 } | NUMBER)+
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
OPER        : '+' | '-' | '*' | '/'
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
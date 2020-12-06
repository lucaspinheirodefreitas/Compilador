package br.com.ufabc.compiler.core.model.token;

public enum Tokens {
    TK_IDENTIFIER(0),
    TK_NUMBER(1),
    TK_OPERATOR(2),
    TK_PONTUATION(3),
    TK_ASSIGN(4);

    private final int token;

    Tokens(int token) {
        this.token = token;
    }

    public int getToken() {
        return this.token;
    }
}

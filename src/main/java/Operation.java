public enum Operation {
    ADD("+", "ADDITION"),
    SUB("-", "SUBSTRACTION"),
    DIV("/", "DIVIDE"),
    MULT("*", "MULTIPLICATION");

    private String operSymbol;
    private String operFull;

    Operation(String operSymbol, String operFull) {
        this.operSymbol = operSymbol;
        this.operFull = operFull;
    }

    public String getOperSymbol() {
        return operSymbol;
    }

    public String getOperFull() {
        return operFull;
    }
}

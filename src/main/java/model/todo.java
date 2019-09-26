package model;

public class todo extends Task {
    private final char symbol = 'T';

    public todo(String description) {
        super(description);
    }

    public todo(String description, boolean isDone) {
        super(description);
        this.setIsDone(isDone);
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public String getTime() {
        return getDescription();
    }
}

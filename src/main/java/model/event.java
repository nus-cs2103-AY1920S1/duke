package model;

public class event extends Task {
    private final char symbol = 'E';
    private String details;

    public event(String description, String details) {
        super(description);
        this.details = details;
    }

    public event(String description, Boolean isDone, String details) {
        super(description);
        this.details = details;
        this.setIsDone(isDone);
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public String getDetails() {
        return this.details;
    }

    @Override
    public String getTime() {
        return null;
    }


}

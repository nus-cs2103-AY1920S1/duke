package Model;

public class todo extends Task{
    private final char symbol = 'T';

    public todo(String description) {
        super(description);
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }
}

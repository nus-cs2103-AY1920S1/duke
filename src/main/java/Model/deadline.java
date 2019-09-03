package Model;

public class deadline extends Task{
    private final char symbol = 'D';
    private String details;

    public deadline(String description, String details){
        super(description);
        this.details = details;
    }

    public deadline(String description, Boolean isDone, String details){
        super(description);
        this.details = details;
        this.setIsDone(isDone);
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public String getDetails(){
        return this.details;
    }

}

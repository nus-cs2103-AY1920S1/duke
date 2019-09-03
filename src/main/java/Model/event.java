package Model;

public class event extends Task{
    private final char symbol = 'E';
    private String details;

    public event(String description, String details){
        super(description);
        this.details = details;
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

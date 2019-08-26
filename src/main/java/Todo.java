public class Todo extends Task {

    //protected String by;
    //protected String symbol = "T";


    public Todo(String description) {
        super(description);
        super.symbol = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
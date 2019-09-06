/**
 * Represents a to-do object.
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "todo";
        this.symbol = "T";
    }

    @Override
    public String toString() {

        return "[" + this.getSymbol() + "]" + this.getStatusIcon() + this.getDescription();
    }
}

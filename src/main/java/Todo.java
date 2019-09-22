public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "todo";
    }

    public Todo(boolean isDone, String description) {
    	super(isDone, description); // usually isDone = true here
    	this.type = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
package duke.errands;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getStatus() {
        String completion;
        if (this.isDone) {
            completion = "1";
        } else {
            completion = "0";
        }
        return "T | " + completion + " | " + this.description;
    }

}
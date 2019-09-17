package seedu.duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String writeToFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description + ((priority == null)? "\n" : " | " + priority +
                "\n");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + ((priority == null)? "" : " <<P: " + priority + ">>");
    }
}

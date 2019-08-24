package duke.task;

public class Todo extends Task {
    private char shortForm = 'T';

    //Used when user entered command
    public Todo(String name) {
        super(name);
    }

    //Used when program loads data from text
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public char getShortForm() {
        return shortForm;
    }

    public String toString() {
        return "[" + getShortForm() + "]" + super.toString();
    }
}

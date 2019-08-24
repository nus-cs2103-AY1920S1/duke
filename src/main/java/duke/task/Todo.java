package duke.task;

public class Todo extends Task {
    private char shortForm = 'T';

    public Todo(String name) {
        //Used when user entered command
        super(name);
    }

    public Todo(String name, boolean isDone) {
        //Used when program loads data from text
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

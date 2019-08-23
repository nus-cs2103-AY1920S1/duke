public class Todo extends Task {
    private char shortForm = 'T';

    public Todo(String name) {
        super(name);
    }

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

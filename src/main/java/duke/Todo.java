package duke;

class Todo extends Task {
    /**
     * Creates a new todo.
     * @param description Description of the todo
     */
    Todo(String description) {
        super(description);
    }

    /**
     * Creates a new todo.
     * @param description Description of the todo
     * @param isDone Flag whether todo is done
     */
    Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Generates the task's textual representation in save file format.
     * @return Textual representation of the task in save file format
     */
    @Override
    String toSaveFormat() {
        return String.format("T | %s", super.toSaveFormat());
    }

    /**
     * Generates the task's textual representation in display format.
     * @return Textual representation of the task in display format
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

package duke.task;

/**
 * Represents a todo task item.
 */
public class ToDo extends Task {

    /**
     * Initialize a todo item with the content.
     * @param content String desctiption.
     */
    public ToDo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return done ? String.format("[T][%c] %s", tick, content) : String.format("[T][%c] %s", cross, content);
    }
}
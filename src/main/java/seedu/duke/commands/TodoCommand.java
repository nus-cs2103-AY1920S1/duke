package seedu.duke.commands;

import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Todo;
import seedu.duke.ui.Ui;

/**
 * Abstraction of the \btodo Command.
 * Contains the {@code description} required to create a To Do Object to add to the list.
 * eg: \btodo something
 */
public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a new \bTodo with the description {@code description} to the TaskList {@code tasks}.
     * @param tasks The current TaskList instance.
     */
    @Override
    public void execute(TaskList tasks) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        Ui.printMessages("Got it. I've added this task:",
            "  " + todo.toString(),
            "Now you have " + tasks.size() + " tasks in the list.");
    }
}

package seedu.duke.commands;

import seedu.duke.storage.TaskList;
import seedu.duke.trackables.Task;
import seedu.duke.trackables.Todo;
import seedu.duke.ui.Ui;

import java.util.List;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        Ui.printMessages("Got it. I've added this task:",
            "  " + todo.toString(),
            "Now you have " + tasks.size() + " tasks in the list.");
    }
}

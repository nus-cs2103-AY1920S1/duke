package seedu.duke.commands;

import seedu.duke.trackables.Task;
import seedu.duke.trackables.Todo;

import java.util.List;

public class TodoCommand extends Command {

    private Todo todo;

    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(List<Task> tasks) {
        tasks.add(todo);
        echo(new String[] {
            "Got it. I've added this task:",
            "  " + todo.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }
}

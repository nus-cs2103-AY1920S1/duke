package seedu.duke.Commands;

import seedu.duke.Task;

import java.util.List;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(List<Task> tasks) {
        tasks.add(task);
        echo(new String[]{"added: " + task.getDescription()});
    }
}

package seedu.duke.commands;

import seedu.duke.trackables.Task;

import java.util.List;

public class AddCommand extends Command {

    private String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(List<Task> tasks) {
        Task task = new Task(description);
        tasks.add(task);
        echo("added: " + task.toString());
    }
}

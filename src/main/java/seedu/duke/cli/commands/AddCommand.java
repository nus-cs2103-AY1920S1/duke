package seedu.duke.cli.commands;

import seedu.duke.cli.Command;
import seedu.duke.cli.CommandException;
import seedu.duke.tasks.Task;

import java.util.List;

public abstract class AddCommand implements Command {
    protected abstract Task createTask() throws CommandException;

    @Override
    public boolean execute(List<Task> taskList) throws CommandException {
        Task t = createTask();
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.%n",
                t, taskList.size());
        return true;
    }
}

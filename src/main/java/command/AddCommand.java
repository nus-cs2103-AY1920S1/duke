package command;
/**
 *
 */

import task.Task;
import task.TaskList;

/**
 *
 */

public class AddCommand extends Command {
    String addedTask;
    Task added;

    /**
     *
     */

    public AddCommand(String x) {
        addedTask = x;
    }

    /**
     *
     */

    @Override
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        added = reference.addTasks(addedTask);
        return this.formatOutput();
    }

    /**
     *
     */

    public String formatOutput() {
        return TextFormatter.addFormat(added,reference.getSize());
    }

}




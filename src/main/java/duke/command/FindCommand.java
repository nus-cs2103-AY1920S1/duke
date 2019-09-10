package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String argument;

    /**
     * Constructs a command to find tasks.
     *
     * @param argument the argument supplied to the command.
     */
    public FindCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes a find command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFindTasks(tasks.findTask(argument));
    }
}

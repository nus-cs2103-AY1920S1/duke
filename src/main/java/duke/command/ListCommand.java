package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    private Class argument;

    /**
     * Constructs a command to list all tasks.
     */
    public ListCommand() {
    }

    /**
     * Constructs a command to list tasks of a specific type.
     *
     * @param argument the argument supplied to the command.
     */
    public ListCommand(Class argument) {
        this.argument = argument;
    }

    /**
     * Executes a list command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (argument == null) {
            ui.printListTasks(tasks);
        } else {
            ui.printFindTasks(tasks.filterByTaskType(argument));
        }

    }
}

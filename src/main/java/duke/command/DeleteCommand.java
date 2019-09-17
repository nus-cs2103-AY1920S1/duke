package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private String argument;

    /**
     * Constructs a command to delete a task.
     *
     * @param argument the argument supplied to the command.
     */
    public DeleteCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes a delete command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     * @throws DukeException if the command fails to execute.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask;
        try {
            myTask = tasks.getTask(Integer.parseInt(argument));
            ui.printTaskDeleted(myTask, tasks);
            tasks.deleteTask(Integer.parseInt(argument));
        } catch (NumberFormatException e) {
            myTask = tasks.getTask(argument);
            ui.printTaskDeleted(myTask, tasks);
            tasks.deleteTask(argument);
        }
        storage.saveMain(tasks);
    }
}

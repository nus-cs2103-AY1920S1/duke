package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private int argument;

    /**
     * Constructs a command to mark a task as done.
     *
     * @param argument the argument supplied to the command.
     */
    public DoneCommand(int argument) {
        this.argument = argument;
    }

    /**
     * Executes a done command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     * @throws DukeException if the command fails to execute.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask = tasks.getTask(argument);
        myTask.markAsDone();
        ui.printMessage("Nice! I've marked this duke.task as done:\n  " + myTask);
        storage.save(tasks);
    }
}

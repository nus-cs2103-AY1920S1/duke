package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.FileNotFoundException;

public class RestoreCommand extends Command {
    private int argument;

    /**
     * Constructs a command to restore an archived task.
     *
     * @param argument the argument supplied to the command.
     */
    public RestoreCommand(int argument) {
        this.argument = argument;
    }

    /**
     * Executes a restore command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     * @throws DukeException if the command fails to execute.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList archivedTasks;
        try {
            archivedTasks = new TaskList(storage.loadArchive());
        } catch (FileNotFoundException e) {
            archivedTasks = new TaskList();
        }
        Task myTask = archivedTasks.getTask(argument);
        tasks.addTask(myTask);
        ui.printTaskRestored(myTask, tasks);
        storage.saveMain(tasks);
        archivedTasks.deleteTask(argument);
        storage.saveArchive(archivedTasks);
    }
}

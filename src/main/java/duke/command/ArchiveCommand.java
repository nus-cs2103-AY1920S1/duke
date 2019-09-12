package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.FileNotFoundException;

public class ArchiveCommand extends Command {
    private int argument;

    /**
     * Constructs a command to delete a task.
     *
     * @param argument the argument supplied to the command.
     */
    public ArchiveCommand(int argument) {
        this.argument = argument;
    }

    /**
     * Executes an archive command using the given task list, UI and file storage.
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
        Task myTask = tasks.getTask(argument);
        ui.printTaskArchived(myTask, tasks);
        archivedTasks.addTask(myTask);
        storage.saveArchive(archivedTasks);
        tasks.deleteTask(argument);
        storage.saveMain(tasks);
    }
}

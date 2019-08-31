package cs2103t.duke.command;

import cs2103t.duke.exception.DukeException;
import cs2103t.duke.exception.NoIdGivenException;
import cs2103t.duke.file.Storage;
import cs2103t.duke.parse.Parser;
import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

/**
 * Represents a done command.
 */
public class DoneCommand extends Command {
    /** Position of the task in list of tasks to mark as done. */
    private String taskId;

    /**
     * Constructs done command.
     * @param idString position of task in list to mark as done.
     */
    public DoneCommand(String idString) {
        this.taskId = idString;
        super.isExit = false;
    }

    /**
     * Marks task as done.
     * @param tasks TaskList agent to handle list of tasks.
     * @param ui Ui in charge of printing.
     * @param storage Storage agent in charge of writing to file.
     * @throws DukeException if command is invalid or cannot write to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskId.equals("")) {
            throw new NoIdGivenException("done");
        }
        int id = Parser.parseStrToInt(this.taskId);

        Task task = tasks.markDone(id);

        storage.updateFile(tasks);

        ui.dukeRespond("Nice! I've marked this task as done:",
                "  " + task.toString());
    }
}

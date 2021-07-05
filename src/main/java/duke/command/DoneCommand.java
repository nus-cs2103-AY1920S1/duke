package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DoneCommand extends TaskCommand {
    private static final String outputDoTask = "Nice! I've marked this task as done:\n";
    private int number;

    public DoneCommand(int number) {
        this.number = number;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Executes done command.
     *
     * @param taskList The list of tasks maintained in Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the tasks
     * @throws DukeException when the task doesn't exist
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder(outputDoTask);
        if (number > taskList.getSize()) {
            throw new DukeException(outputInvalidTask);
        }
        Task doneTask = taskList.markDone(number);
        sb.append(doneTask + "\n");
        return ui.print(sb.toString());
    }
}

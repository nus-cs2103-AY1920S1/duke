package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int number;

    /**
     * Constructor which creates a delete command.
     *
     * @param number the task number
     */
    public DeleteCommand(int number) {
        this.number = number;
    }

    /**
     * Tells this is not an exit command.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes the task in the list.
     *
     * @param taskList list of tasks
     * @param ui       interact with user input
     * @param storage  load and save duke.tasks
     * @throws DukeException when command is invalid
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:");
        if (number > taskList.getSize()) {
            throw new DukeException("It's an invalid task");
        }
        Task deletedTask = taskList.deleteTask(number);
        sb.append("\n" + deletedTask);
        ui.print(sb.toString());
    }
}

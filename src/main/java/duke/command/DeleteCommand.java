package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    /**
     * Creates new deadline task using a TaskList, Ui and Storage, it will then be added into the taskArrayList that
     * was loaded into the TaskList as param.
     * @param tasks the TaskList to be used
     * @param ui the Ui to be used
     * @param storage the Storage to be used
     * @throws DukeException when the format of remainingwords is not legit
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (ui.getRemainingWords().isEmpty()) {
            throw new DukeException("☹OOPS!!! Wrong format");
        }
        int position = Integer.parseInt(ui.getRemainingWords().trim());

        String toPrint = "Noted. I've removed this task. \n";
        toPrint += (tasks.getTaskArrayList().get(position-1)) + "\n";
        tasks.delete(position-1);
        storage.writeData();
        toPrint += ("Now you have " + tasks.getTaskArrayList().size() + " tasks in the list");
        return toPrint;
    }

}

package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Finds and returns all matching tasks within t, as specified by the command represented by
     * this object.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     * @return a String representing all matching tasks within t
     * @throws IndexOutOfBoundsException if no tasks exists within t
     */
    public String execute(TaskList t, Ui ui, Storage storage)
            throws IndexOutOfBoundsException {
        try {
            String search = command.substring("find".length() + 1);
            ArrayList<String> tasks = t.find(search);
            if (tasks.isEmpty()) {
                throw new IndexOutOfBoundsException("No index found.");
            } else {
                return ui.asDukeMessage(
                    new String[]{"Here are the matching tasks in your list:"},
                    new String[]{},
                    tasks.toArray(new String[0])
                );
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(
                    "Sorry! I could not find any tasks matching that search term");
        }
    }
}

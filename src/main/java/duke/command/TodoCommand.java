package duke.command;

import duke.exception.DukeException;
import duke.initials.Todo;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.text.SimpleDateFormat;

public class TodoCommand extends Command {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Creates new deadline task using a TaskList, Ui and Storage, it will then be added into the taskArrayList that
     * was loaded into the TaskList as param.
     * @param tasks the TaskList to be used
     * @param ui the Ui to be used
     * @param storage the Storage to be used
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showLine();
            if (ui.getRemainingWords().equals("")) {
                throw new DukeException("☹OOPS!!! The Description of a todo cannot be empty");
            }
            Todo t = new Todo(ui.getRemainingWords());
            tasks.add(t);
            storage.writeData();
            String toPrint = "Got it. I've added this task: \n "
                    + t + "\n" + "Now you have " + tasks.getTaskArrayList().size() + " tasks in the list.";
            return toPrint;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

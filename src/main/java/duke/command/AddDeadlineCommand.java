package duke.command;

import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

import java.util.Date;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(String[] msg) {
        super(msg);
    }
    /**
     * Check is the user input format correct.
     * If is correct then create a Deadline task and write into the file.
     * @param list  TaskList
     * @param ui    UiText
     * @param storage   Storage
     * @throws DukeException wrong format or wrong input
     */

    @Override
    public String execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if (super.command.length <= 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        assert super.command.length > 1;
        String[] msgs = super.command[1].split("/by");
        //check is the description correct.
        if (msgs.length == 2 && !msgs[1].equals(" ") && !msgs[0].equals("")) {
            try {
                Date temp = new Date(msgs[1]);
                Task task = new Deadline(msgs[0], msgs[1]);
                list.addToList(task);
                return UiText.addedMsg(task);
            } catch (Exception e) {
                throw new DukeException("please put your date as \n\t[dd/mm/yyyy] \n\tor[dd/mm/yyyy xx:xx(24hr)]");
            }
        } else {
            throw new DukeException("OOPS!!! The format of the description of a deadline is wrong");

        }
    }
}

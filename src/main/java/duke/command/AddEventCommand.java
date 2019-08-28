package duke.command;

import duke.DukeException;
import duke.parser.Event;
import duke.parser.Task;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

import java.io.IOException;

public class AddEventCommand extends Command {
    public AddEventCommand(String[] msg) {
        super(msg);
    }

    /**
     * Check is the user input format correct.
     * If is correct then create a event task and write into the file.
     * @param list  TaskList
     * @param ui    UiText
     * @param storage   Storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if (super.command.length > 1) {
            String[] msgs = super.command[1].split("/at");
            if (msgs.length == 2 && !msgs[1].equals(" ") && !msgs[0].equals("")) {
                try {
                    Task task = new Event(msgs[0], msgs[1]);
                    list.getList().add(task);
                    storage.appendToFile(
                            String.format("E | 0 | %s | %s\n",
                                    task.getDescription(),
                                    task.getInformation()));

                    //add file msg
                    ui.addedMsg(task);
                } catch (IOException e) {
                    ui.unableToWriteFileError();
                }
            } else {
                throw new DukeException("\u1F65 OOPS!! The format of the description of a deadline is wrong.");
            }
        } else {
            throw new DukeException("\u1F65 OOPS!! The description of a deadline cannot be empty.");
        }
    }
}

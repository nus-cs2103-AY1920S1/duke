package duke.command;

import duke.DukeException;
import duke.parser.Deadline;
import duke.parser.Task;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(String[] msg) {
        super(msg);
    }

    @Override
    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if(super.command.length > 1) {
            String[] msgs = super.command[1].split("/by");
            //check is the description correct.
            if(msgs.length == 2 && !msgs[1].equals(" ") && !msgs[0].equals("")) {
                try {
                    Task task = new Deadline(msgs[0], msgs[1]);
                    storage.appendToFile(
                            String.format("D | 0 | %s | %s\n",
                                    task.getDescription(),
                                    task.getInformation()));
                    list.getList().add(task);

                    //added msg
                    ui.addedMsg(task);
                } catch (IOException e) {
                    //error msg
                    ui.unableToWriteFileError();
                }
            }else {
                throw new DukeException("\u1F65 OOPS!!! The format of the description of a deadline is wrong");

            }
        }else {
            throw new DukeException("\u1F65 OOPS!!! The description of a deadline cannot be empty.");
        }
    }
}

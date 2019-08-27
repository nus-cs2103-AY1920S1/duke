package duke.command;

import duke.DukeException;
import duke.parser.Task;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] msg) {
        super(msg);
    }

    @Override
    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if(super.command.length == 2) {
            try {
                int index = Integer.parseInt(super.command[1].trim());
                if(index > list.getList().size()) {
                    throw new DukeException("\u1F65 OOPS! the Number you\'ve key in is to big");
                } else if (index < 1) {
                    throw new DukeException("OOPS!! The number should be larger than 0");
                } else {
                    try {
                        Task tk = list.getList().get(index - 1);
                        list.getList().remove(index - 1);
                        Task.reduceByOne();
                        storage.updateFile(list.getList());

                        //removed msg
                        ui.deleteMsg(tk);
                    } catch (IOException e) {
                        //error msg
                        ui.unableToWriteFileError();
                    }
                }
            } catch (NumberFormatException ex) {
                throw new DukeException("\u1F65 OOPS! Invalid number as input");
            }
        } else {
            throw new DukeException("\u1F65 OOPS!! The format of the input is wrong");
        }
    }
}

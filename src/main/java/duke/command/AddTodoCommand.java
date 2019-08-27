package duke.command;

import duke.DukeException;
import duke.parser.Task;
import duke.parser.Todo;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

import java.io.IOException;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String[] msg) {
        super(msg);
    }

    @Override
    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if(super.command.length == 2 && !super.command[1].trim().equals("")) {
            try {
                Task task = new Todo(super.command[1]);
                list.getList().add(task);
                storage.appendToFile(String.format("T | 0 | %s\n",
                        task.getDescription()));
                //ui.addingMsg
                ui.addedMsg(task);
            } catch (IOException e) {
                //error msg
                ui.unableToWriteFileError();
            }
        }else {
            throw new DukeException("\u1F65 OOPS!!! The description of a todo cannot be empty.");
        }
    }

}

package duke.command;

import duke.DukeException;
import duke.parser.Task;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.UiText;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String[] msg) {
        super(msg);
    }

    @Override
    public void execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if (super.command.length == 2) {
            ArrayList<Task> result = new ArrayList<>();
            for (Task task : list.getList()) {
                String description = task.getDescription();
                String searchedWord = super.command[1].trim();
                if (description.contains(searchedWord)) {
                    result.add(task);
                }
            }
            if (result.size() > 0) {
                ui.findMsg(result);
            } else {
                throw new DukeException("\u1F65 OOPS!! There is no match to your searching");
            }
        } else {
            throw new DukeException("\u1F65 OOPS!! The format of the input is wrong");
        }
    }
}
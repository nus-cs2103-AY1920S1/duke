package duke.command;

import duke.dukeexception.DukeException;
import duke.task.Task;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UiText;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String[] msg) {
        super(msg);
    }

    @Override
    public String execute(TaskList list, UiText ui, Storage storage) throws DukeException {
        if (super.command.length != 2) {
            throw new DukeException("OOPS!! The format of the input is wrong");
        }
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : list.getList()) {
            String description = task.getDescription();
            String searchedWord = super.command[1].trim();
            if (description.contains(searchedWord)) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            throw new DukeException("OOPS!! There is no match to your searching");
        }
        return UiText.findMsg(result);
    }
}

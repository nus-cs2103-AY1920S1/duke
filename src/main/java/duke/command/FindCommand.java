package duke.command;

import duke.Duke;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a find command entered by the user.
 *
 * @author Terence Chong Guang Jun
 */
public class FindCommand extends Command {
    FindCommand() {
        super();
    }

    private FindCommand(List<String> arguments) {
        super(arguments);
    }

    @Override
    public int execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (arguments.size() < 1) {
            throw new DukeCommandException("Search term cannot be empty.");
        }
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(arguments.get(0))) {
                matchedTasks.add(task);
            }
        }

        int listSize = matchedTasks.size();
        String[] msg = new String[listSize + 1];
        msg[0] = "Here are the matching tasks in your list:";
        for (int i = 1; i <= listSize; i++) {
            msg[i] = i + ". " + matchedTasks.get(i - 1);
        }
        ui.sendMessage(msg);
        return Duke.CODE_CONTINUE;
    }

    @Override
    String[] getParams() {
        return new String[0];
    }

    @Override
    Command generate(List<String> arguments) {
        return new FindCommand(arguments);
    }
}

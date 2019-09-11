package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command {
    public FindCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert super.description != null;
        if (description.length < 2) {
            throw new DukeException("☹ OOPS!!! The keyword cannot be empty.");
        }

        String keyword = description[1];
        String finalMsg = "";
        for (Task task: tasks.getList()) {
            String d = task.getDescription();
            if (d.contains(keyword)) {
                finalMsg = finalMsg.concat(task.toString());
            }
        }
        return finalMsg;
    }
}

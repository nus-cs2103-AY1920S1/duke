package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command {
    public FindCommand(String[] description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.length < 2) {
            throw new DukeException("☹ OOPS!!! The keyword cannot be empty.");
        }

        String keyword = description[1];
        ui.showLine();
        for (Task task: tasks.getList()) {
            String d = task.getDescription();
            if (d.contains(keyword)) {
                System.out.println("       " + task);
            }
        }
        ui.showLine();
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.logic.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to find a task.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String desc = this.input.substring(4).trim();
        if (desc.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a find cannot be empty.");
        }
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (Task task : tasks.getTasks()) {
            if (task.contains(desc)) {
                result.append("     " + i + "." + task + "\n");
                i++;
            }
        }
        ui.setFindResponse(result.toString());
    }
}


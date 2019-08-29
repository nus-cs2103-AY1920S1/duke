package duke.command;

import duke.component.TaskList;
import duke.component.Ui;
import duke.database.Storage;
import duke.exception.DukeException;

public class FindCommand extends Command {
    public FindCommand(String input, String type) {
        super(input, type);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        find(tasks, ui);
    }

    public void find(TaskList tasks, Ui ui) {
        int index = 1;
        System.out.println(ui.INDENT_COMMENT
                + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getTask().size(); i++) {
            if (tasks.getTask().get(i).toString().toLowerCase().contains(super.input.trim())) {
                System.out.println(ui.INDENT_TASK + index++
                        + "." + tasks.getTask().get(i).toString());
            }
        }
    }
}

package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String str;

    public FindCommand(String str) {
        this.str = str.trim();
    }

    /**
     * Finds all Task containing str and display them via Ui.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.findAllIncludesAsUiString(str));
    }
}

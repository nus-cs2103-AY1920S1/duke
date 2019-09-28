package duke.commands;

import duke.errands.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store)  {
        String search = this.input.trim();
        ArrayList<Task> found = tasks.find(search);
        if (found.isEmpty()) {
            return ui.findEmpty();
        } else {
            return ui.foundItems(found);
        }
    }

}

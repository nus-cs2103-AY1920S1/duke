package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command{

    public FindCommand(String inputCommand) {
        super(inputCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = this.inputCommand.split(" ");
        if (inputsplit.length <= 1 ) {
            throw new DukeException("OOPS!!! The description of a find Command cannot be empty.");
        } else if (inputsplit.length > 2) {
            throw new DukeException("OOPS!!! The find Command can only accept one keyword.");
        } else {
            ArrayList<Task> taskToFind = tasks.findTask(inputsplit[1]);
            ui.printFind(taskToFind);
        }
    }

}

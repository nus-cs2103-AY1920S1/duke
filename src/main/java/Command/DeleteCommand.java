package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(String inputCommand) {
        super(inputCommand);
    }

    //note private can't be accessed by child
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = inputCommand.split(" ", 2);
        if (inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of delete must have a value.");
        } else if (Integer.parseInt(inputsplit[1]) > tasks.getSize() || Integer.parseInt(inputsplit[1]) <= 0) {
            throw new DukeException("OOPS!!! Invalid value for task delete!");
        } else {
            int num = Integer.parseInt(inputsplit[1]);
            Task t = tasks.removeTask(num);
            ui.printDelete(t, tasks.getSize());
            storage.writeToFile(tasks.getTaskList());
        }
    }
}

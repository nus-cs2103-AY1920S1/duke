package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class UnknownCommand extends Command {

    public UnknownCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

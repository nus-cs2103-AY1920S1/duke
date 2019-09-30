package duke.command;

import duke.DukeException;
import duke.TaskList;

public class WrongCommand extends Command {
    public WrongCommand(String[] message) {
        super(message);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

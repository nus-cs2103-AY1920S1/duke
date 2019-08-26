package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;

public class FakeCommand extends Command {

    public FakeCommand() {}

    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException{
        throw new DukeException("Please input a valid command.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

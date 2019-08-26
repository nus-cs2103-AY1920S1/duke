package duke.Commands;

import duke.DirectProcessor.TaskList;
import duke.DirectProcessor.Ui;
import duke.DukeException;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException{
        try{
            tl.rewrite();
            ui.showExitMessage();
        } catch (IOException e) {
            ui.showExitMessage();
            throw new DukeException("Unable to rewrite task list. Modification this time cannot be saved.");
        }

    }

    @Override
    public boolean isExit() {
        return true;
    }
}

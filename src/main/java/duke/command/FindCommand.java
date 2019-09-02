package duke.command;

import duke.DukeException.DukeException;
import duke.task.Task;
import duke.taskHandler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    protected String searchPhrase;

    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ui.printQuerySet(tasks, searchPhrase);
    }
}

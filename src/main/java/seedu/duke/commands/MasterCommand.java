package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

public abstract class MasterCommand {

    public String execute() {
        return null;
    };

    public String execute(TaskList taskList, UI ui, Storage storageHandler) throws DukeException {
        return null;
    };

    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        return null;
    }

    public boolean isExit() {
        return false;
    };
}

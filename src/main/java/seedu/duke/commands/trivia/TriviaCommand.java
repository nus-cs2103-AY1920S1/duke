package seedu.duke.commands.trivia;

import seedu.duke.commands.MasterCommand;
import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public abstract class TriviaCommand extends MasterCommand {

    public abstract String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException;

}

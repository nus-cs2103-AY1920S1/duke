package seedu.duke.commands.trivia;

import seedu.duke.commands.MasterCommand;
import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Abstract class to serve as the parent class for all trivia commands.
 */
public abstract class TriviaCommand extends MasterCommand {

    /**
     * Executes the command as defined in the children classes.
     *
     * @param trivia Trivia object that is used for the trivia.
     * @param ui UI to display messages to the user.
     * @param storage Storage to write or read files if applicable.
     * @return String that displays messages to the user.
     * @throws TriviaException Throws exceptions defined in the children classes.
     */
    public abstract String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException;

}

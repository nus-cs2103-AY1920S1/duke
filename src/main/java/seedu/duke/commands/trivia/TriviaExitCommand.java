package seedu.duke.commands.trivia;

import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Command that exits trivia mode.
 */
public class TriviaExitCommand extends TriviaCommand{

    /**
     * Informs the user that trivia mode has been exited.
     *
     * @param trivia Not applicable.
     * @param ui UI to inform user that they have exited trivia mode.
     * @param storage Not applicable.
     * @return String informing the user that they have exited trivia mode.
     */
    @Override
    public String execute(Trivia trivia, UI ui, Storage storage){
        return ui.exitTrivia();
    }
}

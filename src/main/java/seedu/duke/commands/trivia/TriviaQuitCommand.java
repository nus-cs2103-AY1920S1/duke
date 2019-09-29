package seedu.duke.commands.trivia;

import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Command that allows the user to quit the trivia early.
 */
public class TriviaQuitCommand extends TriviaCommand {

    /**
     * Resets the question counter of the question.
     *
     * @param trivia Trivia object that user is exiting quiz from.
     * @param ui UI to inform the user that the trivia has been exited.
     * @param storage Not applicable.
     * @return
     */
    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) {
        trivia.resetQuestionIndex();
        return ui.finishTrivia();
    }

}

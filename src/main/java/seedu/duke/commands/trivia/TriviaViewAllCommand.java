package seedu.duke.commands.trivia;

import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Command that views all the questions and answers in the trivia so far.
 */
public class TriviaViewAllCommand extends TriviaCommand {

    /**
     * Shows the user all the questions and answers they have put into trivia.
     *
     * @param trivia Trivia object that questions and answers are being shown from.
     * @param ui UI to display questions and answers to the user.
     * @param storage Not applicable.
     * @return
     */
    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) {
        return ui.viewAllTrivia(trivia);
    }
}

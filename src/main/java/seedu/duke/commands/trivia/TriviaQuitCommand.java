package seedu.duke.commands.trivia;

import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public class TriviaFinishCommand extends TriviaCommand {

    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) {
        trivia.resetQuestionIndex();
        return ui.finishTrivia();
    }

}

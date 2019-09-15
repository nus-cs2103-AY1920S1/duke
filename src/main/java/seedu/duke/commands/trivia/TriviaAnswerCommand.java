package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public class TriviaAnswerCommand extends TriviaCommand {
    private String answer;

    public TriviaAnswerCommand(String answer) {
        this.answer = answer;
    }

    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        return null;
    }

}

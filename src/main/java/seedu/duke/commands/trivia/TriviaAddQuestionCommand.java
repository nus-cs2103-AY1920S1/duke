package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public class TriviaAddQuestionCommand extends TriviaCommand {
    private String toAdd;

    public TriviaAddQuestionCommand(String question) {
        toAdd = question;
    }

    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        trivia.addQuestion(toAdd);
        storage.writeTrivia(trivia);
        return ui.questionAdded(toAdd);
    }
}

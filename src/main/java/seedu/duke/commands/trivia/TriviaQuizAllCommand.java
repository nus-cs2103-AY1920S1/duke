package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public class TriviaQuizAllCommand extends TriviaCommand {

    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        try {
            if (trivia.isFinalQuestion()) {
                TriviaAnswerCommand.forceFinal();
            }
            trivia.startQuestionIndex();
            return ui.startQuiz(trivia);
        } catch (IndexOutOfBoundsException ex) {
            throw new TriviaException("Who do you think I am, omniscient? YOU HAVE NO QUESTIONS.");
        }
    }
}

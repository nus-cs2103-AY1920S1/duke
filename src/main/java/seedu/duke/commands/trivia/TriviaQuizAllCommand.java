package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Command to initiate quiz.
 */
public class TriviaQuizAllCommand extends TriviaCommand {

    /**
     * First checks if the trivia only has one question. If so it will tell the parser to accept the next answer as the
     * final answer. Subsequently, the trivia will begin the question counter and inform the user that the quiz has
     * started.
     *
     * @param trivia Trivia object that is used for the quiz.
     * @param ui UI to inform the user that the quiz has started.
     * @param storage Not applicable.
     * @return String that informs the user that the quiz has started.
     * @throws TriviaException Throws if the trivia has no questions.
     */
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

package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Command that checks the answer to the question quizzed to the user.
 */
public class TriviaAnswerCommand extends TriviaCommand {
    private String answer;
    private static boolean isFinal;

    /**
     * Constructor that takes in the answer supplied by the user.
     *
     * @param answer Answer supplied by user.
     */
    public TriviaAnswerCommand(String answer) {
        this.answer = answer;
    }

    /**
     * Checks the answer against the answers already in the trivia, informs the user whether they are correct or not,
     * then check if the next question is the final question.
     *
     * @param trivia Trivia object that the answer is checked against.
     * @param ui UI to inform the user if the answer is correct or not.
     * @param storage Not applicable.
     * @return String that informs the user if the answer is correct or not.
     * @throws TriviaException Throws if answer field is blank.
     */
    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        boolean isCorrect = trivia.checkNextAnswer(answer);
        String toReturn;
        if (!isFinal) {
            if (isCorrect) {
                toReturn = ui.correctAnswer(trivia);
            } else {
                toReturn = ui.wrongAnswer(trivia);
            }
        } else {
            if (isCorrect) {
                toReturn = ui.correctAnswerFinal();
            } else {
                toReturn = ui.wrongAnswerFinal();
            }
            isFinal = false;
        }
        if (trivia.isFinalQuestion()) {
            isFinal = true;
        }
        return toReturn;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public static void forceFinal() {
        isFinal = true;
    }

}

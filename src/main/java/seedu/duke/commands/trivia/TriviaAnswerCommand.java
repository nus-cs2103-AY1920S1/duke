package seedu.duke.commands.trivia;

import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public class TriviaAnswerCommand extends TriviaCommand {
    private String answer;
    private static boolean isFinal;

    public TriviaAnswerCommand(String answer) {
        this.answer = answer;
    }

    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) {
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

}

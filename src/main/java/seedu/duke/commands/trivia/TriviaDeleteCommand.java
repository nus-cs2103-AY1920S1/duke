package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public class TriviaDeleteCommand extends TriviaCommand {

    private int questionIndex = -1;
    private int answerIndex = -1;

    public TriviaDeleteCommand(int questionNumber) {
        this.questionIndex = questionNumber - 1;
    }

    public TriviaDeleteCommand(int questionNumber, int answerNumber) {
        this.questionIndex = questionNumber - 1;
        this.answerIndex = answerNumber - 1;
    }

    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        try {
            if (answerIndex == -1) {
                String deletedQuestion = trivia.deleteQuestion(questionIndex);
                storage.writeTrivia(trivia);
                return ui.deleteQuestion(deletedQuestion);
            } else {
                String deletedAnswer = trivia.deleteAnswer(questionIndex, answerIndex);
                storage.writeTrivia(trivia);
                return ui.deleteAnswer(deletedAnswer);
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new TriviaException("HA!!! You cannot make me forget what is not there.");
        }
    }
}

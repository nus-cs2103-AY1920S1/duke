package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Command that deletes questions and answers.
 */
public class TriviaDeleteCommand extends TriviaCommand {

    private int questionIndex = -1;
    private int answerIndex = -1;

    /**
     * Constructor for deleting questions.
     *
     * @param questionNumber Question number to delete.
     */
    public TriviaDeleteCommand(int questionNumber) {
        this.questionIndex = questionNumber - 1;
    }

    /**
     * Constructor for deleting answers.
     *
     * @param questionNumber Question number to delete answer from.
     * @param answerNumber Answer number to delete.
     */
    public TriviaDeleteCommand(int questionNumber, int answerNumber) {
        this.questionIndex = questionNumber - 1;
        this.answerIndex = answerNumber - 1;
    }

    /**
     * If no answer number was supplied, the question will be deleted, it will be written to storage, then informs the
     * user that the question was deleted. Otherwise, the answer will be deleted, it will be written to storage, then
     * informs the user that the answer was deleted.
     *
     * @param trivia Trivia object that is used for the deletion.
     * @param ui UI to inform user of deletion.
     * @param storage Storage to write deletion of question or answer to.
     * @return String that informs user of deletion.
     * @throws TriviaException Throws if incorrect format was used.
     */
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

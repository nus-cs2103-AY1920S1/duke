package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Command that adds answers to existing questions in Trivia.
 */
public class TriviaAddAnswerCommand extends TriviaCommand {
    private String answerToAdd;
    private int answerIndex;

    /**
     * Constructor that takes in an answer and the question number to add the answer to.
     *
     * @param answerToAdd The answer the user wants to add.
     * @param answerNumber The question number the user wants to add the answer to.
     */
    public TriviaAddAnswerCommand(String answerToAdd, int answerNumber) {
        this.answerToAdd = answerToAdd;
        answerIndex = answerNumber - 1;
    }

    /**
     * Adds answer to the question in trivia, writes it to storage and then informs the user.
     *
     * @param trivia Trivia to add answer to.
     * @param ui UI to inform user that answer has been added.
     * @param storage Storage to write new answer to.
     * @return String that informs user that answer was added.
     * @throws TriviaException Throws if user does not input answer in correct format.
     */
    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        try {
            trivia.addAnswer(answerToAdd, answerIndex);
            storage.writeTrivia(trivia);
            return ui.answerAdded(answerToAdd, trivia.viewQuestion(answerIndex));
        } catch (IndexOutOfBoundsException ex) {
            throw new TriviaException("Tell me the answer, JOTARO!");
        }
    }

}

package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

/**
 * Command that adds question to the trivia.
 */
public class TriviaAddQuestionCommand extends TriviaCommand {
    private String toAdd;

    /**
     * Constructor that takes in the question to add.
     *
     * @param question Question to add into the trivia.
     */
    public TriviaAddQuestionCommand(String question) {
        toAdd = question;
    }

    /**
     * Adds question to the trivia, writes it to storage, then informs the user.
     *
     * @param trivia Trivia object that question is added to.
     * @param ui UI to inform user that question has been added.
     * @param storage Storage to write new question to.
     * @return String that informs user that question has been added.
     * @throws TriviaException Throws when field not defined properly.
     */
    @Override
    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        trivia.addQuestion(toAdd);
        storage.writeTrivia(trivia);
        return ui.questionAdded(toAdd);
    }
}

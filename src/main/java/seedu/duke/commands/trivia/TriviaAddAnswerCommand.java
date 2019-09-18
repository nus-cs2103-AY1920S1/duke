package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public class TriviaAddAnswerCommand extends TriviaCommand {
    private String answerToAdd;
    private int answerIndex;

    public TriviaAddAnswerCommand(String answerToAdd, int answerNumber) {
        this.answerToAdd = answerToAdd;
        answerIndex = answerNumber - 1;
    }

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

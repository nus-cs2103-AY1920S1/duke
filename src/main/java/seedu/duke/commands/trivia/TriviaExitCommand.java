package seedu.duke.commands.trivia;

import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.UI;

public class TriviaExitCommand extends TriviaCommand{

    @Override
    public String execute(Trivia trivia, UI ui, Storage storage){
        return ui.exitTrivia();
    }
}

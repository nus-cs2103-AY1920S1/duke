package seedu.duke.commands.base;

import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

/**
 * Command that starts the trivia.
 */
public class TriviaStartCommand extends Command {

    /**
     * Begins trivia mode in Parser.
     *
     * @param taskList Not applicable.
     * @param ui UI to inform user that trivia mode has started.
     * @param storage Not applicable.
     * @return String informing the user that trivia mode has started.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.startTrivia();
    }
}

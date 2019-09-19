package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    /**
     * Constructs a find command.
     */
    public FindCommand(String params) {
        super(params);
    }

    /**
     * Executes the command.
     *
     * @return List of matching tasks or error message.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) {
        TaskList matches = tasks.find(this.params);

        if (matches.getSize() > 0) {
            return Ui.showFindMatches(matches);
        } else {
            return Ui.showFindNoMatch();
        }
    }
}

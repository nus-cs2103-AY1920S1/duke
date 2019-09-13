package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command find matching tasks based on a specific tag.
 */
public class FindTaggedTaskCommand extends Command {

    /**
     * Constructs a new Command where it does not terminate the Duke Application.
     */
    public FindTaggedTaskCommand() {
        super(false);
    }

    /**
     * Executes the specific command based on the type of the command.
     * In this case, it finds matching tasks which contain a specific tag.
     * @param taskList The List of tasks involved.
     * @param ui The Interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @param dataParser Parses user and task data.
     * @param dateParser Parser date data.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        String keyword = dataParser.findTag();
        ArrayList<Integer> taskIndexes = taskList.findMatchingTaggedTasks(keyword);
        ui.showMatchingTasks(taskIndexes);
    }

    /**
     * Returns a String representation for user guidance.
     * @return a String representation for user guidance.
     */
    public String toString() {
        String helper = "Find Tasks with a tag: \n"
                + "Usage: findTag (tag)";
        return helper;
    }
}

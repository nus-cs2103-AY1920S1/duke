package duke.command;

import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.parser.TagParser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to add a tag to a specific task.
 */
public class AddTagCommand extends Command {

    /**
     * Constructs a new Command where it does not terminate the Duke Application.
     */
    public AddTagCommand() {
        super(false);
    }

    /**
     * Executes the specific command based on the type of the command.
     * In this case, it adds a tag to a Task.
     * @param taskList The List of tasks involved.
     * @param ui The Interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @param dataParser Parses user and task data.
     * @param dateParser Parser date data.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        String[] tagData = dataParser.parseTagData();
        TagParser tagParser = new TagParser();
        tagParser.createTag(tagData);
        Task task = taskList.addTag(tagParser.getIndex(), tagParser.getTag());
        ui.showTaggedTask(task);
        storage.save();
    }

    /**
     * Returns a String representation for user guidance.
     * @return a String representation for user guidance.
     */
    public String toString() {
        String helper = "Adds a tag to a task: \n"
            + "Usage: tag (index) <tag>";
        return helper;
    }

}

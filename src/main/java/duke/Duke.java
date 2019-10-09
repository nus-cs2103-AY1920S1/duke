package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;

/**
 * Main class for Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Duke object with disk storage located at filepath".
     *
     * @param filePath filepath for disk storage
     */
    public Duke(String filePath) {
        assert filePath != null : "Filepath cannot be null.";
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
    }

    /**
     * Generates a response to user input.
     *
     * @param input User input to be processed.
     * @return response to be printed.
     */
    protected String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return String.join("\n", command.execute(taskList));
        } catch (DukeException e) {
            //CHECKSTYLE.OFF: AvoidEscapedUnicodeCharactersCheck
            return "\u26A0 OOPS! " + e.getMessage();
            //CHECKSTYLE.ON: AvoidEscapedUnicodeCharactersCheck
        }
    }
}

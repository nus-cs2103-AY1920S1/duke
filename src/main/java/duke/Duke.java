package duke;

import duke.command.Command;

import java.lang.String;

/**
 * Encapsulates a chat bot.
 */
public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected boolean hasLoadingError = false;

    /**
     * Constructs a Duke object.
     *
     * @param filePath  Path to saved data file on hard disk.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            hasLoadingError = true;
            this.taskList = new TaskList();
        }
    }

    /**
     * Checks if loading of saved data file was a success.
     *
     * @return True if failed to load saved data file, false otherwise.
     */
    public boolean isHasLoadingError() {
        return hasLoadingError;
    }

    /**
     * Gets Duke's response to user input.
     *
     * @param input  User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(storage, taskList);
        } catch (DukeException e) {
            return Ui.showError(e.getMessage());
        }
    }
}
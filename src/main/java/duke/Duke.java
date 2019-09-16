package duke;

import duke.command.Command;

import java.lang.String;

/**
 * Encapsulates a chat bot.
 */
public class Duke {
    protected Storage storage;
    protected TaskList taskList;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        taskList = new TaskList();
    }

    /**
     * Attempts to load data file.
     *
     * @param filePath  Path to saved data file on hard disk.
     */
    public void load(String filePath) throws DukeException {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
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
            return command.execute(this, storage, taskList);
        } catch (DukeException e) {
            return Ui.showError(e.getMessage());
        }
    }
}
package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * Duke class.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
    }

    /**
     * Duke with file path to start.
     *
     * @param filePath The txt file location.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Used to generate a response to user input.
     *
     * @param input The input command from user.
     * @return The String of response message for the given message.
     */
    public String getResponse(String input) {
        assert input != null : "should have input, so getResponse";

        String output;
        try {
            Parser pr = new Parser();
            Command c = pr.parse(input);
            output = c.execute(this.tasks, ui, this.storage);
        } catch (Exception e) {
            output = Ui.frontSpace + e.getMessage();
        }
        return output;
    }
}

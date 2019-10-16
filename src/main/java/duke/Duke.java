package duke;

import duke.exception.DukeException;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a duke object.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        this(Storage.DEFAULT_FILEPATH);
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

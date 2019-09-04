package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.todo.TaskList;
import duke.ui.Ui;

/**
 * Duke class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke object with filePath input from which
     * the data of the existing is stored.
     *
     * @param filePath File path for input.
     */
    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        Parser parser = new Parser();
        ui.resetMessage();

        try {
            Command command = parser.parse(input);
            command.execute(tasks, ui);
            storage.save(tasks);
        } catch (DukeException e) {
            ui.reportError(e);
        }

        return ui.getMessage();
    }

}
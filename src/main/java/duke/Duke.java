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
    private Parser parser = new Parser();

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

    /**
     * Takes in user input, execute the command generated,
     * and returns a response accordingly in String.
     *
     * @param input User input.
     * @return Response to the user.
     */
    public String getResponse(String input) {
        // Create a parser and reset the message buffer in ui
        ui.resetMessage();

        // Try to execute the user input
        try {
            executeOnInput(input);
        } catch (DukeException e) {
            ui.reportError(e);
        }

        return ui.getMessage();
    }

    /**
     * Takes in a command and parse it with parser to get the command object
     * and execute it afterwards.
     *
     * @param input User input.
     * @throws DukeException When the input is invalid.
     */
    private void executeOnInput(String input) throws DukeException {
        // Generate command object with parser and execute the command
        Command command = parser.parse(input);
        command.execute(tasks, ui);

        // Save the updated list back to data file
        storage.save(tasks);
    }

}
package duke;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

import java.text.ParseException;

/**
 * The main driver of Duke program.
 */
class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke.
     */
    Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui);
    }

    /**
     * Gets a response by Duke in the form of string.
     *
     * <p>The list of tasks saved locally would be updated each time this method is called.</p>
     *
     * @param input The input text
     * @return
     */
    String getResponse(String input) {
        String response;
        try {
            response = parser.processLine(input); // add, delete, etc
        } catch (DukeException e) {
            response = e.getMessage();
        } catch (NumberFormatException e) {
            response = "Input must be an integer.";
        } catch (ParseException e) {
            response = (e.getMessage() + "\nPlease use the format: dd/MM/yyyy hhmm");
        } catch (Exception e) {
            response = ("Something is wrong: " + e.getMessage());
        }

        // save to text file
        storage.save(tasks);
        return response;
    }
}
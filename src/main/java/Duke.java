import duke.command.Command;
import duke.command.LoadCommand;
import duke.command.SaveCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

//import org.apache.commons.text.WordUtils;

/**
 * Handles program semantics at a very high level of abstraction.
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Constructor for Duke. Initializes the task list, UI, parser and storage classes.
     */
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage();
        try {
            LoadCommand c = new LoadCommand();
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            try {
                storage.flushData();
            } catch (DukeException ex) {
                ui.displayOutput(ex.getMessage());
            }
        }
    }

    /**
     * Gets Duke's response to the user input.
     * @param input User input.
     * @return Duke's response.
     */
    String getResponse(String input) {
        try {
            Command c = parser.parseInput(input);
            String output = c.execute(tasks, ui, storage);
            //String wrappedOutput = WordUtils.wrap(output, 70);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public void saveTasks() {
        try {
            SaveCommand c = new SaveCommand();
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

}

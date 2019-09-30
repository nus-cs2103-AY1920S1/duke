import duke.command.Command;
import duke.command.LoadCommand;
import duke.command.SaveCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

//import org.apache.commons.text.WordUtils;

/**
 * Handles program semantics at a very high level of abstraction.
 */
public class Duke {

    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    /**
     * Constructor for Duke. Initializes the task list, UI, parser and storage classes.
     */
    public Duke() {
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage();
        try {
            LoadCommand c = new LoadCommand();
            c.execute(tasks, storage);
        } catch (DukeException e) {
            try {
                storage.flushData();
            } catch (DukeException ex) {
                ex.printStackTrace();
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
            String output = c.execute(tasks, storage);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves the currently stored tasks onto a file in the hard-disk.
     */

    public void saveTasks() {
        try {
            SaveCommand c = new SaveCommand();
            c.execute(tasks, storage);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

}

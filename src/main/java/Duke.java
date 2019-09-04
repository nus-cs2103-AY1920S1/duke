import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import java.io.FileNotFoundException;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Initialises a session for Duke and loads tasks, if any, from a previous session.
     */
    public Duke() {
        storage = new Storage("/users/dominique/documents/cs2103t/duke/data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Begins a session of Duke by initialising a Duke object with the filepath to load and store the task list.
     *
     * @param args Unused.
    public static void main(String[] args) {
        new Duke().run();
    }
     */



}

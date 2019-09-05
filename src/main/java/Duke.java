import java.io.IOException;

/**
 * Main class of the program where supporting classes are called
 * and used to make Duke function.
 */

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser = new Parser();

    /**
     * Creates a new Duke object.
     * @param filePath Location of the local file where all tasks are stored or to be stored.
     * @throws IOException If the file path provided does not find a file.
     * @throws DukeException If an unknown or unidentifiable command is stored within the tasks file.
     */
    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        
    }

    public Duke() throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage("/Users/abhimanyadav/Desktop/Duke/duke/src/main/java/dukeTasks.txt");
        taskList = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            return parser.processCommand(input, taskList, ui, storage).toString();
        } catch(Exception e) {
            return e.getMessage();
        }
    }
}

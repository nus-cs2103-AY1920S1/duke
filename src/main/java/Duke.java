import java.io.File;

/**
 * Main class of the program where supporting classes are called
 * and used to make Duke function.
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Parser parser = new Parser();

    /**
     * Creates a new Duke object.
     * @throws DukeException If an unknown or unidentifiable command is stored within the tasks file.
     */
    public Duke() throws DukeException {
        File f = new File("./dukeTasks.txt");
        storage = new Storage(f.getAbsolutePath());
        taskList = new TaskList(storage.load());
    }

    /**
     * Returns the String representation of the response to be given by Duke in response
     * to user commands.
     * @param input The user's command.
     * @return String representation of Duke's response.
     */
    public String getResponse(String input) {
        try {
            return parser.processCommand(input, taskList, storage).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}

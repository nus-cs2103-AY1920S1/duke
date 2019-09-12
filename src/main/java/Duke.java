import java.io.FileNotFoundException;

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
     * @param filePath Location of the local file where all tasks are stored or to be stored.
     * @throws IOException If the file path provided does not find a file.
     * @throws DukeException If an unknown or unidentifiable command is stored within the tasks file.
     */
    public Duke(String filePath) throws DukeException, FileNotFoundException {
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.err.println(e.toString());
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Creates a new Duke object.
     * @throws IOException If the file path provided does not find a file.
     * @throws DukeException If an unknown or unidentifiable command is stored within the tasks file.
     */
    public Duke() throws FileNotFoundException, DukeException {
        storage = new Storage("/Users/abhimanyadav/Desktop/Duke/duke/src/main/java/dukeTasks.txt");
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
        } catch(Exception e) {
            return e.getMessage();
        }
    }

}

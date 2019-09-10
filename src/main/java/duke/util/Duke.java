package duke.util;

/**
 * Duke Class.
 */
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String path = "./saved/taskList_history.txt";

    /**
     * Constructor for duke.
     */
    public Duke() {
        storage = new Storage(path);
        ui = new Ui();
        loadHistory();
    }

    /**
     * Loads history from text file to program.
     */
    private void loadHistory() {
        try {
            if (!storage.historyExists()) {
                storage.createFile();
                tasks = new TaskList(new ArrayList<>());
            } else {
                tasks = new TaskList(storage.retrieveHistory());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    /**
     * Returns the duke's response to a user input.
     * @param input User input
     * @return Message to be displayed
     */
    public String getResponse(String input) {
        try {
            String[] inputParts = input.split(" ", 2);
            Command c = Parser.parse(inputParts);
            return c.execute(storage, ui, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            return invalidCommandMessage(e.getMessage());
        }
        return "";
    }

    /**
     * Returns the message to be displayed when an invalid command is received.
     * @param errorMessage Description of error message
     * @return String of entire error message
     */
    public String invalidCommandMessage(String errorMessage) {
        return errorMessage + "\nType 'commands' to view a list of commands you can use";
    }
}

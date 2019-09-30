import javafx.fxml.FXML;

import command.Command;
import main.Parser;
import main.Storage;
import main.TaskList;
import task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Duke runs as the main and this is where all the work is initialised at.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of Duke. Also instantiates an instance of Storage and TaskList for this instance of Duke.
     */
    public Duke() {
        storage = new Storage();
        ArrayList<Task> list = storage.readFromFile();
        this.taskList = new TaskList(list);
    }

    /**
     * Handles the parsing of inputs and execution of parsed commands.
     *
     * <p></p>As long as a ByeCommand is not parsed, Duke will continue parsing for more commands. If a ByeCommand is
     * parsed, the while loop is terminated and Duke terminates.
     *
     * @throws IOException When the Parser f
     */
    private String run(String command) throws IOException {
        Parser parser = new Parser();
        //Scanner sc = new Scanner(System.in);

        Command c = parser.parse(command);
        return c.execute(taskList, storage);
    }

    /**
     * Sends the user input to Duke backend to be processed.
     *
     * @param input String input from the user.
     * @param duke This instance of Duke.
     *
     * @return Returns a string to be shown to the user.
     */
    @FXML
    public String getResponse(String input, Duke duke) {
        String toReturn = "";
        try {
            toReturn = duke.run(input);
        } catch (IOException io) {
            System.err.println(io);
        }

        return toReturn;
    }
}

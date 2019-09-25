import command.ByeCommand;
import command.Command;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke runs as the main and this is where all the work is initialised at.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of Duke.
     *
     * Also instantiates an instance of Storage and TaskList for this instance of Duke.
     */
    private Duke(){
        storage = new Storage();
        ArrayList<Task> list = storage.readFromFile();
        this.taskList = new TaskList(list);
    }

    /**
     * Handles the parsing of inputs and execution of parsed commands.
     *
     * As long as a ByeCommand is not parsed, Duke will continue parsing for more commands. If a ByeCommand is parsed,
     * the while loop is terminated and Duke terminates.
     *
     * @throws IOException When the Parser f
     */
    private void run() throws IOException {
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();

            Command c = parser.parse(nextLine);
            c.execute(taskList, storage);

            if (c instanceof ByeCommand) {
                break;
            }
        }
    }


    /**
     * Runs the main method.
     *
     * There are three main steps that occur in the main method.
     * Firstly, an instance of Duke is created.
     * Secondly, the UI prints the start message to the console.
     * Next, the duke.run() method is initiated which will handle the parsing and subsequent processes.
     *
     * @param args Input from the User.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        UI.start();

        try {
            duke.run();
        } catch (IOException ioE) {
            System.err.println(ioE);
        }
    }
}

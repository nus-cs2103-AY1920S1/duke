import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of Duke.
     *
     * Also instantiates an instance of Storage and TaskList for this instance of Duke.
     */
    public Duke(){
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
    private String run(String command) throws IOException {
        Parser parser = new Parser();
        //Scanner sc = new Scanner(System.in);

        Command c = parser.parse(command);
        return c.execute(taskList, storage);


        /*while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();

            Command c = parser.parse(nextLine);
            c.execute(taskList, storage);

            if (c instanceof ByeCommand) {
                break;
            }
        }*/
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
    /*public static void main(String[] args) {
        Duke duke = new Duke();
        UI.start();

        try {
            duke.run();
        } catch (IOException ioE) {
            System.err.println(ioE);
        }
    }*/

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
        try{
            toReturn = duke.run(input);
        } catch (IOException io) {
            System.err.println(io);
        }

        return toReturn;
    }
}

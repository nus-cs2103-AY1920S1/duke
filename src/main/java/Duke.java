import java.util.Arrays;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.DukeException;

/**
 * Duke class. Creates a new Ui, duke.command.TaskList and duke.command.Storage object,
 * then runs the main method of the program.
 */
public class Duke {

    /** Stores the Ui object used to display messages to the user. */
    private Ui ui;
    /** Stores the duke.command.Storage object used to read/write from file. */
    private Storage storage;
    /** Stores the duke.command.TaskList object used to add/delete tasks. */
    private TaskList taskList;

    // Solution below adapted from https://github.com/nexolute/duke/blob/master/src/main/java/duke/Duke.java
    /**
     * Initializes the Duke object by setting the Ui, duke.command.Storage and duke.command.TaskList objects.
     * @param mainWindow the MainWindow of the application.
     */
    public void initialize(MainWindow mainWindow) {
        ui = new Ui(mainWindow);
        storage = new Storage("data/duke.txt");
        taskList = new TaskList(storage);
        ui.printIntro();
    }

    /**
     * Requests a response from Duke.
     * @param input The input string from the user.
     */
    void getResponse(String input) {
        int index;
        try {
            String[] userInput = Parser.parseUserInput(input);
            String command = userInput[0];
            String[] params = Arrays.copyOfRange(userInput, 1, userInput.length);

            switch (command) {
            case "bye":
                ui.printGoodbye();
                System.exit(0);
                break;

            case "list":
                ui.printToUser(taskList.list());
                break;

            case "done":
                index = Parser.checkForValidIndex(params);
                ui.printToUser(taskList.markAsDone(index));
                break;

            case "delete":
                index = Parser.checkForValidIndex(params);
                ui.printToUser(taskList.delete(index));
                break;

            case "todo":
                ui.printToUser(taskList.createToDo(params));
                break;

            case "deadline":
                ui.printToUser(taskList.createDeadline(params));
                break;

            case "event":
                ui.printToUser(taskList.createEvent(params));
                break;

            case "note":
                ui.printToUser(taskList.createNote(params));
                break;

            case "find":
                ui.printToUser(taskList.findEvent(params));
                break;

            case "help":
                ui.printToUser(taskList.help());
                break;

            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
            }
        } catch (DukeException | InterruptedException e) {
            ui.printErrToUser(e);
        }
    }


}

import java.util.Arrays;
import java.util.Scanner;
import duke.command.DukeException;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

/**
 * Duke class. Creates a new Ui, TaskList and Storage object, then runs the main method of the program.
 */
public class Duke {

    /** Stores the Ui object used to display messages to the user. */
    private Ui ui;
    /** Stores the TaskList object used to add/delete tasks. */
    private TaskList taskList;
    /** Stores the Storage object used to read/write from file. */
    private Storage storage;

    /**
     * This method runs the Duke chat bot. In particular, it prints the intro, and determines which command should
     * be executed based on the user's input.
     */
    private void run() {
        ui.printIntro();

        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        int index;

        while (run) {
            try {
                String[] userInput = Parser.parseUserInput(scanner.nextLine());
                String command = userInput[0];
                String[] params = Arrays.copyOfRange(userInput, 1, userInput.length);

                switch (command) {
                case "bye":
                    ui.printGoodbye();
                    run = false;
                    break;

                case "list":
                    ui.printToUser(taskList.list());
                    break;

                case "done":
                    try {
                        index = Integer.valueOf(params[0]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You need to specify a task ID to mark as done.");
                    }
                    ui.printToUser(taskList.markAsDone(index));
                    break;

                case "delete":
                    try {
                        index = Integer.valueOf(params[0]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You need to specify a task ID to delete.");
                    }
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

                default:
                    throw new DukeException("I'm sorry, but I don't know what that means.");

                }
            } catch (DukeException e) {
                ui.printErrToUser(e);
            }

        }
    }

    /**
     * This method initialises the Duke class variables by creating a new Storage, TaskList and Ui object.
     * @param filepath The path to save Duke data to.
     */
    private Duke(String filepath) {
        storage = new Storage(filepath);
        taskList = new TaskList(storage);
        ui = new Ui();
    }

    /**
     * Main class. Sets the filepath for the saving of Duke tasks.
     * @param args Not in use.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}

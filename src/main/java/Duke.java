import java.util.Arrays;
import java.util.Scanner;
import duke.command.DukeException;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke class. Creates a new Ui, TaskList and Storage object, then runs the main method of the program.
 */
public class Duke extends Application{

    /** Stores the Ui object used to display messages to the user. */
    private Ui ui = new Ui();
    /** Stores the Storage object used to read/write from file. */
    private Storage storage = new Storage("data/duke.txt");;
    /** Stores the TaskList object used to add/delete tasks. */
    private TaskList taskList = new TaskList(storage);;

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

                case "find":
                    ui.printToUser(taskList.findEvent(params));
                    break;

                default:
                    throw new DukeException("I'm sorry, but I don't know what that means.");

                }
            } catch (DukeException e) {
                ui.printErrToUser(e);
            }

        }

        System.exit(0);
    }

    @Override
    public void start (Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main class. Sets the filepath for the saving of Duke tasks.
     * @param args Not in use.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}

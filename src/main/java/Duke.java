import commands.Command;
import exceptions.DukeException;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;


public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private static final String filePath = "data/duke.txt";


    /**
     * The constructor needed to kickstart JavaFx.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            System.out.println(ui.showErrors(e.getMessage()));
        }
    }

    /**
     * Obtains user input from command line, parses it and executes the commands.
     * Shows errors to the user in case of invalid input.
     *
     * @return String feedback for gui to display
     */
    public String getResponse(String input) {
        Command c;
        try {
            c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showErrors(e.getMessage());
        }
    }

    /**
     * Required main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke(filePath);
        duke.run();
    }

    /**
     * Create a Duke object.
     * Initialise the storage, task list and ui objects.
     *
     * @param filePath the local path to the storage file
     */
    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showErrors(e.getMessage());
        }
    }

    /**
     * Obtains user input from command line, parses it and executes the commands.
     * Shows errors to the user in case of invalid input.
     *
     */
    private void run() {
        System.out.println(ui.welcomeMessage());

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showErrors(e.getMessage()));
            }
        }
    }
}

import commands.Command;
import exceptions.DukeException;
import utils.Parser;
import utils.Storage;
import utils.TaskList;
import utils.Ui;

// javaFx

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Empty constructor to bridge Main and fxml.
     *
     */
    public Duke(){}

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Required main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke("/home/abhinav/Desktop/2103T/duke/data/dukeTest.txt");
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
     * Obtains user input, parses it and executes the commands.
     * Shows errors to the user in case of invalid input.
     *
     */
    private void run() {
        ui.welcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrors(e.getMessage());
            }
        }
    }
}

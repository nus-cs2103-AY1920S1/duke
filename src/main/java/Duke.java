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

    public static void main(String[] args) {
        Duke duke = new Duke("/home/abhinav/Desktop/2103T/duke/data/duke.txt");
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
        tasks = new TaskList(storage.readFromFile());
        ui = new Ui();
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

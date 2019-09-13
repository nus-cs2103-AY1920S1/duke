package duke;

import duke.command.Command;
import duke.command.factory.CommandFactory;
import duke.task.TasksController;
import error.ui.UiException;
import storage.Storage;
import ui.UiActivity;
import ui.UiController;
import ui.input.InputHandler;
import ui.output.OutputHandler;

import java.util.Optional;

/**
 * Main driver class for Duke task manager program.
 */

public class Duke implements UiActivity {
    private UiController ui;
    private CommandFactory commandFactory;

    /**
     * Program entry point.
     * @param args program arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();

        boolean guiEnabled = true;

        if (args.length > 0) {
            if (args[0].equals("console")) {
                guiEnabled = false;
            } else {
                System.out.println("Invalid arguments.");
                System.exit(1);
            }
        }

        try {
            Options options = OptionsFactory.select(guiEnabled, true);
            duke.configure(options);
        } catch (Exception e) {
            System.out.println("FATAL: Unable to configure application.");
        }

        duke.run();
    }

    /**
     * Starts the program.
     */
    public void run() {
        System.out.println("Program starting...");

        ui.initializeUi();
    }

    /**
     * Configures the main driver with a set of customizable options.
     * @param options the program's runtime options
     */
    public void configure(Options options) {
        // Initialize UI component
        InputHandler input = options.getInput();
        OutputHandler output = options.getOutput();
        ui = new UiController(input, output, this);

        // Initialize tasks and storage
        Storage storage = options.getStorage();
        assert storage != null;
        TasksController tasks = TasksController.fromStorage(storage, ui);

        // Initialize command factory
        commandFactory = new CommandFactory(tasks, ui);
    }

    /**
     * Parse input and execute corresponding command.
     * @param input user input
     */
    @Override
    public void onInputReceived(String input) {
        try {
            // Get command and execute
            Optional<Command> command = commandFactory.parse(input);
            if (command.isPresent()) {
                command.get().execute();
            }

        } catch (UiException e) {
            System.out.println("FATAL: Ui stopped working.");
            System.exit(1);
        }
    }

    /**
     * Closes the program.
     */
    @Override
    public void stopActivity() {
        System.exit(0);
    }
}

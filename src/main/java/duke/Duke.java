package duke;

import duke.command.Command;
import duke.command.factory.CommandFactory;
import duke.task.TasksController;
import error.ui.UiException;
import storage.Storage;
import ui.UiController;
import ui.UiDriver;

import java.util.Optional;

/**
 * Main driver class for Duke task manager program.
 */

public class Duke implements UiDriver {
    private UiController ui;
    private CommandFactory commandFactory;

    /**
     * Program entry point.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();

        boolean isGuiEnabled;

        if (args.length == 0) {
            isGuiEnabled = true;
        } else if (args.length == 1 && args[0].equals("-c")) {
            isGuiEnabled = false;
        } else {
            System.out.println("Invalid program arguments.");
            System.exit(1);
            return;
        }

        try {
            DukeOptions options = OptionsFactory.select(isGuiEnabled, true, duke);
            duke.configure(options);
        } catch (Exception e) {
            System.out.println("FATAL: Unable to configure application.");
            System.exit(1);
            return;
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
     *
     * @param options the program's runtime options
     */
    public void configure(DukeOptions options) {
        // Initialize UI component
        this.ui = options.getUiController();

        // Initialize tasks and storage
        Storage storage = options.getStorage();
        TasksController tasks = TasksController.fromStorage(storage, ui);

        // Initialize command factory
        commandFactory = new CommandFactory(tasks, ui);
    }


    @Override
    public void receiveUserInput(String input) {
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

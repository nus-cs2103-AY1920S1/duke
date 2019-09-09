package duke;

import duke.command.Command;
import duke.command.CommandFactory;
import duke.task.TasksView;
import duke.task.TasksController;
import storage.Storage;
import ui.UiActivity;
import ui.UiController;
import ui.input.InputHandler;
import ui.output.OutputHandler;

import java.util.Optional;

/***
 * <p>
 * Main driver class for duke.Duke program.
 * </p>
 */

public class Duke implements UiActivity {
    private UiController ui;
    private CommandFactory factory;


    public static void main(String[] args) {
        Duke duke = new Duke();

        try {
            Options options = OptionsFactory.select(true, true);
            duke.configure(options);
        } catch (Exception e) {
            System.out.println("FATAL: Unable to configure application.");
        }

        duke.run();
    }

    public void run() {
        System.out.println("Program starting...");

        ui.initializeUi();
        exit();
    }

    public void configure(Options options) {
        // Initialize UI component
        InputHandler input = options.getInput();
        OutputHandler output = options.getOutput();
        ui = new UiController(input, output, this);

        // Initialize tasks and storage
        Storage storage = options.getStorage();
        TasksView view = new TasksView(ui);
        TasksController tasks = TasksController.fromStorage(storage, view);

        // Initialize command factory
        factory = new CommandFactory(tasks);
    }

    private void exit() {
        System.exit(1);
    }

    @Override
    public void onInputReceived(String input) {
        Optional<Command> command = factory.parse(input);
        command.ifPresent(Command::execute);
    }
}

package duke;

import duke.command.Command;
import duke.command.CommandFactory;
import duke.command.GreetCommand;
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
    private boolean isConfigured;

    private UiController ui;
    private CommandFactory factory;


    public static void main(String[] args) {
        Duke duke = new Duke();
        Options options = OptionsFactory.select(false, true);

        duke.configure(options);
        duke.run();
    }

    public void run() {
        if (!isConfigured) {
            System.out.print("Fatal error: Unable to configure program!");
        } else {
            startApplication();
        }
    }

    private void startApplication() {
        System.out.println("Program starting...");

        printGreeting();
        ui.start();
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

        // Set flag
        isConfigured = true;
    }

    private void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = logo + "\n" + "\n"
                        + "Hello! I'm duke.Duke\n"
                        + "What can I do for you?";

        ui.displayOutput(greeting);
    }

    private void exit() {

    }

    @Override
    public void onInputReceived(String input) {
        Optional<Command> command = factory.parse(input);
        command.ifPresent(Command::execute);
    }


//    private static final String CUSTOM_CONFIG_FILE_PATH = System.getProperty("user.home") + "/bin/duke.config";
//
//    private TasksController tasksController;
//    private CommandFactory commandFactory;
//    private boolean isConsoleModeEnabled = false;
//
//    private static final OutputBuilder GENERIC_ERROR_MESSAGE =
//            new OutputBuilder("☹ OOPS!!! Something unexpected happened!!!");
//
//    public void run() {
//        if (isConsoleModeEnabled) {
//
//            new GreetCommand().execute();
//
//            while (true) {
//                String command = DukeInput.readUserInput();
//                Optional<Command> next = commandFactory.parse(command);
//
//                try {
//                    next.ifPresent(Command::execute);
//                } catch (Exception e) {
//                    DukeOutput.printMessage(GENERIC_ERROR_MESSAGE);
//                }
//
//                if (next.isPresent() && next.get() instanceof ByeCommand) {
//                    break;
//                }
//            }
//        } else {
//            Application.launch(FxMain.class);
//        }
//    }
//
//    public void initialize() {
//        OutputBuilder initializeMessage = new OutputBuilder("Initializing duke.Duke...");
//        DukeOutput.printMessage(initializeMessage);
//
//
//        try {
//            DukeStorage.initializeDukeStorage(CUSTOM_CONFIG_FILE_PATH);
//            List<Task> taskData = DukeStorage.getInstance().getTaskData();
//            tasksController = new TasksController(taskData);
//            commandFactory = new CommandFactory(tasksController);
//
//
//        } catch (ConfigurationException e) {
//            OutputBuilder configErrorMessage = new OutputBuilder(e.getMessage());
//            DukeOutput.printMessage(configErrorMessage);
//            return;
//        }
//    }
//
//    public TasksController getTasksController() {
//        return tasksController;
//    }


}

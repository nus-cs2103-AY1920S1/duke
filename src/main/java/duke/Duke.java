package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandFactory;
import duke.command.GreetCommand;
import duke.task.TasksView;
import error.ConfigurationException;
import fx.FxMain;
import javafx.application.Application;
import duke.task.tasks.Task;
import duke.task.TasksController;
import storage.Storage;
import ui.UiActivity;
import ui.UiController;
import ui.input.InputHandler;
import ui.output.OutputHandler;
import util.DukeInput;
import util.OutputBuilder;
import util.DukeOutput;
import util.DukeStorage;

import java.util.List;
import java.util.Optional;

/***
 * <p>
 * Main driver class for duke.Duke program.
 * </p>
 */

public class Duke implements UiActivity {
    private boolean isConfigured;
    private boolean isExit;

    private UiController ui;
    private TasksController tasks;


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.configure(OptionsFactory.select(false, false));
        duke.run();
    }

    @Override
    public void onInputReceived(String input) {
        ui.displayOutput(input);
    }

    public void configure(Options options) {
        InputHandler input = options.getInput();
        OutputHandler output = options.getOutput();

        ui = new UiController(input, output, this);

        Storage storage = options.getStorage();
        TasksView view = new TasksView(ui);

        tasks = TasksController.fromStorage(storage, view);

        isConfigured = true;
    }

    public void run() {
        if (!isConfigured) {
            System.out.print("Fatal error: Unable to configure program!");
        } else {
            startApplication();
        }
    }

    public void startApplication() {
        System.out.println("Program starting...");
        ui.start();
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

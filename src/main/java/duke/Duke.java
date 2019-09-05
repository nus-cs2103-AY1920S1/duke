package duke;

import command.ByeCommand;
import command.Command;
import command.CommandFactory;
import command.GreetCommand;
import error.ConfigurationException;
import fx.FxMain;
import javafx.application.Application;
import task.tasks.Task;
import task.TaskListController;
import util.DukeInput;
import util.DukeMessage;
import util.DukeOutput;
import util.DukeStorage;

import java.util.List;
import java.util.Optional;

/***
 * <p>
 * Main driver class for duke.Duke program.
 * </p>
 */

public class Duke {
    private static final String CUSTOM_CONFIG_FILE_PATH = System.getProperty("user.home") + "/bin/duke.config";

    private TaskListController taskListController;
    private CommandFactory commandFactory;
    private boolean isConsoleModeEnabled = false;

    private static final DukeMessage GENERIC_ERROR_MESSAGE =
            new DukeMessage("☹ OOPS!!! Something unexpected happened!!!");

    public void run() {
        if (isConsoleModeEnabled) {

            new GreetCommand().execute();

            while (true) {
                String command = DukeInput.readUserInput();
                Optional<Command> next = commandFactory.parse(command);

                try {
                    next.ifPresent(Command::execute);
                } catch (Exception e) {
                    DukeOutput.printMessage(GENERIC_ERROR_MESSAGE);
                }

                if (next.isPresent() && next.get() instanceof ByeCommand) {
                    break;
                }
            }
        } else {
            Application.launch(FxMain.class);
        }
    }

    public void initialize() {
        DukeMessage initializeMessage = new DukeMessage("Initializing duke.Duke...");
        DukeOutput.printMessage(initializeMessage);


        try {
            DukeStorage.initializeDukeStorage(CUSTOM_CONFIG_FILE_PATH);
            List<Task> taskData = DukeStorage.getInstance().getTaskData();
            taskListController = new TaskListController(taskData);
            commandFactory = new CommandFactory(taskListController);


        } catch (ConfigurationException e) {
            DukeMessage configErrorMessage = new DukeMessage(e.getMessage());
            DukeOutput.printMessage(configErrorMessage);
            return;
        }
    }

    public TaskListController getTaskListController() {
        return taskListController;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialize();
        duke.run();
    }
}

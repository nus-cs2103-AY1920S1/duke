import command.Command;
import command.ListenCommand;
import command.GreetCommand;
import error.ConfigurationException;
import task.tasks.Task;
import task.TaskListController;
import util.DukeMessage;
import util.DukeOutput;
import util.DukeStorage;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * <p>
 * Main driver class for Duke program.
 * </p>
 */
public class Duke {
    private static final String CUSTOM_CONFIG_FILE_PATH = System.getProperty("user.home") + "/bin/duke.config";

    private Queue<Command> commands;
    private TaskListController taskListController;
    private static final DukeMessage GENERIC_ERROR_MESSAGE =
            new DukeMessage("☹ OOPS!!! Something unexpected happened!!!");

    private void run() {
        initialize();

        while (!commands.isEmpty()) {
            Command next = commands.poll();

            try {
                next.execute().ifPresent(command -> commands.offer(command));
            } catch (Exception e) {
                DukeOutput.printMessage(GENERIC_ERROR_MESSAGE);
                commands.offer(new ListenCommand(taskListController));
            }
        }
    }

    private void initialize() {
        DukeMessage initializeMessage = new DukeMessage("Initializing Duke...");
        DukeOutput.printMessage(initializeMessage);

        commands = new LinkedList<>();

        try {
            DukeStorage.initializeDukeStorage(CUSTOM_CONFIG_FILE_PATH);
            List<Task> taskData = DukeStorage.getInstance().getTaskData();
            taskListController = new TaskListController(taskData);
        } catch (ConfigurationException e) {
            DukeMessage configErrorMessage = new DukeMessage(e.getMessage());
            DukeOutput.printMessage(configErrorMessage);
            return;
        }

        commands.offer(new GreetCommand());
        commands.offer(new ListenCommand(taskListController));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

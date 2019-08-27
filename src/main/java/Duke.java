import command.Command;
import command.ListenCommand;
import command.GreetCommand;
import error.ConfigurationException;
import error.handler.MainErrorHandler;
import org.xml.sax.ErrorHandler;
import task.TaskListController;
import util.DukeStorage;

import java.util.LinkedList;
import java.util.Queue;

public class Duke {
    private final String CUSTOM_CONFIG_FILE_PATH = "";

    private Queue<Command> commands;
    private TaskListController taskListController = new TaskListController();
    private MainErrorHandler errorHandler = new MainErrorHandler();
    private DukeStorage dukeStorage;

    private Duke() {
        commands = new LinkedList<>();
        commands.offer(new GreetCommand());
        commands.offer(new ListenCommand(taskListController));
        taskListController = new TaskListController();
        errorHandler = new MainErrorHandler();
    }

    private void run() {
        try {
            initializeStorage();
        } catch (ConfigurationException e) {
            errorHandler.handle(e);
            return;
        }

        while (!commands.isEmpty()) {
            Command next = commands.poll();

            try {
                next.execute().ifPresent(command -> commands.offer(command));
            } catch(Exception e) {
                errorHandler.handle(e);
                commands.offer(new ListenCommand(taskListController));
            }
        }
    }

    private void initializeStorage() throws ConfigurationException {
        dukeStorage = new DukeStorage(CUSTOM_CONFIG_FILE_PATH);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

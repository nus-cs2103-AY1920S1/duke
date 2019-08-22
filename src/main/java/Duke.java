import command.Command;
import command.ListenCommand;
import command.GreetCommand;
import error.handler.MainErrorHandler;
import task.TaskListController;

import java.util.LinkedList;
import java.util.Queue;

public class Duke {
    private Queue<Command> commands;
    private TaskListController taskListController = new TaskListController();
    private MainErrorHandler errorHandler = new MainErrorHandler();

    private Duke() {
        commands = new LinkedList<>();
        commands.offer(new GreetCommand());
        commands.offer(new ListenCommand(taskListController));
    }

    private void run() {
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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

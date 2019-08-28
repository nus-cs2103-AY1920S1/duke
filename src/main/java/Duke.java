import command.Command;
import command.ListenCommand;
import command.GreetCommand;
import task.TaskListController;
import util.DukeMessage;
import util.DukeOutput;

import java.util.LinkedList;
import java.util.Queue;

public class Duke {
    private Queue<Command> commands;
    private TaskListController taskListController = new TaskListController();
    private DukeMessage GENERIC_ERROR_MESSAGE = new DukeMessage("☹ OOPS!!! Something unexpected happened!!!");

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
                DukeOutput.printMessage(GENERIC_ERROR_MESSAGE);
                commands.offer(new ListenCommand(taskListController));
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

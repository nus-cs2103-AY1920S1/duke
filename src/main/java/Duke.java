import command.Command;
import command.GreetCommand;

import java.util.LinkedList;
import java.util.Queue;

public class Duke {
    private Queue<Command> commands;

    private Duke() {
        commands = new LinkedList<>();
        commands.offer(new GreetCommand());
    }

    private void run() {
        while (!commands.isEmpty()) {
            Command next = commands.poll();
            next.execute().ifPresent(command -> commands.offer(command));
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

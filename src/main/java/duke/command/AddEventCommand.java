package duke.command;

import duke.task.Event;
import duke.task.Task;
import java.util.Scanner;

public class AddEventCommand extends AddTaskCommand {
    private Scanner s = new Scanner(restOfCommand);

    public AddEventCommand(String restOfCommand) {
        super(restOfCommand);
        s.useDelimiter("/at");
    }

    @Override
    public String getDescription() {
        return this.s.next().strip();
    }

    @Override
    public String getDeadline() {
        return this.s.next().strip();
    }

    @Override
    public Task createTask() {
        return new Event(getDescription(), getDeadline());
    }
}

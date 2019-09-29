package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import java.util.Scanner;

public class AddDeadlineCommand extends AddTaskCommand {
    private Scanner s = new Scanner(restOfCommand);

    public AddDeadlineCommand(String restOfCommand) {
        super(restOfCommand);
        s.useDelimiter("/by");
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
        return new Deadline(getDescription(), getDeadline());
    }
}

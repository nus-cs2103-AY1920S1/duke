package seedu.duke.commands;

import seedu.duke.trackables.Deadline;
import seedu.duke.trackables.Task;

import java.util.List;

public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(List<Task> tasks) {
        tasks.add(deadline);
        echo(
            new String[] {
                "Got it. I've added this task:",
                "  " + deadline.toString(),
                "Now you have " + tasks.size() + " tasks in the list."
            });
    }
}


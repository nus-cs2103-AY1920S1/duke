package duke.command;

import duke.task.Deadline;
import duke.DukeException;
import duke.TaskList;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    public static final String name = "deadline";

    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        int byIndex = fullCommand.indexOf(" /by ");
        if (byIndex < 0) {
            throw new DukeException("Command deadline requires an argument /by, followed by deadline date");
        }
        String deadlineDescription =  fullCommand.substring(9, byIndex);
        String by = fullCommand.substring(byIndex + 5);
        Deadline deadline;
        try {
            deadline = new Deadline(deadlineDescription, by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must formatted in the form dd/MM/yyyy HHmm");
        }
        tasks.addTask(deadline);
        String output = "Got it. I've added this task: \n"
                + deadline.toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.getNumberOfTasks());
        return output;
    }
}

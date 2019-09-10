package duke.command;

import duke.task.Deadline;
import duke.Parser;
import duke.TaskList;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String[] parts) {
        super(parts);
    }

    @Override
    public String execute(TaskList tasks) {
        assert parts[0].equals("deadline") : "parts[0] should be deadline";
        String secondPart = parts[1];
        String name = Parser.splitIntoNameAndTime(secondPart, " /by ")[0];
        String dateTime = Parser.splitIntoNameAndTime(secondPart, " /by ")[1];
        LocalDateTime localDateTime = Parser.changeToDateTimeFormat(dateTime);

        // Add new task to list
        Deadline newDeadline = new Deadline(name, false, "deadline", dateTime, localDateTime);
        tasks.add(newDeadline);

        String response = "Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1) + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return response;
    }
}

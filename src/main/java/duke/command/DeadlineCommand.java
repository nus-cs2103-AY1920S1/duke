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
    public void execute(TaskList tasks) {
        // deadline
        // Remaining words
        String remainingWords = parts[1];
        String name = Parser.splitIntoNameAndTime(remainingWords, " /by ")[0];
        String dateTime = Parser.splitIntoNameAndTime(remainingWords, " /by ")[1];

        LocalDateTime localDateTime = Parser.changeToDateTimeFormat(dateTime);

        // Add new task to list
        Deadline newDeadline = new Deadline(name, false, dateTime, localDateTime);
        tasks.add(newDeadline);

        // Print output of ADD
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}

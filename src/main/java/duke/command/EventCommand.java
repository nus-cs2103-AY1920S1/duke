package duke.command;

import duke.task.Event;
import duke.Parser;
import duke.TaskList;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public EventCommand(String[] parts) {
        super(parts);
    }

    @Override
    public String execute(TaskList tasks) {
        // event
        // Remaining words
        String remainingWords = parts[1];
        String name = Parser.splitIntoNameAndTime(remainingWords, " /at ")[0];
        String dateTime = Parser.splitIntoNameAndTime(remainingWords, " /at ")[1];

        LocalDateTime localDateTime = Parser.changeToDateTimeFormat(dateTime);

        // Add new task to list
        Event newEvent = new Event(name, false, dateTime, localDateTime);
        tasks.add(newEvent);

        String response = "Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1) + "\n" + "Now you have "
                + tasks.size() + " tasks in the list.\n";
        return response;
    }
}

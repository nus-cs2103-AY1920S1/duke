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
        String secondPart = parts[1];
        String name = Parser.splitIntoNameAndTime(secondPart, " /at ")[0];
        String dateTime = Parser.splitIntoNameAndTime(secondPart, " /at ")[1];
        LocalDateTime localDateTime = Parser.changeToDateTimeFormat(dateTime);

        // Add new task to list
        Event newEvent = new Event(name, false,"event", dateTime, localDateTime);
        tasks.add(newEvent);

        String response = "Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1) + "\n" + "Now you have "
                + tasks.size() + " tasks in the list.\n";
        return response;
    }
}

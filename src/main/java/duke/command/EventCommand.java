package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.TaskList;

import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    public static final String name = "event";

    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        int atIndex = fullCommand.indexOf(" /at ");
        if (atIndex < 0) {
            throw new DukeException("Command event requires an argument /at, followed by event date");
        }
        String eventDescription =  fullCommand.substring(6, atIndex);
        String at = fullCommand.substring(atIndex + 5);
        Event event;
        try {
            event = new Event(eventDescription, at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must formatted in the form dd/MM/yyyy HHmm");
        }
        tasks.addTask(event);
        String output = "Got it. I've added this task: \n"
                + event.toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.getNumberOfTasks());
        return output;
    }
}

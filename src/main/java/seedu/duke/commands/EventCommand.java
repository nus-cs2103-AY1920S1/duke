package seedu.duke.commands;

import seedu.duke.trackables.Event;
import seedu.duke.trackables.Task;

import java.util.List;

public class EventCommand extends Command {

    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(List<Task> tasks) {
        tasks.add(event);
        echo(new String[] {
            "Got it. I've added this task:",
            "  " + event.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }
}

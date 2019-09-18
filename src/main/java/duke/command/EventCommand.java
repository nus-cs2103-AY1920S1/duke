package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Date;

/**
 * Command to create an Event.
 */
public class EventCommand extends CommandAddTask {
    private String dsc;
    private Date eventAt;

    /**
     * Constructs an EventCommand that creates an Event in a TaskList when executed.
     *
     * @param dsc The description of the Event that will be created by EventCommand.
     * @param eventAt The date of the Event that will be created by EventCommand.
     */
    public EventCommand(String dsc, Date eventAt) {
        this.dsc = dsc;
        this.eventAt = eventAt;
    }

    /**
     * Creates an Event in a TaskList.
     *
     * @param tasks The TaskList to create the Event in.
     * @return A string to inform the user that the Event has been created.
     */
    @Override
    public String execute(TaskList tasks) {
        Task addedTask = new Event(this.dsc, this.eventAt);
        tasks.addTask(addedTask);
        return createSuccessMsg(addedTask);
    }
}

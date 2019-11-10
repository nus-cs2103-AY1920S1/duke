package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * EventCommand class which creates a new Event task.
 */
public class EventCommand extends Command {

    /** Description portion of the input. */
    private String desc;
    /** Date when the event starts. **/
    private LocalDate startDate;
    /** Time when the event start. **/
    private LocalTime startTime;
    /** Time when the event ends. **/
    private LocalTime endTime;

    /**
     * Initialises an EventCommand.
     * @param desc Description of the Event.
     * @param startDate Date when the Event starts.
     * @param startTime Time when the Event starts.
     * @param endTime Time when the Event ends.
     */
    public EventCommand(String desc, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        this.desc = desc;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task event = new Event(desc, startDate, startTime, endTime);
        taskList.add(event);
        return new CommandResult(Message.showAddSuccess(event, taskList));
    }
}

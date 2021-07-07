package duke.command;

import duke.todo.Task;
import duke.todo.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand implements Command {
    private String task;
    private LocalDateTime eventDate;

    /**
     * Constructs an event command based on the input string,
     * and store it as description description and event date.
     *
     * @param description Description for the event.
     * @param eventDate Date in LocalDateTime.
     */
    public EventCommand(String description, LocalDateTime eventDate) {
        this.task = description;
        this.eventDate = eventDate;
    }

    /**
     * Returns task type.
     *
     * @return Event.
     */
    public String getTaskType() {
        return "event";
    }

    public int getIndex() {
        return 0;
    }

    /**
     * Returns the event description of this event.
     *
     * @return Description of this event.
     */
    public String getDescription() {
        return task;
    }

    /**
     * Returns the event date of this event.
     *
     * @return Event date.
     */
    public LocalDateTime getDate() {
        return eventDate;
    }

    public String getKeyword() {
        return "error";
    }

    public void execute(TaskList tasks, Ui ui) {
        Task addedEvent = tasks.addTask(getTaskType(), getDescription(), getDate());
        ui.reportAdd(addedEvent, tasks.getNumOfTasks());
    }
}

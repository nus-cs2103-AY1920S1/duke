package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventCommand extends Command{

    private String description;
    private LocalDateTime startDateTime;
    private LocalTime endTime;

    public EventCommand(String description, LocalDateTime startDateTime, LocalTime endTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endTime = endTime;
    }

    /**
     * Adds a <code>Event</code> task to the task list and shows a successful-adding message.
     *
     * @param taskList {@inheritDoc}
     * @param ui       {@inheritDoc}
     * @param storage  {@inheritDoc}
     * @return         a string telling a <code>Event</code> task is added
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = new Event(description, startDateTime, endTime);
        if (taskList.contains(newTask)) {
            return ui.showTaskDuplicated();
        }
        taskList.add(newTask);
        try {
            storage.recordTasks(taskList);
        } catch (IOException e) {
            return ui.showSavingError();
        }
        return ui.showTaskAdded(taskList.getTotalTask(), newTask);
    }

    /**
     * Compares two <code>EventCommand</code> objects according to their description, start date time,
     * and end time. The comparison is mainly used for JUnit tests.
     *
     * @param obj the object to be compared
     * @return    <code>true</code> if two objects are both <code>DeadlineCommand<></code>, and have
     *            the same description, start date and time, and end time;
     *            <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        //@@author ZhangHuafan-reused
        //Reused from https://www.javaworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        //@@author
        EventCommand another = (EventCommand) obj;
        boolean isSameDescription = this.description.equals(another.description);
        boolean isSameStartDateTime = this.startDateTime.equals(another.startDateTime);
        boolean isSameEndTime = this.endTime.equals(another.endTime);
        return  isSameDescription && isSameStartDateTime && isSameEndTime;
    }
}

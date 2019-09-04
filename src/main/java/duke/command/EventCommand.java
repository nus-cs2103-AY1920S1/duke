package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

import java.util.Date;

public class EventCommand extends CommandAddTask {
    private String dsc;
    private Date eventAt;
    public EventCommand(String dsc, Date eventAt) {
        this.dsc = dsc;
        this.eventAt = eventAt;
    }

    @Override
    public String execute(TaskList tasks) {
        Task addedTask = new Event(this.dsc, this.eventAt);
        tasks.addTask(addedTask);
        return createSuccessMsg(addedTask);
    }
}

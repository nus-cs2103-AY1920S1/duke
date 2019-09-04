package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Date;

public class DeadlineCommand extends CommandAddTask {
    private String dsc;
    private Date deadlineBy;
    public DeadlineCommand(String dsc, Date deadlineBy) {
        this.dsc = dsc;
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String execute(TaskList tasks) {
        Task addedTask = new Deadline(this.dsc, this.deadlineBy);
        tasks.addTask(addedTask);
        return createSuccessMsg(addedTask);
    }
}

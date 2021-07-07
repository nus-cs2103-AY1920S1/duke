package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Date;

/**
 * Command to create a Deadline.
 */
public class DeadlineCommand extends CommandAddTask {
    private String dsc;
    private Date deadlineBy;

    /**
     * Constructs a DeadlineCommand that creates an Deadline in a TaskList when executed.
     *
     * @param dsc The description of the Deadline that will be created by DeadlineCommand.
     * @param deadlineBy The date of the Deadline that will be created by DeadlineCommand.
     */
    public DeadlineCommand(String dsc, Date deadlineBy) {
        this.dsc = dsc;
        this.deadlineBy = deadlineBy;
    }

    /**
     * Creates a Deadline in a TaskList.
     *
     * @param tasks The TaskList to create the Deadline in.
     * @return A string to inform the user that the Deadline has been created.
     */
    @Override
    public String execute(TaskList tasks) {
        Task addedTask = new Deadline(this.dsc, this.deadlineBy);
        tasks.addTask(addedTask);
        return createSuccessMsg(addedTask);
    }
}

package q.command;

import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;
import q.task.Deadline;
import q.task.Task;

import java.util.Date;

/**
 * Handles adding of a deadline.
 */
public class DeadlineCommand extends AddCommand {
    private String description;
    private Date by;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param description The description of the deadline.
     * @param by          The due date of the deadline.
     */
    public DeadlineCommand(String description, Date by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a Deadline of description and by to tasks.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String representing the deadline.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        storage.save(tasks);
        return ui.showAddTask(deadline, tasks.getSize());
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;

import java.util.Date;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadlineBy;
    private Date deadlineDate;

    public AddDeadlineCommand(String description, String deadlineBy) {
        this.description = description;
        this.deadlineBy = deadlineBy;
    }

    public AddDeadlineCommand(String description, Date deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        if (deadlineDate == null) {
            deadline = new Deadline(description, deadlineBy);
        } else {
            deadline = new Deadline(description, deadlineDate);
        }
        tasks.add(deadline);
        ui.printAddSuccessMessage(deadline, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}
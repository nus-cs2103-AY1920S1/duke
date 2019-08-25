import java.util.Date;

class AddDeadlineCommand extends Command {
    private String description;
    private String deadlineBy;
    private Date deadlineDate;

    AddDeadlineCommand(String description, String deadlineBy) {
        this.description = description;
        this.deadlineBy = deadlineBy;
    }

    AddDeadlineCommand(String description, Date deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
/**
 * Represents a command that ticks off a task.
 */

public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskDone = tasks.getList().get(taskNumber);
        taskDone.markAsDone();
        storage.writeFile(tasks.getList());
        return toString() + " " + taskDone;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            DoneCommand command = (DoneCommand) obj;
            return taskNumber == command.getTaskNumber();
        }

    }

    @Override
    public String toString() {
        return "Nice! I've marked this task as done:\n";
    }
}

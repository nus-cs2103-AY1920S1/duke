package duke.command;

import duke.todo.Task;
import duke.todo.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand implements Command {
    private String description;
    private LocalDateTime deadline;

    /**
     * Generates deadline command based on the input string.
     * Processes the input string and store it as task and deadline.
     *
     * @param description Description for the deadline.
     * @param deadline Deadline in LocalDateTime.
     */
    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Returns task type.
     *
     * @return Deadline.
     */
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Returns the deadline.
     *
     * @return Date of the deadline.
     */
    public LocalDateTime getDate() {
        return deadline;
    }

    public int getIndex() {
        return 0;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyword() {
        return "error";
    }

    public void execute(TaskList tasks, Ui ui) {
        Task addedDeadline = tasks.addTask(getTaskType(), getDescription(), getDate());
        ui.reportAdd(addedDeadline, tasks.getNumOfTasks());
    }
}

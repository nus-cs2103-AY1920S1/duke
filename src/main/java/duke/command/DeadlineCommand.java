package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DeadlineCommand class which creates a new deadline task.
 */
public class DeadlineCommand extends Command {

    /** Description portion of the input. */
    private String desc;
    private LocalDate date;
    private LocalTime time;

    /**
     * Initialises a deadline command.
     * @param desc description of the deadline.
     * @param date date of the deadline.
     * @param time time of the deadline.
     */
    public DeadlineCommand(String desc, LocalDate date, LocalTime time) {
        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task deadline = new Deadline(desc, date, time);
        taskList.add(deadline);
        return new CommandResult(Message.showAddSuccess(deadline, taskList));
    }
}

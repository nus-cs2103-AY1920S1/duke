package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.calendar.Date;
import duke.calendar.Time;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

public class AddDeadlineCommand extends Command {
    protected String details;

    public AddDeadlineCommand(String details) {
        super();
        this.details = details;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] detailsSplit = details.split("/by");
        if (detailsSplit.length == 0 || detailsSplit[0].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        if (detailsSplit.length < 2 || detailsSplit[1].trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline requires a task and/or a due date");
        }
        String action = detailsSplit[0].trim();
        String deadline = detailsSplit[1].trim();
        String[] dateAndTimeSplit = deadline.split(" ");
        try {
            String date = dateAndTimeSplit[0];
            Date deadlineDate = new Date(date);
            Time deadlineTime;
            if (dateAndTimeSplit.length == 1) {
                deadlineTime = new Time("");
            } else if (dateAndTimeSplit.length == 2){
                deadlineTime = new Time(dateAndTimeSplit[1]);
            } else {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            Task taskDeadline = new Deadline(action, deadlineDate, deadlineTime);
            tasks.addTask(taskDeadline);
            int numberOfTasks = tasks.getListSize();
            ui.printAddedMessage(taskDeadline, numberOfTasks);
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    public boolean isExit() {
        return false;
    }
}

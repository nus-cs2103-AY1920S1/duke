package seedu.duke.commands;

import seedu.duke.exception.DukeException;
import seedu.duke.task.Task;

public class DeleteCommand extends Command{
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    protected void execute() throws DukeException {
        if (taskIndex > taskList.size()) {
            throw new DukeException("");
        }
        Task removedTask = taskList.remove(taskIndex - 1);
        String reply = "Noted. I've removed this task:\n\t  " + removedTask + "\n\t" + "Now you have " + taskList.size()
                + ((taskList.size() == 1) ? " task" : " tasks") + " in the list.";
        ui.printReply(reply);
    }

}

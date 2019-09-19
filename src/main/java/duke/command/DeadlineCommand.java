package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String desc) {
        super(desc);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadlineTask;

        try {
            String[] deadlineArr = getDesc().split("/by");
            deadlineTask = Deadline.of(deadlineArr[0], deadlineArr[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please follow correct format of \"deadline [description] /by [date]\".");
        }

        taskList.addTask(deadlineTask);
        return ui.getAddedTask(deadlineTask.toString(), taskList.getNumTasks());
    }
}

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] deadlineArr = getDesc().split("/by");
            Deadline deadlineTask = Deadline.of(deadlineArr[0], deadlineArr[1]);
            taskList.addTask(deadlineTask);
//            storage.store(deadlineTask);
            ui.showAddedTask(deadlineTask.toString(), taskList.getNumTasks());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please follow correct format of \"deadline [description] /by [date]\".");
        }
    }
}

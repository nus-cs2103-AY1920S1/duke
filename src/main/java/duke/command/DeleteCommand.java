package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.ui.DukeException;

import static duke.ui.TextUi.NEWLINE;

public class DeleteCommand extends Command {
    private int wantToDelete;
    private Task deletedTask;

    /**
     * Constructs an instance of DeleteCommand given the index of task to be deleted.
     *
     * @param wantToDelete index of the task to be deleted
     */
    public DeleteCommand(int wantToDelete) {
        assert wantToDelete >= 0 : "the index should not be negative";
        super.carryOn = true;
        this.wantToDelete = wantToDelete;
    }

    private String deleteDone(int size) {
        assert size >= 0 : "size of taskList cannot be negative";
        String rtn = "Noted. I've removed this task: "
                + NEWLINE + this.deletedTask + NEWLINE
                + "Now thee have " + size + " tasks in the list.";
        return rtn;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            this.deletedTask = taskList.remove(this.wantToDelete);
            taskList.setDeletedTask(this.deletedTask);
            return deleteDone(taskList.getSize());
        } catch (IndexOutOfBoundsException ex) {
            return (new DukeException("The task thee want to delete does not exist.")).toString();
        }
    }
}

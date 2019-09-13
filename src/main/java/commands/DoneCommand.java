package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DoneCommand extends Command {
    int taskNumToMark;

    public DoneCommand(int taskNumToMark) {
        this.taskNumToMark = taskNumToMark;
    }

    /**
     * This method is used to mark a task in the list as completed.
     *
     * @param tasks   the tasklist object to be used in this command
     * @param ui      the ui object to be used in this command
     * @param storage the storage object to be used in this command
     * @return duke's response after marking the task as done
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert Math.abs(taskNumToMark) >= 0;
        try {
            if (!tasks.getTask(taskNumToMark).isDone()) {
                tasks.doneTask(taskNumToMark);
                assert tasks.getList() != null;
                storage.updateList(tasks.getList());
                return ui.print("Nice! I've marked this task as done: "
                        + "\n" + tasks.getList().get(taskNumToMark - 1));
            }
            return ui.print("Error, task already completed!");
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number :(";
        } catch (Exception e) {
            return "Invalid input";
        }

    }

}

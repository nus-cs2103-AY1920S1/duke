package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class ClearCommand extends Command{

    /**
     * Constructor for ClearCommand
     */
    public ClearCommand(){
        super.type = FullCommand.CLEAR;
    }

    /**
     * Used to check if command is an ExitCommand.
     * @return false as command is a ClearCommand.
     */
    public boolean isExit() {
        assert(!super.type.getName().equals("bye"));
        return false;
    }
    /**
     * Clears task list.
     * @param tasks Current TaskList object used in this instance of Duke. Removes specified task from tasks.schedule.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath.
     * @throws DukeException Thrown when index == 0, or when index is larger than number of Task(s) in TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int incompleteNum = tasks.taskNum - tasks.completedNum;
        tasks.reset();
        ui.readClear(incompleteNum);
    }
}

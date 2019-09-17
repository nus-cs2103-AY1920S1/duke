package command;

import task.Task;
import task.TaskList;
import task.InsufficientTaskArgumentException;
import main.Storage;
import main.Ui;

public class DeleteCommand implements Command {

    int taskNumber;

    /**
     * Constructor for "delete" commands.
     * @param taskNumber int. taskNumber is the task number to be deleted in the list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * isExit checks if the command is an exit command.
     * @return boolean whether if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private void handleDeleteCall(TaskList tasks, Ui ui, Storage storage) {
        Task removed = tasks.removeTask(taskNumber - 1);
        storage.updateTasks(tasks);
        ui.nextLine("    ____________________________________________________________\n"
                + "     Noted. I've removed this task: \n"
                + "       " + removed.toString()
                + "\n"
                + "     Now you have "
                + tasks.size()
                + " tasks in the list.\n"
                + "    ____________________________________________________________");
    }

    /**
     * execute performs the command in the gui.Duke app.
     * @param tasks TaskList that contains the list of tasks that is tracked.
     * @param ui Ui of the app.
     * @param storage Storage is the class that manages file reading and file writing of the data passed into the app.
     * @throws InsufficientTaskArgumentException exception thrown when command does not have enough arguments.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException {
        if (tasks.size() < taskNumber) {
            throw new InsufficientTaskArgumentException("Error! Task cannot be found~!");
        }

        handleDeleteCall(tasks, ui, storage);
        return tasks;
    }

    /**
     * toString() returns the command and its arguments if there is any.
     * @return String.
     */
    @Override
    public String toString() {
        return "Command: delete, Argument: " + taskNumber;
    }
}

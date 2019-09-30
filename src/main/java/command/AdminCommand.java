package command;

import task.InsufficientTaskArgumentException;
import task.Task;
import task.TaskList;
import main.Storage;
import main.Ui;

public class AdminCommand implements Command {
    String commandType;
    int commandArg;

    /**
     * Constructor for AdminCommand ("done" and "list" commands).
     * @param commandType String type of command.
     * @param commandArg int argument of command.
     */
    public AdminCommand(String commandType, int commandArg) {
        this.commandType = commandType;
        this.commandArg = commandArg;
    }

    public AdminCommand(String commandType) {
        this.commandType = commandType;
        this.commandArg = -1;
    }

    /**
     * isExit checks if the command is an exit command.
     * @return boolean whether if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private void handleListCall(TaskList tasks, Ui ui) {
        String result = "";
        for (int i = 0; i < tasks.size(); i = i + 1) {
            result = result + "    " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        result = result.equals("") ? "\n" : result;
        String finalResult = "    ____________________________________________________________\n"
                + "    Here are the tasks in your list:\n"
                + result
                + "    ____________________________________________________________";
        ui.nextLine(finalResult);
    }

    private void handleDoneCall(TaskList tasks, Ui ui, Storage storage) {
        Task targetedTask = tasks.get(commandArg - 1);
        Task.markAsDone(targetedTask);
        String result = "    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done: \n"
                + "       "
                + targetedTask.toString()
                + "\n"
                + "    ____________________________________________________________";
        ui.nextLine(result);
        storage.updateTasks(tasks);
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
        if (commandType.equals("list")) {
            handleListCall(tasks, ui);
        } else if (commandType.equals("done")) {
            if (commandArg <=  0) {
                throw new InsufficientTaskArgumentException("Done requires a positive integer argument!");
            }
            if (commandArg > tasks.length()) {
                throw new InsufficientTaskArgumentException("Error! Task not found");
            }
            handleDoneCall(tasks, ui, storage);
            //historyManager.updateRecords();
        }
        return tasks;
    }

    /**
     * toString() returns the command and its arguments if there is any.
     * @return String.
     */
    @Override
    public String toString() {
        if (commandArg == -1) {
            return "Command: " + commandType;
        } else {
            return "Command: " + commandType + ", Arguments: " + commandArg;
        }
    }
}

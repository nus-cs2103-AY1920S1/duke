package command;
import task.*;
import main.*;
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

    /**
     * execute performs the command in the Duke app.
     * @param tasks TaskList that contains the list of tasks that is tracked.
     * @param ui Ui of the app.
     * @param storage Storage is the class that manages file reading and file writing of the data passed into the app.
     * @throws InsufficientTaskArgumentException exception thrown when command does not have enough arguments.
     * @throws InvalidTaskException exception thrown when task is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException, InvalidTaskException {
        if (commandType.equals("list")) {
            String result = "";
            for (int i = 0; i < tasks.size(); i = i + 1) {
                result = result + "    " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
            result = result.equals("") ? "\n" : result;
            String finalResult = "    ____________________________________________________________\n" +
                    "    Here are the tasks in your list:\n" +
                    result +
                    "    ____________________________________________________________";
            ui.nextLine(finalResult);
        } else if (commandType.equals("done")) {
            if (commandArg <=  0) {
                throw new InsufficientTaskArgumentException("Done requires a positive integer argument!");
            }
            Task targetedTask = tasks.get(commandArg - 1);
            Task.markAsDone(targetedTask);
            String result = "    ____________________________________________________________\n" +
                    "     Nice! I've marked this task as done: \n" +
                    "       " + targetedTask.toString() + "\n" +
                    "    ____________________________________________________________";
            ui.nextLine(result);
        } else {
            return ;
        }
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

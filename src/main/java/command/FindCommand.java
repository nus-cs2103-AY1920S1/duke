package command;

import main.Storage;
import task.TaskList;
import main.Ui;
import task.InsufficientTaskArgumentException;

import java.util.ArrayList;

public class FindCommand implements Command {

    private String stringArgument;

    /**
     * Constructor for FindCommand.
     * @param stringArgument String. The keyword to search for in a tasklist.
     */
    public FindCommand(String stringArgument) {
        this.stringArgument = stringArgument;
    }

    private String concatenateStrings(ArrayList<String> strings) {
        String result = "";
        for (String s : strings) {
            result = result + "s" + "\n";
        }
        result = result.equals("") ? "\n" : result;
        return result;
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
        ArrayList<String> tasksThatMatch = tasks.findAllMatches(this.stringArgument);
        String matched = concatenateStrings(tasksThatMatch);
        String result = "    ____________________________________________________________\n"
                + "    Here are the matching tasks in your list:\n"
                + matched
                + "    ____________________________________________________________";
        ui.nextLine(result);
        return tasks;
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
     * toString() returns the command and its arguments if there is any.
     * @return String.
     */
    @Override
    public String toString() {
        return "Command: find, Argument:" + this.stringArgument;
    }
}

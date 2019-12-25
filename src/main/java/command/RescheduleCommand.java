package command;

import exception.DukeException;
import exception.RescheduleParameterException;
import exception.UpdateStateException;
import run.Storage;
import run.TaskList;
import run.Ui;

import java.io.IOException;

/**
 * Extends Command class and is used to reschedule an existing Event or Deadline from TaskList.
 */
public class RescheduleCommand extends Command {
    protected String rawString;
    protected int taskNum;
    protected String newDateTime;


    /**
     * Constructor for reschedule command.
     * @param rawString complete unparsed user input of reschedule request
     * @throws RescheduleParameterException if user does not provide a valid int number
     */
    public RescheduleCommand(String rawString) throws RescheduleParameterException {
        this.rawString = rawString;
        String[] splited = rawString.split(" ");
        if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 4) {
            taskNum = Integer.parseInt(splited[1]);
            newDateTime = splited[2] + " " + splited[3];
        } else {
            throw new RescheduleParameterException(DukeException.RESCHEDULE_PARAMETER_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Updates relevant task in tasklist with new DateTime and stores updated value into storage, then prints
     * results to user.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     * @return String output of executed command to be shown to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, UpdateStateException {
        String output = tasks.reschedule(taskNum, newDateTime);
        storage.updateState(tasks);
        return output;
    }

}
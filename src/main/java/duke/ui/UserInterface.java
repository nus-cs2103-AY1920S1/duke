package duke.ui;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidParameterException;

/**
 * This is the user interface of the Duke program. The user interface will format the information. Return the system
 * output of duke with given inputs. Welcome messages, error messages, display messages for addition, deletion, marked
 * as done etc of tasks will be done here.
 */
public interface UserInterface {

    /**
     * Shows a welcome message for the user.
     * @return a string representation of the welcome message for the user
     */
    String showWelcome();

    /**
     * Shows a loading error message for the user. This will occur when Duke is unable to load the
     * file path specified in Duke. See {@link duke.main.Duke} for more information.
     * @return a string representation of the error message
     */
    String showLoadingError();

    /**
     * Shows an error message for the user with the constituent line number and the its content during parsing.
     * @param lineCount the line number of the error
     * @param line the contents of the line itself
     * @return a string representation of the error message
     */
    String showLineError(int lineCount, String line);

    /**
     * Reads the command entered by the user.
     * @param userInput the input entered by the user
     * @return the input entered by the user
     */
    String readCommand(String userInput);

    /**
     * Shows the current tasks in the list.
     * @param taskList the list to be displayed
     * @return a string representation of the list in table form
     */
    String showTable(String taskList);

    /**
     * Shows the results of tasks found in the list.
     * @param list the list of tasks to be displayed
     * @return a string representation of the results of tasks found in the list in table form
     */
    String showResultsFound(String list);

    /**
     * Shows a message that informs the user that a task has been added into the list.
     * @param task the task to be added into the list
     * @param size the number of tasks in the list
     * @return a string representation of the add information
     */
    String showAddInformation(String task, int size);

    /**
     * Shows a message that informs the user that the task has been marked as done.
     * @param task the task that is marked done
     * @return a string representation of the task information marked as done
     */
    String showMarkedAsDone(String task);

    /**
     * Shows a message that informs the user that the task has been deleted.
     * @param task the task that is deleted
     * @param size the number of tasks remaining in the list
     * @return a string representation of the deleted message
     */
    String showDeletedMessage(String task, int size);

    /**
     * Shows a exit message for the user.
     * @return a string representation of the exit message
     */
    String showExitMessage();

    /**
     * Shows an error message when the user inputs a invalid command.
     * @param invalidCommand the invalid command entered
     * @return a string representation of the error message
     */
    String showInvalidCommandError(InvalidCommandException invalidCommand);

    /**
     * Shows an error message when the user inputs a invalid parameter for the command.
     * @param invalidParameter the invalid parameter entered
     * @return a string representation fo the error message
     */
    String showInvalidParametersError(InvalidParameterException invalidParameter);

    /**
     * Shows an error message for the user when data cannot be saved to <code>data/duke.txt</code>.
     * @return a string representation of the error message
     */
    String showSaveError();

    /**
     * Shows a message that says that the reminder has been set.
     * @param task the task whose reminder has been set
     * @param dateToRemind the date of the reminder set
     * @return
     */
    String showSetReminder(String task, String dateToRemind);

    /**
     * Shows the schedule.
     * @param schedule the schedule to be displayed
     * @return a string representation of the schedule in table form
     */
    String showSchedule(String schedule);

}
